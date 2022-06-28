import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controlador {
	private Player player;
	
	public Controlador(Player player) {
		this.player = player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			player.move(0, -1);
			break;
		case KeyEvent.VK_S:
			player.move(0, 1);
			break;
		case KeyEvent.VK_A:
			player.move(-1, 0);
			break;
		case KeyEvent.VK_D:
			player.move(1, 0);
			break;
		case KeyEvent.VK_E:
			player.playerInteract();
			break;
		case KeyEvent.VK_P:
			player.resetLevel();
			break;
		}
			
	}

	public void playerAction(ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

	public void setPlayerActionNull() {
		// TODO Auto-generated method stub
		
	}

}
