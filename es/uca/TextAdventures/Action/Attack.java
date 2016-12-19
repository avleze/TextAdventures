package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

public class Attack extends BattleAction {
    private Player enemy;

    /**
     * Constructor
     *
     * @param description     This initializes the parameter of the super-class.
     * @param playerCharacter Initializes the super-class parameter.
     * @param enemy           Initializes the instance variable which represents the Enemy.
     */
    public Attack(String description, PlayerCharacter playerCharacter, Player enemy) {
        super(description, playerCharacter);
        this.enemy = enemy;
    }

    @Override
    /**
     * Executes action
     */
    public void run(ActionParameter actionParameters) {
        this.playerCharacter.attack(this.enemy);
    }

}