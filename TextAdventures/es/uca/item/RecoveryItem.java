package TextAdventures.es.uca.item;

/**
 * es.uca.item.RecoveryItem
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class RecoveryItem extends Item{

	private int pointstoHealth;

	public RecoveryItem(int pointstoHealth) {
		this.pointstoHealth = pointstoHealth;
	}


	@Override
	public int use() {
		return pointstoHealth;
	}

}
