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
        Reader reader = new InputStreamReader(Item.class.getResourceAsStream("/items.json"));
        inventory = gson.fromJson(reader, itemListType);
        reader.close();
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
            for (Item item : inventory
            ) {
                System.out.println(item.name);
                if (noun.equals(item.name)) {
                    var takeItem = inventory.remove(inventory.indexOf(item));
                    requestedItem = Optional.of(item);
                } else {
                    /*
                     * will return Optional.empty if noun is not in inventory
                     * */
                    requestedItem = Optional.empty();
                }
            }
        }
        return requestedItem;
    }

    public Optional<String> cmdDropItem(Item droppedItem) {
        Optional<String> requestedDropItem = Optional.empty();
        if (droppedItem != null) {
//           TODO: check if item in player inventory
//            addItem(Item addItem)
//           TODO: replace removing from Item inventory with Player inventory
            //code will be similar to cmdGetItem here
            //get from player, goes to room inventory
            //consider
            //Room.item is a list
            //current room
        }

        return requestedDropItem;
    }

    /*
     * this is a helper method for now, called by cmdDropItem
     * when the item is dropped by the player,
     * it will be added to the current room's inventory
     * */
    public String addItem(Item addItem) {
        inventory.add(addItem);
        return addItem.name + " was dropped";
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

//    TODO: remove after testing
//    public static void main(String[] args) {
//        Item item = new Item();
//        try {
//            Item.fromJsonToArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        var returnedItem = item.cmdGetItem("ksey");
//        //this is not the anticipated flow of the game.
//        //Only for testing
//        if(!returnedItem.isEmpty()){
//            var test = item.addItem(returnedItem.get());
//            System.out.println("TEST " + test);
//        }

//        var returnedItem = item.cmdGetItem("key");

//    }
}