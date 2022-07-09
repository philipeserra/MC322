# PROJETO WARLOCK'S TOWER 2

Em Warlock’s Tower 2, o jogador deve completar puzzles para encontrar a saída das dungeons e chegar no topo da torre, mas cuidado para sua energia não acabar.

# Equipe

#### Philipe Medeiros Serra - 247339
#### Henrique Meneguetti Bianchi - 217802

# Arquivo executável

[WarlocksTower2.jar](https://drive.google.com/file/d/11zQV6E_0r2lF9Khf2KYXxOMbBhHOymdY/view?usp=sharing)

# Slides do Projeto

[Slides da versão final do jogo](https://docs.google.com/presentation/d/1QYKL5Xe0vi7EFR8pOVqEINFce5sySmCFuPCRmYeTAW8/edit?usp=sharing)

## Relatório de Evolução
No começo do projeto, já tinhamos uma arquitetura para seguir: na arquitetura inicial havia uma classe para criação da interface gráfica e os componentes seriam criados dentro das células. Porém, como foi utilizado o Java Swing, a própria classe Dungeon se tornou a interface gráfica (pois ela se tornou uma herdeira do JPanel) e, visto que os componentes se moveriam de célula para célula, foi decidido que eles seriam instanciados dentro da dungeon e teriam suas localizações dadas pela classe montador. No começo também, para cada Componente que poderia ser interagido, percebemos que várias métodos eram os mesmos, então nos atentamos que a maior parte desse código (principalmente o da geração de imagem) poderia ser escrito dentro de classes abstratas que seriam pais desses tais componentes (Antes elas apenas eram usadas para permitir o uso do polimorfismo).

# Destaques de Código
Um destaque pessoalmente escolhido seria:
~~~java
public class Player {
...
private ArrayList<Position> pastPositions;
...
	public void move(int deltaX, int deltaY) {
		
		if(!this.alive) {
			return;
		}
		
		if(!this.inValidPosition(this.x, this.y)){
			return;
		}
		
		if(!this.inValidPosition(this.x + deltaX, this.y + deltaY)) {
			return;
		}
		

		this.pastPositions.add(new Position(this.x, this.y));
		this.x = this.x  + deltaX;
		this.y = this.y + deltaY;
		this.energia --;
		
	}
...
public Position getPastPosition() {
		if(this.pastPositions.size() <= 0) {
			return new Position(this.x, this.y);
		}
		else {
			return this.pastPositions.get(this.pastPositions.size() - 1);
		}
	}
}
~~~

~~~java
public class Caixa extends Componente{
...

	@Override
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {

		
		if(player.getX() == this.x && player.getY() == this.y) {
			//Metodo que move a caixa, note que deltaX = player.getX()-player.getPastPosition().getX() indica para qual direção ele se moveu no
			//eixo x ou se não se moveu nesse eixo (se deltaX = 1, ele foi para direita, se -1 para a esquerda e 0 ele não se moveu no eixo), 
			//o mesmo ocorre com player.getY()-player.getPastPosition().getY() (se deltaY = 1, ele foi para cima, se -1 para a baixo e 0 ele não 
			//se moveu no eixo).
			move(player.getX()-player.getPastPosition().getX(), player.getY()-player.getPastPosition().getY(), componentes, celulas);
	
			
		}
	}
...
~~~
A ArrayList<Position> pastPositions foi criada com os seguintes intuitos: retornar o player para um lugar válido caso ele saia do tabuleiro ou entre em uma parede e (caso houvesse mais tempo) criar animações de caminhar, por exemplo: caso a posição passada do player fosse a esquerda da atual, ele estaria virado como se estivesse olhando para direita. Infelizmente a animação não foi feita, porém foi util para poder criar a movimentação da caixa, no qual poderia saber em qual direção ela foi empurrada pelo player.

# Destaques de Orientação a Objetos
No destaque de Orientação a Objetos, será apresentado como foi usado o polimorfismo na classe Dungeon e nas interações entre o player com os componentes e células.
	
## Diagramas de Classes usada no destaque OO:
![Diagrama de interação com Player](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/InteracaoComPlayerDiagrama.png)

## Código do Destaque OO
Como há diferentes tipos de componentes (entre eles Porta, Alavanva, Caixa...) e vários tipos de células (CelulaPadrao, Saida...) foi usado o polimorfismo, onde todas essas classes que representam componentes são herdeiras de uma classe abstrata chamada Componente e todas as classes que representam células são herdeiras de uma classe abstrata chamada Celula, assim os componentes podem ser instanciados todos numa ArrayList<Componente> e as células numa matriz de células.
~~~java
public class Dungeon extends JPanel implements ActionListener, KeyListener{
	...
	private Player player;
	private ArrayList<Componente> componentes;
	private Celula[][] celulas;
	...
	
}
~~~
Porém, tanta as células como os componentes podem ser interagidos pelo player, por isso elas recebem uma interface Interactable para que seja garantido que elas possuam o método interactedByPlayer(...), que é chamado toda vez que o jogador tecla E. Na dungeon ela recebe um keyListener(...), no qual utiliza a classe Controlador que verifica qual tecla foi pressionada e no caso, se a tecla for E, ele chamará o método playerInteract() do Player que, por fim, aciona o método interactedByPlayer(...) nos componentes e células, exemplo: interagir com uma alavanca:
	
~~~java
	public void playerInteract() {
		
		if(!this.alive) {
			return;
		}
		
		dungeon.getCells()[this.x][this.y].interactedByPlayer(this, dungeon.getComponentes(), dungeon.getCells());
		for(Componente componente: dungeon.getComponentes()) {
			if(componente.getX() == this.x && componente.getY() == this.y) {
				componente.interactedByPlayer(this, dungeon.getComponentes(), dungeon.getCells());
			}
		}
	}
~~~
~~~java
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		this.puxada = !this.puxada;
		if(this.puxada) {
			loadImage("images/alavanca_para_baixo.png");
		}else {
			loadImage("images/alavanca_para_cima.png");
		}
		
		for(Activable ativavel: ativaveis) {
			System.out.println("fui ativada");
			ativavel.setActivabled(!ativavel.getActivabled());
		}
	}
~~~
	

# Destaques de Pattern
Para o destaque de Pattern, será mostrado como foi utilizado o Strategy Pattern para a criação de componentes ativáveis pela alavanca e pelo piso acionador.

## Diagrama do Pattern
![Diagrama de ativaveis](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/ActivablePattern.png)

## Código do Pattern
Tanto a alavanca, como o piso acionador, recebem uma lista de Activable, que, como a tradução induz, uma lista de classes que podem ser ativadas. Como mostrado a seguir, a interface Activable possui dois métodos, nos quais serão declarados novamente dentro das classes para realizar o que se deseja caso a classe seja ativada ou desativada. Por conseguinte, o Strategy Pattern foi utilizado, pois não é necessário para a alavanca saber qual a classe que está sendo ativada para fazê-la agir da forma que queremos. o Strategy também foi usada na lógica de interação do player com os componentes e células, pois o player não precisa saber qual a classe do componente ou a classe da celula para fazermos as tais classes agirem da forma que queremos.

~~~java
	
public class Alavanca extends Componente{
	
	private boolean puxada;
	//Componentes que alavanca torna passable ou inpassable
	private ArrayList<Activable> ativaveis;
	...
}
~~~
	
~~~java
public class PisoAcionador extends Celula{
	private ArrayList<Activable> ativaveis;
	private ArrayList<Boolean> estadosIniciais;
	
	...
	
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		if(player.getX() == this.x && player.getY() == this.y) {
			for(int i = 0; i < ativaveis.size(); i++) {
				this.ativaveis.get(i).setActivabled(!this.estadosIniciais.get(i));
			}
			return;
		}
		
		for(Componente componente: componentes) {
			if(componente.getX() == this.x && componente.getY() == this.y) {
				for(int i = 0; i < ativaveis.size(); i++) {
					this.ativaveis.get(i).setActivabled(!this.estadosIniciais.get(i));
				}
				return;
			}
		}
		
		for(int i = 0; i < ativaveis.size(); i++) {
			this.ativaveis.get(i).setActivabled(this.estadosIniciais.get(i));
		}
		
	}
}
	
~~~
~~~java
public class Porta extends Componente implements Activable{
	...
	@Override
	public boolean getActivabled() {
		// TODO Auto-generated method stub
		return passable;
	}

	@Override
	public void setActivabled(boolean activabled) {
		this.passable = activabled;
		
		if(!this.passable) {
			loadImage("images/porta_aberta.png");
		}
		else {
			loadImage("images/porta_fechada.png");
		}
	}

}	
~~~
	
# Conclusões e Trabalhos Futuros
Um dos pontos mais fortes do código de Warlock's Tower 2 é a possibilidade de facilmente adicionar novos tipos de componentes e células funcionais e de adicionar novos níveis a partir de arquivos txt na pasta data/levels. Desde início, a intenção de deixar o jogo ser facilmente expansível foi desejada. Porém muitos pontos poderiam ter sido melhorados, o jogo possui poucos componentes e, percebendo pela as apresentações de outras equipes, era uma necessidade criar uma interface prática para o usuário. Agora, falando mais sobre a parte de arquitetura e código, percebemos que, pelo feedback, podíamos ter melhorado a forma que recebemos as entradas do usuário: foi implementado na dungeon um método keyListener() que recebe as entradas do usuário e realiza ações e o método actionPerfomed() que atualiza os estados dos objetos e atualiza o frame, porém, como os métodos foram adicionados a partir de interfaces já existentes no Java, demoramos a perceber que os métodos agiam de forma assícrona, o que podia causar algumas inconsistências, pois isso quer dizer que o jogador pode realizar várias ações enquanto a dungeon atualiza um frame. Isso nos impossibilitou de criar componentes que agiam por si só (como um mini dinossauro andando por um corredor, por exemplo), pois isso exigiria que tivéssimos de colocar um timer para atualizar os frames e nesse meio tempo o jogador poderia fazer várias ações antes de sofrer verificações e interações e se teleportar. Em vez disso, poderíamos não ter implementado a interface ActionListener e criar um observador que atualizasse a dungeon a cada ação do jogador. Eu, Philipe Serra, mesmo achando que o projeto não chegou aonde eu queria, senti que aprendi muito sobre criação de jogos e programação orientada o objetos e vou levar esssas conclusões e feedbacks para futuros projetos.

# Documentação dos Componentes

# Diagramas

## Diagrama Geral da Arquitetura do Jogo
A classe App é a classe que possui a função main que serve para a execução do jogo, no método initWindow, a dungeon é instanciada e a partir de agora todas as atualizações de frame serão executadas dentro do objeto dungeon.
na dungeon é instanciado o player, os componentes, a matriz de células, o montador e o controlador. O montador possui métodos para a criação do player, dos componentes e da matriz de células, funcionando apenas como uma classe Facade. O controlador receberá o player como paramêtro para que ele execute os seus métodos. Usando o método actionPerfomed(), a dungeon avisará para o player conferir se ele está em uma localização possível, depois pedirá que todos os componentes e células interajam entre si e depois atualizará o frame. Usando a interface Interactable, o player consegue se comunicar com os componentes e células instanciados e os componentes e células podem comunicar entre si para realizar ações
![Diagrama Geral](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/DiagramaGeral.png)

## Diagrama Geral de Componentes

## Componente Player
			
item | detalhamento
----- | -----
Classe | `Player` 
Interfaces | 
			
## Componente Dungeon
			
item | detalhamento
----- | -----
Classe | `Dungeon` 
Interfaces | 	

## Componente Controlador
			
item | detalhamento
----- | -----
Classe | `Controlador` 
Interfaces | 

## Componente Montador
			
item | detalhamento
----- | -----
Classe | `Montador` 
Interfaces | 
			
## Componente Componente
			
item | detalhamento
----- | -----
Classe | `Componente` 
Interfaces | `Interactable`

## Componente Porta
			
item | detalhamento
----- | -----
Classe | `Porta` 
Interfaces | `Interactable, Activable`

			
## Componente Alavanca
			
item | detalhamento
----- | -----
Classe | `Alavanca` 
Interfaces | `Interactable`
			
## Componente Caixa
			
item | detalhamento
----- | -----
Classe | `Caixa` 
Interfaces | `Interactable`

## Componente Energia
			
item | detalhamento
----- | -----
Classe | `Energia` 
Interfaces | `Interactable`
			
## Componente Celula
			
item | detalhamento
----- | -----
Classe | `Celula` 
Interfaces | `Interactable`

## Componente CelulaPadrao
			
item | detalhamento
----- | -----
Classe | `CelulaPadrao` 
Interfaces | `Interactable`
			
## Componente Saida
			
item | detalhamento
----- | -----
Classe | `Saida` 
Interfaces | `Interactable`

## Componente Parede
			
item | detalhamento
----- | -----
Classe | `Parede` 
Interfaces | `Interactable`

## Componente PisoAcionador
			
item | detalhamento
----- | -----
Classe | `PisoAcionador` 
Interfaces | `Interactable`		
			
### Interfaces

## Detalhamento das interfaces

### interface Interactable
interface provida para qualquer classe que possa interagir com outras classes que implementam Interactable e que possa ser interagida pelo player.

~~~java
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

//Interface para as celulas e componentes
public interface Interactable {
	
	//Funcao tick impede do Player, componente ou celula estar onde nao deveria estar.
	public void tick( Player player, ArrayList<Componente> componentes, Celula[][] celulas);
	
	//Faz a interecao com o player, componentes e celulas na Dungeon
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas);
	
	//Desenha o objeto
	public void draw(Graphics g, ImageObserver observer);
	
	//Funcao ativada caso algo seja interagido player 
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas);
}
~~~

Método | Objetivo
----- | -----
`tick` | `Verifica se o objeto se moveu para algum lugar que não deveria e, caso tenha, ele retorna o objeto a um lugar possível` 
`interact` | `Objeto interage com componentes, células e o player sem a necessidade de ser interagido pelo player`
`draw` | `Desenha o objeto no painel`
`interactedByPlayer` | `Método chamado quando o player interage com o objeto`


### Interface Activable
Interface provida para classes que possuem uma propriedade que pode ser ativada por outras classes.
			
~~~java
public interface Activable{
	
	public boolean getActivabled();
	
	public void setActivabled(boolean activabled);
	
}
~~~
			
Método | Objetivo
----- | -----
`getActivabled` | `retorna se a classe teve uma de suas propriedades ativadas` 
`setActivabled` | `ativa ou desativa a tal propriedade ativável dentro da classe`


# Plano de Exceções
Diagrama de exceções para impedir que fases que não tenham sido bem formatadas acabem impendido do jogador de avançar para a próxima fase ou de alguma forma quebrar/crashar o jogo
![Diagrama de exceções no montador](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/DiagramaDeExce%C3%A7%C3%B5es.png)
			
## Descrição das classes de exceção
Classe | Descrição
----- | -----
badFormatLevel | Cria uma fase de passagem ao invés de criar a fase que está quebrada

