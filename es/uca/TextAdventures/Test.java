package es.uca.TextAdventures;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author juan
 */
public class  Test {

    public static void main(String args[]) throws WeaponItem.TypeNotFoundException, Enemy.TypeNotFoundException {

        Map mapa;
        MapLoader mapLoader;
        Set<Item> inventory = new HashSet<>();
        WeaponItem sword = new WeaponItem(5, 0, 0);
        RecoveryItem superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        RecoveryItem simplePotion = new SimpleRecoveryItem(5, 2);
        RecoveryItem anotherSimplePotion = new SimpleRecoveryItem(8, 3);
        MockDB mockDB = new MockDB();

        inventory.add(sword);
        inventory.add(superPotion);
        inventory.add(simplePotion);
        inventory.add(anotherSimplePotion);

        PlayerCharacter player = new PlayerCharacter("Heroe Anónimo", 1, 100, inventory, 10, 0, 0);

        DecisionEngine decisionEngine = new DecisionEngine(player);

        decisionEngine.run();
    }

}
