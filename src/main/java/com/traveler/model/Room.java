package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static com.traveler.model.Player.itemInInventory;


public class Room {

    private String name;
    private String desc;
    private List<Item> items;
    private List<NPC> npc;
    private String north;
    private String south;
    private String east;
    private String west;
    public static Room currentRoom;

    public static List<Room> allRooms;

    public Room() {
    }

    public Room(String name, String desc, List<Item> items, List<NPC> npc, String north, String south, String east, String west) {
        this.name = name;
        this.desc = desc;
        this.items = items;
        this.npc = npc;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public static void roomsFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type roomListType = new TypeToken<List<Room>>() {
            }.getType();
            Reader reader = new InputStreamReader(Room.class.getResourceAsStream("/rooms.json"));
            allRooms = new Gson().fromJson(reader, roomListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.out.println("Looking north, you see " + getCurrentRoom().getNorth());
                break;
            case "south":
                System.out.println("Looking south, you see " + getCurrentRoom().getSouth());
                break;
            case "east":
                System.out.println("Looking east, you see " + getCurrentRoom().getEast());
                break;
            case "west":
                System.out.println("Looking west, you see " + getCurrentRoom().getWest());
                break;
        }
    }

    // TODO: rename room to destination room, attempt to combine methods, (direction var included, with four if statements)
    public void goNorth() {
        for (Room room : allRooms) {
            if (room.getName().equals(getCurrentRoom().getNorth())) {
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
            if (targetRoom.npc.size() > 0 && targetRoom.npc.get(0).getName().equals(noun)) {
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
                itemList.append(i + 1).append(". ").append(currentRoom.items.get(i).getName())
                        .append(" --- ").append(currentRoom.items.get(i).getDesc()).append("\n");
            }
        }
        return itemList.toString();

    }

    // returns a string with the npc's name if npc is in the room
    public String getNpcInCurrentRoom() {
        String npcInRoom = "";
        if (currentRoom.npc.size() > 0) {
            npcInRoom = currentRoom.npc.get(0).getName();
        } else {
            npcInRoom = "NONE";
        }
        return npcInRoom;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<NPC> getNpc() {
        return npc;
    }

    public void setNpc(List<NPC> npc) {
        this.npc = npc;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static List<Room> getAllRooms() {
        return allRooms;
    }

    public static void setAllRooms(List<Room> allRooms) {
        Room.allRooms = allRooms;
    }

    @Override
    public String toString() { // desc should return items and npc in the room
        return "============================================\n" +
                "You are in the " + getName() + "\n" +
                getDesc() + "\n" +
                "--------------------------------------------\n" +
                "To the north is " + getNorth() + "\n" +
                "To the south is " + getSouth() + "\n" +
                "To the east is " + getEast() + "\n" +
                "To the west is " + getWest() + "\n" +
                "--------------------------------------------\n" +
                "Items in the room: \n" + getItemsInCurrentRoom() + "\n" +
                "NPC in the room: " + getNpcInCurrentRoom() + "\n";
    }

}
