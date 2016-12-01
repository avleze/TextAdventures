package TextAdventures.es.uca.item;

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
