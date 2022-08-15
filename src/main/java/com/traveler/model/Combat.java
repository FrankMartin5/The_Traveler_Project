package com.traveler.model;

import com.traveler.controller.TravelerApp;
import com.traveler.view.Prompter;
import com.traveler.view.Text;

import java.util.ArrayList;
import java.util.Scanner;

import static com.traveler.model.Item.inventory;
import static com.traveler.model.Riddle.riddlesFromJsonToArray;

public class Combat {

    Text text = new Text();

    private ArrayList<String> friendly;


    public Combat() {
    }

    public Combat(ArrayList<String> friendly) {
        this.friendly = friendly;
    }

    Item key = new Item("key", "opens the door to the crypt");
    Item antiShield = new Item("anti-shield", "it may remove a certain impregnable shield");

    public void initialize() {
        riddlesFromJsonToArray();
    }

    public ArrayList<String> getFriendly() {
        return friendly;
    }

    public void setFriendly(ArrayList<String> friendly) {
        this.friendly = friendly;
    }

    private String textParse(String input) {
        return input.trim().toLowerCase();
    }

    public static boolean itemInInventory(String itemName) {
        boolean res = false;
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                res = true;
            }
        }
        return res;
    }

}
