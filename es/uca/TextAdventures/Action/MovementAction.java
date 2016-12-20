package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This class represents a movement action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public class MovementAction extends Action {
    MovementType movementType;

    /**
     * Constructor
     *
     * @param description This initializes the parameter of the super-class.
     * @param player      Initializes the super-class parameter.
     * @param tipo
     */
    public MovementAction(String description, PlayerCharacter player, MovementType movementType) {
        super(description, player);
        this.movementType = movementType;
    }

    @Override
    /**
     * Executes the action
     */
    public void run(ActionParameter actionParameters) {

        PlayerCharacter playerChararacter = actionParameters.getPlayerCharacter();

        switch (this.movementType) {
            case UP:
                playerChararacter.moveUp();
                break;
            case DOWN:
                playerChararacter.moveDown();
                break;
            case LEFT:
                playerChararacter.moveLeft();
                break;
            case RIGHT:
                playerChararacter.moveRight();
                break;
        }
    }

    public enum MovementType {
        UP, DOWN, LEFT, RIGHT
    }
}
