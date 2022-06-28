import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


public class Dungeon extends JPanel implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6198124569792911337L;
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGTH = 900;
	public static final int CELL_SIZE = 64;
	private static final int DELAY = 0;
	
	private BufferedReader levelsReader;
	private int nivel;
	private int nivelMaximo;
	private Timer timer;
	private Player player;
	
	private ArrayList<Componente> componentes;
	private Celula[][] celulas;
	private Montador montador;
	private Controlador controlador;
	
	private JLabel energyBoard;
	
	public Dungeon(int nivel) {
		loadLevels();
		String s;
		try {
			if((s = levelsReader.readLine()) != null) {
				this.nivelMaximo = Integer.parseInt(s);
			}
		} catch(Exception e) {
			System.out.println("Arquivo levels.txt não inserido corretamente");
			this.nivelMaximo = 1;
		}

		this.nivel = nivel;
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGTH));
		setBackground(new Color(0, 0, 0));
	
		montador = new Montador(this);
		
		//O Player, as Celulas e os Componentes devem ser instanciados nessa ordem.
		player = montador.createPlayer();
		componentes = montador.createComponents();
		celulas = montador.createCells(componentes);
		
		controlador = new Controlador(player);
		
		energyBoard = new JLabel(""+player.getEnergy());
		this.add(energyBoard);
		
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	private void loadLevels() {
		File file = new File("data/levels/levels.txt");
		try {
			levelsReader = new BufferedReader(new FileReader(file));
		}catch(IOException e) {
			System.out.println("Os niveis não puderam ser carregados");
		}
	}
	
	public int getLevel() {
		return this.nivel;
	}
	
	public Celula[][] getCells(){
		return celulas;
	}
	
	public ArrayList<Componente> getComponentes(){
		return componentes;
	}
	
	//KEY EVENTS
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlador.keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	//
	
	//ACTIONS

	@Override
	public void actionPerformed(ActionEvent e) {
		player.tick(componentes, celulas);
		
		cellsInteraction();
		componentsInteraction();
		
		if(player.getWin()) {
			this.dungeonConcluded();
		}
		
		energyBoard.setText(""+ player.getEnergy());
		
		repaint();
		
	}
	
	public void dungeonConcluded() {
		System.out.println("VOCE CONCLUIU A DUNGEON");
		if(this.nivel < this.nivelMaximo) {
			this.nivel ++;

			montador = new Montador(this);

			//O Player, as Celulas e os Componentes devem ser instanciados nessa ordem.
			player = montador.createPlayer();
			componentes = montador.createComponents();
			celulas = montador.createCells(componentes);
			
			controlador = new Controlador(player);
			
		}
	}
	
	public void resetLevel() {

		montador = new Montador(this);

		//O Player, as Celulas e os Componentes devem ser instanciados nessa ordem.
		player = montador.createPlayer();
		componentes = montador.createComponents();
		celulas = montador.createCells(componentes);
		controlador = new Controlador(player);
	}
	
	public void cellsInteraction() {
		
		for(Celula[] celulaArray: celulas) {
			for(Celula celula: celulaArray) {
				celula.tick(player, componentes, celulas);
				celula.interact(player, componentes, celulas);
			}
		}
	}
	
	public void componentsInteraction() {
		for(Componente componente: componentes) {
			componente.tick(player, componentes, celulas);
			componente.interact(player, componentes, celulas);
		}
	}
	
	//
	
	//DRAW
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawBackground(g);
		
		for(Celula[] celulaArray: celulas) {
			for(Celula celula: celulaArray) {
				celula.draw(g, this);
			}
		}
		
		for(Componente componente: componentes) {
			componente.draw(g, this);
		}
		
		player.draw(g, this);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void drawBackground(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		for(int row = 0; row < (int)SCREEN_HEIGTH/CELL_SIZE; row++) {
			for(int col = 0; col < (int)SCREEN_WIDTH/CELL_SIZE; col++) {
				if((row+col) % 2 == 1) {
					g.fillRect(row*CELL_SIZE, col*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}
	
	
}
