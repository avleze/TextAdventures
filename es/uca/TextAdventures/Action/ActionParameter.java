package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.Set;

/**
 * @author Antonio Vélez Estévez.
 */
public class ActionParameter {
    private OutputManager output;
    private InputManager input;
    private Set<Action> playerActions;
    private PlayerCharacter playerCharacter;
    private Enemy enemy;


    public ActionParameter(OutputManager output, InputManager input, Set<Action> playerActions, PlayerCharacter playerCharacter, Enemy enemy) {
        this.output = output;
        this.input = input;
        this.playerActions = playerActions;
        this.playerCharacter = playerCharacter;
        this.enemy = enemy;
    }

    public OutputManager getOutput() {
        return output;
    }

    public InputManager getInput() {
        return input;
    }

    public Set<Action> getPlayerActions() {
        return playerActions;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
