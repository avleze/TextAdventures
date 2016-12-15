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

    EnemyBehaviour enemyBeh;

    public BattleManager(EnemyBehaviour EnActions) {
        this.enemyBeh = enemyBeh;
    }

    public void run (OutputManager out, InputManager in, Set<Action> PlActions, PlayerCharacter player, Enemy enemy){ //PlayerCharacter Player, Enemy enemy) {
        out.showMessage("Battle is starting, prepare yourself!");
        while(player.isAlive() || !enemy.isAlive()){
            out.showMessage("Enemy attacks!")
            enemyBeh.getAction().run();
            out.showMessage("You attack the enemy! It's so effective")
            ((Action)PlActions.toArray()[in.getInput()]).run();
        }
        out.showMessage("Battle has ended");
    }





}
