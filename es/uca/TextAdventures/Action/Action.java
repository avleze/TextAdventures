package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This abstract class represents an action of the game.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public abstract class Action {
    private String description;
    private PlayerCharacter player;

    public Action(String description, PlayerCharacter player) {
        this.description = description;
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run();
}
