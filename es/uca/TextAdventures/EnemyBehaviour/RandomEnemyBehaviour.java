package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.AttackAction;
import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.Action.HealAction;

import java.util.Random;
import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class RandomEnemyBehaviour extends EnemyBehaviour {

    public RandomEnemyBehaviour(Set<BattleAction> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {

        Random rand = new Random();
        Action action;

        if (rand.nextBoolean()) {
            action = this.actions.stream().filter((i) -> i instanceof HealAction).iterator().next();
        } else {
            action = this.actions.stream().filter((i) -> i instanceof AttackAction).iterator().next();
        }

        return action;

    }
}
