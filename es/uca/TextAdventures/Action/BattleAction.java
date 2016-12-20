package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * This class represents a battle action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1.1
 */
public abstract class BattleAction extends Action {

    public BattleAction(String description, Player playerCharacter) {
        super(description, playerCharacter);
    }

    public abstract void run(ActionParameter actionParameters);
}
