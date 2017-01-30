package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class HealAction extends BattleAction {

    public HealAction(String description, Player playerCharacter) {
        super(description, playerCharacter);
    }

    public void run(ActionParameter actionParameters) {
        this.player.usePotion();
    }
}
