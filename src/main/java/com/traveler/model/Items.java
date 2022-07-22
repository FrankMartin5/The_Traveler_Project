package com.traveler.model;

import java.util.*;

public class Items {
    private HashMap<String, String> item = new HashMap<>();

    public static List<HashMap<String, String>> allItems = new ArrayList<HashMap<String, String>>();

    public void setItem(String name, String desc) {
        item.put(name,desc);
        allItems.add(item);
    }

    Map test = new Hashtable<String, String>();

    public Items(){
        this.populateItem();
    }

    public static void getAllItems(){
        for (HashMap<String, String> item : allItems
        ) {
            System.out.println(item.keySet());
        }
    }

    public static void cmdLook(String noun) {
        for (HashMap<String, String> itemNoun : allItems
        ) {

            if(!itemNoun.containsKey(noun)){

                System.out.println(noun+": not available.");
                return;
            }
            if( itemNoun.containsKey(noun)){
                System.out.println(noun+": " + itemNoun.get(noun));
                return;
            }
        }
    }

    private void populateItem() {
        this.setItem("key", "opens door");
        this.setItem("shield", "blocks");
    }

//      TODO: delete this main method
//    public static void main(String[] args) {
//        Items i = new Items();
//        Items.cmdLook("key");
//    }
}