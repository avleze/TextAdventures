package es.uca.TextAdventures.Item.RecoveryItemDecorator;

import es.uca.TextAdventures.Item.Item;

/**
 * Created by juan on 21/12/16.
 */
public abstract class RecoveryItem extends Item {

    protected int pointsToHealth;

    RecoveryItem(int pointsToHealth, int id) {
        super(id);
        this.pointsToHealth = pointsToHealth;

    }

    public abstract int use();

}
