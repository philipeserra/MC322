import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

//Interface para as celulas e componentes
public interface Interactable {
	
	//Funcao tick impede do Player, componente ou celula estar onde nao deveria estar.
	public void tick( Player player, ArrayList<Componente> componentes, Celula[][] celulas);
	
	//Faz a interecao com o player, componentes e celulas na Dungeon
	public void interact(Player player, ArrayList<Componente> componentes, Celula[][] celulas);
	
	//Desenha o objeto
	public void draw(Graphics g, ImageObserver observer);
	
	//Funcao ativada caso algo seja interagido player 
	public void interactedByPlayer(Player player, ArrayList<Componente> componentes, Celula[][] celulas);
}
