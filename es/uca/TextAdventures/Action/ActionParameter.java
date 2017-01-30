package es.uca.TextAdventures.Action;

import es.uca.TextAdventures.Input.InputManager;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Output.OutputManager;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.PlayerCharacter;

import java.util.Set;

/**
 * @author Antonio Vélez Estévez.
 */
public class ActionParameter {
    private OutputManager output;
    private InputManager input;
    private Set<Action> playerActions;
    private Set<InventoryAction> inventoryActions;
    private PlayerCharacter playerCharacter;
    private Enemy enemy;
    private Item itemSelected;

    public ActionParameter(OutputManager output, InputManager input, Set<Action> playerActions, Set<InventoryAction> inventoryActions, PlayerCharacter playerCharacter, Enemy enemy, Item itemSelected) {
        this.output = output;
        this.input = input;
        this.playerActions = playerActions;
        this.inventoryActions = inventoryActions;
        this.playerCharacter = playerCharacter;
        this.enemy = enemy;
        this.itemSelected = itemSelected;
    }

    public OutputManager getOutput() {
        return output;
    }

    public InputManager getInput() {
        return input;
    }

    public Set<Action> getPlayerActions() {
        return playerActions;
    }

    public Set<InventoryAction> getInventoryActions() {
        return inventoryActions;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Item getItemSelected() {
        return this.itemSelected;
    }

    public void setItemSelected(Item itemSelected) {
        this.itemSelected = itemSelected;
    }
}
