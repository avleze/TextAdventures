
public class WeaponItem extends Item{

	private int Damage;
	
	public WeaponItem(int Damage) {
		this.Damage = Damage;
	}
	
	@Override
	public int use() {
		return Damage;
		
	}
}
