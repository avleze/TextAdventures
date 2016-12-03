package es.uca.TextAdventures;

import TextAdventures.Input.InputManager;
import TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * DecisionEngine Class
 *
 * @author Juan Antonio Rodicio López
 */
public class DecisionEngine {

    PlayerCharacter playerCharacter;
    Map map;
    OutputManager output;
    InputManager input;
    MapLoader mapLoader;

    DecisionEngine(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
        this.map = mapLoader.loadFromFile("mapa.xml", playerCharacter);
    }

    void run() {

        boolean gameOver = false;

        while (!gameOver) {

            Room room = map.getRoom(playerCharacter.getXPosition(),
                    playerCharacter.getYPosition());

            output.show(playerCharacter, room, room.getActions());

            room.getAction(input.getInput()).run();

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
