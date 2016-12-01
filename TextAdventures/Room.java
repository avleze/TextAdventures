package TextAdventures;
import java.util.*;

/**
 * This class represents a room of the map.
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.1
 */
public class Room {
	private Message message;
	private Set<Action> actions;
	private Enemy enemy;
	
	/**
	 * Constructor.
	 * @param message Message of the room
	 * @param actions Set the available actions within the room
	 * @param enemy The enemy in the room
	 */
	public Room(Message message, Set<Action> actions, Enemy enemy){
		this.message = message;
		this.actions = actions;
		this.enemy = enemy;
	}
	
	/**
	 * Returns the message of the room.
	 * @return Message
	 */
	public Message getMessage(){
		return message;
	}
	
	/**
	 * Returns the set of the available actions within the room.
	 * @return Set<Action>
	 */
	public Set<Action> getActions(){
		return actions;
	}
	
	/**
	 * Returns the room's enemy.
	 * @return Enemy
	 */
	public Enemy getEnemy(){
		return enemy;
	}
}
