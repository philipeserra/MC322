package pt.c40task.l05wumpus;

public class Controle {
	private Heroi heroi;
	
	public Controle(Heroi heroi) {
		this.heroi = heroi;
	}
	
	public void setPlayerPosition(int x, int y) {
		this.heroi.setPosition(x, y);
	}
	
	public void equipArrow() {
		this.heroi.setArrow(true);
	}
	
	public void collectGold() {
		this.heroi.setGold(true);
	}
	
	public void quitGame() {
		this.heroi.quitGame();
	}
	
	public void playerMoviment(char command) {
		if(this.heroi.getAlive()) {
			
			int x = heroi.getX();
			int y = heroi.getY();
			
			switch(command) {
			
			case 'k':
				this.equipArrow();
				break;
				
			case 'c':
				this.collectGold();
				break;
				
			case 'q':
				this.quitGame();
				break;
				
			case 'w':
				this.setPlayerPosition(x, y-1); //O programa processa y crescendo para baixo
				break;
				
			case 's':
				this.setPlayerPosition(x, y+1);
				break;
				
			case 'd':
				this.setPlayerPosition(x+1, y);
				break;
				
			case 'a':
				this.setPlayerPosition(x-1, y);
				break;
				
			}
		}
		
	}

}
