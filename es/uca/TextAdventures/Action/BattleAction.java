package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

/**
 * This class represents a battle action.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1.1
 */
public class BattleAction extends Action {
    private Player enemy;

    /**
     * Constructor
     *
     * @param description This initializes the parameter of the super-class.
     * @param player      Initializes the super-class parameter.
     * @param enemy       Initializes the instance variable which represents the Enemy.
     */
    public BattleAction(String description, PlayerCharacter player, Player enemy) {
        super(description, player);
        this.enemy = enemy;
    }

    @Override
    /**
     * Executes the action
     */
    void run() {

    }

}
