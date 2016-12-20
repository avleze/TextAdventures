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
public class Parameter {
    private OutputManager out;
    private InputManager in;
    private Set<Action> PlActions;
    private PlayerCharacter player;
    private Enemy enemy;
}
