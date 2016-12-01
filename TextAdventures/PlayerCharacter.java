import java.util.*;

public class PlayerCharacter extends Player{

	private int xPosition;
	private int yPosition;


	public PlayerCharacter(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
		super(name, id, HealthPoints, Inventory, baseDamage);
		this.xPosition = xInitPosition;
		this.yPosition = yInitPosition;
	}

	public bool usePotion() {

			if(item Potion = inventory.stream().filter((i) -> i instanceof RecoveryItem).iterator().next() == null)
				return false
			else {
				this.setHealthPoints(this.getHealthPoints() + Potion.use());
				return true
			}
	}

	@Override
	public bool attack(Player Enemy) {

		if(this.hasWeapon()) {
			Item PlayerWeapon = inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
			Enemy.setHealthPoints(Enemy.getHealthPoints() - PlayerWeapon.use()*DAMAGE_TABLE[PlayerWeapon.type()][Enemy.type()];
		} else {
			Enemy.setHealthPoints(Enemy.getHealthPoints() - this.baseDamage);
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
