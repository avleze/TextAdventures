package es.uca.TextAdventures.Item.RecoveryItemDecorator;

/**
 * Created by juan on 21/12/16.
 */
public class SimpleRecoveryItem extends RecoveryItem {


    public SimpleRecoveryItem(int pointsToHealth, int id) {
        super(pointsToHealth, id);
    }

    @Override
    public int use() {
        return this.pointsToHealth;
    }
}
