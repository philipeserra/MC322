package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	private boolean haveArrow = true;
	private boolean arrowEquiped = false;
	private boolean gold = false;
	private boolean collectGold = false;
	private int score = 0;
	private boolean win = false;
	private boolean quit = false;
	
	public Heroi(int x, int y, Caverna caverna) {
		super(x, y, caverna);
		this.setType("Heroi");
	}
	
	public void setPosition(int x, int y) {
		int pastX = this.getX();
		int pastY = this.getY();
		super.setPosition(x, y);
		this.score -= 15;
		this.getCaverna().setPlayerPosition(pastX, pastY, this.getX(), this.getY());
		this.arrowEquiped = false;
		if(this.getX() == 0 && this.getY() == 0 && this.gold) {
			this.win();
		}

	}
	
	public void setArrowEquiped(boolean equiped) {
		if(haveArrow) {
			this.arrowEquiped = equiped;
			this.score -= 100;
			this.haveArrow = false;
			System.out.println("Voce equipou a flecha");
			this.getCaverna().playerAction(this.getX(), this.getY());
		}else {
			System.out.println("Sem flechas para equipar");
		}
	}
	
	public boolean getArrowEquiped() {
		return this.arrowEquiped;
	}
	
	public void collectGold(boolean collect) {
		this.collectGold = collect;
		this.getCaverna().playerAction(this.getX(), this.getY());
	}
	
	public boolean getCollectGold() {
		return this.collectGold;
		
	}
	
	public void setGold(boolean collected) {
		this.gold = collected;
	}
	
	public boolean getGold() {
		return this.gold;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void death() {
		this.score -= 1000;
		this.setAlive(false);
	}
	
	public void win() {
		this.score += 1000;
		this.win = true;
		System.out.println("Voce ganhou!");
	}
	
	public char playerState() {
		if(this.quit)
			return 'Q';
		
		if(this.win)
			return 'W';
		
		if(!this.getAlive())
			return 'L';
		
		return 'P';
			

	}
	
	public void quitGame() {
		this.quit = true;
		System.out.println("player saiu do jogo");
	}
	

}
