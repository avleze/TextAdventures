package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.Attack;
import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class AgressiveEnemyBehaviour extends EnemyBehaviour {

    public AgressiveEnemyBehaviour(Set<Action> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {
        return this.actions.stream().filter((i) -> i instanceof Attack).iterator().next();
    }
}
