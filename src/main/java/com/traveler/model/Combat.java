package com.traveler.model;

import java.util.ArrayList;

import static com.traveler.model.Item.inventory;
import static com.traveler.model.Room.currentRoom;

public class Combat { // combat class that handles all aspects of combat
    ArrayList<String> friendly = new ArrayList<String>();
    Item key = new Item("key","opens the door to the crypt");
    Item antiShield = new Item("anti-shield","it may remove a certain impregnable shield");

    public void initialize() {
        friendly.add("elon");
        friendly.add("gnome");
    }

    public String cmdFight(String enemy) { // method that passes an enemy noun to start combat
        String result = "no fight";
        // first check to see if npc in room and if that npc's name matches enemy
        if (currentRoom.npc.size() > 0 && currentRoom.npc.get(0).name.equals(enemy)) {
            NPC enemyInRoom = currentRoom.npc.get(0);
            //check to verify enemy is not friendly
            if (friendly.contains(enemy)) {
                System.out.println("You can not fight " + enemy + ", they are friendly.");
            } else {// at this point enemy is in currentRoom and not friendly
                //start of combat with said enemy
                if (itemInInventory(enemyInRoom.item)) {
                    System.out.println(enemyInRoom.defeat);
                    if (enemyInRoom.name.equals("racumen")) {
                        result = "bosswin";
                    } else if (enemyInRoom.name.equals("orc")){
                        currentRoom.items.add(key);
                        result = "win";
                    } else if (enemyInRoom.name.equals("troll")) {
                        currentRoom.items.add(antiShield);
                        result = "win";
                    } else {
                        result = "win";
                    }
                } else {
                    System.out.println(enemyInRoom.win);
                    result = "loss";
                }
            }
        } else { // either no one in the room or enemy in the room does not match given name
            System.out.println(enemy + " is not in the room");
        }
        return result;
    }

    public static boolean itemInInventory(String itemName) {
        boolean res = false;
        for (Item item : inventory) {
            if (item.name.equals(itemName)) {
                res = true;
            }
        }
        return res;
    }

}