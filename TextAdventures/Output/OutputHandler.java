package TextAdventures.Output;
import java.util.Set;

/**
 * OutputHandler
 * @author Antonio Vélez Estévez
 */
public interface OutputHandler {
    void show(PlayerCharacter playerCharacter, Room room, Set<Action> actions);

    void showWelcomeScreen(PlayerCharacter playerCharacter);

    void showGameOverScreen(PlayerCharacter playerCharacter);

    void showWinnerScreen(PlayerCharacter playerCharacter);
}
