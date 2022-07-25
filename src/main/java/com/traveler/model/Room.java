package com.traveler.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;


public class Room {

    String name;
    String desc;
    String item;
    String north;
    String south;
    String east;
    String west;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getItem() {
        return item;
    }

    public String getNorth() {
        return north;
    }

    public String getSouth() {
        return south;
    }

    public String getEast() {
        return east;
    }

    public String getWest() {
        return west;
    }

    static String roomsArray =
                    "[{\"name\" : \"great_hall\",\"desc\" : \"(Starting point of game. )You are standing in the great hall of Racumens Castle. there is a random troll in the east corner of the room. (suggest to talk to him) \" ,\"item\" : \"lantern\",\"south\" : \"warlocks chamber\" ,\"east\" : \"torture chamber\",\"north\" : \"dining chamber\",\"west\" : \"crypt\"}, " +
                    "{\"name\" : \"dining_chamber\" ,\"desc\" : \"you are north of great_hall. be careful soldier. An orc charges at you. (Here we battle for an item)\",\"item\" :\"key (locked) \" ,\"south\" : \" great hall\",\"east\" : \" ancient study\"}," +
                    "{\"name\" : \"crypt\" ,\"desc\" : \"Locked room. You need a key to get in. Perhaps you collected one in your deadly defeat of the orc in the dining chamber. Beware of traps.\" ,\"item\" : \"confusion potion , shield potion\" ,\"east\" : \"great hall\"}," +
                    "{\"name\" : \"torture_chamber\" ,\"desc\" : \"you are in the torture chamber. be careful soldier,danger lurks ahead \" ,\"south\" : \"torture chamber\" ,\"east\" : \" crypt\"}," +
                    "{\"name\" : \"warlocks_chamber\" ,\"desc\" : \"Boss room. Racumen lurks nearby\" ,\"north\" : \"great hall\" }," +
                    "{\"name\" : \"Ancient_study\" ,\"desc\" : \"Ancient study. Library of all the great books in the world \",\"west\" :\"dining chamber\"}]";


    public void  getCurrentRoom() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println("test");
            Room[] room = mapper.readValue(roomsArray, Room[].class);

            for (Room r : room) {
                this.name = r.getName();
                this.desc = r.getDesc();
                this.item = r.getItem();
                this.north = r.getNorth();
                this.south = r.getSouth();
                this.east = r.getEast();
                this.west = r.getWest();
            }
            System.out.println(this.toString());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Rooms{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", item='" + item + '\'' +
                ", north='" + north + '\'' +
                ", south='" + south + '\'' +
                ", east='" + east + '\'' +
                ", west='" + west + '\'' +
                '}';
    }
}