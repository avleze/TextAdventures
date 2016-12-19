package es.uca.TextAdventures.Factory;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;
import es.uca.TextAdventures.EnemyBehaviour.HealerEnemyBehaviour;

import java.util.Set;

/**
 * Created by manuel on 19/12/16.
 */
public class HealerCreator extends Creator{

    @Override
    public EnemyBehaviour factoryMethod(Set<BattleAction> Actions) {
        return new HealerEnemyBehaviour(Actions);
    }
}
