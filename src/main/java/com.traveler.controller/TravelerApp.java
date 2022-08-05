package com.traveler.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveler.jsonparser.Json;
import com.traveler.model.*;
import com.traveler.view.Prompter;
import com.traveler.view.SplashScreens;
import com.traveler.view.Text;

import java.io.IOException;
import java.util.*;

import static com.traveler.model.Item.itemsFromJsonToArray;
import static com.traveler.model.NPC.NPCArray;
import static com.traveler.model.Quiz.quizzesFromJsonToArray;
import static com.traveler.model.Room.*;
import static com.traveler.view.Map.cmdMap;

class TravelerApp {

    // Fields
    private boolean gameOver = false;
    Prompter prompter = new Prompter(new Scanner(System.in));
    Room room = new Room();
    Item item = new Item();
    NPC npc = new NPC();
    Combat combat = new Combat();
    Text text = new Text();
    Player player = new Player();
    HashMap<String, String> enemyDrops = new HashMap<String, String>();

    // dir carries directions for parsing
    ArrayList<String> dir = new ArrayList<String>();

    //    initialize calls methods that is needed before game starts
    public void initialize() {
        generatePlayerFromJson();
        quizzesFromJsonToArray();
        dir.add("north");
        dir.add("south");
        dir.add("west");
        dir.add("east");
        itemsFromJsonToArray();
        roomsFromJsonToArray();
        NPCArray();
        combat.initialize();
        welcome();
        promptForNewGame();
    }

    public void generateDrops() {
        enemyDrops.put("lint", "Pocket lint. Why do they even have this?");
        enemyDrops.put("coins", "Small metallic coins that looks to be some sort of currency.");
        enemyDrops.put("essence", "A ghost-like ball of swirling energy that seems to evade your capture no matter how much you try to hold it");
        enemyDrops.put("parchment", "A crumpled piece of parchment with unfamiliar text scrawled onto it");

    }

    public void randDrop(){
        generateDrops();

        Object[] keys = enemyDrops.keySet().toArray(new String[0]);
        Object key = keys[new Random().nextInt(keys.length)];
        System.out.println("The vanquished foe drops " + key + "!\n"
                + "Description: " + enemyDrops.get(key));
    }

    // start called from promptForNewGame(), main part of game
    public void start() {
        while (!gameOver) {
            // command is the main prompt that dictates flow of game
            String command = prompter.prompt(text.prompt);
            // TODO: place else if statements inside switch case
            if (textParse(command).equals("help")) {
                System.out.println(text.help);
            } else if (textParse(command).equals("map")) {
                cmdMap();
            } else if (textParse(command).contains("status")) {
                playerStat(player);
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
                        switch (combatResult) {
                            case "win":
                                room.removeNPC(noun);
                                awardXP();
                                room.refreshCurrentRoom();
                                break;
                            case "loss":
                                break;
                            case "bosswin":
                                endWin();
                                break;
                        }
                        break;
                    case "get":
                        item.cmdPickUpItem(noun);
                        // TODO: Item is being added inventory but returning null.
                        player.getInventory().add(item);
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

    public String levelUp(){
        String message = "Your current level is: " + player.getLvl();
        if(player.getLvl() == 1 && player.getExp() >= 10){
            player.setLvl(2);
            System.out.println(message);
        }else if (player.getLvl() == 2 && player.getExp() >= 20){
            player.setLvl(3);
            System.out.println(message);
        }else if (player.getLvl() == 3 && player.getExp() >= 30) {
            player.setLvl(4);
            System.out.println(message);
        }
        return message;
    }

    public void awardXP(){
        int min = 5;
        int max = 10;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        int exp = value;


        System.out.println("\nYou have been awarded " + exp +
                " experience points!");
        player.setExp(player.getExp() + exp);
        System.out.println("You now have a total of " + (player.getExp()) + " experience points gained so far.");
        levelUp();

    }

    public void generatePlayerFromJson() {
        try {
            Json json = new Json();
            JsonNode playerNode = json.parse(json.getResourceStream("/player.json"));
            player = json.fromJson(playerNode, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerStat(Player player) {

        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Player Level: " + player.getLvl());
        System.out.println("Player XP: " + player.getExp());
        System.out.println("Inventory: " + player.getInventory());
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
