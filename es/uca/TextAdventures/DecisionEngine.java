package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Output.*;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * DecisionEngine Class
 *
 * @author Juan Antonio Rodicio López
 */
public class DecisionEngine {

    PlayerCharacter playerCharacter;
    Map map;
    ConsoleOutput console = new ConsoleOutput(800,480);
    OutputManager output;
    InputManager input;
    MapLoader mapLoader;

    DecisionEngine(PlayerCharacter playerCharacter) throws WeaponItem.TypeNotFoundException{
        this.playerCharacter = playerCharacter;
        this.map = mapLoader.loadFromFile("mapa.xml");
    }

    void run() {

        boolean gameOver = false;

        while (!gameOver) {

            Room room = map.getRoom(playerCharacter.getXPosition(),
                    playerCharacter.getYPosition());
            output = new OutputManager(console, room, playerCharacter);

            output.show();
            Action selectedAction = room.getAction(input.getInput());
            
            if(selectedAction instanceof StartBattleAction)
                (StartBattleAction)selectedAction.run(output,input,/*PlayerActions,*/playerCharacter,room.getEnemy());
            else
                selectedAction.run();
            
            if (!playerCharacter.isAlive()) {

                gameOver = true;
                output.showGameOverScreen();

            } else if (/* Player reach the treasure room*/) {

                gameOver = true;
                output.showWinnerScreen();

            }

        }

    }
}
