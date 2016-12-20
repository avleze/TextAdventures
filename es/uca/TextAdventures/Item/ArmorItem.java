package es.uca.TextAdventures.Item;

public class ArmorItem extends Item {

    private int defensePoints;

    public ArmorItem(int defensePoints, int id) {
        super(id);
        this.defensePoints = defensePoints;
    }

    public void decrease(double value) {
        this.defensePoints -= value;
    }

    public boolean isBroken() {
        return defensePoints <= 0;
    }

    public int use() {
        return defensePoints;
    }


}
