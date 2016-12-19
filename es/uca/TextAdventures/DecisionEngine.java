package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Input.ConsoleInput;
import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Output.*;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;
import java.util.HashSet;
import java.util.Set;

/**
 * DecisionEngine Class
 *
 * @author Juan Antonio Rodicio López
 */
public class DecisionEngine {

    PlayerCharacter playerCharacter;
    Map map;
    ConsoleOutput consoleOut;
    OutputManager output;
    InputManager input;
    ConsoleInput consoleIn;
    MapLoader mapLoader;
    ActionParameter actionParameters;
    Set<BattleAction> playerActions;


    DecisionEngine(PlayerCharacter playerCharacter) throws WeaponItem.TypeNotFoundException, Enemy.TypeNotFoundException{
        this.playerCharacter = playerCharacter;
        this.mapLoader = new MapLoader(this.playerCharacter);
        
        this.consoleIn = new ConsoleInput();
        
        this.playerActions = new HashSet<>();
        this.input = new InputManager(consoleIn);
        
        this.map = mapLoader.loadFromFile("map.xml");
        this.consoleOut = new ConsoleOutput(80,100);

    }

    void run() {

        boolean gameOver = false;
        
        output = new OutputManager(consoleOut, null, playerCharacter);

        while (!gameOver) {

            Room room = map.getRoom(playerCharacter.getXPosition(),
                    playerCharacter.getYPosition());
            
            playerActions.add(new Heal("Curarse.", playerCharacter));
            playerActions.add(new RunAway("Huir.", playerCharacter));
            playerActions.add(new Attack("Atacar.", playerCharacter, room.getEnemy()));
            
            actionParameters = new ActionParameter(output,input, playerActions, playerCharacter, room.getEnemy());
            
            output.setCurrentRoom(room);

            output.show();
            Action selectedAction = room.getAction(input.getInput() - 1);
            
            if(selectedAction instanceof StartBattleAction)
                ((StartBattleAction)selectedAction).run(actionParameters);
            else
                selectedAction.run(null);
            
            if (!playerCharacter.isAlive()) {

                gameOver = true;
                output.showGameOverScreen();

            } else if (playerCharacter.getXPosition() == 1 && playerCharacter.getYPosition() == 1) {

                gameOver = true;
                output.showWinnerScreen();

            }

        }

    }
}
