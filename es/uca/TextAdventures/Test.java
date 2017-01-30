package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

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
        MockDB mockDB = new MockDB();

        inventory.add(sword);
        inventory.add(superPotion);

        PlayerCharacter player = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        DecisionEngine decisionEngine = new DecisionEngine(player);

        decisionEngine.run();

        /*mapLoader = new MapLoader(player);

        mapa = mapLoader.loadFromFile("map.xml");

        System.out.println("Tamaño: " + mapa.getWidth() + "x" + mapa.getHeight());
        Room[][] room = mapa.getRooms();

        System.out.println(room[0][0].getMessage().getCaption());
        System.out.println(room[0][0].getMessage().getMessage());
        for(Action action : room[0][0].getActions()){
            System.out.println(action.getDescription());
        }
        System.out.println(room[0][0].getEnemy().getName());
        
        System.out.println(room[0][1].getMessage().getCaption());
        System.out.println(room[0][1].getMessage().getMessage());
        System.out.println(room[0][1].getActions().size());
        System.out.println(room[0][1].getEnemy().getName());
        
        System.out.println(room[1][0].getMessage().getCaption());
        System.out.println(room[1][0].getMessage().getMessage());
        System.out.println(room[1][0].getActions().size());
        System.out.println(room[1][0].getEnemy().getName());
        
        player.setYPosition(1);
        
        mockDB.saveCurrentGame(player);
        
        PlayerCharacter savedPlayer = mockDB.loadLastGame();
        
        System.out.println("Name: "+savedPlayer.getName());
        System.out.println("ID: "+savedPlayer.getId());
        System.out.println("Health Points: "+savedPlayer.getHealthPoints());
        for(Item item : savedPlayer.getInventory()){
            System.out.println(item.getId());
        }
        
        System.out.println("Base Damage: "+savedPlayer.getBaseDamage());
        System.out.println("X position: "+savedPlayer.getXPosition());
        System.out.println("Y position: "+savedPlayer.getYPosition());*/
    }

}
