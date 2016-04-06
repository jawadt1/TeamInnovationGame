package testPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;



public class Game extends JComponent implements MouseListener, MouseMotionListener {

	// Graphics is the base class that allows for drawing on components

	/**
	 * 
	 */
	
	private int player = 1;
	private int columns;
	private int rows;
	private int[][] tilemap;
	private boolean movePlayer;
	private int X;
	private int Y;
	private boolean horizontalWall;
	private boolean selectOPtion;
	private boolean verticalWall;
	private int mouseWallX = 0;
	private int mouseWallY = 0;
	private Board board;
	private RuleChecker rules;
	private Color wallColour;
	private Player players;

	public Game() {
		board = new Board();
		tilemap = board.getBoard();
		rows = board.getRow();
		columns = board.getColoumn();
		addMouseListener(this);
		addMouseMotionListener(this);
		rules = new RuleChecker();
		wallColour = new Color(204, 102, 0);
		players = new PlayerOne();
	}

	public void paint(Graphics g) {
		if (player > 2) {
			player = 1;
		}
		board.drawBoard(g);
		if (horizontalWall) {
			g.setColor(wallColour);
			g.fillRect(mouseWallX - 50, mouseWallY - 3, 100, 7);
		}
		if (verticalWall) {
			g.setColor(wallColour);
			g.fillRect(mouseWallX - 1, mouseWallY - 50, 7, 100);
		}
		
		if(movePlayer){
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					if(tilemap[i][j] == player){
						tilemap[i][j] = 0;
					}
				}
			}
			g.setColor(Color.RED);
			g.fillOval(mouseWallX-9, mouseWallY-9, 27, 27);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x1 = e.getX();
		int y1 = e.getY();
		int tempX1 = x1 / 53;
		int tempY1 = y1 / 53;
		int checkX1 = tempX1 + tempX1;
		int checkY1 = tempY1 + tempY1;
		if (tilemap[checkY1][checkX1] == player) {
			setMovePlayer();
		}
		if (movePlayer) {

			int x = e.getX();
			int y = e.getY();
			int tempX = x / 53;
			int tempY = y / 53;
			int checkX = tempX + tempX;
			X = tempX;
			Y = tempY;
			int checkY = tempY + tempY;
			if (tilemap[checkY][checkX] == 8) {
				for (int i = 0; i < columns; i++) {
					for (int j = 0; j < rows; j++) {
						if(tilemap[i][j] == player){
							tilemap[i][j] = 0;
						}
					}
				}
				tilemap[checkY][checkX] = player;
				movePlayer = false;
				player++;
				board.clearBoard();
				repaint();

			}
		}

		if (horizontalWall) {
			int x = e.getX();
			int y = e.getY();
			int tempX = x / 50;
			int tempY = y / 50;
			// if(tempX%2 ==0 && tempY %2 ==0){
			int checkX = tempX + tempX - 1;
			int checkY = tempY + tempY - 1;
			if(rules.checkHorizontalWall(tilemap, checkX, checkY)){
			tilemap[checkY][checkX] = 6;
			tilemap[checkY][checkX - 1] = 6;
			tilemap[checkY][checkX + 1] = 6;
			selectOPtion = true;
			horizontalWall = false;
			player++;
			repaint();
			}
			
			
			
		}

		if (verticalWall) {
			boolean valid;
			int x = e.getX();
			int y = e.getY();
			int tempX = x / 50;
			int tempY = y / 50;
			int checkX = tempX + tempX - 1;
			int checkY = tempY + tempY - 1;
			valid = rules.checkVerticalWall(tilemap, checkX, checkY);
			if(valid){
				tilemap[checkY][checkX] = 6;
				tilemap[checkY - 1][checkX] = 6;
				tilemap[checkY + 1][checkX] = 6;
				selectOPtion = true;
				verticalWall = false;
				player++;
				repaint();
			}
			
			// showStatus("Mouse on ("+ x+", "+
			// y+")."+checkX+checkY+ tilemap[checkY][checkX]);
			

		}

		// createTilemap();
		// showStatus("Mouse on ("+ x+", "+ y+")."+checkX+checkY+
		// tilemap[checkY][checkX]);
		e.consume();
	}

	

	public void setMovePlayer() {
		if (!movePlayer) {
			movePlayer = true;
			horizontalWall = false;
			verticalWall = false;
			tilemap = rules.checkValidPositions(tilemap, player);
			repaint();
		}
	}

	public void setHorizontalWall() {
		if (movePlayer) {
			board.clearBoard();
		}
		if (!horizontalWall) {
			board.clearBoard();
			horizontalWall = true;
			verticalWall = false;
			movePlayer = false;

		}
	}

	public void setVerticalWall() {
		if (movePlayer) {
			board.clearBoard();
		}
		verticalWall = true;
		horizontalWall = false;
		movePlayer = false;
	}

//	private void getValidPositions() {
//		
//		repaint();
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		// showStatus("Mouse on ("+ e.getX()+", "+ e.getY()+")");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseWallX = e.getX();
		mouseWallY = e.getY();

		e.consume();
		repaint();
	}
	public void getPlayer(){
		players = players.getNextPlayer();
	}
}
