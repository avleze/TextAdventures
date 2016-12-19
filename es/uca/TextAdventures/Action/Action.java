package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This abstract class represents an action of the game.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public abstract class Action {
    protected String description;
    protected PlayerCharacter playerCharacter;

    public Action(String description, PlayerCharacter playerCharacter) {
        this.description = description;
        this.playerCharacter = playerCharacter;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run(ActionParameter actionParameters);
}
