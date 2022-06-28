import java.util.ArrayList;

public class Porta extends Componente implements Activable{
	
	
	public Porta(int x, int y, boolean passable) {
		this.x = x;
		this.y = y;
		this.passable = passable;
		if(!this.passable) {
			loadImage("images/porta_aberta.png");
		}
		else {
			loadImage("images/porta_fechada.png");
		}
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
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getActivabled() {
		// TODO Auto-generated method stub
		return passable;
	}

	@Override
	public void setActivabled(boolean activabled) {
		this.passable = activabled;
		
		if(!this.passable) {
			loadImage("images/porta_aberta.png");
		}
		else {
			loadImage("images/porta_fechada.png");
		}
	}

}
