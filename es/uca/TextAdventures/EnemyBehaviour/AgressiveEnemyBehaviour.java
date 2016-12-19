package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.Attack;
import es.uca.TextAdventures.Action.BattleAction;

import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class AgressiveEnemyBehaviour extends EnemyBehaviour {

    public AgressiveEnemyBehaviour(Set<BattleAction> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {
        return this.actions.stream().filter((i) -> i instanceof Attack).iterator().next();
    }
}
