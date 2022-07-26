package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Item{
    String name;
    String desc;

    // Creates inventory which carries Item objects
    public static List<Item> inventory;

    // method that reads from json file and loads inventory with Item objects
    public static List<Item> fromJsonToArray() throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<Item>>() {}.getType();
        inventory = new Gson().fromJson(new FileReader("src/main/resources/items.json"), itemListType);
        return inventory;
    }

    // when command is "look <item>" returns desc
    public void cmdLook(String noun) {
        for(Item item:inventory){
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
        if(noun != null && !noun.isEmpty()){
            for (Item item: inventory
            ) {
                System.out.println(item.name);
                if(noun.equals(item.name)){
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

//    TODO: remove after testing
//    public static void main(String[] args) {
//        Item item = new Item();
//        try {
//            Item.fromJsonToArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        var returnedItem = item.cmdGetItem("key");
//    }
}