

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



abstract public class Componente implements Interactable{
	
	protected int x;
	protected int y;
	protected BufferedImage image;
	protected boolean alive = true;
	protected boolean passable = true;
	
	protected void loadImage(String path) {
		try {
			image = ImageIO.read(new File("assets/" + path));
		} catch (IOException e) {
			System.out.println("Error opening " + path + " image file: " + e.getMessage());
		}
		
	}
	
	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(image, x * Dungeon.CELL_SIZE, y*Dungeon.CELL_SIZE, observer);
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
		
	}

	public void setY(int y) {
		this.y = y;
		
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
	public boolean getPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	

	
	
}
