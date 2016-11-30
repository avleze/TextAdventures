
import java.util.*;

public abstract class Player {

	protected String name;
	protected int id;
	protected int healthPoints;
	protected Set<Item> inventory;
	protected int baseDamage;
	
	public Player(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage) {
		this.name = name;
		this.id = id;
		this.healthPoints = HealthPoints;
		this.inventory = Inventory;
		this.baseDamage = baseDamage;
	}
	
	
	public int getHealthPoints() {
		return healthPoints;
	}
	
	public int getId() {
		return id;
	}
	
	public Set<Item> getInventory() {
		return inventory;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public boolean isAlive() {
		return healthPoints > 0;
	}
	
	public boolean hasWeapon() {
		
		return inventory.stream().anyMatch((i) -> i instanceof WeaponItem);
	
	}
	
	public boolean attack(Player Enemy) {
		
		if(this.hasWeapon()) {
			Item It = inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
			Enemy.setHealthPoints(Enemy.getHealthPoints() - It.use());
		} else {
			Enemy.setHealthPoints(Enemy.getHealthPoints() - this.baseDamage);
		}
		
		return Enemy.isAlive();
	}
}
