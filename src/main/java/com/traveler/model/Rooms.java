package com.traveler.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.List;

public class Rooms {

    String name;
    String desc;
    String item;
    String north;
    String south;
    String east;
    String west;
    Rooms currentRoom;

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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    static String roomsArray =
                    "[{\"name\" : \"great_hall\",\"desc\" : \"(Starting point of game. )You are standing in the great hall of Racumens Castle. there is a random troll in the east corner of the room. (suggest to talk to him) \" ,\"item\" : \"lantern\",\"south\" : \"warlocks chamber\" ,\"east\" : \"torture chamber\",\"north\" : \"dining chamber\",\"west\" : \"crypt\"}, " +
                    "{\"name\" : \"dining_chamber\" ,\"desc\" : \"you are north of great_hall. be careful soldier. An orc charges at you. (Here we battle for an item)\",\"item\" :\"key (locked) \" ,\"south\" : \" great hall\",\"east\" : \" ancient study\"}," +
                    "{\"name\" : \"crypt\" ,\"desc\" : \"Locked room. You need a key to get in. Perhaps you collected one in your deadly defeat of the orc in the dining chamber. Beware of traps.\" ,\"item\" : \"confusion potion , shield potion\" ,\"east\" : \"great hall\"}," +
                    "{\"name\" : \"torture_chamber\" ,\"desc\" : \"you are in the torture chamber. be careful soldier,danger lurks ahead \" ,\"south\" : \"torture chamber\" ,\"east\" : \" crypt\"}," +
                    "{\"name\" : \"warlocks_chamber\" ,\"desc\" : \"Boss room. Racumen lurks nearby\" ,\"north\" : \"great hall\" }," +
                    "{\"name\" : \"Ancient_study\" ,\"desc\" : \"Ancient study. Library of all the great books in the world \",\"west\" :\"dining chamber\"}]";

    }