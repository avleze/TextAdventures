package es.uca.TextAdventures.Output;

import java.util.Set;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;
import es.uca.TextAdventures.Action.Action;

/**
 * ConsoleOutput
 *
 * @author Antonio Vélez Estévez
 */
public class ConsoleOutput implements OutputHandler {

    private char[][] output;
    private int consoleWidth;
    private int consoleHeight;
    private int lastRow;
    private int lastColumn;

    ConsoleOutput(int consoleWidth, int consoleHeight) {
        this.consoleWidth = consoleWidth;
        this.consoleHeight = consoleHeight;
        this.output = new char[consoleWidth][consoleHeight];
    }

    private void clear() {
        for (int i = 0; i < consoleHeight; ++i)
            for (int j = 0; j < consoleWidth; ++j)
                output[i][j] = ' ';
    }

    @Override
    public void showGameInformation(PlayerCharacter playerCharacter, Room room, Set<Action> actions) {
        clear();
        int headWidth = (int) (consoleHeight * 0.1);
        int headHeight = consoleWidth;

        int bodyWidth = consoleWidth;
        int bodyHeight = (int) (consoleHeight * 0.8);

        int inputHeight = (int) (consoleHeight * 0.1);
        int inputWidth = consoleWidth;


        String headText = String.format("Current playerCharacter: %s\t Health: %d", playerCharacter.getName(),
                playerCharacter.getHealthPoints());
        String separatorBar = "--------------------------------------------------------------------------------";

        this.print(headText, 0, 0, headWidth, headHeight);
        this.print(separatorBar, 0, lastRow + 1, consoleWidth, consoleHeight);
        this.print("You have arrived at the " + room.getMessage().getCaption() + " room",
                0, lastRow + 1, consoleWidth, consoleHeight);
        this.print(separatorBar, 0, lastRow + 1, consoleWidth, consoleHeight);
        this.print(room.getMessage().getMessage(), 0, lastRow + 1, consoleWidth, consoleHeight);


        int counter = 1;
        for (Action i : actions)
            this.print(String.format("%d. %s", counter++, i.getDescription()), 10, lastRow + 1, consoleWidth, consoleHeight);
        this.print("Selecciona una opcion:", 0, lastRow + 3, consoleWidth, consoleHeight);
    }

    @Override
    public void showWelcomeScreen(PlayerCharacter playerCharacter) {
        clear();
        String text = String.format("Welcome to TextAdventures %s, we hope you have fun.", playerCharacter.getName());
        this.printCentered(text, consoleWidth, consoleHeight);
    }

    @Override
    public void showGameOverScreen(PlayerCharacter playerCharacter) {
        clear();
        String text = String.format("GAME OVER! You should try again %s ...", playerCharacter.getName());
        this.printCentered(text, consoleWidth, consoleHeight);
    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {
        clear();
        String text = String.format("You win this time %s ...", playerCharacter.getName());
        this.printCentered(text, consoleWidth, consoleHeight);
        this.printMatrix();
    }

    @Override
    public void showMessage(String message) {
        clear();
        this.printCentered(message, consoleWidth, consoleHeight);
        this.printMatrix();
    }

    @Override
    public void showBattleActions(Set<BattleAction> battleActions) {
        clear();

    }

    private void printMatrix() {
        for (int i = 0; i < consoleHeight; ++i) {
            for (int j = 0; j < consoleWidth; ++j)
                System.out.print(output[i][j]);
            System.out.println("");
        }
    }

    private void print(String text, int x, int y, int limX, int limY) {
        int textIndex = 0;
        int i = 0;
        int j = 0;
        for (i = y; i < limY && textIndex < text.length(); ++i)
            for (j = x; j < limX && textIndex < text.length(); ++j)
                output[i][j] = text.charAt(textIndex++);
        lastRow = i;
        lastColumn = j;
    }

    private void printCentered(String text, int width, int height) {
        int centerWidth = width / 2;
        int centerHeight = height / 2;

        int halfTextLength = text.length() / 2;

        int i = 0;
        for (int x = centerWidth - halfTextLength; x != centerWidth + halfTextLength; ++x)
            output[centerHeight][x] = text.charAt(i++);
    }

    public static void main(String[] args) {

    }
}
