package es.uca.TextAdventures.Item.RecoveryItemDecorator;

/**
 * Created by juan on 21/12/16.
 */
public abstract class RecoveryItemDecorator extends RecoveryItem {

    protected final RecoveryItem decoratedRecoveryItem;

    public RecoveryItemDecorator(RecoveryItem recoveryItem) {
        super(recoveryItem.pointsToHealth, recoveryItem.getId());
        this.decoratedRecoveryItem = recoveryItem;
    }

    @Override
    public int use() {
        return decoratedRecoveryItem.use();
    }
}
