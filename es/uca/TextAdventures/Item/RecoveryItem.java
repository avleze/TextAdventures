package es.uca.TextAdventures.Item;


/**
 * es.uca.item.RecoveryItem
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class RecoveryItem extends Item {

    private int pointstoHealth;

    public RecoveryItem(int pointstoHealth, int id) {
        super(id);
        this.pointstoHealth = pointstoHealth;
    }


    @Override
    public int use() {
        return pointstoHealth;
    }

}
