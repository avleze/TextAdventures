package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This class represents a movement action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public class MovementAction extends Action {
    MovementType movType;

    /**
     * Constructor
     *
     * @param description This initializes the parameter of the super-class.
     * @param player      Initializes the super-class parameter.
     * @param tipo
     */
    public MovementAction(String description, PlayerCharacter player, MovementType tipo) {
        super(description, player);
        this.movType = tipo;
    }

    @Override
    /**
     * Executes the action
     */
    public void run(ActionParameter actionParameters) {

        PlayerCharacter playerChar = actionParameters.getPlayerCharacter();

        switch (movType) {
            case UP:
                playerChar.setXPosition(playerChar.getXPosition() - 1);
                break;
            case DOWN:
                playerChar.setXPosition(playerChar.getXPosition() + 1);
                break;
            case LEFT:
                playerChar.setYPosition(playerChar.getYPosition() - 1);
                break;
            case RIGHT:
                playerChar.setYPosition(playerChar.getYPosition() + 1);
                break;
        }
    }

    public enum MovementType {
        UP, DOWN, LEFT, RIGHT
    }
}
