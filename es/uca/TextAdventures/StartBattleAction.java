package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.Set;

/**
 * Created by luisrozo on 15/12/16.
 */
public class StartBattleAction extends Action {

    public StartBattleAction(String description, PlayerCharacter player) {
        super(description,player);
    }
    void run(Parameter parameters) {

    }
}
