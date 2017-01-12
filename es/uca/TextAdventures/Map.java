package es.uca.TextAdventures;

/**
 * TextAdventures.Map class
 *
 * @author Juan Antonio Rodicio López
 */
public class Map {

    private final int width;
    private final int height;
    private Room[][] rooms;

    Map(Room[][] rooms, int width, int height) {
        this.rooms = rooms;
        this.width = width;
        this.height = height;
    }

    Room[][] getRooms() {
        return rooms;
    }

    void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

}


