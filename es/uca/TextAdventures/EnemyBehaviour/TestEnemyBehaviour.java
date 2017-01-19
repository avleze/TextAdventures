package es.uca.TextAdventures.EnemyBehaviour;

import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.RecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Map;
import es.uca.TextAdventures.MapLoader;
import es.uca.TextAdventures.Player.PlayerCharacter;
import es.uca.TextAdventures.Room;

import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by luisrozo on 19/01/17.
 */
public class TestEnemyBehaviour {
    private Set<Item> inventory;
    private WeaponItem sword;
    private RecoveryItem superPotion;
    private PlayerCharacter playerCharacter;
    private Map map;
    private Room room;
    private Attack attackAction;
    private Set<Action> playerActions;
    private Set<BattleAction> enemyActions;

    @BeforeAll
    public void initAll() throws Exception {
        this.inventory = new HashSet<>();
        this.sword = new WeaponItem(5, 0, 0);
        this.superPotion = new SuperRecoveryItem(new SimpleRecoveryItem(10, 1));
        inventory.add(sword);
        inventory.add(superPotion);

        this.playerCharacter = new PlayerCharacter("Juan", 1, 100, inventory, 10, 0, 0);

        MapLoader mapLoader = new MapLoader(playerCharacter);

        this.map = mapLoader.loadFromFile("map.xml");

        this.room = map.getRoom(playerCharacter.getXPosition(),
                playerCharacter.getYPosition());

        this.attackAction = new Attack("Attack.", playerCharacter, room.getEnemy());

        this.playerActions = new LinkedHashSet<>();
        this.playerActions.add(new Heal("Heal.", playerCharacter));
        this.playerActions.add(new RunAway("Run away.", playerCharacter));
        this.playerActions.add(attackAction);

        this.enemyActions = new LinkedHashSet<>();
        this.enemyActions.add(new Attack("Attack.", playerCharacter, room.getEnemy()));
        this.enemyActions.add(new Heal("Heal.", room.getEnemy()));
    }

    @Test
    public void testAggressiveEnemyBehaviour() {
        AggressiveEnemyBehaviour aggressive = new AggressiveEnemyBehaviour(enemyActions);

        assert(aggressive.getAction() instanceof Attack);
    }

    @Test
    public void testHealerEnemyBehaviour() {
        HealerEnemyBehaviour healer = new HealerEnemyBehaviour(enemyActions);

        assert(healer.getAction() instanceof Heal);
    }

    @Test
    public void testRandomEnemyBehaviour() {
        RandomEnemyBehaviour random = new RandomEnemyBehaviour(enemyActions);

        assert(random.getAction() instanceof Attack || random.getAction() instanceof Heal);
    }
}
