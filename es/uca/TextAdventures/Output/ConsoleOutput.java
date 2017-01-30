package es.uca.TextAdventures.Output;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.InventoryAction;
import es.uca.TextAdventures.Item.Item;
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

    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_BLACK = "\u001B[30m";
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_PURPLE = "\u001B[35m";
    protected static final String ANSI_CYAN = "\u001B[36m";
    protected static final String ANSI_WHITE = "\u001B[37m";


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
    public abstract void showInventoryActions(Set<InventoryAction> inventoryActions);

    @Override
    public void showEnemyInformation(Enemy enemy) {
        String EnemyInf = String.format("Current enemy:" + ANSI_RED + " %s " + ANSI_RESET + "\t Health:" + ANSI_YELLOW + " %f " + ANSI_RESET + "\t", enemy.getName(),
                enemy.getHealthPoints());
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
        String text = String.format(ANSI_YELLOW + "You win this time %s ... " + ANSI_RESET, playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }


    @Override
    public void showInventory(Set<Item> inventory) {
        int counter = 1;
        for (Item i : inventory)
            System.out.println(String.format("\t%d. %s", counter++, i.getClass().getName()));
        System.out.println("Choose an item or enter 0 to cancel:");
    }

    @Override
    public void showMenu() {
        this.showMessage("Text Adventures");
        this.showMessage("\t1. New game");
        this.showMessage("\t2. Options");
        this.showMessage("\t3. Credits");
    }

    @Override
    public void showOptions() {
        this.showMessage("Options");
        this.showMessage("\t1. Change to color-blind mode");
        this.showMessage("\t2. Change to normal-color mode");
    }

    @Override
    public void showCredits() {
        try {
            this.showMessage(ANSI_GREEN + "\t\tCREDITS");
            this.showMessage("\t\t-----------------");
            Thread.sleep(1200);
            this.showMessage("\t\t\tDevelopers:");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tJuan Antonio Rodicio Lopez");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tManuel Rodriguez-Sanchez Guerra");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tLuis Gonzaga Rozo Bueno");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tAntonio Velez Estevez");
            Thread.sleep(2000);
            this.showMessage("\n");
            this.showMessage(ANSI_CYAN + "\t\t\tCreated for Diseño de Sistemas Software, subject of" +
                    " Software Engineering in the University of Cadiz.");
            this.showMessage("\n");
            Thread.sleep(2000);
            this.showMessage("\t\t\tThanks for playing :D!" + ANSI_RESET);
            this.showMessage("\n");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBattleEndedMessage(){
        this.showMessage(ANSI_YELLOW + "Battle has ended" + ANSI_RESET);
    }
}
