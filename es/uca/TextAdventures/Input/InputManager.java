package es.uca.TextAdventures.Input;

/**
 * Input.InputManager
 *
 * @author Antonio Vélez Estévez
 */
public class InputManager {
    private InputHandler inputHandler;

    public InputManager(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public int getInput() {
        return inputHandler.getInput();
    }
}