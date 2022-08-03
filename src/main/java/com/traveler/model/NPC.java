package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import static com.traveler.model.Room.currentRoom;

public class NPC {
    String name;
    List<String> talk;
    String item;
    String win;
    String defeat;


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

    // when command is "talk <npc>" returns talk
    public void cmdTalk(String noun) {
        Random rn = new Random();
        int maxNum = 3;
        int rand = rn.nextInt(maxNum);
        for (NPC i : currentRoom.npc) {
            if (i.name.equals(noun)) {
                System.out.println(i.talk.get(rand));
                return;
            }
        }
        System.out.println(noun + " not found");
    }

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", talk='" + talk + '\'' +
                '}';
    }
}


