package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Player.Player;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by juan on 19/01/17.
 */
public class ShowInventoryAction extends Action {

    public ShowInventoryAction(String description, Player player) {
        super(description, player);
    }

    public void run(ActionParameter actionParameter) {
        actionParameter.getOutput().showInventory();
        int selectedItem = actionParameter.getInput().getInput();

        if (selectedItem != 0) {
            Item itemSelected = (Item) actionParameter.getPlayerCharacter().getInventory().toArray()[selectedItem - 1];
            actionParameter.setItemSelected(itemSelected);
            actionParameter.getOutput().showMessage("Item's options: ");
            if (itemSelected instanceof RecoveryItem)
                actionParameter.getOutput().showInventoryActions(actionParameter.getInventoryActions());
            else {
                Set<InventoryAction> notAPotionActions = new LinkedHashSet<>();
                notAPotionActions.add(new DropItemAction("Drop " + itemSelected.getClass().getName(), this.player));
                actionParameter.getOutput().showInventoryActions(notAPotionActions);
            }
            int selectedAction = actionParameter.getInput().getInput();
            InventoryAction inventoryAction = (InventoryAction) actionParameter.getInventoryActions().toArray()[selectedAction - 1];
            inventoryAction.run(actionParameter);
        }
    }
}
