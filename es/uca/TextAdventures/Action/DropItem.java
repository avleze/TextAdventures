package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Player.Player;

/**
 * Created by juan on 19/01/17.
 */
public class DropItem extends InventoryAction {

    public DropItem(String description, Player player) {
        super(description, player);
    }

    public void run(ActionParameter actionParameter, Item itemToDrop) {
        actionParameter.getOutput().showMessage("Item eliminado");
        actionParameter.getPlayerCharacter().getInventory().remove(itemToDrop);
    }
}
