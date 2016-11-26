package TextAdventures;

/**
 * TextAdventures.Map class
 *
 * @author Juan Antonio Rodicio López
 */
public class Map {

    private Room[][] rooms;
    private final int width;
    private final int height;

    Map(Room[][] rooms, int width, int height) {
        this.rooms = rooms;
        this.width = width;
        this.height = height;
    }

    Room[][] getRooms() {
        return rooms;
    }
    
    Room getRoom(int CoordX, int CoordY){
        return rooms[CoordX][CoordY];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

}


