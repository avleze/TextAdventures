package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
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
    public void showRoomInformation(Room room) {
        String separatorBar = "--------------------------------------------------------------------------------";

        System.out.println("You have arrived at the " + room.getMessage().getCaption() + " room");
        System.out.println(separatorBar);
        System.out.println(room.getMessage().getMessage());
    }

    @Override
    public void showWinnerScreen(PlayerCharacter playerCharacter) {
        String text = String.format(ANSI_WHITE + "You win this time %s ..." + ANSI_RESET, playerCharacter.getName());
        System.out.println(text);
    }


    @Override
    public void showBattleEndedMessage() {
        this.showMessage( ANSI_YELLOW + "Battle has ended" + ANSI_RESET);
    }
}
