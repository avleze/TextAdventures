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
public class ConsoleOutput implements OutputHandler {

    public ConsoleOutput() {

    }


    @Override
    public void showGameInformation(PlayerCharacter playerCharacter, Room room, Set<Action> actions) {


        String headText = String.format("Current playerCharacter: %s\t Health: %f", playerCharacter.getName(),
                playerCharacter.getHealthPoints());
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println(headText);
        System.out.println(separatorBar);
        System.out.println("You have arrived at the " + room.getMessage().getCaption() + " room");
        System.out.println(separatorBar);
        System.out.println(room.getMessage().getMessage());

        showActions(actions);
    }

    @Override
    public void showCharacterInformation(PlayerCharacter playerCharacter) {
        String characterInf = String.format("Current player: \u001B[34m  %s \u001B[0m \t Health: \u001B[33m %f \u001B[0m \t", playerCharacter.getName(),
                playerCharacter.getHealthPoints());
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println(separatorBar);
        System.out.println(characterInf);
        System.out.println(separatorBar);
    }

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
    public void showActions(Set<Action> actions) {
        int counter = 1;
        for (Action i : actions)
            System.out.println(String.format("\t%d. %s", counter++, i.getDescription()));
        System.out.println("Choose an option:");
    }

    @Override
    public void showMenu() {
        this.showMessage("Text Adventures");
        this.showMessage("\t1. Load game");
        this.showMessage("\t2. New game (unimplemented yet)");
        this.showMessage("\t3. Credits (unimplemented yet)");
    }
}
