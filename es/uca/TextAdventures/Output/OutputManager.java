package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

/**
 * OutputManager
 *
 * @author Antonio Vélez Estévez
 */
public class OutputManager {
    private OutputHandler currentOutputHandler;
    private Room currentRoom;
    private PlayerCharacter playerCharacter;

    public OutputManager(OutputHandler currentOutputHandler, Room currentRoom, PlayerCharacter playerCharacter) {
        this.currentOutputHandler = currentOutputHandler;
        this.currentRoom = currentRoom;
        this.playerCharacter = playerCharacter;
    }

    public void setCurrentOutputHandler(OutputHandler currentOutputHandler) {
        this.currentOutputHandler = currentOutputHandler;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void show() {
        currentOutputHandler.showGameInformation(this.playerCharacter,
                this.currentRoom,
                this.currentRoom.getActions());
    }

    public void showWelcomeScreen() {
        currentOutputHandler.showWelcomeScreen();
    }

    public void showGameOverScreen() {
        currentOutputHandler.showGameOverScreen();
    }

    public void showWinnerScreen() {
        currentOutputHandler.showWinnerScreen();
    }
}
