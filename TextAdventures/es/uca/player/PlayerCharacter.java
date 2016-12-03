import java.util.*;
import es.uca.item.*;
package TextAdventures.es.uca.player;

/**
 * es.uca.player.PlayerCharacter
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class PlayerCharacter extends Player{

	private int xPosition;
	private int yPosition;


	public PlayerCharacter(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
		super(name, id, HealthPoints, Inventory, baseDamage);
		this.xPosition = xInitPosition;
		this.yPosition = yInitPosition;
	}

	public bool usePotion() {
			Iterator<Item> itPotion = this.inventory.stream().filter((i) -> i instanceof RecoveryItem).iterator();
			Item Potion = itPotion.next();
		
			if(Potion  == null)
				return false
			else {
				this.setHealthPoints(this.getHealthPoints() + Potion.use());
				return true
			}
	}

	@Override
	public bool attack(Player Enemy) {
		int Armor = 0;
		if(this.hasWeapon()) {
			WeaponItem PlayerWeapon = this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
			ArmorItem EnemyArmor = Enemy.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();

			if(EnemyArmor  != null) {
				Armor = EnemyArmor.getArmor();
			}

			Enemy.setHealthPoints( (Enemy.getHealthPoints() + Armor) - PlayerWeapon.use()*DAMAGE_TABLE[PlayerWeapon.getType()][Enemy.getType()]);
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
