package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.*;

/**
 * @author Luis Rozo Bueno
 * @author Antonio Vélez Estévez
 */
public class Heal extends BattleAction {

    public Heal(String description, PlayerCharacter playerCharacter) {
        super(description, playerCharacter);
    }

    public void run(ActionParameter actionParameters) {
        this.playerCharacter.usePotion();
    }
}
