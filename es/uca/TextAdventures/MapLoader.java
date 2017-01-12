package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.MovementAction;
import es.uca.TextAdventures.Action.StartBattleAction;
import es.uca.TextAdventures.Item.ArmorItem;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.HyperRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SimpleRecoveryItem;
import es.uca.TextAdventures.Item.RecoveryItemDecorator.SuperRecoveryItem;
import es.uca.TextAdventures.Item.WeaponItem;
import es.uca.TextAdventures.Player.Enemy;
import es.uca.TextAdventures.Player.Monster;
import es.uca.TextAdventures.Player.PlayerCharacter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * MapLoader class
 *
 * @author juan
 */
public class MapLoader {

    PlayerCharacter player;

    public MapLoader(PlayerCharacter player) {
        this.player = player;
    }

    private Message readMessage(NamedNodeMap msgAtributtes) {

        String caption = msgAtributtes.getNamedItem("caption").getNodeValue();
        String messages = msgAtributtes.getNamedItem("message").getNodeValue();
        return new Message(caption, messages);

    }

    private Action buildAction(Node action, Enemy enemy) {

        NamedNodeMap actionAtributtes = action.getAttributes();

        if (action.getNodeName().equals("StartBattleAction")) {
            String description = actionAtributtes.getNamedItem("description").getNodeValue();
            return new StartBattleAction(description, player, enemy);
        } else {
            String description = actionAtributtes.getNamedItem("description").getNodeValue();
            MovementAction.MovementType movementType
                    = MovementAction.MovementType.valueOf(actionAtributtes.getNamedItem("type").getNodeValue());
            return new MovementAction(description, player, movementType);
        }

    }

    private Set<Action> readActions(NodeList actionsNodes, Enemy enemy) {

        Set<Action> actions = new LinkedHashSet<>();

        for (int i = 0; i < actionsNodes.getLength(); ++i) {
            Node action = actionsNodes.item(i);

            if (action.getNodeType() == Node.ELEMENT_NODE) {

                actions.add(buildAction(action, enemy));
            }
        }
        return actions;
    }

    private Item buildItem(Node item) throws WeaponItem.TypeNotFoundException {

        NamedNodeMap itemAtributtes = item.getAttributes();

        if (item.getNodeName().equals("SimpleRecoveryItem")) {

            int idRecoveryItem =
                    Integer.parseInt(itemAtributtes.getNamedItem("id").getNodeValue());

            int pointsToHealth =
                    Integer.parseInt(itemAtributtes.getNamedItem("pointsToHealth").getNodeValue());

            return new SimpleRecoveryItem(pointsToHealth, idRecoveryItem);

        } else if (item.getNodeName().equals("SuperRecoveryItem")) {
            int idRecoveryItem =
                    Integer.parseInt(itemAtributtes.getNamedItem("id").getNodeValue());

            int pointsToHealth =
                    Integer.parseInt(itemAtributtes.getNamedItem("pointsToHealth").getNodeValue());

            return new SuperRecoveryItem(new SimpleRecoveryItem(pointsToHealth, idRecoveryItem));

        } else if (item.getNodeName().equals("HyperRecoveryItem")) {
            int idRecoveryItem =
                    Integer.parseInt(itemAtributtes.getNamedItem("id").getNodeValue());

            int pointsToHealth =
                    Integer.parseInt(itemAtributtes.getNamedItem("pointsToHealth").getNodeValue());

            return new HyperRecoveryItem(new SuperRecoveryItem(new SimpleRecoveryItem(pointsToHealth, idRecoveryItem)));

        } else if (item.getNodeName().equals("WeaponItem")) {

            int idWeapon = Integer.parseInt(itemAtributtes.getNamedItem("id").getNodeValue());
            int typeWeapon = Integer.parseInt(itemAtributtes.getNamedItem("type").getNodeValue());
            int damageWeapon = Integer.parseInt(itemAtributtes.getNamedItem("damage").getNodeValue());
            return new WeaponItem(damageWeapon, typeWeapon, idWeapon);

        } else {
            int idArmor = Integer.parseInt(itemAtributtes.getNamedItem("id").getNodeValue());
            int defensePoints =
                    Integer.parseInt(itemAtributtes.getNamedItem("defensePoints").getNodeValue());

            return new ArmorItem(idArmor, defensePoints);
        }
    }

    private Set<Item> readInventory(Node enemy) throws WeaponItem.TypeNotFoundException {

        Set<Item> inventory = new HashSet<>();
        NodeList inventoryNodes = enemy.getChildNodes();

        for (int i = 0; i < inventoryNodes.getLength(); ++i) {
            Node inventoryNode = inventoryNodes.item(i);

            if (inventoryNode.getNodeType() == Node.ELEMENT_NODE) {

                NodeList items = inventoryNode.getChildNodes();

                for (int j = 0; j < items.getLength(); j++) {
                    Node item = items.item(j);

                    if (item.getNodeType() == Node.ELEMENT_NODE)
                        inventory.add(buildItem(item));
                }

            }
        }

        return inventory;

    }

    private Enemy readEnemy(Node enemy) throws Enemy.TypeNotFoundException, WeaponItem.TypeNotFoundException {

        NamedNodeMap enemyAtributtes = enemy.getAttributes();

        String name = enemyAtributtes.getNamedItem("name").getNodeValue();
        int id = Integer.parseInt(enemyAtributtes.getNamedItem("id").getNodeValue());
        double healthPoints =
                Double.parseDouble(enemyAtributtes.getNamedItem("healthPoints").getNodeValue());
        int baseDamage = Integer.parseInt(enemyAtributtes.getNamedItem("baseDamage").getNodeValue());
        int typeEnemy = Integer.parseInt(enemyAtributtes.getNamedItem("type").getNodeValue());
        Set<Item> inventory = readInventory(enemy);

        //At the moment Monster, but we will include more Enemies
        return new Monster(name, id, healthPoints, inventory, baseDamage, typeEnemy);
    }

    private Room readRoom(Document doc, Node room, Room[][] rooms) throws Enemy.TypeNotFoundException, WeaponItem.TypeNotFoundException {

        NodeList roomChilds = room.getChildNodes();
        NamedNodeMap attributesForRoom = room.getAttributes();
        int row = Integer.parseInt(attributesForRoom.getNamedItem("row").getNodeValue());
        int col = Integer.parseInt(attributesForRoom.getNamedItem("col").getNodeValue());
        Boolean treasureRoom = Boolean.parseBoolean(attributesForRoom.getNamedItem("treasureRoom").getNodeValue());

        Message message = null;
        Set<Action> actions = null;
        Enemy enemy = null;

        for (int i = 0; i < roomChilds.getLength(); ++i) {

            Node roomChild = roomChilds.item(i);

            if (roomChild.getNodeType() == Node.ELEMENT_NODE) {
                switch (roomChild.getNodeName()) {
                    case "message":
                        NamedNodeMap msgAtributtes = roomChild.getAttributes();
                        message = readMessage(msgAtributtes);
                        break;

                    case "actions":
                        NodeList actionsNodes = roomChild.getChildNodes();
                        actions = readActions(actionsNodes, enemy);
                        break;

                    case "enemy":
                        enemy = readEnemy(roomChild);
                        break;

                }
            }
        }

        Room ret = new Room(message, actions, enemy, treasureRoom);
        rooms[row][col] = ret;
        return ret;

    }

    public Map loadFromFile(String file) throws WeaponItem.TypeNotFoundException, Enemy.TypeNotFoundException {
        int width = 0;
        int height = 0;
        Room[][] rooms = new Room[0][0];
        try {
            File filexml = new File(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filexml);

            doc.getDocumentElement().normalize();

            width = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
            height = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));
            rooms = new Room[width][height];

            NodeList nRooms = doc.getDocumentElement().getElementsByTagName("room");

            for (int i = 0; i < nRooms.getLength(); ++i) {
                Node room = nRooms.item(i);

                if (room.getNodeType() == Node.ELEMENT_NODE) {
                    readRoom(doc, room, rooms);
                }

            }

        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }

        return new Map(rooms, width, height);
    }
}
