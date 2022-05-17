package pt.c40task.l05wumpus;

public class Sala {
	private Componente[] componentes = new Componente[2]; //Pode haver no maximo dois componentes que nao sao Herois
	private Heroi heroi; //Pode estar ou não na sala
	private boolean playerIn;
	
	public Sala(Componente[] componentes, Heroi heroi, boolean playerIn) {
		this.componentes = componentes;
		this.heroi = heroi;
		this.playerIn = playerIn;
	}
	
	//Interações
		//A sala não podera interagir com o heroi caso ele não esteja na sala
}
