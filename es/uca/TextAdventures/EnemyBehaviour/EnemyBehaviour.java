package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;

import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public abstract class EnemyBehaviour {

    protected Set<Action> actions;
    public abstract Action getAction();

    public EnemyBehaviour (Set<Action> actions) {
        this.actions = actions;
    }

}
