package es.uca.TextAdventures.Factory;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;

import java.util.Random;
import java.util.Set;

/**
 * Created by manuel on 19/12/16.
 */
public class RandomBehaviourCreator {

    public EnemyBehaviour getBehaviour(Set<BattleAction> Actions) {
        Random rand = new Random();
        int randomnumber = rand.nextInt(3) + 1;
        EnemyBehaviour Behaviour = null;

        switch (randomnumber) {
            case 1: {
                Creator factory = new AgressiveCreator();
                Behaviour = factory.factoryMethod(Actions);
                break;
            }

            case 2: {
                Creator factory = new HealerCreator();
                Behaviour = factory.factoryMethod(Actions);
                break;
            }

            case 3: {
                Creator factory = new RandomCreator();
                Behaviour = factory.factoryMethod(Actions);
                break;
            }

        }

        return Behaviour;

    }


}
