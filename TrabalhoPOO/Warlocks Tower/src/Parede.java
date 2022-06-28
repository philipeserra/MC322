import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Parede extends Celula{
	
	public Parede(int x, int y) {
		loadImage("images/parede.png");
		this.x = x;
		this.y = y;
		this.passable = false;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void tick(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getPassable() {
		// TODO Auto-generated method stub
		return this.passable;
	}
	@Override
	public void setPassable(boolean passable) {
		this.passable = passable;
		
	}
	@Override
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

}
