package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Player.Player;

/**
 * Created by juan on 19/01/17.
 */
public abstract class InventoryAction extends Action {

    public InventoryAction(String description, Player player) {
        super(description, player);
    }

}
