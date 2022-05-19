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
		this.heroi.setArrowEquiped(true);
	}
	
	public void collectGold() {
		this.heroi.collectGold(true);
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
				return;
				
			case 'c':
				this.collectGold();
				return;
				
			case 'q':
				this.quitGame();
				return;
				
			case 'a':
				this.setPlayerPosition(x, y-1); //O programa processa y crescendo para baixo
				return;
				
			case 'd':
				this.setPlayerPosition(x, y+1);
				return;
				
			case 's':
				this.setPlayerPosition(x+1, y);
				return;
				
			case 'w':
				this.setPlayerPosition(x-1, y);
				return;
				
			}
			
			System.out.println("Tecla invalida");
		}
		
	}

}
