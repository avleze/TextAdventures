package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Input.ConsoleInput;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Output.NormalConsoleOutput;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DecisionEngine Class
 *
 * @author Juan Antonio Rodicio López
 */
public class DecisionEngine {

    private PlayerCharacter playerCharacter;
    private OutputManager output;
    private InputManager input;
    private MapLoader mapLoader;

    DecisionEngine(PlayerCharacter playerCharacter) throws WeaponItem.TypeNotFoundException, Enemy.TypeNotFoundException {
        this.playerCharacter = playerCharacter;
        this.mapLoader = new MapLoader(this.playerCharacter);
        this.input = new InputManager(new ConsoleInput());

    }

    private void startGame() throws WeaponItem.TypeNotFoundException, Enemy.TypeNotFoundException {
        boolean gameOver = false;
        Set<Action> playerActions;
        Set<InventoryAction> inventoryActions;
        ActionParameter actionParameters;
        Map map = mapLoader.loadFromFile("definitivemap.xml");

        while (!gameOver) {

            Room room = map.getRoom(playerCharacter.getXPosition(),
                    playerCharacter.getYPosition());

            playerActions = new LinkedHashSet<>();
            playerActions.add(new Heal("Heal.", playerCharacter));
            playerActions.add(new RunAway("Run away.", playerCharacter));
            playerActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));
            playerActions.add(new ShowInventory("Show inventory.", playerCharacter));

            inventoryActions = new LinkedHashSet<>();
            inventoryActions.add(new DropItem("Drop item", playerCharacter));
            inventoryActions.add(new SuperifyPotion("Superify potion", playerCharacter));
            inventoryActions.add(new HyperifyPotion("Hyperify potion", playerCharacter));

            actionParameters = new ActionParameter(output, input, playerActions, inventoryActions, playerCharacter, room.getEnemy());

            output.setCurrentRoom(room);
            output.show();
            int option;

            Action selectedAction = null;

            do {
                option = input.getInput();
            } while (option > room.getActions().size() || option <= 0);

            selectedAction = room.getAction(option - 1);

            selectedAction.run(actionParameters);

            if (!playerCharacter.isAlive()) {
                gameOver = true;
                output.showGameOverScreen();
            }
            
            if (room.isTreasureRoom()) {
                gameOver = true;
                output.showWinnerScreen();
            }

        }
    }

    public void run() {
        int menuOption;

        output = new OutputManager(new NormalConsoleOutput(), null, playerCharacter);

        do {
            output.showMenu();
            menuOption = input.getInput();
        } while (menuOption < 1 || menuOption > 3);

        switch (menuOption) {
            case 1:
                try {
                    startGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                // Needs to be implemented
                break;
            case 3:
                // Credits
                break;
        }

    }
}
