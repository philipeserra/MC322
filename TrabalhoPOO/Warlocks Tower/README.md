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
			move(player.getX()-player.getPastPosition().getX(), player.getY()-player.getPastPosition().getY(), componentes, celulas);
	
			
		}
	}
...
~~~
A ArrayList<Position> pastPositions foi criada com os seguintes intuitos: retornar o player para um lugar válido caso ele saia do tabuleiro ou entre em uma parede e (caso houvesse mais tempo) criar animações de caminhar, por exemplo: caso a posição passada do player fosse a esquerda da atual, ele estaria virado como se estivesse olhando para direita. Infelizmente a animação não foi feita, porém foi util para poder criar a movimentação da caixa, no qual poderia saber em qual direção ela foi empurrada pelo player.

# Destaques de Orientação a Objetos

## Diagramas de Classes usada no destaque OO:
No destaque de Orientação a Objetos, será apresentado como foi usado o polimorfismo na classe Dungeon e nas interações entre o player com os componentes e células.
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
	~~~

## Diagrama do Pattern

## Código do Pattern

# Conclusões e Trabalhos Futuros

# Documentação dos Componentes

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

### Arquitetura Simplificada
![Diagrama de Arquitetura Simplificado](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/ArquiteturaSimplificada.png)

### Arquitetura dos Componentes e Células Simplificadas
![Diagrama de Arquitetura Simplificado](https://github.com/philipeserra/MC322/blob/main/TrabalhoPOO/Diagramas/ArquiteturaComponentesSimplificada.png)

## Diagrama Geral de Componentes

## Componente <Nome do Componente> (para cada componente)

### Ficha Técnica

### Interfaces

## Detalhamento das interfaces

### Ficha técnica

# Plano de Exceções

## Descrição das classes de exceção


