import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class MineField extends JFrame {
	Container container;
	
	public void init() {
		setTitle("Minefield");
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setLayout(new BorderLayout());        
        container = getContentPane(); 
		
        MineFieldMenu menu = new MineFieldMenu();
        
		menu.setContainer(container);
		menu.createMenu();
	}
	
	public static void main(String[] args) {
		MineField minefield = new MineField();

		minefield.init();
		minefield.setVisible(true);
	}
}
