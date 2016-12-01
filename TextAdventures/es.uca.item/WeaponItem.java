package TextAdventures.es.uca.item;

/**
 * es.uca.item.WeaponItem
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class WeaponItem extends Item{

	static final int TYPE_WATER = 0;
	static final int TYPE_FIRE = 1;
	static final int TYPE_WIND = 2;
	static final int TYPE_EARTH = 3;
	static final int TYPE_TEPIC = 4;

	private int Damage;
	private int Type;

	class TypeNotFoundException extends Exception { }

	public WeaponItem(int Damage, int Type) {
		if(Type > TYPE_TEPIC || Type < TYPE_WATER)
			throw TypeNotFoundException;

		this.Damage = Damage;
		this.type = Type;
	}

	public int getType() {
		return Type;
	}

	@Override
	public int use() {
		return Damage;

	}
}
