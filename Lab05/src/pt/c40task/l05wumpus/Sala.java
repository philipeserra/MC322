package pt.c40task.l05wumpus;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Sala {
	private ArrayList<Componente> componentes = new ArrayList<Componente>();
	private Heroi heroi; 
	private boolean playerIn;
	private boolean isVisited = false;

	
	public Sala(ArrayList<Componente> componentes, Heroi heroi, boolean playerIn) {
		this.componentes = componentes;
		this.heroi = heroi;
		this.playerIn = playerIn;
	}
	
	public void setPlayerIn(boolean playerIn) {
		this.playerIn = playerIn;
		if(playerIn)
			this.isVisited = true;
	}
	
	public boolean getPlayerIn() {
		return this.playerIn;
	}
	
	
	public Heroi getHero() {
		return this.heroi;
	}
	
	public void addComponent(Componente component) {
		this.componentes.add(component);
	}
	
	//
	
	public void spreadAlerts() {
		for(int i = 0; i < this.componentes.size(); i++) {
			switch(this.componentes.get(i).getType()) {
			case "Wumpus":
				this.componentes.get(i).spreadAlert();
				break;
			case "Buraco":
				this.componentes.get(i).spreadAlert();
				break;
			}
		}
		
	}
	
	//Interactions
	
	public void playerFindsWumpus(Componente wumpus) {
		Random rand = new Random();
		int result = rand.nextInt(2);
		
		if(!wumpus.getAlive()) {
			System.out.println("Wumpus esta morto");
			return;
		}
		
		if(heroi.getArrowEquiped() && result == 1) {
			wumpus.death();
			System.out.println("Voce matou o Wumpus");
			this.heroi.setScore(this.getHero().getScore() + 500);
		}
		else {
			heroi.death();
			System.out.println("Voce morreu");
		}
		
	}
	
	public void playerFindsGold(Componente ouro) {
		
		if(!ouro.getAlive()) {
			System.out.println("O ouro estava aqui");
			return;
		}
		
		if(heroi.getCollectGold()) {
			heroi.setGold(true);
			heroi.collectGold(false);
			System.out.println("Voce coleteu o ouro");
			ouro.death();
		}
		else{
			System.out.println("O Ouro esta aqui!");
		}
	}
	
	public void playerFindsBrisa() {
		System.out.println("Ventinho forte");
	}
	
	public void playerFindsFedor() {
		System.out.println("Um cheiro estranho por aqui");
	}
	
	public void playerFindsBuraco() {
		heroi.death();
		System.out.println("voce caiu em um poco profundo");
	}
	
	public void interaction() {
		if(this.playerIn) {
			for(int i = 0; i < this.componentes.size(); i++) {
				switch(this.componentes.get(i).getType()) {
				case "Wumpus":
					this.playerFindsWumpus(this.componentes.get(i));
					break;
				case "Ouro":
					this.playerFindsGold(this.componentes.get(i));
					break;
				case "Buraco":
					this.playerFindsBuraco();
					break;
				case "Brisa":
					this.playerFindsBrisa();
					break;
				case "Fedor":
					this.playerFindsFedor();
					break;
				}
			}
		}
	}
	
	public char draw() {
		if(!this.isVisited)
			return '-';
		
		for(int i = 0; i < this.componentes.size(); i++) {
			switch(this.componentes.get(i).getType()) {
			case "Wumpus":
				if(this.componentes.get(i).getAlive())
					return 'W';
			case "Ouro":
				if(this.componentes.get(i).getAlive())
					return 'O';
			case "Buraco":
				if(this.componentes.get(i).getAlive())
					return 'B';
			}
		}
		
		if(this.playerIn) {
			return 'P';
		}
		
		for(int i = 0; i < this.componentes.size(); i++) {
			switch(this.componentes.get(i).getType()) {
			case "Fedor":
				return 'f';
			case "Brisa":
				return 'b';
			}
		}
		

		return '#';
		

	}
	
}
