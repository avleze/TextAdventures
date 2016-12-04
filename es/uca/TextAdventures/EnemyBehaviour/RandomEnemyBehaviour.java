package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.Attack;
import es.uca.TextAdventures.Action.Heal;

import java.util.Random;
import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class RandomEnemyBehaviour extends EnemyBehaviour{

    public RandomEnemyBehaviour(Set<Action> actions) {
        super(actions);
    }

    @Override
    public Action getAction() {

        Random rand = new Random();
        Action action;

        if(rand.nextBoolean()){
            action = this.actions.stream().filter((i) -> i instanceof Heal).iterator().next();
        }
        else {
            action = this.actions.stream().filter((i) -> i instanceof Attack).iterator().next();
        }

        return action;

    }
}
