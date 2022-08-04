package com.traveler.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveler.jsonparser.Json;


import java.io.IOException;
import java.util.List;

public class Player {

    private String name;



    private int health;
    private List<Item> inventory;

    public Player() {
    }

    public Player(String name, int health, List<Item> inventory) {
        this.name = name;
        this.health = health;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player: ");
        sb.append("name=").append(name);
        sb.append(", health=").append(health);
        sb.append(", inventory=").append(inventory);
        sb.append('}');
        return sb.toString();
    }
}
