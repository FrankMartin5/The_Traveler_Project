package com.traveler.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.traveler.model.Room.currentRoom;
import static com.traveler.model.Room.getCurrentRoom;

public class Taunts {
    private static Random r = new Random();
    

    public static ArrayList<String> genOrcDialogue(){
        ArrayList<String> orcDialogue = new ArrayList<>();
        orcDialogue.add("\"You're CLEARLY a waste o' good food\"\n");
        orcDialogue.add("\"HAH, AWFUL GUESS!\"\n");
        orcDialogue.add("\"Are ALL humans as dim-witted as you?\"\n");
        orcDialogue.add("\"He just looks at you disappointingly...\"\n");
        return orcDialogue;
    }

    public static ArrayList<String> genTrollDialogue(){
        ArrayList<String> trollDialogue = new ArrayList<>();
        trollDialogue.add("\"WHY YOU SO DUMB?\" it slaps the ground in anger\n");
        trollDialogue.add("\"HEHEHE WROOOOONG\"\n");
        trollDialogue.add("\"BOOOOO HUMAN, BOOOOOO! YOU'RE VERY BAD AT THIS!\"\n");
        trollDialogue.add("\"The Troll gives you a thumbs down of disapproval. A massive thumbs down at that.\"\n");
        return trollDialogue;
    }

    public static ArrayList<String> genFbDialogue(){
        ArrayList<String> fbDialogue = new ArrayList<>();
        fbDialogue.add("\"Embarrassing.\"\n");
        fbDialogue.add("\"Is this the best you could muster against me after all this time?\"\n");
        fbDialogue.add("\"I'm surprised my earlier two minions didn't wipe you out first.\"\n");
        fbDialogue.add("\"Are you done yet?\"\n");
        return fbDialogue;
    }

    public static void orcTaunts() {
        try {
            ArrayList<String> orcDialogue = genOrcDialogue();

            int randomItem = r.nextInt(orcDialogue.size());
            String randomElement = orcDialogue.get(randomItem);


            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("The Orc shakes his head. \n" + randomElement);
            orcDialogue.remove(String.valueOf(randomElement));
            TimeUnit.MILLISECONDS.sleep(750);
            

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public static void trollTaunts() {
        try {
            ArrayList<String> trollDialogue = genTrollDialogue();

            int randomItem = r.nextInt(trollDialogue.size());
            String randomElement = trollDialogue.get(randomItem);

            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("The Troll cackles at you. \n" + randomElement);

            TimeUnit.MILLISECONDS.sleep(750);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void fbTaunts() {
        try {
            ArrayList<String> fbDialogue = genFbDialogue();

            int randomItem = r.nextInt(fbDialogue.size());
            String randomElement = fbDialogue.get(randomItem);


            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Racumen sighs. \n" + randomElement);
            TimeUnit.MILLISECONDS.sleep(750);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void npcTaunts(){
        switch (getCurrentRoom().getNpc().get(0).getName()) {
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
