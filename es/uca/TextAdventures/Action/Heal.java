package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class Heal extends BattleAction {

    public Heal(String description, Player playerCharacter) {
        super(description, playerCharacter);
    }

    public void run(ActionParameter actionParameters) {
        ((PlayerCharacter) this.player).usePotion();
    }
}
