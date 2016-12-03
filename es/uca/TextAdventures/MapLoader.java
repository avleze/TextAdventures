package es.uca.TextAdventures;

import es.uca.TextAdventures.Action.Action;
import es.uca.TextAdventures.Action.BattleAction;
import es.uca.TextAdventures.Action.MovementAction;
import es.uca.TextAdventures.Item.Item;
import es.uca.TextAdventures.Item.RecoveryItem;
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
import java.util.Set;

/**
 * MapLoader class
 *
 * @author juan
 */
public class MapLoader {

    //Otra opción
    /*TextAdventures.PlayerCharacter player;
    public MapLoader(TextAdventures.PlayerCharacter player){
        this.player = player;
    }*/
    Map loadFromFile(String file, PlayerCharacter player) {
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

            NodeList nList = doc.getDocumentElement().getElementsByTagName("room");

            for (int i = 0; i < nList.getLength(); ++i) {

                Node nRoom = nList.item(i);
                if (nRoom.getNodeType() == Node.ELEMENT_NODE) {

                    NodeList nListChilds = nRoom.getChildNodes();
                    NamedNodeMap attributesForRoom = nRoom.getAttributes();
                    int row = Integer.parseInt(attributesForRoom.item(1).getNodeValue());
                    int col = Integer.parseInt(attributesForRoom.item(0).getNodeValue());

                    Message message = null;
                    Set<Action> actions = new HashSet<>();
                    Enemy enemy = null;

                    for (int j = 0; j < nListChilds.getLength(); j++) {
                        Node nRoomChild = nListChilds.item(j);

                        if (nRoomChild.getNodeType() == Node.ELEMENT_NODE) {

                            NamedNodeMap atributtes;

                            switch (nRoomChild.getNodeName()) {

                                case "message":
                                    atributtes = nRoomChild.getAttributes();
                                    String caption = atributtes.item(0).getNodeValue();
                                    String messages = atributtes.item(1).getNodeValue();
                                    message = new Message(caption, messages);
                                    break;

                                case "actions":
                                    NodeList actionsNodes = nRoomChild.getChildNodes();

                                    Set<BattleAction> battleacts = new HashSet<>();
                                    Set<MovementAction> movacts = new HashSet<>();

                                    for (int m = 0; m < actionsNodes.getLength(); ++m) {
                                        Node actionChild = actionsNodes.item(m);
                                        if (actionChild.getNodeType() == Node.ELEMENT_NODE) {
                                            atributtes = actionChild.getAttributes();
                                            String description = atributtes.item(0).getNodeValue();

                                            if (actionChild.getNodeName().equals("BattleAction")) {

                                                BattleAction action = new BattleAction(description, player);
                                                battleacts.add(action);

                                            } else {

                                                MovementAction action = new MovementAction(description, player);
                                                movacts.add(action);
                                            }
                                        }
                                    }

                                    for (BattleAction it : battleacts) {
                                        actions.add((Action) it);
                                    }

                                    for (MovementAction it : movacts) {
                                        actions.add((Action) it);
                                    }
                                    break;

                                case "enemy":
                                    atributtes = nRoomChild.getAttributes();
                                    String name = atributtes.item(3).getNodeValue();
                                    int id = Integer.parseInt(atributtes.item(2).getNodeValue());
                                    int healthPoints = Integer.parseInt(atributtes.item(1).getNodeValue());
                                    int baseDamage = Integer.parseInt(atributtes.item(0).getNodeValue());

                                    NodeList itemsNodes = nRoomChild.getChildNodes();
                                    Set<Item> inventory = new HashSet<>();

                                    for (int k = 0; k < itemsNodes.getLength(); ++k) {
                                        Node inventoryChilds = itemsNodes.item(k);
                                        if (inventoryChilds.getNodeType() == Node.ELEMENT_NODE) {
                                            if (inventoryChilds.getNodeName().equals("pointsToHealth")) {
                                                RecoveryItem item = new RecoveryItem(Integer.parseInt(atributtes.item(0).getNodeValue()));
                                                inventory.add(item);
                                            } else {
                                                WeaponItem item = new WeaponItem(Integer.parseInt(atributtes.item(0).getNodeValue()));
                                                inventory.add(item);
                                            }

                                        }
                                        if (name.equals("Monster")) {
                                            enemy = new Monster(name, id, healthPoints, inventory, baseDamage);
                                        }
                                    }
                                    break;
                            }

                        }

                        Room room = new Room(message, actions, enemy);
                        rooms[row][col] = room;
                    }
                }

            }

        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }

        map = new Map(rooms, width, height);
        return map;
    }
}
