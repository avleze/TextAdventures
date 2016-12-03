package es.uca.TextAdventures.Item;

public class ArmorItem extends Item {

    private int defensePoints;

    public ArmorItem (int defensePoints, int id) {
        super(id);
        this.defensePoints = defensePoints;
    }

    public int use() {
        return defensePoints;
    }


}
