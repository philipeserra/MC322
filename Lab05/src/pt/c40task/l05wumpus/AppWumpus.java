package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      Montador montador = new Montador();
      Caverna caverna  = montador.createCave(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      Heroi heroi = caverna.getSala(0, 0).getHero();
      Controle controlador = new Controle(heroi);
      String movements = tk.retrieveMovements();

      
      if(movements.length() > 0) {
    	  caverna.drawConsole();
    	  System.out.println("Nao estou no modo interativo");

          tk.writeBoard(caverna.drawBoard(), 0, 'P');
          if(montador.verifyCave(arquivoCaverna, arquivoSaida, arquivoMovimentos)) {
              for(int i = 0; i < movements.length() && (heroi.playerState() == 'P'); i++) {
            	  controlador.playerMoviment(movements.charAt(i));
            	  tk.writeBoard(caverna.drawBoard(), heroi.getScore(), heroi.playerState());
            	  caverna.drawConsole();
              }
          }else {
        	  System.out.println("A caverna não é valida");
          }

         
          
          tk.stop();
      }
      else {
   	  
    	  System.out.println("Estou no modo interativo");
    	  caverna.drawConsole();
    	  tk.writeBoard(caverna.drawBoard(), 0, 'P');  
		  Scanner keyboard = new Scanner(System.in);
    	  if(montador.verifyCave(arquivoCaverna, arquivoSaida, arquivoMovimentos)) {
    		  while((heroi.playerState() == 'P') && keyboard.hasNextLine() ) {

    			  String input = keyboard.nextLine();
    			  controlador.playerMoviment(input.charAt(0));
    			  tk.writeBoard(caverna.drawBoard(), heroi.getScore(), heroi.playerState());
    			  caverna.drawConsole();
    		  }
    	  }else {
        	  System.out.println("A caverna não é valida");
          }
    	  
		  keyboard.close();
    	  tk.stop();
      }

   }

}
