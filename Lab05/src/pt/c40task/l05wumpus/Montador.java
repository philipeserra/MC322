package pt.c40task.l05wumpus;
import java.util.ArrayList;
import java.util.HashMap;

public class Montador {
	
	private Sala createRoom(char componentType, int x, int y, Caverna caverna, Heroi heroi) {
		ArrayList<Componente> componentes  = new ArrayList<Componente>() ;
		
		switch(componentType) {
		case 'P':
			Sala sala = new Sala(componentes, heroi, true);
			sala.setPlayerIn(true);
			return sala;
		
		case 'W':
			Componente wumpus = new Wumpus(x,y,caverna);
			componentes.add(wumpus);
			return new Sala(componentes, heroi, false);
			
		case 'O':
			Componente ouro = new Ouro(x,y,caverna);
			componentes.add(ouro);
			return new Sala(componentes, heroi, false);
			
		case 'B':
			Componente buraco = new Buraco(x,y,caverna);
			componentes.add(buraco);
			return new Sala(componentes, heroi, false);

		}
		
		return new Sala(componentes, heroi, false);
	}
	
	public boolean verifyCave(String arquivoCaverna, String arquivoSaida,
            String arquivoMovimentos){
		
		Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
	    String cave[][] = tk.retrieveCave();
		HashMap<String, Integer> componentsLimit = new HashMap<String, Integer>();
		componentsLimit.put("P", 0);
		componentsLimit.put("W", 0);
		componentsLimit.put("B", 0);
		componentsLimit.put("O", 0);
		componentsLimit.put("_", 0);
		
		for(int i = 0; i < cave.length; i++) {
			String componentType = cave[i][2];
			Integer j = componentsLimit.get(componentType) + 1;
			componentsLimit.replace(componentType, j);
		}
		
		
	    tk.stop();
		if(componentsLimit.get("P") != 1)
			return false;
		if(componentsLimit.get("W") != 1)
			return false;
		if(componentsLimit.get("O") != 1)
			return false;
		if((componentsLimit.get("B") < 2) && (componentsLimit.get("B") > 3))
			return false;
		
		return true;
	}
	
	public Caverna createCave(String arquivoCaverna, String arquivoSaida,
            String arquivoMovimentos) {
		Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
	    String cave[][] = tk.retrieveCave();
	    Sala[][] salas = new Sala[4][4];
	    Caverna caverna = new Caverna(salas);
	    Heroi heroi = new Heroi(0,0, caverna);
	    
		for(int i = 0; i < cave.length; i++) {
			int x = Integer.parseInt(cave[i][0]) - 1;
			int y = Integer.parseInt(cave[i][1]) - 1;
			char componentType = cave[i][2].charAt(0);
			
			Sala sala = this.createRoom(componentType, x, y, caverna, heroi);
			caverna.setSala(x,y, sala);
		}
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				caverna.updateSala(i, j);
	    
	    tk.stop();
	    return caverna;
	}

}
