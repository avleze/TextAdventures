package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.Item;

import java.util.Set;


/**
 * ies.uca.player.TextAdventures.Monster
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class Monster extends Enemy {

    public Monster(String name, int id, int healthPoints, Set<Item> inventory, int baseDamage, int type) {
        super(name, id, healthPoints, inventory, baseDamage, type);
    }


}
