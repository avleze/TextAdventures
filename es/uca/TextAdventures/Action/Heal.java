package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class Heal extends BattleAction {

    public Heal(String description, Player playerCharacter) {
        super(description, playerCharacter);
    }

    public void run(ActionParameter actionParameters) {
        this.player.usePotion();
    }
}
