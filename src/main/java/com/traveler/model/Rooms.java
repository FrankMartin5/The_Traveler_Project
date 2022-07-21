package com.traveler.model;

public class Rooms {

    static class Room {
        String name;
        String desc;
        String north;
        String south;
        String east;
        String west;
        Room currentRoom;

        //constructor
        public Room(String name, String desc, String north, String south, String east, String west) {
            this.name = name;
            this.desc = desc;
            this.north = north;
            this.south = south;
            this.east = east;
            this.west = west;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public void cmdLook(String noun){
            System.out.println(currentRoom.south);

        }

        public void createRoom(){

        Room room1 = new Room("great_hall","main room, starting point of game" , "dining chamber",
                    "warlocks chamber", "torture chamber", "crypt");

        Room room2 = new Room("dining_chamber","north of great_hall. be careful soldier. " +
                "danger lurks ahead. be at alert for keys" , null,
                "great_hall", "ancient study", null);

        Room room3 = new Room("warlocks_quarters","main room" , null,
                null, null , "crypt");

        Room room4 = new Room("crypt","main room" , "dining chamber",
                "warlocks chamber", "torture chamber", "crypt");

        Room room5 = new Room("torture_chamber","main room" , "dining chamber",
                "warlocks chamber", "torture chamber", "crypt");

        }

        public static void main(String[] args) {

        }




    }
}