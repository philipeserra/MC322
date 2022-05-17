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
	}
	
	//Draw
	
	
}
