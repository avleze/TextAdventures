package es.uca.TextAdventures.Item;


/**
 * es.uca.item.RecoveryItem
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class RecoveryItem extends Item {

    private int pointsToHealth;

    public RecoveryItem(int pointsToHealth, int id) {
        super(id);
        this.pointsToHealth = pointsToHealth;
    }


    @Override
    public int use() {
        return pointsToHealth;
    }

}
