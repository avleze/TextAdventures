package TextAdventures;

/**
 * 
 * This class represents a message of the game
 * @author Luis Gonzaga Rozo Bueno
 * @version 1.0
 */

public class Message {
	private String caption;
	private String message;
	
	/**
	 * Class constructor
	 * @param caption
	 * @param message
	 */
	public Message(String caption,String message){
		this.caption = caption;
		this.message = message;
	}
	
	/** 
	 * @return String This returns the message's caption.
	 */
	public String getCaption(){
		return caption;
	}
	
	/**
	 * @return String This returns the message.
	 */
	public String getMessage(){
		return message;
	}
}
