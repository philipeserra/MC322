import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Montador {
	
	public static final int MAX_HEIGHT = (int)Dungeon.SCREEN_HEIGTH/Dungeon.CELL_SIZE;
	public static final int MAX_WIDTH = (int)Dungeon.SCREEN_WIDTH/Dungeon.CELL_SIZE;
	private Dungeon dungeon;
	private BufferedReader br;
	private boolean badFormatFile = false;
	
	public Montador(Dungeon dungeon) {
		this.dungeon = dungeon;
		readFile();
	}

	private void readFile() {
		
		File file = new File("data/levels/" + dungeon.getLevel() + ".txt");
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			
		} catch (IOException e) {
			
			this.badFormatFile = true;

		}
		
	}
	
	
	private void fileBadFormat() {
		System.out.println("Arquivo está com a formatacao errada");
		this.badFormatFile = true;
	}
	
	public Player createPlayer() {
		
		if(this.badFormatFile) {
			return new Player(0,0,dungeon,0);
		}
		
		String[] st;
		try {
			st = br.readLine().split(" ");
		} catch (IOException e1) {
			fileBadFormat();
			System.out.println(e1.getMessage());
			return new Player(0, 0, dungeon, 0);
		}
		
		if(st.length != 4) {
			fileBadFormat();
			System.out.println("Player mal formatado");
			return new Player(0, 0, dungeon, 0);
		}
		
		if(!st[0].equals("Player")){
			System.out.println("O arquivo deve comecar com Player");
			fileBadFormat();
			return new Player(0, 0, dungeon, 0);
		}
		
		int x;
		int y;
		int energia;
		
		try {
			x = Integer.parseInt(st[1]);
			y = Integer.parseInt(st[2]);
			energia = Integer.parseInt(st[3]);
			
		} catch(Exception e){
			System.out.println("x, y e energia devem ser inteiros");
			fileBadFormat();
			return new Player(0, 0, dungeon, 0);
		}
		
		return new Player(x, y, dungeon, energia);
	}
	

	public ArrayList<Componente> createComponents() {
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		
		if(this.badFormatFile) {
			return componentes;
		}
		String[] st;
		String s;
		try {
//			while((s = br.readLine()) != null) {
//				System.out.println(s);
//			}
			
			while((s = br.readLine()) != null && !s.equals("END")) {
				st = s.split(" ");
				String ComponentName;
				int x;
				int y;
				
				if(st.length < 3) {
					fileBadFormat();
					System.out.println("Componente mal formatado");
				}
				
				ComponentName = st[0];
				
				try {
					x = Integer.parseInt(st[1]);
					y = Integer.parseInt(st[2]);
					
					if(ComponentName.equals("Energia")) {
						
						if(st.length == 4){
							int energia;
							try {
								energia = Integer.parseInt(st[3]);
								componentes.add(new Energia(x, y, energia));
							} catch(Exception e) {
								System.out.println("Energia mal formatada");
							}

						}else {
							System.out.println("Energia mal formatada");
						}

					}
					else if(ComponentName.equals("Alavanca")){
						
						if(st.length > 4) {
							ArrayList<Activable> ativaveis = new ArrayList<Activable> ();
							try {
								for(int i = 3; i < st.length; i += 2) {
									int n = Integer.parseInt(st[i]);
									int m = Integer.parseInt(st[i+1]);
									for(Componente componente: componentes) {
										if(n == componente.x && m == componente.y) {
											ativaveis.add((Activable) componente);
										}
									}
								}
								
								componentes.add(new Alavanca(x, y, ativaveis));
								
							} catch(Exception e) {
								System.out.println("Alavanca mal formatada");
							}
						}else {
							System.out.println("Alavanca mal formatada");
						}
						
						
					}
					else if(ComponentName.equals("Porta")) {
						
						if(st.length == 4){
							boolean aberta;
							try {
								aberta = Boolean.parseBoolean(st[3]);
								componentes.add(new Porta(x, y, aberta));
							} catch(Exception e) {
								System.out.println("Porta mal formatada");
							}

						}
						
					}
					else if(ComponentName.equals("Caixa")) {
						if(st.length == 3) {
							componentes.add(new Caixa(x,y));
						}
					}

					
				} catch(Exception e){
					System.out.println("x, y do componente devem ser inteiros");
					fileBadFormat();
				}
				

				
			};
			
		} catch (IOException e) {
			System.out.println("Erro ao pegar componentes");
			return componentes;
		}
		
		return componentes;
	}

	public Celula[][] createCells(ArrayList<Componente> componentes) {
		

		
		Celula[][] celulasBadFormat = new Celula[MAX_WIDTH][MAX_HEIGHT];
		for(int i = 0; i < MAX_WIDTH; i++) {
			for(int j = 0; j < MAX_HEIGHT; j++) {
					celulasBadFormat[i][j] = new CelulaPadrao(i, j);
			}
		}
		
		celulasBadFormat[0][0] = new CelulaPadrao(0, 0);
		celulasBadFormat[0][1] = new Saida(0, 1);
		
		if(this.badFormatFile) {
			return celulasBadFormat;
		}
		
		Celula[][] celulas = new Celula[MAX_WIDTH][MAX_HEIGHT];
		for(int i = 0; i < MAX_WIDTH; i++) {
			for(int j = 0; j < MAX_HEIGHT; j++) {
					celulas[i][j] = new Parede(i, j);
			}
		}
		
	
		
		String s;
		
		try {
			while((s = br.readLine()) != null && !s.equals("END")) {
				String[] st = s.split(" ");
				int x;
				int y;
				String cellName;
				
				if(st.length < 2) {
					System.out.println("aaaaaaaaaaaa celula mal formatada");
					fileBadFormat();
					return celulasBadFormat;
				}
				
				
				cellName = st.length > 2? st[2] : " ";
				
				try {
					x = Integer.parseInt(st[0]);
					y = Integer.parseInt(st[1]);
					

					
				} catch(Exception e){
					System.out.println("x, y da celula devem ser inteiros");
					fileBadFormat();
					return celulasBadFormat;
				}
				
				if(cellName.equals("Saida")) {
					celulas[x][y] = new Saida(x, y);
				}
				else if(cellName.equals("PisoAcionador")) {
					ArrayList<Activable> ativaveis = new ArrayList<Activable> ();
					try {
						for(int i = 3; i < st.length; i += 2) {
							int n = Integer.parseInt(st[i]);
							int m = Integer.parseInt(st[i+1]);
							for(Componente componente: componentes) {
								if(n == componente.x && m == componente.y) {
									ativaveis.add((Activable) componente);
								}
							}
						}
						
						celulas[x][y] = new PisoAcionador(x,y,ativaveis);
						
					} catch(Exception e) {
						System.out.println("Alavanca mal formatada");
					}

				}
				else {
					celulas[x][y] = new CelulaPadrao(x, y);
				}
				
			}
			
		} catch (IOException e) {
			fileBadFormat();
			
			return celulasBadFormat;
			
		}
		
		return celulas;
	}

}
