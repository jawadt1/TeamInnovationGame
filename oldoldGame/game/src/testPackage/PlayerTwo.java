package testPackage;

import java.awt.Color;

public class PlayerTwo implements Player {
	private int walls;
	private String name;
	private Color playerColour;
	
	public PlayerTwo() {
		walls = 10;
		this.name = "Jawad";
		this.playerColour = Color.BLACK;
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
	public void setPlayerName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayerColour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getPlayerColour() {
		// TODO Auto-generated method stub
		return playerColour;
	}

	@Override
	public void getPlayerState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getNextPlayer() {
		// TODO Auto-generated method stub
		return new PlayerOne();
	}

}
