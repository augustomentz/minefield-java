import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MineFieldMenu {
	private JTextField fieldHeight;
	private JTextField fieldWidth;
	private Container container;
	JPanel field;
	JComboBox<String> difficulty;

	public void createMenu() {
	    JPanel mineFieldMenu = new JPanel(new GridLayout(1, 3, 5, 5));
	    
		setFieldWidth(new JTextField("15"));
		getFieldWidth().setPreferredSize(new Dimension(190, 30));
	    setFieldHeight(new JTextField("15"));
		getFieldHeight().setPreferredSize(new Dimension(190, 30));
		
		JLabel label = new JLabel("Difficulty", SwingConstants.RIGHT);
		
		difficulty = new JComboBox<>();
		difficulty.addItem("EASY");
		difficulty.addItem("MEDIUM");
		difficulty.addItem("HARD");
		
		JButton start = new JButton("Start");
		start.setForeground(Color.BLACK);
		start.setFocusPainted(false);
		start.setBorderPainted(true);
		start.setOpaque(true);
		start.setContentAreaFilled(false);
		start.setPreferredSize(new Dimension(190, 30));
		start.addActionListener(createMineFieldBoard);
		
		mineFieldMenu.add(getFieldHeight());
		mineFieldMenu.add(getFieldWidth());
		mineFieldMenu.add(label);
		mineFieldMenu.add(difficulty);
		mineFieldMenu.add(start);
		mineFieldMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContainer().add(mineFieldMenu, BorderLayout.NORTH);
    }

	ActionListener createMineFieldBoard = new ActionListener() {
	   @Override
	   public void actionPerformed(ActionEvent ae) {
		   MineFieldGrid grid = new MineFieldGrid(
			   Integer.parseInt(getFieldWidth().getText()),
			   Integer.parseInt(getFieldHeight().getText()),
			   difficulty.getSelectedItem()
		   );
		   
		   grid.createBoard();
		   
		   if (field != null) {
			   field.removeAll();
		   }

//		   if (!isDimensionsValid()) {
//			   JOptionPane.showMessageDialog(getContainer(), "The game field only accept the dimensions greater than 15x15");
//			   return;
//		   }

		   field = grid.createGrid(
			   field
		   );
		   
		   grid.positionBombs();
		   grid.setHints();
		   
		   field.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		   getContainer().add(field, BorderLayout.CENTER);
		   getContainer().revalidate();
		   getContainer().repaint();
	   }
    };
    
    public boolean isDimensionsValid() {
    	return (Integer.parseInt(getFieldWidth().getText()) < 15 || Integer.parseInt(getFieldHeight().getText()) < 15) ? false : true;
    }
	
    JTextField getFieldHeight() {
		return fieldHeight;
	}

	void setFieldHeight(JTextField fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	JTextField getFieldWidth() {
		return fieldWidth;
	}

	void setFieldWidth(JTextField fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	Container getContainer() {
		return container;
	}

	void setContainer(Container container) {
		this.container = container;
	}
}