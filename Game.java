package testPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Game extends JComponent implements MouseListener, MouseMotionListener {

	// Graphics is the base class that allows for drawing on components

	/**
	 * 
	 */

	// private int player = 1;
	private int columns;
	private int rows;
	private int[][] tilemap;
	private boolean movePlayer;

	private boolean horizontalWall;
	private boolean verticalWall;
	private int mouseWallX = 0;
	private int mouseWallY = 0;
	private Board board;
	// private RuleChecker rules;
	private Color wallColour;

	private int sizeofBoard;
	private int offset;
	private int wallThickness;
	private int wallLengthEnd;
	private int wallLengthStart;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Player currentPlayer;
	private AI ai;
	private Color validPositionColour;
	private Player[] players;
	private boolean fourplayer;
	private boolean challenger;
	private boolean removeable;
	private JPanel boardPanel;
	private JPanel textPanel;
	private double textOffset;

	public Game(JComboBox boardSize, boolean AI, boolean fourplayer, boolean challenger) {
		this.fourplayer = fourplayer;
		this.challenger = challenger;
		// Initialise the players

		if (fourplayer && challenger) {
			player1 = new PlayerOne(false, 5, 0, 0, challenger);
			player2 = new PlayerTwo(AI, 5, 16, 0, challenger);
			player3 = new PlayerThree(AI, 5, 16, 16, challenger);
			player4 = new PlayerFour(AI, 5, 0, 16, challenger);
			players = new Player[4];
			players[0] = player1;
			players[1] = player2;
			players[2] = player3;
			players[3] = player4;
		} else {
			player1 = new PlayerOne(false, 10, 8, 0, challenger);
			player2 = new PlayerTwo(AI, 10, 8, 16, challenger);
			players = new Player[2];
			players[0] = player1;
			players[1] = player2;
		}
		currentPlayer = player1;

		ai = new AI();
		// make the board object
		board = new Board(players);
		tilemap = board.getBoard();
		rows = board.getRow();
		columns = board.getColoumn();
		// for the mouse listner
		addMouseListener(this);
		addMouseMotionListener(this);
		// create the rule object
		// rules = new RuleChecker();
		wallColour = new Color(204, 102, 0);
		validPositionColour = new Color(0, 153, 0);
		// size of the actual square peice
		sizeofBoard = 50;
		// used to find the array value when clicked on
		offset = 44;
		wallThickness = 7;
		wallLengthStart = 48;
		wallLengthEnd = 90;
		textOffset = 1;
		if (boardSize.getSelectedItem() == "Medium") {
			sizeofBoard = 60;
			offset = 64;
			wallThickness = 17;
			wallLengthStart = 65;
			wallLengthEnd = 130;
			textOffset = 1.56;
		} else if (boardSize.getSelectedItem() == "Large") {
			sizeofBoard = 70;
			offset = 88;
			wallThickness = 27;
			wallLengthStart = 80;
			wallLengthEnd = 160;
			textOffset = 1.75;
		}

	}

	// method will paint the board
	public void paint(Graphics g) {
		// if (player > 2) {
		// player = 1;
		// }
		Player Winner = board.checkFinished(players);
		if (Winner != null) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, (int) (55 * textOffset)));
			g.drawString("The Winner is ", 50, 170);
			g.drawString(Winner.getPlayerName(), 50, 250);
			
		} else {
			// pass the size of the board over to be drawn.
			int positionX = 0;
			int positionY = 0;

			for (int i = 0; i < columns; i++) {

				if (i % 2 == 1) {
					positionX += (sizeofBoard - 8);// m=55
				}
				if (i % 2 == 0) {
					positionX += (sizeofBoard - 46);// m=17
				}
				positionY = 0;
				for (int j = 0; j < rows; j++) {
					if (j % 2 == 1) {

						positionY += (sizeofBoard - 8);
					} else {
						positionY += (sizeofBoard - 46);
					}
					switch (tilemap[j][i]) {
					case 0:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						break;
					case 1:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						g.setColor(Color.GREEN);
						g.fillOval(positionX + 8, positionY + 8, (sizeofBoard - 26), (sizeofBoard - 26));

						break;
					case 2:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						g.setColor(Color.BLUE);
						g.fillOval(positionX + 8, positionY + 8, (sizeofBoard - 23), (sizeofBoard - 23));
						break;
					case 3:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						g.setColor(player3.getPlayerColour());
						g.fillOval(positionX + 8, positionY + 8, (sizeofBoard - 23), (sizeofBoard - 23));
						break;
					case 4:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						g.setColor(player4.getPlayerColour());
						g.fillOval(positionX + 8, positionY + 8, (sizeofBoard - 23), (sizeofBoard - 23));
						break;
					case 5:
						g.setColor(Color.DARK_GRAY);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));
						break;
					case 12:
					case 11:
						g.setColor(player1.getPlayerColour());
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						break;
					case 23:
					case 22:
						g.setColor(player2.getPlayerColour());
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						break;
					case 34:
					case 33:
						g.setColor(player3.getPlayerColour());
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						break;
					case 45:
					case 44:
						g.setColor(player4.getPlayerColour());
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						break;

					case 7:
						g.setColor(Color.DARK_GRAY);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));
						break;
					case 8:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(validPositionColour);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));

						break;
					case 10:
						g.setColor(Color.BLACK);
						g.fillRect(positionX, positionY, (sizeofBoard - 8), (sizeofBoard - 8));

						g.setColor(Color.GRAY);
						g.fillRect(positionX + 3, positionY + 3, (sizeofBoard - 14), (sizeofBoard - 14));
						break;

					}

				}
			}

			// positionX = positionX +70;

			// if this is true it will draw an horizontal wall following the
			// mouse
			if (horizontalWall) {
				g.setColor(currentPlayer.getPlayerColour());
				g.fillRect(mouseWallX - wallLengthStart, mouseWallY - 5, wallLengthEnd, wallThickness);
			}
			// if this is true it will draw an vertical wall following the mouse
			if (verticalWall) {
				g.setColor(currentPlayer.getPlayerColour());
				g.fillRect(mouseWallX - 3, mouseWallY - wallLengthStart, wallThickness, wallLengthEnd);
			}
		}
		// if this is true it will draw the player piece and make it follow the
		// mouse
		if (movePlayer) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					if (tilemap[i][j] == currentPlayer.getPlayerID()) {
						tilemap[i][j] = 10;
					}
				}
			}
			// will draw this and follow the mouse
			g.setColor(currentPlayer.getPlayerColour());
			g.fillOval(mouseWallX - 9, mouseWallY - 9, 27, 27);

		}
		// will draw the walls if it hasnt been clicked and if they have any
		if (!horizontalWall && currentPlayer.getWallAmount() != 0) {
			g.setColor(currentPlayer.getPlayerColour());
			g.fillRect((int) (501 * textOffset), (int) (225 * textOffset), wallLengthEnd, wallThickness);
		}
		if (!verticalWall && currentPlayer.getWallAmount() != 0) {
			g.setColor(currentPlayer.getPlayerColour());
			g.fillRect((int) (687 * textOffset), (int) (215 * textOffset), wallThickness, wallLengthEnd);
		}
		// To write some text on the game screen

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, (int) (55 * textOffset)));
		g.drawString(Integer.toString(currentPlayer.getWallAmount()), (int) (615 * textOffset),
				(int) (250 * textOffset));
		g.setFont(new Font("TimesRoman", Font.BOLD, (int) (15 * textOffset)));
		g.drawString(currentPlayer.getPlayerName() + "'s turn ", (int) (447 * textOffset), (int) (25 * textOffset));
		g.drawString("Peice Colour: ", (int) (447 * textOffset), (int) (50 * textOffset));
		g.setColor(currentPlayer.getPlayerColour());
		g.fillOval((int) (552 * textOffset), (int) (30 * textOffset), (int) (23 * textOffset), (int) (23 * textOffset));
		g.setColor(Color.WHITE);
		g.drawString("Game Information ", (int) (447 * textOffset), (int) (100 * textOffset));
		g.drawString("Player Name: " + player1.getPlayerName(), (int) (447 * textOffset), (int) (125 * textOffset));
		g.drawString("Wall Amount: " + player1.getWallAmount(), (int) (447 * textOffset), (int) (150 * textOffset));
		g.drawString("Player Name: " + player2.getPlayerName(), (int) (625 * textOffset), (int) (125 * textOffset));
		g.drawString("Wall Amount: " + player2.getWallAmount(), (int) (625 * textOffset), (int) (150 * textOffset));
		if (fourplayer) {
			g.drawString("Player Name: " + player3.getPlayerName(), (int) (447 * textOffset), (int) (175 * textOffset));
			g.drawString("Wall Amount: " + player3.getWallAmount(), (int) (447 * textOffset), (int) (200 * textOffset));
			g.drawString("Player Name: " + player4.getPlayerName(), (int) (625 * textOffset), (int) (175 * textOffset));
			g.drawString("Wall Amount: " + player4.getWallAmount(), (int) (625 * textOffset), (int) (200 * textOffset));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// get the x and y position of the click
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + " " + y);
		// help find the array number
		int tempX = x / offset;
		int tempY = y / offset;
		System.out.println(tempX + " " + tempY);
		// must be doubled to find the actual value of array
		int checkX = tempX + tempX;
		int checkY = tempY + tempY;
		System.out.println(checkX + " " + checkY);
		if (y >= 223 * textOffset && y <= 231 * textOffset && x >= 501 * textOffset && x <= 589 * textOffset
				&& currentPlayer.getWallAmount() != 0) {
			setHorizontalWall();
		}
		if (x > 686 * textOffset && x < 693 * textOffset && y > 214 * textOffset && y < 304 * textOffset
				&& currentPlayer.getWallAmount() != 0) {
			setVerticalWall();
		}

		// if the user clicks on the player piece it will allow him to move it
		if (checkX < 18 && checkY < 18) {
			if (tilemap[checkY][checkX] == currentPlayer.getPlayerID()) {
				setMovePlayer(checkX, checkY);
			}
			// will be set true if the user clicked the player piece
			if (challenger && removeable) {
				if (checkX >= 0 && checkY >= 0 && checkX <= 16 && checkY <= 16)
					if (board.removeWall(checkX, checkY, currentPlayer, players)) {

						verticalWall = false;
						// currentPlayer.removeWall();
						getNextPlayer();
						// player++;
						repaint();
					}
			}
			if (movePlayer) {

				// if the array value is 8 that means the user can move there
				if (board.movePlayer(checkX, checkY, currentPlayer)) {
					// set the boolean to false
					movePlayer = false;
					// change player
					currentPlayer.setXpos(checkX);
					currentPlayer.setYpos(checkY);
					// player++;
					// clear the board
					board.clearBoard(currentPlayer.getPlayerID());
					// System.out.println(checkY + " " + checkX);
					// repaint everything
					removeable = true;
					getNextPlayer();
					repaint();

				}
			}

			if (horizontalWall) {
				if (board.placeHorizontalWall(checkX, checkY, currentPlayer, players)) {
					// check if the wall being placed is valid

					horizontalWall = false;
					currentPlayer.removeWall();
					removeable = true;
					getNextPlayer();
					// player++;
					repaint();
				}

			}

			if (verticalWall) {

				if (board.placeVerticalWall(checkX, checkY, currentPlayer, players)) {

					verticalWall = false;
					currentPlayer.removeWall();
					removeable = true;
					getNextPlayer();
					// player++;
					repaint();

				}

			}

		}
		e.consume();
	}

	// if the move player is false then it will make it true and make the other
	// options false
	// it will then get all the valid positions and draw it on the board and
	// remove the player piece
	// temporary and make it follow the mouse
	public void setMovePlayer(int x, int y) {
		if (!movePlayer) {
			movePlayer = true;
			horizontalWall = false;
			verticalWall = false;
			removeable = false;
			tilemap = RuleChecker.checkValidPositions(tilemap, currentPlayer.getPlayerID(), x, y, false);
			repaint();
		}
	}

	public void setHorizontalWall() {

		if (movePlayer) {
			board.clearBoard(currentPlayer.getPlayerID());
		}

		horizontalWall = true;
		verticalWall = false;
		movePlayer = false;
		removeable = false;

	}

	public void setVerticalWall() {
		if (movePlayer) {
			board.clearBoard(currentPlayer.getPlayerID());
		}
		verticalWall = true;
		horizontalWall = false;
		movePlayer = false;
		removeable = false;
	}

	// private void getValidPositions() {
	//
	// repaint();
	// }

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

		if (movePlayer || horizontalWall || verticalWall) {
			repaint();
		}
		e.consume();
	}

	// public void getPlayer() {
	// players = players.getNextPlayer();
	// }
	public void getNextPlayer() {

		if (fourplayer) {
			if (currentPlayer.getPlayerID() == 1) {
				currentPlayer = player2;
			} else if (currentPlayer.getPlayerID() == 2) {
				currentPlayer = player3;
			} else if (currentPlayer.getPlayerID() == 3) {
				currentPlayer = player4;
			} else if (currentPlayer.getPlayerID() == 4) {
				currentPlayer = player1;
			}
		} else {
			if (currentPlayer.getPlayerID() == 1) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
		}

		if (currentPlayer.isAI()) {

			ai.aiMakeMove(tilemap, currentPlayer, players);
			board.clearBoard(currentPlayer.getPlayerID());
			repaint();

			getNextPlayer();

		}

	}

}
