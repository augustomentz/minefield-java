import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineFieldGrid {
	Integer sizeX;
	Integer sizeY;
	Object difficulty;
	public static int MINE = -1;
	public static int EMPTY = 0;
	public static String EASY = "EASY";
	public static String MEDIUM = "MEDIUM";
	public static String HARD = "HARD";
	
	int [][] board;
	
	public MineFieldGrid() {
		
	}
	
	public MineFieldGrid(Integer x, Integer y, Object difficulty) {
		this.sizeX = x; 
		this.sizeY = y;
		this.difficulty = difficulty;
	}
	
	public JPanel createGrid(JPanel panel) {
		JButton button;
		 
		if (panel != null) {
			panel.setLayout(new GridLayout(getSizeX(), getSizeY()));
		} else {
			panel = new JPanel(new GridLayout(getSizeX(), getSizeY()));
		}
		
		for (int x = 0; x < getSizeX(); x++) {
			for (int y = 0; y < getSizeY(); y++) {
				button = new MineButton(x, y, board);

				panel.add(button);
			}
		}
		
		return panel;
	}    
	
	public void createBoard() {
		board = new int[getSizeX()][getSizeY()];
		
		for (int x = 0; x < getSizeX(); x++) {
			for (int y = 0; y < getSizeY(); y++) {
				board[x][y] = EMPTY;
			}
		}
	}
	
	public void printBoard() {
		for (int x = 0; x < getSizeX(); x++) {
			for (int y = 0; y < getSizeY(); y++) {
				System.out.print(board[x][y]);
			}
			System.out.print("\n");
		}
	}
	
	public void positionBombs() {
		Double percentOfBombs = 0.0;
		
		if (difficulty == EASY) percentOfBombs = 0.2;
		if (difficulty == MEDIUM) percentOfBombs = 0.4;
		if (difficulty == HARD) percentOfBombs = 0.6;
		
		Long numMines = Math.round(getSizeX() * getSizeY() * percentOfBombs);
		Random r = new Random();
		
		for (int i = 0; i < numMines; i++) {
			int x = r.nextInt((getSizeY()));
			int y = r.nextInt((getSizeX()));
			
			if (board[x][y] != MINE) {
				board[x][y] = MINE;
			} else {
				i--;
			}
		}
		
	}
	
	public Integer getPositionContent(Integer posX, Integer posY) {
		return board[posX][posY];
	}
	
	public void setHints() {
		for (int x = 0; x < getSizeX(); x++) {
			for (int y = 0; y < getSizeY(); y++) {
				if (board[x][y] == MINE) {
					fillSlotWithNumberOfAdjacentBombs(x, y);
				}		
			}
		}
	}
	
	public void fillSlotWithNumberOfAdjacentBombs(Integer posX, Integer posY) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				try {
					if (board[posX + i][posY + j] != MINE) {
						board[posX + i][posY + j] += 1;
					}
				} catch (Exception e) {
					// Caso a posição testada não exista, não faz nada
				}
			}
		}
	}
	
	public void endGame() {
		 Object[] options = { "Restart game", "Show minefield map" };
		 
		 int result = JOptionPane.showOptionDialog(null, "BOOOOM! Pisou no lugar errado!", "Minefield V1.0", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
	}
	
	Integer getSizeX() {
		return sizeX;
	}

	Integer getSizeY() {
		return sizeY;
	}
}
