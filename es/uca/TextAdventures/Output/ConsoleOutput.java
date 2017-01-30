package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

import java.util.Set;

/**
 * ConsoleOutput
 *
 * @author Antonio Vélez Estévez
 */
public abstract class ConsoleOutput implements OutputHandler {

    public ConsoleOutput() {

    }


    @Override
    public void showGameInformation(PlayerCharacter playerCharacter, Room room, Set<Action> actions) {
        showCharacterInformation(playerCharacter);
        showRoomInformation(room);
        showActions(actions);
    }

    @Override
    public abstract void showCharacterInformation(PlayerCharacter playerCharacter);

    public abstract void showRoomInformation(Room room);

    @Override
    public abstract void showActions(Set<Action> actions);

    @Override
    public void showEnemyInformation(Enemy enemy) {
        String EnemyInf = String.format("Current enemy: \u001B[31m %s \u001B[0m \t Health: \u001B[33m %f \u001B[0m \t", enemy.getName(), enemy.getHealthPoints());
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println(separatorBar);
        System.out.println(EnemyInf);
        System.out.println(separatorBar);
    }

    @Override
    public void showWelcomeScreen(PlayerCharacter playerCharacter) {
        String text = String.format("Welcome to TextAdventures %s, we hope you have fun.", playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showGameOverScreen(PlayerCharacter playerCharacter) {
        String text = String.format(" \u001B[34m GAME OVER! You should try again %s ...  \u001B[0m", playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {
        String text = String.format("\u001B[33m You win this time %s ... \u001B[0m", playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }


    @Override
    public void showMenu() {
        this.showMessage("Text Adventures");
        this.showMessage("\t1. Load game");
        this.showMessage("\t2. New game (unimplemented yet)");
        this.showMessage("\t3. Credits (unimplemented yet)");
    }
}
