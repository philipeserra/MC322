# Laboratório 5

## Alunos
#### Philipe Medeiros Serra 
RA 247339
#### Henrique Meneguetti Bianchi 
RA 217802

## Destaques sobre decisões de arquitetura

### Codigo:


		public void playerFindsWumpus(Componente wumpus) {
		  Random rand = new Random();
		  int result = rand.nextInt(2);
      if(!wumpus.getAlive()) {
			  System.out.println("Wumpus esta morto");
			  return;
		  }
      if(heroi.getArrowEquiped() && result == 1) {
			  wumpus.death();
			  System.out.println("Voce matou o Wumpus");
			  this.heroi.setScore(this.getHero().getScore() + 500); }
        
### Texto
<p>   Nesse trecho do codigo é demonstrado o uso do polimorfismo para a criação da intereção entre o player e o Wumpus. As classes que representam o Heroi, o Wumpus, o Ouro, o Buraco, a Brisa e o Fedor são todas herdeiras da classe Componente. Ao adicionar um componente em uma sala, não sabemos qual das classes mencionadas será colocada, por isso foi utilizado o polimorfismo, onde que os métodos essenciais de comportamento (getX(), getY(), death(), etc.) foram já adicionados na classe Componente e possivelmente reescritas, caso necessário, em suas classes herdeiras para executar ações mais complexas, por exemplo: todo componente possui o método .death(), porém a classe Heroi além de "morrer" diminui sua quantidade de pontos (score) e avisa ao usuário pelo console. Desta forma é possível centralizar as interações entre player e componentes em um unico método da sala (Sala.interaction()).</p>


## Uso do programa
1. <p> É necessário ter os arquivos cave.csv, movements.csv e results.csv dentro da pasta src/pt/c40task/l05wumpus para a execução do programa.</p>
2. <p> Para utilizar o modo iterativo, apenas deixe o arquivo movements.csv vazio, assim você poderá movimentar o player a partir do seu teclado. </p>
