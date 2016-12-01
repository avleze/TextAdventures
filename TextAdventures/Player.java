
import java.util.*;

public abstract class Player {

	protected String name;
	protected int id;
	protected int healthPoints;
	protected Set<Item> inventory;
	protected int baseDamage;

	static final int DAMAGE_TABLE[4][4] = {
			{1, 2, 0.5, 1, 0.5},
			{0.5, 1, 2, 1, 0.5},
			{2, 0.5, 1, 2, 0.5},
			{0.5, 2, 0.5, 1, 0.5},
			{2, 2, 2, 2, 1}
	};

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

	public boolean attack(Player otherCharacter);
}
