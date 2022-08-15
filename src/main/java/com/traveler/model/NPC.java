package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traveler.controller.TravelerApp;
import com.traveler.view.Prompter;
import com.traveler.view.Text;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NPC {
    Text text = new Text();

    private String name;
    private List<String> talk;
    private String item;
    private String win;
    private String defeat;
    private String answer = "";


    // Creates npc which carries NPC objects
    public static List<NPC> npcList;

    // method that reads from json file and loads npc with NPC objects
    public static void NPCArray() {
        try {
            Gson gson = new Gson();
            Type itemListType = new TypeToken<List<NPC>>() {}.getType();
            Reader reader = new InputStreamReader(NPC.class.getResourceAsStream("/npc.json"));
            npcList = new Gson().fromJson(reader, itemListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTalk() {
        return talk;
    }

    public void setTalk(List<String> talk) {
        this.talk = talk;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDefeat() {
        return defeat;
    }

    public void setDefeat(String defeat) {
        this.defeat = defeat;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", talk='" + talk + '\'' +
                '}';
    }
}
