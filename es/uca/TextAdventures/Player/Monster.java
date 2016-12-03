package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.Item;
import es.uca.item.*;

import java.util.Set;


/**
 * ies.uca.player.TextAdventures.Monster
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class Monster extends Enemy {

    public Monster(String name, int id, int HealthPoints, Set<Item> Inventory, int baseDamage, int Type) {
        super(name, id, HealthPoints, Inventory, baseDamage, Type);
    }


}
