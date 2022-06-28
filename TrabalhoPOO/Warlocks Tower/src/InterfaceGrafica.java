import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Virou estudos, nao ha mais necessidade de interface grafica
public class InterfaceGrafica implements ActionListener {
	
	private int count = 0; 
	JPanel panel;
	JFrame frame;
	JLabel label;
	
	public InterfaceGrafica() {
		frame  = new JFrame();
		panel = new JPanel();
		JButton button = new JButton(String.valueOf("Click me"));
		button.addActionListener(this);
		label = new JLabel("Numero: 0");
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Warlock's Tower");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new InterfaceGrafica();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.count ++;
		this.label.setText("Numero:"+this.count);
		
	}
}
