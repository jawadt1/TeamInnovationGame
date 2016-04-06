package testPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Board {
	private int columns;
	private int rows;
	private int[][] tilemap;
	Color wallColour;
	private Color validPositionColour;
	public Board(Player[] players) {
		createTilemap();
		wallColour = new Color(204, 102, 0);
		validPositionColour = new Color(0, 153, 0);
		for(int i =0; i< players.length;i++){
			addPlayers(players[i]);
		}
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
				//8 represents the possible position 
				//

				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },
				{ 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5, 7, 5 },
				{ 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 },

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

	//remove all valid position marks and add player back if he was temporarily removed 
	public void clearBoard(int player) {
		// TODO Auto-generated method stub
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				if (tilemap[i][j] == 8) {
					tilemap[i][j] = 0;
					// repaint(i*50, j*50, 52,52);
					// revalidate();
					//
				}
				if (tilemap[i][j] == 10) {
					tilemap[i][j] = player;

				}
			}

		}

	}
	private void addPlayers(Player player){
		tilemap[player.getYpos()][player.getXpos()] = player.getPlayerID();
	}



	public boolean placeHorizontalWall(int checkX, int checkY, Player currentPlayer, Player[] players) {
		if (RuleChecker.checkHorizontalWall(tilemap, checkX - 1, checkY - 1, players)) {
			// if it is then it will change the array values to 6 which
			// will update everything
			tilemap[checkY - 1][checkX - 1] = currentPlayer.getWallID();
			tilemap[checkY - 1][checkX - 2] = currentPlayer.getWallID();
			tilemap[checkY - 1][checkX] = currentPlayer.getWallID();
			return true;
		}return false;
	}



	public boolean placeVerticalWall(int checkX, int checkY, Player currentPlayer, Player[] players) {

		if (RuleChecker.checkVerticalWall(tilemap, checkX - 1, checkY - 1, players)) {
			tilemap[checkY - 1][checkX - 1] = currentPlayer.getWallID()+1 ;
			tilemap[checkY - 2][checkX - 1] = currentPlayer.getWallID();
			tilemap[checkY][checkX - 1] = currentPlayer.getWallID();
			return true;
		}
		return false;
	}
	public boolean removeWall(int checkX, int checkY, Player currentPlayer, Player[] players){
		System.out.println("Y= "+ tilemap[checkY-1][checkX-1]);
		if(tilemap[checkY-1][checkX-1] != currentPlayer.getWallID()){
			for(int i=0; i<players.length; i++){
				if(tilemap[checkY -1][checkX-1] == players[i].getWallID()+1){
					players[i].addWall();
					tilemap[checkY - 1][checkX - 1] = 7;
					tilemap[checkY - 2][checkX - 1] = 5;
					tilemap[checkY][checkX - 1] = 5;
					return true;
				}
				if(tilemap[checkY -1][checkX-1] == players[i].getWallID()){
					players[i].addWall();
					tilemap[checkY - 1][checkX - 1] = 7;
					tilemap[checkY - 1][checkX - 2] = 5;
					tilemap[checkY - 1][checkX] = 5;
					return true;
				}
			}
		}
		return false;
		
	}


	public boolean movePlayer(int checkY, int checkX, Player currentPlayer) {
		// TODO Auto-generated method stub
		if (tilemap[checkX][checkY] == 8) {
			// will change all 10 which is the temp player location to 0
			// meaning empty space
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					if (tilemap[i][j] == 10) {
						tilemap[i][j] = 0;
					}
				}
			}
			// will make the new position hold the player number
			tilemap[checkX][checkY] = currentPlayer.getPlayerID();
			currentPlayer.setXpos(checkX);
			currentPlayer.setYpos(checkY);
			return true;
		}
		return false;
	}



	public Player checkFinished(Player[] players) {
		for(int i =0; i<players.length;i++){
			if(players[i].isFinished(players[i].getXpos(), players[i].getYpos())){
				return players[i];
			}
		}
		return null;
	}
}
