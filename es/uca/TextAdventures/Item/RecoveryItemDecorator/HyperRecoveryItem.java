package es.uca.TextAdventures.Item.RecoveryItemDecorator;

/**
 * Created by juan on 21/12/16.
 */
public class HyperRecoveryItem extends RecoveryItemDecorator {

    public HyperRecoveryItem(RecoveryItem recoveryItem) {
        super(recoveryItem);
    }

    public int use() {
        return super.use() + 10;
    }
}
