import java.util.*;

public class PlayerCharacter extends Player{

	private int xPosition;
	private int yPosition;
	
	
	public PlayerCharacter(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
		super(name, id, HealthPoints, Inventory, baseDamage);
		this.xPosition = xInitPosition;
		this.yPosition = yInitPosition;
	}
	
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
}
