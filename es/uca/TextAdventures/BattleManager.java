package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.ActionParameter;
import es.uca.TextAdventures.Action.InventoryAction;
import es.uca.TextAdventures.Action.ShowInventoryAction;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.Set;

/**
 * es.uca.TextAdventures.BattleManager
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */
public class BattleManager {

    EnemyBehaviour enemyBehaviour;

    public BattleManager(EnemyBehaviour enemyBehaviour) {
        this.enemyBehaviour = enemyBehaviour;
    }

    public void run(OutputManager out, InputManager in, Set<Action> playerActions, Set<InventoryAction> inventoryActions, PlayerCharacter player, Enemy enemy) {

        int input;
        ActionParameter actionParameter = new ActionParameter(out, in, playerActions, inventoryActions, player, enemy, null);

        out.showMessage("\u001B[36m Battle is starting, prepare yourself! \u001B[0m");
        player.enableBattle();
        while (player.isAlive() && enemy.isAlive() && player.isOnBattle()) {
            out.showEnemyInformation(enemy);
            out.showCharacterInformation();

            out.showMessage("Enemy attacks!");
            enemyBehaviour.getAction().run(null);
            out.showActions(playerActions);

            do {
                input = in.getInput();
            } while (input > playerActions.size() || input <= 0);

            if (playerActions.toArray()[input - 1] instanceof ShowInventoryAction)
                ((ShowInventoryAction) playerActions.toArray()[input - 1]).run(actionParameter);
            else
                ((Action) playerActions.toArray()[input - 1]).run(null);

            out.showMessage("You attack the enemy! It's so effective");
        }
        out.showMessage("\u001B[33m Battle has ended \u001B[0m");
    }


}
