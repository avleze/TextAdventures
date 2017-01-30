package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.InventoryAction;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

import java.util.Set;

/**
 * Created by antonio on 29/01/17.
 */



public class ColorBlindConsoleOutput extends ConsoleOutput {


    @Override
    public void showCharacterInformation(PlayerCharacter playerCharacter) {
        String characterInf = String.format("Current player:" + ANSI_PURPLE + " %s " + ANSI_RESET + "\t Health:" + ANSI_CYAN + " %f " + ANSI_RESET + "\t", playerCharacter.getName(),
                playerCharacter.getHealthPoints());
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println(separatorBar);
        System.out.println(characterInf);
        System.out.println(separatorBar);
    }

    public void showActions(Set<Action> actions) {
        int counter = 1;
        for (Action i : actions)
            System.out.println(String.format("\t%d. %s", counter++, i.getDescription()));
        System.out.println("Choose an option:");
    }

    @Override
    public void showInventoryActions(Set<InventoryAction> inventoryActions) {
        int counter = 1;
        for (InventoryAction i : inventoryActions)
            System.out.println(String.format("\t%d. %s", counter++, i.getDescription()));
        System.out.println("Choose an option:");
    }

    @Override
    public void showRoomInformation(Room room) {
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println("You have arrived at the " + room.getMessage().getCaption() + " room");
        System.out.println(separatorBar);
        System.out.println(room.getMessage().getMessage().replaceAll(";br;","\n"));
    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {
        String text = String.format(ANSI_WHITE + "You win this time %s ..." + ANSI_RESET, playerCharacter.getName());
        System.out.println(text);
    }

    @Override
    public void showMenu() {
        this.showMessage("Text Adventures" + ANSI_WHITE + " - " + ANSI_RESET + ANSI_YELLOW + "Color-Blind mode" + ANSI_RESET);
        this.showMessage("\t1. New game");
        this.showMessage("\t2. Options");
        this.showMessage("\t3. Credits");
    }

    @Override
    public void showCredits() {
        try {
            this.showMessage(ANSI_CYAN + "\t\tCREDITS" + ANSI_RESET);
            this.showMessage("\t\t-----------------");
            Thread.sleep(1200);
            this.showMessage(ANSI_PURPLE + "\t\t\tDevelopers:" + ANSI_RESET);
            Thread.sleep(1200);
            this.showMessage(ANSI_YELLOW + "\t\t\t\tJuan Antonio Rodicio Lopez");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tManuel Rodriguez-Sanchez Guerra");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tLuis Gonzaga Rozo Bueno");
            Thread.sleep(1200);
            this.showMessage("\t\t\t\tAntonio Velez Estevez" + ANSI_RESET);
            Thread.sleep(2000);
            this.showMessage("\n");
            this.showMessage(ANSI_GREEN + "\t\t\tCreated for Diseño de Sistemas Software, subject of" +
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

    @Override
    public void showBattleEndedMessage() {
        this.showMessage( ANSI_YELLOW + "Battle has ended" + ANSI_RESET);
    }
}
