package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	
	public Buraco(int x, int y, Caverna caverna) {
		super(x, y, caverna);
		this.setType("Buraco");
	}
	
	public void spreadAlert() {
		int x = this.getX();
		int y = this.getY();
		if(x+1 < 4)
			this.getCaverna().spreadBrisa(x+1, y);
		if(x-1 >= 0)
			this.getCaverna().spreadBrisa(x-1, y);
		if(y+1 < 4)
			this.getCaverna().spreadBrisa(x, y+1);
		if(y-1 >= 0)
			this.getCaverna().spreadBrisa(x, y-1);
	}
}
