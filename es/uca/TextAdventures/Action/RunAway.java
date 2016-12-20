package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class RunAway extends BattleAction {
    public RunAway(String description, Player player) {
        super(description, player);
    }

    public void run(ActionParameter actionParameters) {
        ((PlayerCharacter) player).disableBattle();
    }
}
