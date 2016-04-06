package testPackage;

import java.awt.Color;

public interface Player {
	
	
	public int getWallAmount();
	public String getPlayerName();
	public Color getPlayerColour();
	public int getPlayerID();
	public void removeWall();
	public boolean isAI();
	public void setXpos(int x);
	public void setYpos(int y);
	public int getXpos();
	public int getYpos();
	public int getWallID();
	public void addWall();
	public boolean isFinished(int x, int y);
}
