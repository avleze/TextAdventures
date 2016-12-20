package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Input.ConsoleInput;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Output.ConsoleOutput;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.HashSet;
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
        ActionParameter actionParameters;
        Map map = mapLoader.loadFromFile("map.xml");

        while (!gameOver) {

            Room room = map.getRoom(playerCharacter.getXPosition(),
                    playerCharacter.getYPosition());

            playerActions = new LinkedHashSet<>();
            playerActions.add(new Heal("Heal.", playerCharacter));
            playerActions.add(new RunAway("Run away.", playerCharacter));
            playerActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));

            actionParameters = new ActionParameter(output, input, playerActions, playerCharacter, room.getEnemy());

            output.setCurrentRoom(room);
            output.show();

            Action selectedAction = room.getAction(input.getInput() - 1);

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

        output = new OutputManager(new ConsoleOutput(), null, playerCharacter);

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
