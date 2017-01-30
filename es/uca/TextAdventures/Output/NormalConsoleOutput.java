package es.uca.TextAdventures.Output;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.InventoryAction;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

import java.util.Set;

/**
 * Created by antonio on 29/01/17.
 */
public class NormalConsoleOutput extends ConsoleOutput {

    @Override
    public void showCharacterInformation(PlayerCharacter playerCharacter) {
        String characterInf = String.format("Current player: \u001B[34m  %s \u001B[0m \t Health: \u001B[33m %f \u001B[0m \t", playerCharacter.getName(),
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
}
