package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas = new Sala[4][4];
	
	public Caverna(Sala[][] salas) {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				this.salas[i][j] = salas[i][j];
		
	}
	
	public void setPlayerPosition(int pastX, int pastY, int x, int y) {
		salas[pastX][pastY].setPlayerIn(false);
		salas[x][y].setPlayerIn(true);
		salas[x][y].interaction();
	}
	
	public void playerAction(int x, int y) {
		salas[x][y].interaction();
	}
	public void spreadFedor(int x, int y) {
		Fedor fedor = new Fedor(x, y, this);
		salas[x][y].addComponent(fedor);
	}
	
	public void spreadBrisa(int x, int y) {
		Brisa brisa = new Brisa(x, y, this);
		salas[x][y].addComponent(brisa);
	}
	
	public void setSala(int x, int y, Sala sala) {
		this.salas[x][y] = sala;
	}
	
	public Sala getSala(int x, int y) {
		return this.salas[x][y];
	}
	
	public void updateSala(int x, int y) {
		salas[x][y].spreadAlerts();
	}
	//Draw
	public char[][] drawBoard() {
		char[][] board = new char[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j] = salas[i][j].draw();
			}
		}
		
		return board;
	}
	
	public void drawConsole() {
		Sala playerSala = null;

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(salas[i][j].draw());
				if(salas[i][j].getPlayerIn())
					playerSala = salas[i][j];
			}
			System.out.println();
		}
		System.out.println("Score: " + playerSala.getHero().getScore());
		System.out.println("=====");
	}
	
	
}
