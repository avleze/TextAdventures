package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.ArmorItem;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.WeaponItem;

import java.util.Set;


/**
 * es.uca.playerCharacter.TextAdventures.Enemy
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public abstract class Enemy extends Player {

    private static final int TYPE_WATER = 0;
    private static final int TYPE_FIRE = 1;
    private static final int TYPE_WIND = 2;
    private static final int TYPE_EARTH = 3;
    private static final int TYPE_TEPIC = 4;

    private int type;

    public Enemy(String name, int id, double healthPoints, Set<Item> inventory, int baseDamage, int type) throws TypeNotFoundException {
        super(name, id, healthPoints, inventory, baseDamage);
        if (type > TYPE_TEPIC || type < TYPE_WATER) {
            TypeNotFoundException exceptionType = new TypeNotFoundException();
            throw exceptionType;
        }
        this.type = type;
    }

    @Override
    public boolean attack(Player currentPlayer) {

        int armor = 0;

        if (this.hasWeapon()) {
            if (currentPlayer.hasArmor()) {

                WeaponItem playerWeapon = (WeaponItem) this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
                ArmorItem enemyArmor = (ArmorItem) currentPlayer.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();


                if (enemyArmor.isBroken())
                    currentPlayer.setHealthPoints(currentPlayer.getHealthPoints() - playerWeapon.use() * DAMAGE_TABLE[this.getType()][playerWeapon.getType()]);
                else
                    enemyArmor.decrease(playerWeapon.use() * DAMAGE_TABLE[playerWeapon.getType()][((Enemy) currentPlayer).getType()]);

            } else {
                WeaponItem playerWeapon = (WeaponItem) this.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
                currentPlayer.setHealthPoints(currentPlayer.getHealthPoints() - playerWeapon.use() * DAMAGE_TABLE[this.getType()][playerWeapon.getType()]);
            }
        } else {
            if (currentPlayer.hasArmor()) {

                ArmorItem enemyArmor = (ArmorItem) currentPlayer.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();

                if (enemyArmor.isBroken())
                    currentPlayer.setHealthPoints(currentPlayer.getHealthPoints() - this.baseDamage);
                else
                    enemyArmor.decrease(this.baseDamage);

            } else
                currentPlayer.setHealthPoints(currentPlayer.getHealthPoints() - this.baseDamage);
        }

        return currentPlayer.isAlive();
    }

    public int getType() {
        return this.type;
    }

    @Override
    public boolean usePotion() {
        this.setHealthPoints(this.getHealthPoints() + 10);
        return true;
    }


    public class TypeNotFoundException extends Exception {
    }

}
