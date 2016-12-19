package es.uca.TextAdventures.Player;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.WeaponItem;

import java.util.Set;


/**
 * ies.uca.playerCharacter.TextAdventures.Monster
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class Monster extends Enemy {

    public Monster(String name, int id, double healthPoints, Set<Item> inventory, int baseDamage, int type) throws Enemy.TypeNotFoundException{
        super(name, id, healthPoints, inventory, baseDamage, type);
    }


}
