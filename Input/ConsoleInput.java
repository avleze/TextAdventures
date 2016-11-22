package Input;

import java.util.Scanner;

/**
 * Input.ConsoleInput
 * @author Antonio Vélez Estévez
 */
public class ConsoleInput implements InputHandler {
    @Override
    public int getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}