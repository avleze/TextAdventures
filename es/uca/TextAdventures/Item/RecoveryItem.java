package es.uca.TextAdventures.Item;

import es.uca.TextAdventures.Item.Item;

/**
 * es.uca.item.RecoveryItem
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class RecoveryItem extends Item {

    private int pointstoHealth;

    public RecoveryItem(int pointstoHealth) {
        this.pointstoHealth = pointstoHealth;
    }


    @Override
    public int use() {
        return pointstoHealth;
    }

}
