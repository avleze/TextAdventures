package es.uca.TextAdventures.Action;

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
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.Monster;
import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Test class for Action classes
 * @author Luis Rozo
 */
public class TestAction {

    @Test
    public void testActionParameter() throws Exception{
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

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Heal("Heal.", playerCharacter));
        playerActions.add(new RunAway("Run away.", playerCharacter));
        playerActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));

        ActionParameter actionParameters = new ActionParameter(output, input, playerActions, playerCharacter, room.getEnemy());

        assert(actionParameters.getEnemy() == room.getEnemy());
        assert(actionParameters.getInput() == input);
        assert(actionParameters.getPlayerActions() == playerActions);
        assert(actionParameters.getPlayerCharacter() == playerCharacter);
        assert(actionParameters.getOutput() == output);
    }

    @Test
    public void testHeal() throws Exception {
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

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Heal("Heal.", playerCharacter));

        ActionParameter actionParameters = new ActionParameter(output, input, playerActions, playerCharacter, room.getEnemy());
        Heal heal = new Heal("Test case",playerCharacter);
        heal.run(actionParameters);

        //The player has 100 health points and we simulate an use of a 10-recovery-health-points potion.
        assert(playerCharacter.getHealthPoints() == 110);
    }

    @Test
    public void testRunAway() throws Exception {
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

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new RunAway("Run away.", playerCharacter));

        ActionParameter actionParameters = new ActionParameter(output, input, playerActions, playerCharacter, room.getEnemy());
        RunAway runAway = new RunAway("Test case",playerCharacter);
        runAway.run(actionParameters);

        //The player is not on battle yet.
        assert(!playerCharacter.isOnBattle());
    }

    @Test
    public void testStartBattleAction() throws Exception {
        Set<Item> inventory = new HashSet<>();
        WeaponItem sword = new WeaponItem(5, 0, 0);
        RecoveryItem superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        PlayerCharacter playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);
        Monster enemy = new Monster("Monster",4,50,inventory,10,2);

        OutputManager output = new OutputManager(new ConsoleOutput(), null, playerCharacter);

        InputManager input = new InputManager(new ConsoleInput());

        MapLoader mapLoader = new MapLoader(playerCharacter);

        Map map = mapLoader.loadFromFile("map.xml");

        Room room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        Set<Action> playerActions;
        playerActions = new LinkedHashSet<>();
        playerActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));

        ActionParameter actionParameters = new ActionParameter(output, input, playerActions, playerCharacter, room.getEnemy());
        StartBattleAction startBattleAction = new StartBattleAction("Test case",playerCharacter,enemy);
        startBattleAction.run(actionParameters);

        //The player is now on battle.
        assert(playerCharacter.isOnBattle());
    }

}