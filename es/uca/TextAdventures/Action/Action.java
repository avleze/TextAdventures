package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * This abstract class represents an action of the game.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public abstract class Action {
    protected String description;
    protected Player player;

    public Action(String description, Player player) {
        this.description = description;
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run(ActionParameter actionParameters);
}
