package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This class represents a movement action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public class MovementAction extends Action {
    private enum movementType {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Constructor
     *
     * @param description This initializes the parameter of the super-class.
     * @param player      Initializes the super-class parameter.
     */
    public MovementAction(String description, PlayerCharacter player, movementType tipo) {
        super(description, player);
    }

    @Override
    /**
     * Executes the action
     */
    public void run() {

    }
}
