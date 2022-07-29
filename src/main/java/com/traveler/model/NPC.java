package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class NPC {
    String name;
    List<String> talk;


    // Creates npc which carries NPC objects
    public static List<NPC> npcList;

    // method that reads from json file and loads npc with NPC objects
    public static List<NPC> NPCArray() throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<NPC>>() {}.getType();
        Reader reader = new InputStreamReader(NPC.class.getResourceAsStream("/npc.json"));
        npcList = new Gson().fromJson(reader, itemListType);
        reader.close();
        return npcList;
    }

    // when command is "talk <npc>" returns talk
    public void cmdTalk(String noun) {
        Random rn = new Random();
        int maxNum = 3;
        int rand = rn.nextInt(maxNum);
        for (NPC i : npcList) {
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


