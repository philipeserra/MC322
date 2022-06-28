import java.util.ArrayList;

public class PisoAcionador extends Celula{
	private ArrayList<Activable> ativaveis;
	private ArrayList<Boolean> estadosIniciais;
	
	public PisoAcionador(int x, int y, ArrayList<Activable> ativaveis) {
		loadImage("images/piso_acionador.png");
		this.x = x;
		this.y = y;
		this.ativaveis = ativaveis; 
		this.estadosIniciais = this.createInitialStates();
		this.passable = true;
	}
	
	private ArrayList<Boolean> createInitialStates() {
		ArrayList<Boolean> states = new ArrayList<Boolean> ();
		for(Activable ativavel: ativaveis) {
			states.add(ativavel.getActivabled());
		}
		
		return states;
	}
	@Override
	public void tick(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		if(player.getX() == this.x && player.getY() == this.y) {
			for(int i = 0; i < ativaveis.size(); i++) {
				this.ativaveis.get(i).setActivabled(!this.estadosIniciais.get(i));
			}
			return;
		}
		
		for(Componente componente: componentes) {
			if(componente.getX() == this.x && componente.getY() == this.y) {
				for(int i = 0; i < ativaveis.size(); i++) {
					this.ativaveis.get(i).setActivabled(!this.estadosIniciais.get(i));
				}
				return;
			}
		}
		
		for(int i = 0; i < ativaveis.size(); i++) {
			this.ativaveis.get(i).setActivabled(this.estadosIniciais.get(i));
		}
		
	}

	@Override
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}

}
