package es.uca.TextAdventures.Item;

/**
 * es.uca.item.WeaponItem
 *
 * @author Manuel Rodríguez-Sánchez Guerra
 */

public class WeaponItem extends Item {

    static final int TYPE_WATER = 0;
    static final int TYPE_FIRE = 1;
    static final int TYPE_WIND = 2;
    static final int TYPE_EARTH = 3;
    static final int TYPE_TEPIC = 4;

    private int damage;
    private int type;

    class TypeNotFoundException extends Exception {
    }

    public WeaponItem(int damage, int type, int id) {
        super(id);
        try{
        if (type > TYPE_TEPIC || type < TYPE_WATER){
            TypeNotFoundException exceptionType = new TypeNotFoundException();
            throw exceptionType;
        }
        }catch(TypeNotFoundException e){
            /*Do something*/
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
}
