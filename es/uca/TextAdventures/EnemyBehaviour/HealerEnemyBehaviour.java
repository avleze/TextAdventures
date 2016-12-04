package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.Heal;
import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class HealerEnemyBehaviour extends EnemyBehaviour {

    public HealerEnemyBehaviour(Set<Action> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {
        return this.actions.stream().filter((i) -> i instanceof Heal).iterator().next();
    }
}
