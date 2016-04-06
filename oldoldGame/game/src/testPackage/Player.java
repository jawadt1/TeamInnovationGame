package testPackage;

import java.awt.Color;

public interface Player {
	public int getWallAmount();
	public String getPlayerName();
	public void setPlayerName();
	public void setPlayerColour();
	public Color getPlayerColour();
	public void getPlayerState();
	public Player getNextPlayer();
	
}
