import java.util.Set;

public abstract class Enemy extends Player{

	public Enemy(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage) {
		super(name, id, HealthPoints, Inventory, baseDamage);
	}

}
