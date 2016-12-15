package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.*;

public class Attack extends BattleAction {
    private Player enemy;

    /**
     * Constructor
     *
     * @param description This initializes the parameter of the super-class.
     * @param player      Initializes the super-class parameter.
     * @param enemy       Initializes the instance variable which represents the Enemy.
     */
    public Attack(String description, PlayerCharacter player, Player enemy) {
        super(description, player);
        this.enemy = enemy;
    }

    @Override
    /**
     * Executes action
     */
    public void run() {

    }

}