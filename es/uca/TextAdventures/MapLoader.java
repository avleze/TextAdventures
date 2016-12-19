package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.*;
import es.uca.TextAdventures.Item.*;
import es.uca.TextAdventures.Player.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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

        String caption = msgAtributtes.item(0).getNodeValue();
        String messages = msgAtributtes.item(1).getNodeValue();
        return new Message(caption, messages);

    }

    private Action buildAction(Node action) {

        NamedNodeMap actionAtributtes = action.getAttributes();

        if (action.getNodeName().equals("StartBattleAction")) {
            String description = actionAtributtes.item(0).getNodeValue();
            return new StartBattleAction(description, player);
        } else {
            String description = actionAtributtes.item(1).getNodeValue();
            MovementAction.movementType movementType
                    = MovementAction.movementType.valueOf(actionAtributtes.item(0).getNodeValue());
            return new MovementAction(description, player, movementType);
        }

    }

    private Set<Action> readActions(NodeList actionsNodes) {

        Set<Action> actions = new HashSet<>();

        for (int i = 0; i < actionsNodes.getLength(); ++i) {
            Node action = actionsNodes.item(i);

            if (action.getNodeType() == Node.ELEMENT_NODE) {

                actions.add(buildAction(action));
            }
        }
        return actions;
    }

    private Item buildItem(Node item) throws WeaponItem.TypeNotFoundException {

        NamedNodeMap itemAtributtes = item.getAttributes();

        if (item.getNodeName().equals("RecoveryItem")) {

            int idRecoveryItem = Integer.parseInt(itemAtributtes.item(1).getNodeValue());
            int pointsToHealth = Integer.parseInt(itemAtributtes.item(0).getNodeValue());
            return new RecoveryItem(pointsToHealth, idRecoveryItem);

        } else if (item.getNodeName().equals("WeaponItem")) {

            int idWeapon = Integer.parseInt(itemAtributtes.item(2).getNodeValue());
            int typeWeapon = Integer.parseInt(itemAtributtes.item(1).getNodeValue());
            int damageWeapon = Integer.parseInt(itemAtributtes.item(0).getNodeValue());
            return new WeaponItem(damageWeapon, typeWeapon, idWeapon);

        } else {
            int idArmor = Integer.parseInt(itemAtributtes.item(1).getNodeValue());
            int defensePoints = Integer.parseInt(itemAtributtes.item(0).getNodeValue());

            return new ArmorItem(idArmor, defensePoints);
        }
    }

    private Set<Item> readInventory(Node enemy) throws WeaponItem.TypeNotFoundException {

        Set<Item> inventory = new HashSet<>();
        NodeList itemsNodes = enemy.getChildNodes();

        for (int i = 0; i < itemsNodes.getLength(); ++i) {
            Node item = itemsNodes.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE) {
                inventory.add(buildItem(item));
            }
        }

        return inventory;

    }

    private Enemy readEnemy(Node enemy) throws WeaponItem.TypeNotFoundException {

        NamedNodeMap enemyAtributtes = enemy.getAttributes();

        String name = enemyAtributtes.item(4).getNodeValue();
        int id = Integer.parseInt(enemyAtributtes.item(3).getNodeValue());
        int healthPoints = Integer.parseInt(enemyAtributtes.item(2).getNodeValue());
        int baseDamage = Integer.parseInt(enemyAtributtes.item(1).getNodeValue());
        int typeEnemy = Integer.parseInt(enemyAtributtes.item(0).getNodeValue());
        Set<Item> inventory = readInventory(enemy);
        
        //At the moment Monster, but we will include more Enemies
        return new Monster(name,id,healthPoints, inventory, baseDamage, typeEnemy);
    }

    private Room readRoom(Document doc, Node room, int row, int col) throws WeaponItem.TypeNotFoundException {

        NodeList roomChilds = room.getChildNodes();
        NamedNodeMap attributesForRoom = room.getAttributes();
        row = Integer.parseInt(attributesForRoom.item(1).getNodeValue());
        col = Integer.parseInt(attributesForRoom.item(0).getNodeValue());

        Message message = null;
        Set<Action> actions = new HashSet<>();
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
                        actions = readActions(actionsNodes);
                        break;

                    case "enemy":
                        enemy = readEnemy(roomChild);
                        break;

                }
            }
        }

        return new Room(message, actions, enemy);

    }

    Map loadFromFile(String file) throws WeaponItem.TypeNotFoundException {
        Map map;
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
                    int row = 0;
                    int col = 0;
                    Room roomReaded = readRoom(doc, room, row, col);
                    rooms[row][col] = roomReaded;
                }

            }

        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }

        return new Map(rooms, width, height);
    }
}