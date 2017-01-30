package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Player.Player;

/**
 * Created by juan on 19/01/17.
 */
public abstract class InventoryAction {

    protected String description;
    protected Player player;

    public InventoryAction(String description, Player player) {
        this.description = description;
        this.player = player;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract void run(ActionParameter actionParameter, Item itemSelected);
}
