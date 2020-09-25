import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MineButton extends JButton implements ActionListener {
	Integer x, y;
	int[][] board;
	public static int MINE = -1;
	
	public MineButton(Integer x, Integer y, int[][] board) {
		this.x = x;
		this.y = y;
		this.board = board;
		
		setText("C");
		addActionListener(this);
	}
	
	 @Override
     public void actionPerformed(ActionEvent e) {
		 if (board[this.x][this.y] == MINE) {
			 ImageIcon icon = new ImageIcon(getClass().getResource("/assets/bomb.png"));
			 Image img = icon.getImage();
			 Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			 ImageIcon newIcon = new ImageIcon(resizedImg);
			 
			 setText("");
			 setIcon(newIcon);
			 setDisabledIcon(newIcon);
			 System.out.println("BOOOM! Você morreu!");
		 } else {
			 UIManager.put("Button.disabledText", getColor());
			 setText(Integer.toString(board[this.x][this.y]));
			 repaint();
		 }
		 
		 Font font = new Font(getFont().getName(), Font.BOLD, getFont().getSize() + 2);
		 setFont(font);
		 setEnabled(false);
     }
	 
	 public Color getColor() {
		 switch(board[this.x][this.y]) {
		 	case 0:
		 	case 1: return new Color(0, 255, 0);
		 	case 2: return new Color(0, 0, 255);
		 	default: return new Color(255, 0, 0);		
		 }
	 }
	 
}
