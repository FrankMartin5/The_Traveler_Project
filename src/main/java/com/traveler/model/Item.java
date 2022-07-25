package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}