package pt.c40task.l05wumpus;

public class Wumpus extends Componente{

	public Wumpus(int x, int y, Caverna caverna) {
		super(x, y, caverna);
		this.setType("Wumpus");
	}
	
	public void spreadAlert() {
		int x = this.getX();
		int y = this.getY();
		if(x+1 < 4)
			this.getCaverna().spreadFedor(x+1, y);
		if(x-1 >= 0)
			this.getCaverna().spreadFedor(x-1, y);
		if(y+1 < 4)
			this.getCaverna().spreadFedor(x, y+1);
		if(y-1 >= 0)
			this.getCaverna().spreadFedor(x, y-1);
	}
}
