package com.traveler.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.traveler.model.Room.currentRoom;

public class Taunts {
    private static Random r = new Random();

    public static void orcTaunts() {
        try {
            ArrayList<String> dialogue = new ArrayList<>();
            dialogue.add("\"You're CLEARLY a waste o' good food\"\n");
            dialogue.add("\"HAH, AWFUL GUESS!\"\n");
            dialogue.add("\"Are ALL humans as dim-witted as you?\"\n");
            dialogue.add("\"He just looks at you disappointingly...\"\n");

            int randomItem = r.nextInt(dialogue.size());
            String randomElement = dialogue.get(randomItem);


            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("The Orc shakes his head. " + randomElement);
            TimeUnit.MILLISECONDS.sleep(750);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void trollTaunts() {
        try {
            ArrayList<String> dialogue = new ArrayList<>();
            dialogue.add("\"WHY YOU SO DUMB?\" it slaps the ground in anger\n");
            dialogue.add("\"HEHEHE WROOOOONG\"\n");
            dialogue.add("\"BOOOOO HUMAN, BOOOOOO! YOU'RE VERY BAD AT THIS!\"\n");
            dialogue.add("\"The Troll gives you a thumbs down of disapproval. A massive thumbs down at that.\"\n");

            int randomItem = r.nextInt(dialogue.size());
            String randomElement = dialogue.get(randomItem);

            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("The Troll cackles at you. " + randomElement);
            TimeUnit.MILLISECONDS.sleep(750);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void fbTaunts() {
        try {
            ArrayList<String> dialogue = new ArrayList<>();
            dialogue.add("\"Embarrassing.\"\n");
            dialogue.add("\"Is this the best you could muster against me after all this time?\"\n");
            dialogue.add("\"I'm surprised my earlier two minions didn't wipe you out first.\"\n");
            dialogue.add("\"Are you done yet?\"\n");

            int randomItem = r.nextInt(dialogue.size());
            String randomElement = dialogue.get(randomItem);


            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Racumen sighs. " + randomElement);
            TimeUnit.MILLISECONDS.sleep(750);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void npcTaunts(){
        switch (currentRoom.npc.get(0).name) {
            case "racumen":
                fbTaunts();
                break;
            case "troll":
                trollTaunts();
                break;
            case "orc":
                orcTaunts();
                break;
        }
    }

}