package es.uca.TextAdventures

import TextAdventures.Player.PlayerCharacter;
import TextAdventures.Item.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Mock de la base de datos.
 *
 * @author juan
 */
public class MockDB {

    //Method to add an Attr to Element
    private void createAttr(Document doc, Element elementToAddAttr,
            String attribute, String valueForAttr) {

        Attr attributeToAdd = doc.createAttribute(attribute);
        attributeToAdd.setValue(valueForAttr);
        elementToAddAttr.setAttributeNode(attributeToAdd);

    }

    void saveCurrentGame(PlayerCharacter player) {
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("SavedPlayer");

            doc.appendChild(rootElement);

            Element playerElement = doc.createElement("PlayerCharacter");

            rootElement.appendChild(playerElement);

            createAttr(doc, playerElement, "name", player.getName());

            createAttr(doc, playerElement, "id", Integer.toString(player.getId()));

            createAttr(doc, playerElement, "healthPoints",
                    Integer.toString(player.getHealthPoints()));

            Element playerInventory = doc.createElement("Inventory");
            playerElement.appendChild(playerInventory);

            Iterator<Item> it = player.getInventory().iterator();

            WeaponItem weaponItem;
            RecoveryItem recoveryItem;
            ArmorItem armorItem;

            //Saving the Inventory
            while (it.hasNext()) {
                Item item = it.next();
                if (item.getClass().getSimpleName().equals("WeaponItem")) {

                    weaponItem = (WeaponItem) item;
                    Element weapon = doc.createElement("WeaponItem");

                    createAttr(doc, weapon, "damage", Integer.toString(weaponItem.use()));
                    createAttr(doc, weapon, "type", Integer.toString(weaponItem.getType()));
                    createAttr(doc, weapon, "id", Integer.toString(weaponItem.getId()));
                    
                    playerInventory.appendChild(weapon);

                } else if(item.getClass().getSimpleName().equals("RecoveryItem")){
                    recoveryItem = (RecoveryItem) item;
                    Element potion = doc.createElement("RecoveryItem");

                    createAttr(doc, potion, "pointsToHealth",
                            Integer.toString(recoveryItem.use()));
                    createAttr(doc, potion, "id",Integer.toString(recoveryItem.getId()));
                    
                    playerInventory.appendChild(potion);
                }else{
                    armorItem = (ArmorItem) item;
                    Element armor = doc.createElement("ArmorItem");
                    
                    createAttr(doc,armor,"defensePoints",
                                Integer.toString(armorItem.use()));
                    createAttr(doc,armor,"id",
                                Integer.toString(armorItem.getId()));
                    playerInventory.appendChild(armor);
                }

            }

            createAttr(doc, playerElement, "baseDamage", Integer.toString(player.getBaseDamage()));

            createAttr(doc, playerElement, "xPosition",
                    Integer.toString(player.getXPosition()));

            createAttr(doc, playerElement, "yPosition",
                    Integer.toString(player.getYPosition()));

            TransformerFactory transformerFactory
                    = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("GameSaved.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            /*StreamResult consoleResult
                    = new StreamResult(System.out);
            transformer.transform(source, consoleResult);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PlayerCharacter getSavedPlayer(Node player){
        NamedNodeMap playerAttributes = player.getAttributes();

        int playerBaseDamage = Integer.parseInt(playerAttributes.item(0).getNodeValue());
        int playerHealthPoints = Integer.parseInt(playerAttributes.item(1).getNodeValue());
        int playerId = Integer.parseInt(playerAttributes.item(2).getNodeValue());
        String playerName = playerAttributes.item(3).getNodeValue();
        int playerXPosition = Integer.parseInt(playerAttributes.item(4).getNodeValue());
        int playerYPosition = Integer.parseInt(playerAttributes.item(5).getNodeValue());

        NodeList Inventory = player.getChildNodes();

        WeaponItem weaponItem = null;
        RecoveryItem recoveryItem;
        ArmorItem armorItem;
        Set<Item> playerInventory = new HashSet<>();

        NodeList items = Inventory.item(0).getChildNodes();

        for (int i = 0; i < items.getLength(); ++i) {
            Node item = items.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap itemAttributes = item.getAttributes();

                if (itemAttributes.item(2).getNodeName().equals("damage")) {
                    
                    int damageSaved = Integer.parseInt(itemAttributes.item(2).getNodeValue());
                    int type = Integer.parseInt(itemAttributes.item(1).getNodeValue());
                    int id = Integer.parseInt(itemAttributes.item(0).getNodeValue());
                    
                    try{
                    weaponItem = new WeaponItem(id,type,damageSaved);
                    }catch(WeaponItem.TypeNotFoundException e){}
                    
                    playerInventory.add(weaponItem);
                } else if(itemAttributes.item(1).getNodeName().equals("pointsToHealth")){
                    int pointsToHealth = Integer.parseInt(itemAttributes.item(1).getNodeValue());
                    int id = Integer.parseInt(itemAttributes.item(0).getNodeValue());
                    
                    recoveryItem = new RecoveryItem(pointsToHealth, id);
                    playerInventory.add(recoveryItem);
                }else{
                    int defensePoints = Integer.parseInt(itemAttributes.item(1).getNodeValue());
                    int id = Integer.parseInt(itemAttributes.item(0).getNodeValue());
                    
                    armorItem = new ArmorItem(defensePoints,id);
                    playerInventory.add(armorItem);
                }
            }
        }

        PlayerCharacter playerSaved;
        playerSaved = new PlayerCharacter(playerName,
                playerId,
                playerHealthPoints,
                playerInventory,
                playerBaseDamage,
                playerXPosition,
                playerYPosition);

        return playerSaved;
    }

    PlayerCharacter loadLastGame() {

        PlayerCharacter savedPlayer = null;

        try {
            File gameSavedXml = new File("GameSaved.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(gameSavedXml);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("PlayerCharacter");

            Node player = nList.item(0);
            if (player.getNodeType() == Node.ELEMENT_NODE) {
                savedPlayer = getSavedPlayer(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No existe ninguna partida guardada");
        }
        return savedPlayer;
    }

}
