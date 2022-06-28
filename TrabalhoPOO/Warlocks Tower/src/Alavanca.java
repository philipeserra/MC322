import java.util.ArrayList;

public class Alavanca extends Componente{
	
	private boolean puxada;
	//Componentes que alavanca torna passable ou inpassable
	private ArrayList<Activable> ativaveis;
	
	public Alavanca(int x, int y, ArrayList<Activable> ativaveis) {
		loadImage("images/alavanca_para_cima.png");
		this.x = x;
		this.y = y;
		this.puxada = false;
		this.ativaveis = ativaveis;
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
		this.puxada = !this.puxada;
		if(this.puxada) {
			loadImage("images/alavanca_para_baixo");
		}else {
			loadImage("images/alavanca_para_cima");
		}
		
		for(Activable ativavel: ativaveis) {
			System.out.println("fui ativada");
			ativavel.setActivabled(!ativavel.getActivabled());
		}
	}

}
