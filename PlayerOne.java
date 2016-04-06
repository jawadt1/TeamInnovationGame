package testPackage;

import java.awt.Color;

public class PlayerOne implements Player {
	private final int id = 1;
	public static final int WallID=11;
	private int walls;
	private String name;
	private Color playerColour;
	private boolean isAI;
	private int x;
	private int y;
	private boolean challenger;
	
	public PlayerOne(boolean isAI, int walls, int x, int y, boolean challenger) {
		this.walls = walls;
		this.name = "Player 1";
		this.playerColour = Color.GREEN;
		this.isAI = false;
		this.x = x;//8
		this.y= y;//0
		this.challenger = challenger;
	}
	//get player named used for array 
	public int getPlayerID(){
		return id;
	}
	//get the wall amount for player 
	@Override
	public int getWallAmount() {
		// TODO Auto-generated method stub
		return walls;
	}
//get the player name
	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return name;
	}
	//get the players colour to make on the board
	@Override
	public Color getPlayerColour() {
		// TODO Auto-generated method stub
		return playerColour;
	}
	//if the user uses a wall we minus it from the amount of walls he has 
	public void removeWall(){
		walls--;
	}
	@Override
	public boolean isAI() {
		// TODO Auto-generated method stub
		return isAI;
	}
	@Override
	public void setXpos(int x) {
		// TODO Auto-generated method stub
		this.x= x;
	}
	@Override
	public void setYpos(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}
	@Override
	public int getXpos() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public int getYpos() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getWallID() {
		// TODO Auto-generated method stub
		return WallID;
	}
	@Override
	public void addWall() {
		walls++;
		
	}
	@Override
	public boolean isFinished(int x, int y) {
		if(challenger){
			if(x == 16 && y ==16){
				return true;
			}
		}else{
			if(y == 16){
				return true;
			}
		}
		return false;
	}


}
