package com.traveler.model;

import com.traveler.view.Prompter;
import com.traveler.view.Text;

import java.util.ArrayList;
import java.util.Scanner;

import static com.traveler.model.Item.inventory;
import static com.traveler.model.Riddle.allRiddles;
import static com.traveler.model.Room.currentRoom;
import static com.traveler.model.Riddle.riddlesFromJsonToArray;

public class Combat { // combat class that handles all aspects of combat
    Prompter prompter = new Prompter(new Scanner(System.in));

    Text text = new Text();

    ArrayList<String> friendly = new ArrayList<String>();
    Item key = new Item("key", "opens the door to the crypt");
    Item antiShield = new Item("anti-shield", "it may remove a certain impregnable shield");

    public void initialize() {
        riddlesFromJsonToArray();
        friendly.add("elon");
        friendly.add("gnome");
    }

    public String cmdFight(String enemy) { // method that passes an enemy noun to start combat
        String result = "no fight";
        Riddle riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));

        // first check to see if npc in room and if that npc's name matches enemy
        if (currentRoom.npc.size() > 0 && currentRoom.npc.get(0).name.equals(enemy)) {
            NPC enemyInRoom = currentRoom.npc.get(0);
            //check to verify enemy is not friendly
            if (friendly.contains(enemy)) {
                System.out.println("You can not fight " + enemy + ", they are friendly.");
            } else {// at this point enemy is in currentRoom and not friendly
                //start of combat with said enemy
                switch (enemyInRoom.name) {
                    case "racumen":
                        System.out.println(riddle.getQuestion());
                        System.out.println(riddle.getHint());
                        String answer = prompter.prompt(text.askRiddle);
                        if (answer.equals(riddle.getAnswer())) {
                            System.out.println("You answered correctly, you win!");
                            result = "bosswin";
                        } else {
                            System.out.println("You answered incorrectly, you lose!");
                            result = "loss";
                        }
                        break;

                    case "orc":
                        System.out.println(riddle.getQuestion());
                        System.out.println(riddle.getHint());
                        answer = prompter.prompt(text.askRiddle);
                        if (answer.equals(riddle.getAnswer())) {
                            System.out.println("You answered correctly, you win!");
                            result = "win";
                        } else {
                            System.out.println("You answered incorrectly, you lose!");
                            result = "loss";
                        }
                        break;

                    case "troll":
                        System.out.println(riddle.getQuestion());
                        System.out.println(riddle.getHint());
                        answer = prompter.prompt(text.askRiddle);
                        if (answer.equals(riddle.getAnswer())) {
                            System.out.println("You answered correctly, you win!");
                            currentRoom.items.add(antiShield);
                            result = "win";
                        } else {
                            System.out.println("You answered incorrectly, you lose!");
                            result = "loss";
                        }
                        break;

                    default:
                        result = "win";
                        break;
                }
            }
        } else { // either no one in the room or enemy in the room does not match given name
            System.out.println(enemy + " is not in the room");
        }
        return result;
    }

    private String textParse(String input) {
        return input.trim().toLowerCase();
    }

    public static boolean itemInInventory(String itemName) {
        boolean res = false;
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                res = true;
            }
        }
        return res;
    }
}