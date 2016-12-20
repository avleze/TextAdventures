package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.BattleAction;
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
    public void showWelcomeScreen(PlayerCharacter playerCharacter) {
        String text = String.format("Welcome to TextAdventures %s, we hope you have fun.", playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showGameOverScreen(PlayerCharacter playerCharacter) {
        String text = String.format("GAME OVER! You should try again %s ...", playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {
        String text = String.format("You win this time %s ...", playerCharacter.getName());
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
            System.out.println(String.format("%d. %s", counter++, i.getDescription()));
        System.out.println("Selecciona una opcion:");
    }

    @Override
    public void showMenu() {
        this.showMessage("Text Adventures");
        this.showMessage("1. Load game");
        this.showMessage("2. New game (unimplemented yet)");
        this.showMessage("3. Credits (unimplemented yet)");
    }
}
