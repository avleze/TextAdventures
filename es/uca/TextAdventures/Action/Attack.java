package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

public class Attack extends BattleAction {
    private Player secondPlayer;

    /**
     * Constructor
     *
     * @param description     This initializes the parameter of the super-class.
     * @param playerCharacter Initializes the super-class parameter.
     * @param enemy           Initializes the instance variable which represents the Enemy.
     */
    public Attack(String description, Player firstPlayer, Player secondPlayer) {
        super(description, firstPlayer);
        this.secondPlayer = secondPlayer;
    }

    @Override
    /**
     * Executes action
     */
    public void run(ActionParameter actionParameters) {
        this.player.attack(this.secondPlayer);
    }

}