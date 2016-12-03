import java.util.Set;
package TextAdventures.es.uca.player;

/**
 * es.uca.player.Enemy
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public abstract class Enemy extends Player{

	static final int TYPE_WATER = 0;
	static final int TYPE_FIRE = 1;
	static final int TYPE_WIND = 2;
	static final int TYPE_EARTH = 3;
	static final int TYPE_TEPIC = 4;

	private int Type;

	class TypeNotFoundException extends Exception { }

	public Enemy(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int Type) {
		if(Type > TYPE_TEPIC || Type < TYPE_WATER)
			throw TypeNotFoundException;
		super(name, id, HealthPoints, Inventory, baseDamage);
		this.Type = Type;
	}

	@Override
	public bool attack(Player currentPlayer) {

		int Armor = 0;
		if ( Item PlayerArmor = currentPlayer.inventory.stream().filter( (i) -> i instanceof WeaponItem.iterator().next() ) != null) {
			Armor = PlayerArmor.getArmor();
		}

		if(this.hasWeapon()) {
			Item EnemyWeapon = inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next());
			Item PlayerWeapon = currentPlayer.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
			Enemy.setHealthPoints( (currentPlayer.getHealthPoints() + Armor) - EnemyWeapon.use()*DAMAGE_TABLE[EnemyWeapon.type()][PlayerWeapon.type()] )
		} else {
			Enemy.setHealthPoints( (currentPlayer.getHealthPoints() + Armor - this.baseDamage);
		}

		return currentPlayer.isAlive();
	}

}
