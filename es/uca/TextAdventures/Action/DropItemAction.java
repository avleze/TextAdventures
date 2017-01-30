package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * Created by juan on 19/01/17.
 */
public class DropItemAction extends InventoryAction {

    public DropItemAction(String description, Player player) {
        super(description, player);
    }

    public void run(ActionParameter actionParameter) {
        actionParameter.getOutput().showMessage("Item eliminado");
        actionParameter.getPlayerCharacter().getInventory().remove(actionParameter.getItemSelected());
    }
}
