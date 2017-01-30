package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.AttackAction;
import es.uca.TextAdventures.Action.BattleAction;

import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class AggressiveEnemyBehaviour extends EnemyBehaviour {

    public AggressiveEnemyBehaviour(Set<BattleAction> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {
        return this.actions.stream().filter((i) -> i instanceof AttackAction).iterator().next();
    }
}
