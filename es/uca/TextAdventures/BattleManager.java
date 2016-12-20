package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.Set;

/**
 * @author Manuel Rodríguez-Sánchez Guerra.
 */
public class BattleManager {

    EnemyBehaviour enemyBehaviour;

    public BattleManager(EnemyBehaviour enemyBehaviour) {
        this.enemyBehaviour = enemyBehaviour;
    }

    public void run(OutputManager out, InputManager in, Set<Action> playerActions, PlayerCharacter player, Enemy enemy) { //PlayerCharacter Player, Enemy enemy) {
        out.showMessage("Battle is starting, prepare yourself!");
        player.enableBattle();
        while (player.isAlive() && enemy.isAlive() && player.isOnBattle()) {
            out.showMessage("Enemy attacks!");
            enemyBehaviour.getAction().run(null);
            out.showActions(playerActions);
            ((Action) playerActions.toArray()[in.getInput() - 1]).run(null);

            out.showMessage("You attack the enemy! It's so effective");
        }
        out.showMessage("Battle has ended");
    }


}
