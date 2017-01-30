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
import es.uca.TextAdventures.Output.NormalConsoleOutput;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Monster;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Test class for Action classes
 * @author Luis Rozo
*/

public class TestAction {
    private Set<Item> inventory;
    private WeaponItem sword;
    private RecoveryItem superPotion;
    private PlayerCharacter playerCharacter;
    private OutputManager output;
    private InputManager input;
    private MapLoader mapLoader;
    private Map map;
    private Room room;
    private Set<Action> playerActions;
    private Set<InventoryAction> inventoryActions;
    private ActionParameter actionParameters;

    @BeforeAll
    public void initAll() throws Exception {
        this.inventory = new HashSet<>();
        this.sword = new WeaponItem(5, 0, 0);
        this.superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        this.playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        this.output = new OutputManager(new NormalConsoleOutput(), null, playerCharacter);

        this.input = new InputManager(new ConsoleInput());

        this.mapLoader = new MapLoader(playerCharacter);

        this.map = mapLoader.loadFromFile("map.xml");

        this.room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        this.playerActions = new LinkedHashSet<>();
        this.playerActions.add(new HealAction("Heal.", playerCharacter));
        this.playerActions.add(new RunAwayAction("Run away.", playerCharacter));
        this.playerActions.add(new AttackAction("Attack.", playerCharacter, room.getEnemy()));
        this.playerActions.add(new ShowInventoryAction("Show inventory.", playerCharacter));

        this.inventoryActions = new LinkedHashSet<>();
        inventoryActions.add(new DropItemAction("Drop item", playerCharacter));
        inventoryActions.add(new SuperifyPotionAction("Superify potion", playerCharacter));
        inventoryActions.add(new HyperifyPotionAction("Hyperify potion", playerCharacter));

        this.actionParameters = new ActionParameter(output, input, playerActions, inventoryActions, playerCharacter, room.getEnemy());
    }

    @Test
    public void testActionParameter() {
        assert(actionParameters.getEnemy() == room.getEnemy());
        assert(actionParameters.getInput() == input);
        assert(actionParameters.getPlayerActions() == playerActions);
        assert(actionParameters.getPlayerCharacter() == playerCharacter);
        assert(actionParameters.getOutput() == output);
    }

    @Test
    public void testHeal() {
        HealAction heal = new HealAction("Test case",playerCharacter);
        heal.run(actionParameters);

        //The player has 100 health points and we simulate an use of a 10-recovery-health-points potion.
        assert(playerCharacter.getHealthPoints() == 110);
    }

    @Test
    public void testRunAway() {
        RunAwayAction runAway = new RunAwayAction("Test case",playerCharacter);
        runAway.run(actionParameters);

        //The player is not on battle yet.
        assert(!playerCharacter.isOnBattle());
    }

    @Test
    public void testStartBattleAction() throws Exception {
        Monster enemy = new Monster("Monster",4,50,inventory,10,2);
        StartBattleAction startBattleAction = new StartBattleAction("Test case",playerCharacter,enemy);
        startBattleAction.run(actionParameters);

        //The player is now on battle.
        assert(playerCharacter.isOnBattle());
    }

}
