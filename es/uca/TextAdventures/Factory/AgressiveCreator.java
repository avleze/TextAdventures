package es.uca.TextAdventures.Factory;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.EnemyBehaviour.AggressiveEnemyBehaviour;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;

import java.util.Set;

/**
 * Created by manuel on 19/12/16.
 */
public class AgressiveCreator extends Creator {
    @Override
    public EnemyBehaviour makeEnemyBehaviour(Set<BattleAction> Actions) {
        return new AggressiveEnemyBehaviour(Actions);
    }
}
