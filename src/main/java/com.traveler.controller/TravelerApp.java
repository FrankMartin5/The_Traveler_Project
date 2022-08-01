package com.traveler.controller;

import com.traveler.model.*;
import com.traveler.view.Prompter;
import com.traveler.view.SplashScreens;
import com.traveler.view.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.traveler.model.Item.inventory;
import static com.traveler.model.Item.itemsFromJsonToArray;
import static com.traveler.model.NPC.NPCArray;
import static com.traveler.model.Room.*;
import static com.traveler.view.Map.cmdMap;

class TravelerApp {
    private boolean gameOver = false;
    Prompter prompter = new Prompter(new Scanner(System.in));
    Room room = new Room();
    Item item = new Item();
    NPC npc = new NPC();
    Combat combat = new Combat();
    Text text = new Text();

    // dir carries directions for parsing
    ArrayList<String> dir = new ArrayList<String>();

    //    initialize calls methods that is needed before game starts
    public void initialize() throws IOException {
        dir.add("north");
        dir.add("south");
        dir.add("west");
        dir.add("east");
        itemsFromJsonToArray();
        roomsFromJsonToArray();
        NPCArray();
        combat.initialize();
        welcome();
        promptForNewGame(); // sets gameOver
    }

    // start called from promptForNewGame(), main part of game
    public void start() {
        while (!gameOver) {
            // command is the main prompt that dictates flow of game
            String command = prompter.prompt(text.prompt);
            // TODO: place else if statements inside switch case
            if (textParse(command).equals("help")) {
                System.out.println(text.help);
            } else if (textParse(command).equals("inventory")) {
                System.out.println(item.lookInventory());
            } else if (textParse(command).equals("map")) {
                cmdMap();
            } else if (!textParse(command).contains(" ")) {
                System.out.println("You can't do that");
                System.out.println(text.help);
            } else {
                String verb = verbParse(command);
                String noun = nounParse(command);
                // switch case to direct verb to the correct class
                switch (verb) {
                    case "quit":
                        if (noun.equals("game")) {
                            end();
                        } else {
                            wrongCmd();
                        }
                        break;
                    case "room":
                        if (noun.equals("info")) {
                            cmdRoomInfo();

                        } else {
                            wrongCmd();
                        }
                        break;
                    case "go":
                        // TODO: if unrecognized noun, handle error
                        room.cmdGo(noun);
                        break;
                    // look verb can be Items or Rooms, calls items if not 'north, west, south, east'
                    case "look":
                        //if noun is in dir arraylist, means it is a direction
                        if (dir.contains(noun)) {
                            room.cmdLook(noun);
                        } else { // else it is an item noun
                            item.cmdLook(noun);
                        }
                        break;
                    case "talk":
                        npc.cmdTalk(noun);
                        break;
                    case "fight":
                        String combatResult = combat.cmdFight(noun);
                        if (combatResult.equals("win")) {
                            room.removeNPC(noun);
                            room.refreshCurrentRoom();
                        } else if (combatResult.equals("loss")) {
                            end();
                        } else if(combatResult.equals("bosswin")){
                            endWin();
                        }
                        break;
                    case "get":
                        item.cmdPickUpItem(noun);
                        room.refreshCurrentRoom();
                        break;
                    case "drop":
                        item.cmdDropItem(noun);
                        room.refreshCurrentRoom();
                        break;
                    default:
                        wrongCmd();
                        break;
                }
            }
        }
    }

    public void wrongCmd() {
        System.out.println("You can't do that");
        System.out.println(text.help);
    }

    // game end method that handles end of game
    public void end() {
        setGameOver(true);
        System.out.println(text.gameOver);
    }

    public void endWin() {
        setGameOver(true);
        SplashScreens.win();
        System.out.println(text.gameWin);
    }

    private void welcome() {
        SplashScreens.art();
    }

    // prompts for new game or saved game
    private void promptForNewGame() {
        String start = prompter.prompt(text.newGamePrompt);
        // initialization for starting new game
        if (textParse(start).equals("n")) {
            System.out.println(text.newGame);
            System.out.println(text.intro);
            room.setCurrentRoom(allRooms.get(0));
            System.out.println(text.help);
            start();
        } else if (textParse(start).equals("s")) {
            System.out.println(text.newGame);
            start();
        }
        //error handling
        else {
            System.out.println(text.newGamePromptError);
            promptForNewGame();
        }
    }

    private String textParse(String input) {
        return input.trim().toLowerCase();
    }

    // verb parser to get just the verb
    private String verbParse(String input) {
        String[] command = textParse(input).split(" ");
        return command[0];
    }

    // a noun parser to get just the noun
    private String nounParse(String input) {
        String[] command = textParse(input).split(" ");
        return command[1];
    }

    //Getter and setter

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
