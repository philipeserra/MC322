import java.util.ArrayList;

public class Saida extends Celula{

	public Saida(int x, int y) {
		loadImage("images/saida.png");
		this.x = x;
		this.y = y;
		this.passable = true;
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
		player.Win();
	}

}
