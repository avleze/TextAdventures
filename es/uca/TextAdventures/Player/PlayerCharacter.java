package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.ArmorItem;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;

import java.util.Iterator;
import java.util.Set;


/**
 * es.uca.playerCharacter.TextAdventures.PlayerCharacter
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class PlayerCharacter extends Player {

    private int xPosition;
    private int yPosition;
    private boolean onBattle;

    public PlayerCharacter(String name, int id, double HealthPoints, Set<Item> Inventory, int baseDamage, int xInitPosition, int yInitPosition) {
        super(name, id, HealthPoints, Inventory, baseDamage);
        this.xPosition = xInitPosition;
        this.yPosition = yInitPosition;
        this.onBattle = false;
    }

    @Override
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
            if (enemy.hasArmor()) {
                WeaponItem playerWeapon = (WeaponItem) this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
                ArmorItem enemyArmor = (ArmorItem) enemy.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();


                if (enemyArmor.isBroken())
                    enemy.setHealthPoints(enemy.getHealthPoints() - playerWeapon.use() * DAMAGE_TABLE[playerWeapon.getType()][((Enemy) enemy).getType()]);
                else
                    enemyArmor.decrease(playerWeapon.use() * DAMAGE_TABLE[playerWeapon.getType()][((Enemy) enemy).getType()]);
            } else {
                WeaponItem playerWeapon = (WeaponItem) this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
                enemy.setHealthPoints(enemy.getHealthPoints() - playerWeapon.use() * DAMAGE_TABLE[playerWeapon.getType()][((Enemy) enemy).getType()]);
            }
        } else {
            if (enemy.hasArmor()) {
                ArmorItem enemyArmor = (ArmorItem) enemy.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();

                if (enemyArmor.isBroken())
                    enemy.setHealthPoints(enemy.getHealthPoints() - this.baseDamage);
                else
                    enemyArmor.decrease(this.baseDamage);
            } else
                enemy.setHealthPoints(enemy.getHealthPoints() - this.baseDamage);
        }

        return enemy.isAlive();
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void moveLeft() {
        this.xPosition -= 1;
    }

    public void moveRight() {
        this.xPosition += 1;
    }

    public void moveUp() {
        this.yPosition -= 1;
    }

    public void moveDown() {
        this.yPosition += 1;
    }

    public boolean isOnBattle() {
        return onBattle;
    }

    public void enableBattle() {
        this.onBattle = true;
    }

    public void disableBattle() {
        this.onBattle = false;
    }

}
