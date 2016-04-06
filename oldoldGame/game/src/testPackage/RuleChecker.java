package testPackage;

import java.util.ArrayList;
import java.util.Stack;

public class RuleChecker {
	private Board board;
	private int columns;
	private int rows;
	private Stack confirmWallMove;
	public RuleChecker() {
		board = new Board();
		rows = board.getRow();
		columns = board.getColoumn();
	}
	public int[][] checkValidPositions(int[][] boardMap, int player){
		//double for loop for the array
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				//find the player
				if (boardMap[i][j] == player) {
					//check if i +2 is 16 or less than it
					checkRightSide(boardMap, i, j);
					checkLeftSide(boardMap, i, j);
					checkBottom(boardMap, i, j);
					checkTop(boardMap, i, j);

				}
			}

		} return boardMap;
	}
	private void checkRightSide(int[][] boardMap, int i, int j) {
		if (j + 2 <= 16) {
			if (boardMap[i][j + 1] == 5) {
				if (boardMap[i][j + 2] != 0) {
					if (j + 4 <= 16) {
						if (boardMap[i][j + 3] == 5 && boardMap[i][j + 3] == 0) {
							boardMap[i][j + 4] = 8;

						}
					}
					//if j+2 is less than or equal to 16
					if (i - 2 >=0 && j + 2 <=16) {
						//check if row+1 and width +2 is free from a wall
						if (boardMap[i- 1][j + 2] == 5 && boardMap[i-2][j + 2] == 0) {
							//turn pink
							boardMap[i- 2][j + 2] = 8;
						}
					}
					//check if row -2 is greater than 0
					if (i - 2 >=0 && j - 2 >=0 ) {
						//if coloumn +2 and row -1 is free from a wall
						if (boardMap[i+ 1][j + 2] == 5) {
							//turn pink
							boardMap[i+ 2][j + 2] = 8;
						}
					}
				}
				if (boardMap[i][j + 2] == 0) {
					boardMap[i][j + 2] = 8;

				}
			}
		}
	}
	private void checkLeftSide(int[][] boardMap, int i, int j) {
		if (j - 2 >= 0) {
			if (boardMap[i][j - 1] == 5) {
				if (boardMap[i][j - 2] != 0) {
					if (j - 4 >= 0) {
						if (boardMap[i][j - 3] == 5) {
							boardMap[i][j - 4] = 8;

						}
					}
					//if j+2 is less than or equal to 16
					if (i + 2 <= 16 && j + 2 <=16) {
						//check if row+1 and width +2 is free from a wall
						if (boardMap[i- 1][j - 2] == 5) {
							//turn pink
							boardMap[i- 2][j - 2] = 8;
						}
					}
					//check if row -2 is greater than 0
					if (i + 2 <=16 && j - 2 >=0 ) {
						//if coloumn +2 and row -1 is free from a wall
						if (boardMap[i+ 1][j - 2] == 5) {
							//turn pink
							boardMap[i+ 2][j - 2] = 8;
						}
					}
				}
				if (boardMap[i][j - 2] == 0) {
					boardMap[i][j - 2] = 8;

				}
			}
		}
	}
	private void checkTop(int[][] boardMap, int i, int j) {
		if (i - 2 >= 0) {
			if (boardMap[i - 1][j] == 5) {
				if (boardMap[i - 2][j] != 0) {
					if (i - 4 >= 0) {
						if (boardMap[i - 3][j] == 5) {
							boardMap[i - 4][j] = 8;

						}
					}
					//if j+2 is less than or equal to 16
					if (j + 2 <= 16) {
						//check if row+1 and width +2 is free from a wall
						if (boardMap[i- 2][j + 1] == 5) {
							//turn pink
							boardMap[i- 2][j + 2] = 8;
						}
					}
					//check if row -2 is greater than 0
					if (j - 2 >=0) {
						//if coloumn +2 and row -1 is free from a wall
						if (boardMap[i- 2][j - 1] == 5) {
							//turn pink
							boardMap[i- 2][j - 2] = 8;
						}
					}

				}
				if (boardMap[i - 2][j] == 0) {
					boardMap[i - 2][j] = 8;

				}
			}
		}
	}
	private void checkBottom(int[][] boardMap, int i, int j) {
		if (i + 2 <= 16) {
			//if i +1 has no wall placed
			if (boardMap[i + 1][j] == 5) {
				//and i +2 has a player on 
				if (boardMap[i + 2][j] != 0) {
					//check if i+4 is less than or equal to 16
					if (i + 4 <= 16) {
						//check if i+3 hasn't got a wall there
						if (boardMap[i + 3][j] == 5) {
							//change it pink
							boardMap[i + 4][j] = 8;
						}
					}
					//if j+2 is less than or equal to 16
					if (j + 2 <= 16) {
						//check if row+1 and width +2 is free from a wall
						if (boardMap[i+ 2][j + 1] == 5) {
							//turn pink
							boardMap[i+ 2][j + 2] = 8;
						}
					}
					//check if row -2 is greater than 0
					if (j - 2 >=0) {
						//if coloumn +2 and row -1 is free from a wall
						if (boardMap[i+ 2][j - 1] == 5) {
							//turn pink
							boardMap[i+ 2][j - 2] = 8;
						}
					}

				}
				//if coloumn plus 2 is free then 
				if (boardMap[i + 2][j] == 0) {
					//turn it pink
					boardMap[i + 2][j] = 8;

				}
			}
		}
	}
	public boolean checkVerticalWall(int[][] boardmap, int checkX, int checkY) {
		if (checkX <= 15 && checkY <= 15 && checkX >=0 && checkY >=0) {
			if (boardmap[checkY][checkX] == 7) {
				if (boardmap[checkY - 1][checkX] == 5 && boardmap[checkY + 1][checkX] == 5) {
					return true;
					
				}
			}

		}return false;
	}
	public boolean checkHorizontalWall(int[][] boardmap, int checkX, int checkY) {
		if (checkX <= 16 && checkY <= 16 && checkX >= 0 && checkY >= 0) {
			if (boardmap[checkY][checkX] == 7) {
				if (boardmap[checkY][checkX - 1] == 5 && boardmap[checkY][checkX + 1] == 5) {
					return true;
				}
			}
		}return false;
	}
	public boolean checkWallMove(int[][] boardmap, int player){
		int[][] temporaryBoard = boardmap;
		int currentPlayer = player;
		int x=0;
		int y=0;
		confirmWallMove = new Stack<>();
		ArrayList validMoves = new ArrayList<>();
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				if(temporaryBoard[i][j] == currentPlayer){
					x= i;
					y = j;
				}
			}
		}
		boolean found = false;
		confirmWallMove.push(temporaryBoard[x][y]);
		while(!found && !confirmWallMove.isEmpty()){
			
		}
		
		
		return false;
		
	}
}