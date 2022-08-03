package com.traveler.model;

import java.util.List;

public class Characters {

    private int health;
    private int damage;
    private int shield;
    private List<Item> inventory;
    private Room room;

    public Characters() {
    }

    public Characters(int health, int damage, int shield, List<Item> inventory, Room room) {
        this.health = health;
        this.damage = damage;
        this.shield = shield;
        this.inventory = inventory;
        this.room = room;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Characters: ");
        sb.append(", health=").append(health);
        sb.append(", damage=").append(damage);
        sb.append(", shield=").append(shield);
        sb.append(", inventory=").append(inventory);
        sb.append(", room=").append(room);
        sb.append('}');
        return sb.toString();
    }
}
