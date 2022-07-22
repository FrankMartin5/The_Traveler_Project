package com.traveler.model;

import java.util.*;

class Items {
    private HashMap<String, String> item = new HashMap<>();

    public static List<HashMap<String, String>> allItems = new ArrayList<HashMap<String, String>>();

    public void setItem(String name, String desc) {
        item.put(name,desc);
        allItems.add(item);
    }

    Map test = new Hashtable<String, String>();

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
}