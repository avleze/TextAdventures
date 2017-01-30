package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Player.Player;


/**
 * Created by juan on 30/01/17.
 */
public class SuperifyPotionAction extends InventoryAction {


    public SuperifyPotionAction(String description, Player player) {
        super(description, player);
    }

    public void run(ActionParameter actionParameter) {

        if (actionParameter.getPlayerCharacter().getInventory().size() > 1 && actionParameter.getItemSelected() instanceof SimpleRecoveryItem) {
            actionParameter.getOutput().showMessage("In order to superify a potion, you need to sacrifice an item ;) ");
            actionParameter.getOutput().showInventory();
            int itemSelected = actionParameter.getInput().getInput();

            if (itemSelected != 0) {
                Item itemToSacrifice = (Item) actionParameter.getPlayerCharacter().getInventory().toArray()[itemSelected - 1];

                if (itemToSacrifice != actionParameter.getItemSelected()) {
                    actionParameter.getPlayerCharacter().getInventory().remove(itemToSacrifice);

                    actionParameter.getOutput().showMessage("Ok!, lets superify that potion...");

                    SuperRecoveryItem superPotion = new SuperRecoveryItem((RecoveryItem) actionParameter.getItemSelected());
                    actionParameter.getPlayerCharacter().getInventory().remove(actionParameter.getItemSelected());
                    actionParameter.getPlayerCharacter().getInventory().add(superPotion);
                } else
                    actionParameter.getOutput().showMessage("You can't sacrifice the same item that you want to superify. Don't be clever!. Now you lost your turn :)");
            }
        } else
            actionParameter.getOutput().showMessage("You can't superify a potion if you don't have any items to sacrifice! or if that potion it's superified yet! :(");
    }
}
