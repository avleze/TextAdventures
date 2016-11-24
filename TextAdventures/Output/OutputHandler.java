package TextAdventures.Output;
import java.util.Set;

/**
 * OutputHandler
 * @author Antonio Vélez Estévez
 */
public interface OutputHandler {
    void showPlayerInformation(PlayerCharacter playerCharacter);
    void showGameInformation(Room room);
    void showWelcomeScreen();
    void showActions(Set<Action> actions);
}
