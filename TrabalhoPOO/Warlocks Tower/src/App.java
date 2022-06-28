import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class App {
	
	private static final String TITLE = "WARLOCK'S TOWER: RETURN";
	
	private BufferedReader levelsReader;
	private int nivel = 1;
	
	
	
	private static void initWindow() {
		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dungeon dungeon = new Dungeon(1);
		window.add(dungeon, BorderLayout.CENTER);
		
		window.addKeyListener(dungeon);
		window.setResizable(true);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initWindow();
			}
		});
	}
}
