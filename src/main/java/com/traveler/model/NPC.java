package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class NPC {
    String name;
    String talk;

    // Creates npc which carries NPC objects
    public static List<NPC> npcList;

    // method that reads from json file and loads npc with NPC objects
    public static List<NPC> NPCArray() throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<NPC>>() {}.getType();
        npcList = new Gson().fromJson(new FileReader("src/main/resources/npc.json"), itemListType);
        return npcList;
    }

    // when command is "talk <npc>" returns talk
    public void cmdTalk(String noun) {
        for(NPC i: npcList){
            if (i.name.equals(noun)) {
                System.out.println(i.talk);
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


//    public static void main(String[] args) {
//
//        NPC npc1 = new NPC();
//        try {
//            NPCArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(npcList);
//        NPC.cmdTalk("elon musk");
//
//    }
}