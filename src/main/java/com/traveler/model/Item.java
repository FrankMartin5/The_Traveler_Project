package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import static com.traveler.model.Room.currentRoom;

public class Item {
    String name;
    String desc;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    // Creates inventory which carries Item objects
    public static List<Item> inventory;

    public Item() {

    }

    // method that reads from json file and loads inventory with Item objects
    public static void itemsFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type itemListType = new TypeToken<List<Item>>() {}.getType();
            Reader reader = new InputStreamReader(Item.class.getResourceAsStream("/inventory.json"));
            inventory = gson.fromJson(reader, itemListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lookInventory(){
        String res ="";
        if(inventory.size()>0){
            for(Item item:inventory){
                res += item.name + ",";
            }
        }
        return res;
    }

    // when command is "look <item>" returns desc
    public void cmdLook(String noun) {
        for (Item item : inventory) {
            if (item.name.equals(noun)) {
                System.out.println(item.desc);
                return;
            }
        }
        System.out.println(noun + " not found, you can only look at items in your inventory");
    }

    //This should take items from the
    public Optional<Item> cmdGetItem(String noun) {
        Optional<Item> requestedItem = Optional.empty();
        boolean itemNotAvailable = false;
        if (noun != null && !noun.isEmpty()) {
            if(inventory.size() == 0) return Optional.empty();
            for (int i = 0; i < inventory.size(); i++) {
                if(inventory.get(i).name.equals(noun)){
                    requestedItem = Optional.ofNullable(inventory.remove(i));
                    System.out.println("You now have " + requestedItem.get().name);
                }else{
                    itemNotAvailable = true;
                }
            }
            if(itemNotAvailable){
                System.out.println(requestedItem.get().name + " is not available.");
            }
        }
        return requestedItem;
    }

    //This should remove items from the Inventory and add to currentroom
    public void cmdDropItem(String droppedItem) {
        Optional<Item> requestedDropItem = Optional.empty();
        boolean playerDoesNotHaveItem = false;
        if (droppedItem != null) {
            for (int i = 0; i < inventory.size(); i++) {
                if(inventory.get(i).name.equals(droppedItem)){
                    requestedDropItem = Optional.ofNullable(inventory.remove(i));
                    requestedDropItem.ifPresent(this::addItemToRoom);
                    break;
                }else{
                    playerDoesNotHaveItem = true;
                }
            }
                if(playerDoesNotHaveItem){
                    System.out.println("You do not have " + droppedItem);
                }
        }
    }

    /*
     * this is a helper method for now, called by cmdDropItem
     * when the item is dropped by the player,
     * it will be added to the current room's inventory
     * */
    public void addItemToInventory(Item addItem) {
        inventory.add(addItem);
    }

    public void addItemToRoom(Item addItem){
        currentRoom.items.add(addItem);
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

//    This should get items IN the room and add to inventory
    public Optional<Item> cmdPickUpItem(String noun) {
        boolean itemNotPickedUp = false;
        var currentRoomItems =  currentRoom.items;
        Optional<Item> requestedPickedUpItem = Optional.empty();
        boolean foundItem = false;
        if (noun != null && !noun.isEmpty()) {
            if(currentRoomItems.size() == 0) return Optional.empty();
            for (int i = 0; i < currentRoomItems.size(); i++) {
                if (currentRoomItems.get(i).name.equals(noun)) {
                    requestedPickedUpItem = Optional.ofNullable((currentRoomItems.remove(i)));
                    System.out.println("You picked up " + requestedPickedUpItem.get().name);
                    requestedPickedUpItem.ifPresent(itemToAdd -> addItemToInventory(itemToAdd));
                    break;
                }else{
                    itemNotPickedUp = true;
                }
            }
            if(itemNotPickedUp){
                System.out.println(noun + " is not available.");
            }
        }
        return requestedPickedUpItem;
    }
}
