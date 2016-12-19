package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.BattleAction;

import java.util.Set;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public abstract class EnemyBehaviour {

    protected Set<BattleAction> actions;

    public EnemyBehaviour(Set<BattleAction> actions) {
        this.actions = actions;
    }

    public abstract Action getAction();

}
