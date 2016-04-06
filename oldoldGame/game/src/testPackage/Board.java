package testPackage;

import java.awt.Color;
import java.awt.Graphics;

public class Board {
	private int columns;
	private int rows;
	private int[][] tilemap;
	Color wallColour;
	private Color player1Colour;
	private Color validPositionColour;
	public Board() {
		createTilemap();
		wallColour = new Color(204, 102, 0);
		player1Colour = new Color(76, 0, 153);
		validPositionColour = new Color(0, 153, 0);
	}



	private void createTilemap() {
		tilemap = new int[17][17];

		rows = tilemap.length;
		columns = tilemap[1].length;

		int[][] grid = {
				// 0 represents player possible positions on the grid
				// 1-4 represent player
				// 5 represents possible wall positions
				// 7 represents unusable positions
				// 6 represents wall space taken by wall

				{ 0, 5, 0, 5, 0, 5, 0, 5, 1, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 4, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 3 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 2, 5, 0, 5, 0, 5, 0, 5, 0 },

		};

		tilemap = grid;

	}

	public int[][] getBoard() {
		return tilemap;
	}

	public int getRow() {
		return rows;
	}

	public int getColoumn() {
		return columns;
	}
	public void clearBoard() {
		// TODO Auto-generated method stub
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				if (tilemap[i][j] == 8) {
					tilemap[i][j] = 0;
					// repaint(i*50, j*50, 52,52);
					// revalidate();
					//
				}
			}
			
		}

	}
	public void drawBoard(Graphics g) {
		int positionX = 0;
		int positionY = 0;

		for (int i = 0; i < columns; i++) {

			if (i % 2 == 1) {
				positionX += 45;
			}
			if (i % 2 == 0) {
				positionX += 7;
			}
			positionY = 0;
			for (int j = 0; j < rows; j++) {
				if (j % 2 == 1) {

					positionY += 45;
				} else {
					positionY += 7;
				}
				switch (tilemap[j][i]) {
				case 0:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(Color.GRAY);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);
					break;
				case 1:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(Color.GRAY);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);
					g.setColor(player1Colour);
					g.fillOval(positionX + 8, positionY + 8, 27, 27);
					
					break;
				case 2:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(Color.GRAY);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);
					g.setColor(Color.BLUE);
					g.fillOval(positionX + 8, positionY + 8, 30, 30);
					break;
				case 3:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(Color.GRAY);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);
					g.setColor(Color.WHITE);
					g.fillOval(positionX + 8, positionY + 8, 30, 30);
					break;
				case 4:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(Color.GRAY);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);
					g.setColor(Color.MAGENTA);
					g.fillOval(positionX + 8, positionY + 8, 30, 30);
					break;
				case 5:
					g.setColor(Color.DARK_GRAY);
					g.fillRect(positionX, positionY, 45, 45);
					break;
				case 6:
					g.setColor(wallColour);
					g.fillRect(positionX, positionY, 45, 45);

					break;
				case 7:
					g.setColor(Color.DARK_GRAY);
					g.fillRect(positionX, positionY, 45, 45);
					break;
				case 8:
					g.setColor(Color.BLACK);
					g.fillRect(positionX, positionY, 45, 45);

					g.setColor(validPositionColour);
					g.fillRect(positionX + 3, positionY + 3, 39, 39);

					break;

				}

			}
		}

	}
}
