package testPackage;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class RuleChecker {
	private Board board;
	private int columns;
	private int rows;
	private Stack<int[]> confirmWallMove;

	public RuleChecker() {
		//board = new Board();
		//rows = board.getRow();
		//columns = board.getColoumn();

	}

	public static int[][] checkValidPositions(int[][] boardMap, int player, int y, int x, boolean checkblocked) {
		// double for loop for the array
		// for (int i = 0; i < columns; i++) {
		// for (int j = 0; j < rows; j++) {
		// find the player
		if (boardMap[x][y] == player) {
			// check if i +2 is 16 or less than it
			checkRightSide(boardMap, x, y, checkblocked);
			checkLeftSide(boardMap, x, y, checkblocked);
			checkBottom(boardMap, x, y, checkblocked);
			checkTop(boardMap, x, y, checkblocked);

			// }
			// }

		}
		return boardMap;
	}

	private static void checkRightSide(int[][] boardMap, int i, int j, boolean checkblocked) {
		if (j + 2 <= 16) {
			if (boardMap[i][j + 1] == 5) {
				if (boardMap[i][j + 2] != 0) {
					
					if (j + 4 <= 16) {
						if (boardMap[i][j + 3] == 5 && boardMap[i][j + 4] == 0) {
							boardMap[i][j + 4] = 8;
						}
					}
					// if j+2 is less than or equal to 16
					if (i - 2 >= 0 && j + 2 <= 16) {
						// check if row+1 and width +2 is free from a wall
						if (boardMap[i - 1][j + 2] == 5 && boardMap[i - 2][j + 2] == 0) {
							// turn pink
							boardMap[i - 2][j + 2] = 8;
						}
					}
					// check if row -2 is greater than 0
					if (i + 2 <= 16 && j + 2 <= 16) {
						// if coloumn +2 and row -1 is free from a wall
						if (boardMap[i + 1][j + 2] == 5 && boardMap[i + 2][j + 2] == 0) {
							// turn pink
							boardMap[i + 2][j + 2] = 8;
						}
					}
					if(checkblocked){
						boardMap[i][j + 2] = 8;
					}
				}
				if (boardMap[i][j + 2] == 0) {
					boardMap[i][j + 2] = 8;

				}
			}
		}
	}

	private static void checkLeftSide(int[][] boardMap, int i, int j, boolean checkblocked) {
		if (j - 2 >= 0) {
			if (boardMap[i][j - 1] == 5) {
				if (boardMap[i][j - 2] != 0) {
					
					if (j - 4 >= 0) {
						if (boardMap[i][j - 3] == 5 && boardMap[i][j - 4] == 0) {
							boardMap[i][j - 4] = 8;

						}
					}
					// if j+2 is less than or equal to 16
					if (i - 2 >= 0 && j - 2 >= 0) {
						// check if row+1 and width +2 is free from a wall
						if (boardMap[i - 1][j - 2] == 5 && boardMap[i - 2][j - 2] ==0) {
							// turn pink
							boardMap[i - 2][j - 2] = 8;
						}
					}
					// check if row -2 is greater than 0
					if (i + 2 <= 16 && j - 2 >= 0) {
						// if coloumn +2 and row -1 is free from a wall
						if (boardMap[i + 1][j - 2] == 5 && boardMap[i + 2][j - 2] ==0) {
							// turn pink
							boardMap[i + 2][j - 2] = 8;
						}
					}
					if(checkblocked){
						boardMap[i][j - 2] = 8;
					}
				}
				if (boardMap[i][j - 2] == 0) {
					boardMap[i][j - 2] = 8;

				}
			}
		}
	}

	private static void checkTop(int[][] boardMap, int i, int j, boolean checkblocked) {
		if (i - 2 >= 0) {
			if (boardMap[i - 1][j] == 5) {
				if (boardMap[i - 2][j] != 0) {
					
					if (i - 4 >= 0) {
						if (boardMap[i - 3][j] == 5 && boardMap[i - 4][j] == 0) {
							boardMap[i - 4][j] = 8;

						}
					}
					// if j+2 is less than or equal to 16
					if (j + 2 <= 16) {
						// check if row+1 and width +2 is free from a wall
						if (boardMap[i - 2][j + 1] == 5 && boardMap[i - 2][j + 2] == 0) {
							// turn pink
							boardMap[i - 2][j + 2] = 8;
						}
					}
					// check if row -2 is greater than 0
					if (j - 2 >= 0) {
						// if coloumn +2 and row -1 is free from a wall
						if (boardMap[i - 2][j - 1] == 5 && boardMap[i - 2][j - 2] ==0) {
							// turn pink
							boardMap[i - 2][j - 2] = 8;
						}
					}
					if(checkblocked){
						boardMap[i - 2][j] = 8;
					}
				}
				if (boardMap[i - 2][j] == 0) {
					boardMap[i - 2][j] = 8;

				}
			}
		}
	}

	private static void checkBottom(int[][] boardMap, int i, int j, boolean checkblocked) {
		if (i + 2 <= 16) {
			// if i +1 has no wall placed
			if (boardMap[i + 1][j] == 5) {
				// and i +2 has a player on
				if (boardMap[i + 2][j] != 0) {
					
					// check if i+4 is less than or equal to 16
					if (i + 4 <= 16) {
						// check if i+3 hasn't got a wall there
						if (boardMap[i + 3][j] == 5 && boardMap[i + 4][j] ==0) {
							// change it pink
							boardMap[i + 4][j] = 8;
						}
					}
					// if j+2 is less than or equal to 16
					if (j + 2 <= 16) {
						// check if row+1 and width +2 is free from a wall
						if (boardMap[i + 2][j + 1] == 5 && boardMap[i + 2][j + 2] ==0) {
							// turn pink
							boardMap[i + 2][j + 2] = 8;
						}
					}
					// check if row -2 is greater than 0
					if (j - 2 >= 0) {
						// if coloumn +2 and row -1 is free from a wall
						if (boardMap[i + 2][j - 1] == 5 && boardMap[i + 2][j - 2] == 0) {
							// turn pink
							boardMap[i + 2][j - 2] = 8;
						}
					}
					if(checkblocked){
						boardMap[i + 2][j] = 8;
					}
				}
				// if coloumn plus 2 is free then
				if (boardMap[i + 2][j] == 0) {
					// turn it pink
					boardMap[i + 2][j] = 8;

				}
			}
		}
	}

	public static boolean checkVerticalWall(int[][] boardmap, int checkX, int checkY, Player[] players) {
		if (checkX <= 15 && checkY <= 15 && checkX >= 1 && checkY >= 1) {
			if (boardmap[checkY][checkX] == 7) {
				if (boardmap[checkY - 1][checkX] == 5 && boardmap[checkY + 1][checkX] == 5) {
					
					if (checkifBlocked(boardmap, checkX, checkY, players, false)) {
					return true;
					}
				}
			}

		}
		return false;
	}

	public static boolean checkHorizontalWall(int[][] boardmap, int checkX, int checkY, Player[] players) {
		if (checkX <= 16 && checkY <= 16 && checkX >= 1 && checkY >= 1) {
			if (boardmap[checkY][checkX] == 7) {
				if (boardmap[checkY][checkX - 1] == 5 && boardmap[checkY][checkX + 1] == 5) {
					
					if (checkifBlocked(boardmap, checkX, checkY, players, true)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checkifBlocked(int[][] oldboard, int checkX, int checkY, Player[] players, boolean horizontal) {
		int[][] boardmap = new int[oldboard.length][oldboard[0].length];
		
		int x;
		int y;
		Stack<int[]> positions = new Stack<>();
		//ArrayList<int[]> positions = new ArrayList<>();
		boolean playerone = false;
		boolean playertwo = false;
		boolean found;
		for (int i = 0; i < players.length; i++) {
			found = false;
			for (int row = 0; row < oldboard.length; row++) {
				boardmap[row] = Arrays.copyOf(oldboard[row], oldboard[row].length);
			}
			if(horizontal){
				boardmap[checkY][checkX] = 6;
				boardmap[checkY][checkX - 1] = 6;
				boardmap[checkY][checkX + 1] = 6;
				
			}else{
				boardmap[checkY][checkX] = 6;
				boardmap[checkY - 1][checkX] = 6;
				boardmap[checkY + 1][checkX] = 6;
				
			}
			
			for (int row = 0; row < oldboard.length; row++) {
				for(int col=0; col <oldboard[0].length; col++){
					if(boardmap[row][col]== 1 || boardmap[row][col]== 2 || boardmap[row][col]== 3 || boardmap[row][col]== 4  ){
						boardmap[row][col]=0;
					}
					//System.out.print(boardmap[row][col] + ", ");
				}
				//System.out.println("");
			}
			
			x = players[i].getXpos();
			y = players[i].getYpos();
			//positions.clear();
			positions.clear();
			System.out.println(players[i].getPlayerName());
			System.out.println(checkY +", "+checkX +", "+boardmap[x][y] +", "+boardmap[checkY][checkX] +", "+ boardmap[8][1] + ", ");
			int[] currentpositions;
			int count = 0;
			currentpositions = new int[2];
			currentpositions[0] = y;
			currentpositions[1] = x;
			positions.add(currentpositions);
			
			while (!positions.isEmpty() && !found) {
				
				//boardmap = checkValidPositions(boardmap, players[i].getPlayerID(),y, x, true);
				checkRightSide(boardmap, y, x, false);
				checkLeftSide(boardmap, y, x, false);
				checkBottom(boardmap, y, x, false);
				checkTop(boardmap, y, x, false);
				for (int row = 0; row < oldboard.length; row++) {
					for(int col=0; col <oldboard[0].length; col++){
						if(boardmap[row][col]== 8  ){
							currentpositions = new int[2];
							currentpositions[1] = col;
							currentpositions[0] = row;
							positions.add(currentpositions);
							boardmap[row][col]=11;
						}
						//System.out.print(boardmap[row][col] + ", ");
					}
					//System.out.println("");
				}
				System.out.println("done");
				int[] temp = (int[]) positions.pop();
				x = temp[1];
				y = temp[0];

				if(players[i].isFinished(x, y)){
					System.out.println("all valid");
					found = true;
				}
				//count++;
			} 
			if(!found){
				System.out.println("one invalid");
				return false;
			}
		}

		for (int row = 0; row < oldboard.length; row++) {
			for(int col=0; col <oldboard[0].length; col++){
				System.out.print(boardmap[row][col] + ", ");
			}
			System.out.println("");
		}
		System.out.println("Valid for all");
		return true;
	}


}