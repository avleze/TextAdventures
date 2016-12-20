package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

import java.util.Set;

/**
 * OutputHandler
 *
 * @author Antonio Vélez Estévez
 */
public interface OutputHandler {
    void showGameInformation(PlayerCharacter playerCharacter, Room room, Set<Action> actions);

    void showWelcomeScreen(PlayerCharacter playerCharacter);

    void showCharacterInformation(PlayerCharacter playerCharacter);

    void showGameOverScreen(PlayerCharacter playerCharacter);

    void showWinnerScreen(PlayerCharacter playerCharacter);

    void showMessage(String message);

    void showActions(Set<Action> actions);

    void showMenu();
}
