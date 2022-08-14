package com.traveler.model;

import com.traveler.controller.TravelerApp;
import com.traveler.view.Prompter;
import com.traveler.view.Text;
import com.traveler.model.Taunts;

import java.util.ArrayList;
import java.util.Scanner;

import static com.traveler.model.Item.inventory;
import static com.traveler.model.Riddle.allRiddles;
import static com.traveler.model.Riddle.riddlesFromJsonToArray;
import static com.traveler.model.Room.getCurrentRoom;

public class Combat { // combat class that handles all aspects of combat
    Prompter prompter = new Prompter(new Scanner(System.in));

    Text text = new Text();

    private ArrayList<String> friendly;

//    TravelerView gui = TravelerView.getInstance();
//    TravelerApp gui = new TravelerApp();

    public Combat() {
    }

    public Combat(ArrayList<String> friendly) {
        this.friendly = friendly;
    }

    Item key = new Item("key", "opens the door to the crypt");
    Item antiShield = new Item("anti-shield", "it may remove a certain impregnable shield");

    public void initialize() {
        riddlesFromJsonToArray();
    }

//    public String cmdFight(String enemy) { // method that passes an enemy noun to start combat
//        String result = "no fight";
//        Riddle riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
//        if (getCurrentRoom().getNpc().size() > 0 && getCurrentRoom().getNpc().get(0).getName().equals(enemy)) {
//            NPC enemyInRoom = getCurrentRoom().getNpc().get(0);
//            //check to verify enemy is not friendly
//            if (friendly.contains(enemy)) {
//                System.out.println("You can not fight " + enemy + ", they are friendly.");
//            } else {// at this point enemy is in currentRoom and not friendly
//                //start of combat with said enemy
//                switch (enemyInRoom.getName()) {
//                    case "racumen":
//                        int boss_round = 3;
//                        int win_1 = 0;
//                        int lose_1 = 0;
//
//                        while (boss_round > 0 ) {
//                            System.out.println("You have " + boss_round + " rounds to complete.");
//                            System.out.println("Riddle: " + riddle.getQuestion());
//                            System.out.println("Hint: " + riddle.getHint());
//                            synchronized (Combat.class) {
//                                try {
//                                    Combat.class.wait();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
////                            gui.mainTextArea.setText("");
//                            String answer = gui.getInput();
//
//                            if (answer.equals(riddle.getAnswer())) {
//                                System.out.println("You win the round!");
//                                win_1++;
//                                boss_round--;
//                                riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
//                                if (win_1 == 2) {
//                                    System.out.println("You win the combat!");
//                                    result = "bosswin";
//                                    break;
//                                }
//                            } else {
//                                System.out.println("You lose the round!");
//                                Taunts.npcTaunts();
//                                boss_round--;
//                                lose_1++;
//                                if (lose_1 == 2) {
//                                    System.out.println("You lost two times in a row, you lose the combat!");
//                                    result = "lose";
//                                    break;
//                                }
//                                riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
//                            }
//
//                        }
//                        break;
//
//                    case "orc":
//                    case "troll":
//                        int regular_round = 3;
//                        int win_2 = 0;
//                        int lose_2 = 0;
//
//                        while (regular_round > 0 ) {
//                            System.out.println("You have " + regular_round + " rounds to complete.");
//                            System.out.println("Riddle: " + riddle.getQuestion());
//                            System.out.println("Hint: " + riddle.getHint());
//                            synchronized (Combat.class) {
//                                try {
//                                    Combat.class.wait();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
////                            gui.mainTextArea.setText("");
//                            String answer = gui.getInput();
//
//                            if (answer.equals(riddle.getAnswer())) {
//                                System.out.println("You win the round!");
//                                win_2++;
//                                regular_round--;
//                                riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
//                                if (win_2 == 2) {
//                                    System.out.println("You win the combat!");
//                                    result = "win";
//                                    break;
//                                }
//                            } else {
//                                System.out.println("You lose the round!");
//                                Taunts.npcTaunts();
//                                regular_round--;
//                                lose_2++;
//                                if (lose_2 == 2) {
//                                    System.out.println("You lost two times in a row, you lose the combat!");
//                                    result = "lose";
//                                    break;
//                                }
//                                riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
//                            }
//
//                        }
//                        break;
//
//                    default:
//                        result = "win";
//                        break;
//                }
//            }
//        } else { // either no one in the room or enemy in the room does not match given name
//            System.out.println(enemy + " is not in the room");
//        }
//        return result;
//    }

    public ArrayList<String> getFriendly() {
        return friendly;
    }

    public void setFriendly(ArrayList<String> friendly) {
        this.friendly = friendly;
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
