package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Item {
    String name;
    String desc;

    // Creates inventory which carries Item objects
    public static List<Item> inventory;

    // method that reads from json file and loads inventory with Item objects
    public static List<Item> itemsFromJsonToArray() throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<Item>>() {
        }.getType();
        inventory = new Gson().fromJson(new FileReader("src/main/resources/items.json"), itemListType);
        return inventory;
    }

    // when command is "look <item>" returns desc
    public void cmdLook(String noun) {
        for (Item item : inventory) {
            if (item.name.equals(noun)) {
                System.out.println(item.desc);
                return;
            }
        }
        System.out.println(noun + " not found");
    }

    //Optional used to avoid returning null
    public Optional<Item> cmdGetItem(String noun) {
        Optional<Item> requestedItem = Optional.empty();
        if (noun != null && !noun.isEmpty()) {
            if(inventory.size() == 0) return Optional.empty();
            for (int i = 0; i < inventory.size(); i++) {
                if(inventory.get(i).name.equals(noun)){
                   requestedItem = Optional.ofNullable(inventory.remove(i));
                }
            }
        }
        System.out.println("You now have " + requestedItem.get().name);
        return requestedItem;
    }

    //Does inventory belong to the one player????
    public Optional<Item> cmdDropItem(String droppedItem) {
        Optional<Item> requestedDropItem = Optional.empty();
        if (droppedItem != null) {
            for (int i = 0; i < inventory.size(); i++) {
                if(inventory.size()> 0 && inventory.get(i).name.equals(droppedItem)){
                    requestedDropItem = Optional.ofNullable(inventory.remove(i));
                    requestedDropItem.ifPresent(e -> {
                        addItem(e);
                    });
                    break;
                }
            }
        }
        return requestedDropItem;
    }

    /*
     * this is a helper method for now, called by cmdDropItem
     * when the item is dropped by the player,
     * it will be added to the current room's inventory
     * */
    public void addItem(Item addItem) {
        try {
            Room.roomsFromJsonToArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Room.currentRoom.items.add(addItem);

        System.out.println(addItem.name + " was dropped.");
    }

    public String cmdUseItem(String noun) {
        String message = "";
//        TODO: check if item is in Players inventory
//        If item in players inventory and single use, remove item from players inventory
//        and return a string message to user
//        Else return message informing player item is not in players inventory
        /*
         * Will/there will be single use and multi-use items
         * If item is one time use, it will be removed from players inventory
         * items will a field designating item as single or multi-use item
         * */
        return message;
    }

    public Optional<Item> cmdPickUpItem(String noun) {
        var currentRoomItems =  Room.currentRoom.items;
        Optional<Item> requestedPickedUpItem = Optional.empty();
        boolean foundItem = false;
        if (noun != null && !noun.isEmpty()) {
            if(currentRoomItems.size() == 0) return Optional.empty();
            for (int i = 0; i < currentRoomItems.size(); i++) {

                System.out.println(currentRoomItems.size());
                if (currentRoomItems.get(i).name.equals(noun)) {
                    requestedPickedUpItem = Optional.ofNullable((currentRoomItems.remove(i)));
                    System.out.println("You picked up " + requestedPickedUpItem.get().name);
                    break;
                }
            }
        }
        return requestedPickedUpItem;
    }

}