package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Item.ArmorItem;

import java.util.Set;


/**
 * es.uca.player.TextAdventures.Enemy
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public abstract class Enemy extends Player {

    static final int TYPE_WATER = 0;
    static final int TYPE_FIRE = 1;
    static final int TYPE_WIND = 2;
    static final int TYPE_EARTH = 3;
    static final int TYPE_TEPIC = 4;

    private int type;

    class TypeNotFoundException extends Exception {
    }

    public Enemy(String name, int id, int healthPoints, Set<Item> inventory, int baseDamage, int type) {
        super(name, id, healthPoints, inventory, baseDamage);
        if (type > TYPE_TEPIC || type < TYPE_WATER)
            throw TypeNotFoundException;
        this.type = type;
    }

    @Override
    public boolean attack(Player currentPlayer) {

        int Armor = 0;
        Item playerArmor = currentPlayer.inventory.stream().filter((i) -> i instanceof ArmorItem).iterator().next();
        if (playerArmor != null ) {
            Armor = playerArmor.use();
        }

        if (this.hasWeapon()) {
            Item EnemyWeapon = inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
            Item PlayerWeapon = currentPlayer.inventory.stream().filter((i) -> i instanceof WeaponItem).iterator().next();
            setHealthPoints((currentPlayer.getHealthPoints() + Armor) - EnemyWeapon.use() * DAMAGE_TABLE[EnemyWeapon.type()][PlayerWeapon.type()]);
        } else {
            setHealthPoints(currentPlayer.getHealthPoints() + Armor - this.baseDamage);
        }

        return currentPlayer.isAlive();
    }

    public int getType() {
        return this.type;
    }

}
