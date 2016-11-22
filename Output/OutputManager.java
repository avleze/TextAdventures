package Output;

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

    public void show(){

    }

    public void showWelcomeScreen(){

    }
}
