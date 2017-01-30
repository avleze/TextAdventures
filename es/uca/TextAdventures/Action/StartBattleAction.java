package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.BattleManager;
import es.uca.TextAdventures.Factory.RandomBehaviourCreator;
import es.uca.TextAdventures.Player.Player;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Antonio Vélez Estévez.
 */
public class StartBattleAction extends Action {
    private Player enemy;
    private BattleManager battleManager;

    public StartBattleAction(String description,
                             PlayerCharacter playerCharacter,
                             Player enemy) {
        super(description, playerCharacter);
        this.enemy = enemy;
        Set<BattleAction> enemyActions = new HashSet<BattleAction>();
        enemyActions.add(new HealAction("HealAction.", enemy));
        enemyActions.add(new AttackAction("AttackAction.", enemy, playerCharacter));

        RandomBehaviourCreator randomBehaviour = new RandomBehaviourCreator();

        this.battleManager = new BattleManager(randomBehaviour.getBehaviour(enemyActions));
    }

    @Override
    public void run(ActionParameter actionParameters) {
        battleManager.run(actionParameters.getOutput(),
                actionParameters.getInput(),
                actionParameters.getPlayerActions(),
                actionParameters.getInventoryActions(),
                actionParameters.getPlayerCharacter(),
                actionParameters.getEnemy());
    }
}
