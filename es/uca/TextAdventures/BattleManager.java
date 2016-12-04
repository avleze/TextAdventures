package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.EnemyBehaviour.EnemyBehaviour;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;
import java.util.*;

/**
 * Created by manuelrdsg on 4/12/16.
 */
public class BattleManager{

    EnemyBehaviour enemy;

    public BattleManager(EnemyBehaviour EnActions) {
        this.enemy = enemy;
    }

    public void run (OutputManager out, InputManager in, Set<Action> PlActions, PlayerCharacter player, Enemy enemy){ //PlayerCharacter Player, Enemy enemy) {
        while(player.isAlive() || !enemy.isAlive()){
            //llamada al output manager
            enemy.getAction().run();
            PlActions.toArray().[in.getInput()].run();
        }
    }





}
