package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.ArmorItem;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;

import java.util.Iterator;
import java.util.Set;


/**
 * es.uca.player.TextAdventures.PlayerCharacter
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class PlayerCharacter extends Player {

    private int xPosition;
    private int yPosition;


    public PlayerCharacter(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
        super(name, id, HealthPoints, Inventory, baseDamage);
        this.xPosition = xInitPosition;
        this.yPosition = yInitPosition;
    }

    public boolean usePotion() {
        Iterator<Item> itPotion = this.inventory.stream().filter((i) -> i instanceof RecoveryItem).iterator();
        Item potion = itPotion.next();

        if (potion == null)
            return false;
        else {
            this.setHealthPoints(this.getHealthPoints() + potion.use());
            return true;
        }
    }

    @Override
    public boolean attack(Player enemy) {
        int armor = 0;
        if (this.hasWeapon()) {
            WeaponItem playerWeapon = (WeaponItem) this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
            ArmorItem enemyArmor = (ArmorItem) enemy.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();

            if (enemyArmor != null) {
                armor = enemyArmor.use();
            }

            enemy.setHealthPoints((enemy.getHealthPoints() + armor) - playerWeapon.use() * DAMAGE_TABLE[playerWeapon.getType()][ enemy.getType()]);
        } else {
            enemy.setHealthPoints((enemy.getHealthPoints() + armor) - this.baseDamage);
        }

        return enemy.isAlive();
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
