package es.uca.TextAdventures.Item;

/**
 * es.uca.item.WeaponItem
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class WeaponItem extends Item {

    private static final int TYPE_WATER = 0;
    private static final int TYPE_FIRE = 1;
    private static final int TYPE_WIND = 2;
    private static final int TYPE_EARTH = 3;
    private static final int TYPE_TEPIC = 4;

    private int damage;
    private int type;

    public WeaponItem(int damage, int type, int id) throws TypeNotFoundException {
        super(id);
        if (type > TYPE_TEPIC || type < TYPE_WATER) {
            TypeNotFoundException exceptionType = new TypeNotFoundException();
            throw exceptionType;
        }
        this.damage = damage;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public int use() {
        return damage;

    }

    public class TypeNotFoundException extends Exception {
    }
}
