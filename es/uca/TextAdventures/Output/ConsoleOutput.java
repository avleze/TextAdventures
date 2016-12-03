package es.uca.TextAdventures.Output;

import java.util.Set;

import es.uca.TextAdventures.PlayerCharacter;
import es.uca.TextAdventures.Room;
import es.uca.TextAdventures.Action;

/**
 * ConsoleOutput
 *
 * @author Antonio Vélez Estévez
 */
public class ConsoleOutput implements OutputHandler {

    private char[][] output;
    private int consoleWidth;
    private int consoleHeight;

    ConsoleOutput(int consoleWidth, int consoleHeight) {
        this.consoleWidth = consoleWidth;
        this.consoleHeight = consoleHeight;
        this.output = new char[consoleWidth][consoleHeight];
    }

    @Override
    public void show(PlayerCharacter playerCharacter, Room room, Set<Action> actions) {

    }

    @Override
    public void showWelcomeScreen(PlayerCharacter playerCharacter) {
        String text = String.format("Welcome to TextAdventures %s, we hope you have fun.", playerCharacter.getName());
        printCentered(text, consoleWidth, consoleHeight);
    }

    @Override
    public void showGameOverScreen(PlayerCharacter playerCharacter) {

    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {

    }

    private void printCentered(String text, int width, int height) {
        int centerWidth = width / 2;
        int centerHeight = height / 2;

        int halfTextLength = text.length() / 2;

        int i = 0;
        for (int x = centerWidth - halfTextLength; x != centerWidth + halfTextLength; ++x)
            output[x][centerHeight] = text.charAt(i++);
    }
}
