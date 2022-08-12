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
    private String name;
    private String desc;


    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    // Creates inventory which carries Item objects
    public static List<Item> inventory;

    public Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    // method that reads from json file and loads inventory with Item objects
    public static void itemsFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type itemListType = new TypeToken<List<Item>>() {}.getType();
            Reader reader = new InputStreamReader(Objects.requireNonNull(Item.class.getResourceAsStream("/inventory.json")));
            inventory = gson.fromJson(reader, itemListType);
            reader.close();
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
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


    //    This should get items IN the room and add to inventory
    public void cmdPickUpItem(String noun) {
        boolean itemNotPickedUp = false;
        var currentRoomItems =  currentRoom.items;
        Optional<Item> requestedPickedUpItem = Optional.empty();
        Optional<Item> foundItem = Optional.empty();
        if (noun != null && !noun.isEmpty()) {
            for (int i = 0; i < currentRoomItems.size(); i++) {
                if (currentRoomItems.get(i).name.equals(noun)) {
                    requestedPickedUpItem = Optional.ofNullable((currentRoomItems.remove(i)));
                    System.out.println("You picked up " + requestedPickedUpItem.get().name);
                    requestedPickedUpItem.ifPresent(addItem -> Player.getInventory().add(addItem));
                    break;
                }else{
                    itemNotPickedUp = true;
                }
            }
            if(itemNotPickedUp){
                System.out.println(noun + " is not available.");
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
