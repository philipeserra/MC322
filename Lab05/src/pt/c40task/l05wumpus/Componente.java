package pt.c40task.l05wumpus;

public class Componente {
	private Caverna caverna;
	private int x;
	private int y;
	private boolean alive = true;
	private String componentType = "Vazio";

	
	public Componente(int x, int y, Caverna caverna) {
		this.x = x;
		this.y = y;
		this.caverna = caverna;
	}
	
	public void setPosition(int x, int y) {
		if(x >= 0 && x < 4)
			this.x = x;
		if(y >= 0 && y < 4)
			this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getAlive() {
		return this.alive;
	}
	
	public void death() {
		this.alive = false;
	}
	
	public String getType() {
		return this.componentType;
	}
	
	protected void setType(String type) {
		this.componentType = type;
	}
	
	public Caverna getCaverna() {
		return this.caverna;
	}
}
