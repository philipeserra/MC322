import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Player {
	private int x;
	private int y;
	private ArrayList<Position> pastPositions;
	private BufferedImage image;
	private Dungeon dungeon;
	private int energia;
	private boolean win;
	private boolean alive;
	
	public Player(int x, int y, Dungeon dungeon, int energia) {
		loadImage("assets/images/player.png");
		this.alive = true;
		this.win = false;
		this.energia = energia;
		this.dungeon = dungeon;
		this.x = x;
		this.y = y;
		pastPositions = new ArrayList<Position>();
		
	}
	
	private boolean inValidPosition(int x, int y) {
		boolean isValid = true;
		
		if(x >= Montador.MAX_WIDTH || x < 0 || y >= Montador.MAX_HEIGHT || y < 0) {
			isValid = false;
			return isValid;
		}
		
		if(!dungeon.getCells()[x][y].getPassable()) {
			isValid = false;
			return isValid;
		}
		
		for(Componente componente: dungeon.getComponentes()) {
			if(componente.x == this.x && componente.y == this.y && !componente.getPassable()) {
				isValid = false;
				return isValid;
			}
		}
		
		return isValid;
	}
	
	
	public void tick(ArrayList<Componente> componentes, Celula[][] celulas) {
		
		
		if(x >= Montador.MAX_WIDTH || x < 0 || y >= Montador.MAX_HEIGHT || y < 0) {
			this.x = pastPositions.get(pastPositions.size()-1).getX();
			this.y = pastPositions.get(pastPositions.size()-1).getY();
		}
		
		if(!celulas[this.x][this.y].getPassable()) {
			this.x = pastPositions.get(pastPositions.size()-1).getX();
			this.y = pastPositions.get(pastPositions.size()-1).getY();
		}
		
		for(Componente componente: dungeon.getComponentes()) {
			if(componente.x == this.x && componente.y == this.y && !componente.getPassable()) {
				this.x = pastPositions.get(pastPositions.size()-1).getX();
				this.y = pastPositions.get(pastPositions.size()-1).getY();
			}
		}
		
		if(this.energia < 0) {
			die();
		}
		
		
	}
	
	
	private void loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Error opening player image file: " + e.getMessage());
		}
		
	}
	
	public void draw(Graphics g, ImageObserver dungeon) {
		g.drawImage(image, x * Dungeon.CELL_SIZE, y*Dungeon.CELL_SIZE, dungeon);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int deltaX, int deltaY) {
		
		if(!this.alive) {
			return;
		}
		
		if(!this.inValidPosition(this.x, this.y)){
			return;
		}
		
		if(!this.inValidPosition(this.x + deltaX, this.y + deltaY)) {
			return;
		}
		

		this.pastPositions.add(new Position(this.x, this.y));
		this.x = this.x  + deltaX;
		this.y = this.y + deltaY;
		this.energia --;
		
	}
	
	public void playerInteract() {
		
		if(!this.alive) {
			return;
		}
		
		dungeon.getCells()[this.x][this.y].interactedByPlayer(this, dungeon.getComponentes(), dungeon.getCells());
		for(Componente componente: dungeon.getComponentes()) {
			if(componente.getX() == this.x && componente.getY() == this.y) {
				componente.interactedByPlayer(this, dungeon.getComponentes(), dungeon.getCells());
			}
		}
	}
	
	public void resetLevel() {
		dungeon.resetLevel();
	}
	
	public void setEnergy(int energy) {
		this.energia = energy;
	}
	
	public int getEnergy() {
		return this.energia;
	}
	
	public boolean getWin() {
		return win;
	}
	
	public void Win() {
		this.win = true;
	}
	
	public void die() {
		this.alive = false;
		loadImage("assets/images/playerdead.png");
	}
	
	public Position getPastPosition() {
		if(this.pastPositions.size() <= 0) {
			return new Position(this.x, this.y);
		}
		else {
			return this.pastPositions.get(this.pastPositions.size() - 1);
		}
	}

}
