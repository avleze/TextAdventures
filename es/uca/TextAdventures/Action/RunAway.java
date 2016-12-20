package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class RunAway extends BattleAction {
    public RunAway(String description, PlayerCharacter playerCharacter) {
        super(description, playerCharacter);
    }

    public void run(ActionParameter actionParameters) {
        playerCharacter.disableBattle();
    }
}
