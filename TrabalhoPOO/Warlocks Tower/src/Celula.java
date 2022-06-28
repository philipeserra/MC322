import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

abstract public class Celula implements Interactable{
	
	protected int x;
	protected int y;
	protected BufferedImage image;
	protected boolean passable = true;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	
	
	protected void loadImage(String path) {
		try {
			image = ImageIO.read(new File("assets/" + path));
		} catch (IOException e) {
			System.out.println("Error opening cell image file: " + e.getMessage());
		}
		
	}
	
	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(image, x * Dungeon.CELL_SIZE, y*Dungeon.CELL_SIZE, observer);
		
	}
}
