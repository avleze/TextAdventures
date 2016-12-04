package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This class represents a battle action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1.1
 */
public abstract class BattleAction extends Action {

    public BattleAction(String d,PlayerCharacter pc) {
        super(d,pc);
    }

    public abstract void run();
}
