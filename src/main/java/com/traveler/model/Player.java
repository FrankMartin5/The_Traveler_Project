package com.traveler.model;


import java.util.List;

public class Player extends Item{

    private static List<Item> inventory = List.of();
    private String name;

    private int health = 100;
    private int exp = 0;



    private int lvl = 1;




    public Player() {
    }




    public Player(String name, int health, int exp, int lvl, List<Item> inventory) {
        this.name = name;
        this.health = health;
        this.exp = exp;
        this.lvl = lvl;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public static List<Item> getInventory() {
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
