package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	private boolean arrow = false;
	private boolean gold = false;
	
	public Heroi(int x, int y, Caverna caverna) {
		super(x, y, caverna);
		setType("Heroi");
	}
	
	public void setPosition(int x, int y) {
		int pastX = this.getX();
		int pastY = this.getY();
		super.setPosition(x, y);
		this.getCaverna().setPlayerPosition(pastX, pastY, this.getX(), this.getY());

	}
	
	public void setArrow(boolean equiped) {
		this.arrow = equiped;
	}
	
	public boolean getArrow() {
		return this.arrow;
	}
	
	public void setGold(boolean collected) {
		this.gold = collected;
	}
	
	public boolean getGold() {
		return this.gold;
	}
	
	public void quitGame() {
		//Nao sei 
	}
	

}
