package testPackage;

import java.awt.Color;

public class PlayerThree implements Player {
	private final int id = 3;
	public static final int WallID= 33;
	private int walls;
	private String name;
	private Color playerColour;
	private boolean isAI;
	private int x;
	private int y;
	private boolean challenger;
	
	public PlayerThree(boolean isAI, int walls, int x, int y, boolean challenger) {
		this.walls = walls;
		this.name = "Player 3";
		this.playerColour = Color.YELLOW;
		this.isAI = isAI;
		this.x = x;//8
		this.y= y;//16
		this.challenger = challenger;
	}
	public int getPlayerID(){
		return id;
	}
	@Override
	public int getWallAmount() {
		// TODO Auto-generated method stub
		return walls;
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Color getPlayerColour() {
		// TODO Auto-generated method stub
		return playerColour;
	}

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
		this.x = x;
	}
	@Override
	public void setYpos(int y) {
		// TODO Auto-generated method stub
		this.y =y;
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
			if(x == 0 && y ==0){
				return true;
			}
		}else{
			if(x == 0){
				return true;
			}
		}
		return false;
	}
	

}
