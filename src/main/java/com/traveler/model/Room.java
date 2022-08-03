package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static com.traveler.model.Combat.itemInInventory;


public class Room {

    String name;
    String desc;
    List<Item> items;
    List<NPC> npc;
    String north;
    String south;
    String east;
    String west;
    static Room currentRoom;

    public static List<Room> allRooms;

    public static void roomsFromJsonToArray() throws IOException {
        Gson gson = new Gson();
        Type roomListType = new TypeToken<List<Room>>() {
        }.getType();
        Reader reader = new InputStreamReader(Objects.requireNonNull(Room.class.getResourceAsStream("/rooms.json")));
        allRooms = new Gson().fromJson(reader, roomListType);
        reader.close();
    }

    // a method that returns current room info, aka toString
    public static void cmdRoomInfo() {
        System.out.println(currentRoom.toString());
    }

    // method cmdGo that changes the currentRoom to a corresponding room
    public void cmdGo(String noun) {
        switch (noun) {
            case "north":
                System.out.println("Attempting to go north\n");
                goNorth();
                break;
            case "south":
                System.out.println("Attempting to go south\n");
                goSouth();
                break;
            case "east":
                System.out.println("Attempting to go east\n");
                goEast();
                break;
            case "west":
                System.out.println("Attempting to go west\n");
                if (currentRoom.name.equals("great hall")) {
                    goCrypt();
                } else {
                    goWest();
                }
                break;
        }
    }

    // TODO: a method that returns what they see in that direction
    public void cmdLook(String noun) {
        switch (noun) {
            case "north":
                System.out.println("Looking north, you see " + currentRoom.north);
                break;
            case "south":
                System.out.println("Looking south, you see " + currentRoom.south);
                break;
            case "east":
                System.out.println("Looking east, you see " + currentRoom.east);
                break;
            case "west":
                System.out.println("Looking west, you see " + currentRoom.west);
                break;
        }
    }

    // TODO: rename room to destination room, attempt to combine methods, (direction var included, with four if statements)
    public void goNorth() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.north)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go North");
    }

    public void goSouth() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.south)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go South");
    }

    public void goEast() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.east)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go East");
    }

    public void goWest() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.west)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go West");
    }

    public void goCrypt() {
        if (!itemInInventory("key")){
            System.out.println("The door to the crypt is locked, you need a key");
        } else {
            goWest();
        }
    }

    // removes NPC from allRooms, should update current room
    public void removeNPC(String noun) {
        for (Room targetRoom : allRooms) {
            if (targetRoom.npc.size() > 0 && targetRoom.npc.get(0).name.equals(noun)) {
                targetRoom.npc.remove(0);
            }
        }
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
        System.out.println(currentRoom.toString());
    }

    // method that refreshes the current room after defeating an enemy
    public void refreshCurrentRoom() {
        for (Room sameRoom : allRooms) {
            // you have to grab the name because currentRoom and allRoom
            // are not equal with removing of npc
            if (sameRoom.name.equals(currentRoom.name)) {
                setCurrentRoom(sameRoom);
            }
        }
    }

    public String getItemsInCurrentRoom() {
        StringBuilder itemList = new StringBuilder();

        if (currentRoom.items.size() == 0) {
            itemList.append("-----There are no items in this room-----");
        } else {
            for (int i = 0; i < currentRoom.items.size(); i++) {
                itemList.append(i + 1).append(". ").append(currentRoom.items.get(i).name)
                        .append(" --- ").append(currentRoom.items.get(i).desc).append("\n");
            }
        }
        return itemList.toString();

    }

    // returns a string with the npc's name if npc is in the room
    public String getNpcInCurrentRoom() {
        String npcInRoom = "";
        if (currentRoom.npc.size() > 0) {
            npcInRoom = currentRoom.npc.get(0).name;
        } else {
            npcInRoom = "NONE";
        }
        return npcInRoom;
    }

    @Override
    public String toString() { // desc should return items and npc in the room
        return "============================================\n" +
                "You are in the " + name + "\n" +
                desc + "\n" +
                "--------------------------------------------\n" +
                "To the north is " + north + "\n" +
                "To the south is " + south + "\n" +
                "To the east is " + east + "\n" +
                "To the west is " + west + "\n" +
                "--------------------------------------------\n" +
                "Items in the room: \n" + getItemsInCurrentRoom() + "\n" +
                "NPC in the room: " + getNpcInCurrentRoom() + "\n";
    }

}