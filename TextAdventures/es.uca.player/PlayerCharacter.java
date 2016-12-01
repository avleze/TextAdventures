import java.util.*;
import es.uca.item.*;

public class PlayerCharacter extends Player{

	private int xPosition;
	private int yPosition;


	public PlayerCharacter(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
		super(name, id, HealthPoints, Inventory, baseDamage);
		this.xPosition = xInitPosition;
		this.yPosition = yInitPosition;
	}

	public bool usePotion() {

			if(item Potion = this.inventory.stream().filter((i) -> i instanceof RecoveryItem).iterator().next() == null)
				return false
			else {
				this.setHealthPoints(this.getHealthPoints() + Potion.use());
				return true
			}
	}

	@Override
	public bool attack(Player Enemy) {

		if(this.hasWeapon()) {
			Item PlayerWeapon = this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
			int Armor = 0;
			if( Item EnemyArmor = Enemy.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next() != null) {
				Armor = EnemyArmor.getArmor();
			}

			Enemy.setHealthPoints( (Enemy.getHealthPoints() + Armor) - PlayerWeapon.use()*DAMAGE_TABLE[PlayerWeapon.type()][Enemy.type()]);
		} else {
			Enemy.setHealthPoints( (Enemy.getHealthPoints() + Armor) - this.baseDamage);
		}

		return Enemy.isAlive();
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
