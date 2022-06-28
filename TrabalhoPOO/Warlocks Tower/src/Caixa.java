import java.util.ArrayList;

public class Caixa extends Componente{
	
	public Caixa(int x, int y) {
		loadImage("images/caixa.png");
		this.x = x;
		this.y = y;
	}

	@Override
	public void tick(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
//		if(player.getX() == this.x && player.getY() == this.y + 1) {
//			if(inValidPosition(0, -1, componentes, celulas))
//				setPassable(true);
//			else
//				setPassable(false);
//		}
//		
//		if(player.getX() == this.x && player.getY() == this.y - 1) {
//			if(inValidPosition(0, 1, componentes, celulas))
//				setPassable(true);
//			else
//				setPassable(false);
//		}
//		
//		if(player.getX() == this.x + 1 && player.getY() == this.y) {
//			if(inValidPosition(1, 0, componentes, celulas))
//				setPassable(true);
//			else
//				setPassable(false);
//		}
//		
//		if(player.getX() == this.x - 1 && player.getY() == this.y) {
//			if(inValidPosition(-1, 0, componentes, celulas))
//				setPassable(true);
//			else
//				setPassable(false);
//		}
		
	}

	@Override
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {

		
		if(player.getX() == this.x && player.getY() == this.y) {
			move(player.getX()-player.getPastPosition().getX(), player.getY()-player.getPastPosition().getY(), componentes, celulas);
	
			
		}
	}

	@Override
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean inValidPosition(int x, int y, ArrayList<Componente> componentes, Celula[][] celulas) {
		boolean isValid = true;
		
		if(x >= Montador.MAX_WIDTH || x < 0 || y >= Montador.MAX_HEIGHT || y < 0) {
			isValid = false;
			return isValid;
		}
		
		if(!celulas[x][y].getPassable()) {
			isValid = false;
			return isValid;
		}
		
		for(Componente componente: componentes) {
			if(componente.x == this.x && componente.y == this.y && !componente.getPassable()) {
				isValid = false;
				return isValid;
			}
		}
		
		return isValid;
	}
	
	private void move(int deltaX, int deltaY, ArrayList<Componente> componentes, Celula[][] celulas) {
		if(inValidPosition(this.x+deltaX, this.y+deltaY, componentes, celulas)) {
			this.x += deltaX;
			this.y += deltaY;
			System.out.println(deltaX);
			System.out.println(deltaY);
		}
	}

}
