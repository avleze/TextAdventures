package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Input.ConsoleInput;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Map;
import es.uca.TextAdventures.MapLoader;
import es.uca.TextAdventures.Output.ConsoleOutput;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by luisrozo on 19/01/17.
 */
public class TestEnemyBehaviour {

    @Test
    public void testAggressiveEnemyBehaviour() throws Exception {
        Set<Item> inventory = new HashSet<>();
        WeaponItem sword = new WeaponItem(5, 0, 0);
        RecoveryItem superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        PlayerCharacter playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        OutputManager output = new OutputManager(new ConsoleOutput(), null, playerCharacter);

        InputManager input = new InputManager(new ConsoleInput());

        MapLoader mapLoader = new MapLoader(playerCharacter);

        Map map = mapLoader.loadFromFile("map.xml");

        Room room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        Attack attackAction = new Attack("Attack.", playerCharacter, room.getEnemy());

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Heal("Heal.", playerCharacter));
        playerActions.add(new RunAway("Run away.", playerCharacter));
        playerActions.add(attackAction);
        Set<BattleAction> enemyActions;
        enemyActions = new LinkedHashSet<>();
        enemyActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));
        AggressiveEnemyBehaviour aggressive = new AggressiveEnemyBehaviour(enemyActions);

        assert(aggressive.getAction() instanceof Attack);
    }

    @Test
    public void testHealerEnemyBehaviour() throws Exception {
        Set<Item> inventory = new HashSet<>();
        WeaponItem sword = new WeaponItem(5, 0, 0);
        RecoveryItem superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        PlayerCharacter playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        OutputManager output = new OutputManager(new ConsoleOutput(), null, playerCharacter);

        InputManager input = new InputManager(new ConsoleInput());

        MapLoader mapLoader = new MapLoader(playerCharacter);

        Map map = mapLoader.loadFromFile("map.xml");

        Room room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        Attack attackAction = new Attack("Attack.", playerCharacter, room.getEnemy());

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Heal("Heal.", playerCharacter));
        playerActions.add(new RunAway("Run away.", playerCharacter));
        playerActions.add(attackAction);
        Set<BattleAction> enemyActions;
        enemyActions = new LinkedHashSet<>();
        enemyActions.add(new Heal("Heal.", room.getEnemy()));
        HealerEnemyBehaviour healer = new HealerEnemyBehaviour(enemyActions);

        assert(healer.getAction() instanceof Heal);
    }

    @Test
    public void testRandomEnemyBehaviour() throws Exception {
        Set<Item> inventory = new HashSet<>();
        WeaponItem sword = new WeaponItem(5, 0, 0);
        RecoveryItem superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        PlayerCharacter playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        OutputManager output = new OutputManager(new ConsoleOutput(), null, playerCharacter);

        InputManager input = new InputManager(new ConsoleInput());

        MapLoader mapLoader = new MapLoader(playerCharacter);

        Map map = mapLoader.loadFromFile("map.xml");

        Room room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        Attack attackAction = new Attack("Attack.", playerCharacter, room.getEnemy());

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Heal("Heal.", playerCharacter));
        playerActions.add(new RunAway("Run away.", playerCharacter));
        playerActions.add(attackAction);
        Set<BattleAction> enemyActions;
        enemyActions = new LinkedHashSet<>();
        enemyActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));
        enemyActions.add(new Heal("Heal.", room.getEnemy()));
        RandomEnemyBehaviour random = new RandomEnemyBehaviour(enemyActions);

        assert(random.getAction() instanceof Attack || random.getAction() instanceof Heal);
    }
}
