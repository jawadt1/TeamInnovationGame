package testPackage;
//Testing 
import java.util.ArrayList;
import java.util.Random;

public class AI {
	private Random findmove;

	public AI() {
		findmove = new Random();
	}

	public int[][] makemove(int[][] board, Player player, int x, int y) {
		//		int move = findmove.nextInt(100);

		//		if (move <= 70) {
		//
		//			if (board[x - 1][y] == 5 && x-2 > 0) {
		//				board[x][y] = 0;
		//				board[x - 2][y] = player.getPlayerID();
		//			}else{
		//				makemove(board, player, x, y);
		//			}
		//
		//		} else if (move <= 80 && move > 70) {
		//			if (board[x][y + 1] == 5 && y+1 <15) {
		//				board[x][y] = 0;
		//				board[x][y + 2] = player.getPlayerID();
		//			}else{
		//				makemove(board, player, x, y);
		//			}
		//		}else if (move <= 90 && move > 80) {
		//			if (board[x][y - 1] == 5 &&y-1 <0) {
		//				board[x][y] = 0;
		//				board[x][y - 2] = player.getPlayerID();
		//			}else{
		//				makemove(board, player, x, y);
		//			}
		//		}else{
		//			if (board[x + 1][y] == 5 && x+1 <16) {
		//				board[x][y] = 0;
		//				board[x + 2][y] = player.getPlayerID();
		//			}else{
		//				makemove(board, player, x, y);
		//			}
		//		}
		board = RuleChecker.checkValidPositions(board, player.getPlayerID(), x, y, false);
		ArrayList<int[]> validmoves = new ArrayList<int[]>();
		validmoves.clear();
		int[] currentmove = null;
		for (int row = 0; row < board.length; row++) {
			for(int col=0; col <board[0].length; col++){
				if(board[row][col]==8){
					board[row][col]=0;
					currentmove = new int[2];
					currentmove[0] = row;
					currentmove[1] = col;
					validmoves.add(currentmove);
				}
			}
			//System.out.println("");
		}
		int move = findmove.nextInt(validmoves.size());
		int[] moveto = validmoves.get(move);
		board[moveto[0]][moveto[1]] = player.getPlayerID();
		board[x][y] = 0;
		player.setXpos(moveto[0]);
		player.setYpos(moveto[1]);
		
		return board;
	}
	public boolean placeWall(){
		int number = findmove.nextInt(10);
		if(number < 6){
			return false;
		}else{
			return true;
		}


	}

	public int placewall() {
		// TODO Auto-generated method stub
		int x = findmove.nextInt(16);

		if(x%2 == 0 ){
			placewall();
		}
		return x;

	}
	public boolean placeHwall() {
		int x = findmove.nextInt(2);
		if (x == 1) {
			return true;
		}
		return false;
	}
	public int[][] aiMakeMove(int[][] tilemap, Player currentPlayer, Player[] players) {
		if (placeWall() && currentPlayer.getWallAmount() != 0) {
			if (placeHwall()) {
				int x = placewall();
				int y =  placewall();
				while (!RuleChecker.checkHorizontalWall(tilemap, y, x, players)) {
					x = placewall();
					y =  placewall();
				}
				System.out.println(x + ", " + y);
				// if it is then it will change the array values to 6 which
				// will update everything
				if(RuleChecker.checkHorizontalWall(tilemap, y, x, players)) {
					tilemap[x][y] = currentPlayer.getWallID();
					tilemap[x][y - 1] = currentPlayer.getWallID();
					tilemap[x][y + 1] = currentPlayer.getWallID();
				}
				//horizontalWall = false;
				currentPlayer.removeWall();
				// currentPlayer.removeWall();
				// getNextPlayer();
				// player++;
				return tilemap;
			} else {
				int x = placewall();
				int y = placewall();
				while (!RuleChecker.checkVerticalWall(tilemap, y, x, players)) {
					x = placewall();
					y = placewall();
				}

				// if it is then it will change the array values to 6 which
				// will update everything
				tilemap[x][y] = currentPlayer.getWallID();
				tilemap[x - 1][y] = currentPlayer.getWallID();
				tilemap[x + 1][y] = currentPlayer.getWallID();
				//horizontalWall = false;
				currentPlayer.removeWall();
				return tilemap;
			}
		} else {

			int x = 0/*currentPlayer.getXpos()*/ ;
			int y = 0/*currentPlayer.getYpos()*/ ;
			for (int i = 0; i < tilemap.length; i++) {
				for (int j = 0; j < tilemap[0].length; j++) {
					if (tilemap[i][j] == currentPlayer.getPlayerID()) {
						x = i;
						y = j;
					}
				}
			}
			int[][] tempboard = RuleChecker.checkValidPositions(tilemap, currentPlayer.getPlayerID(), y, x, false);
			tilemap = makemove(tilemap, currentPlayer, x, y);
			return tilemap;


		}
	}
}
