package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Player.Enemy;

import java.util.Set;

/**
 * This class represents a room of the map.
 *
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public class Room {
    private Message message;
    private Set<Action> actions;
    private Enemy enemy;
    private boolean treasureRoom;

    /**
     * Constructor.
     *
     * @param message Message of the room
     * @param actions Set the available actions within the room
     * @param enemy   The enemy in the room
     * @param treasureRoom Indicates wether this room is the treasure one or not.
     */
    public Room(Message message, Set<Action> actions, Enemy enemy, boolean treasureRoom) {
        this.message = message;
        this.actions = actions;
        this.enemy = enemy;
        this.treasureRoom = treasureRoom;
    }

    /**
     * Returns the message of the room.
     *
     * @return Message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Returns the set of the available actions within the room.
     *
     * @return Set<Action>
     */
    public Set<Action> getActions() {
        return actions;
    }

    public Action getAction(int index) {
        return (Action) actions.toArray()[index];
    }

    /**
     * Returns the room's enemy.
     *
     * @return Enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }
}
