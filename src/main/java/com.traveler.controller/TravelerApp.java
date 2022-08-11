package com.traveler.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveler.model.*;
import com.traveler.util.Json;
import com.traveler.view.Prompter;
import com.traveler.view.SplashScreens;
import com.traveler.view.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static com.traveler.model.Item.itemsFromJsonToArray;
import static com.traveler.model.NPC.NPCArray;
import static com.traveler.model.Quiz.quizzesFromJsonToArray;
import static com.traveler.model.Room.*;
import static com.traveler.view.Map.cmdMap;

public class TravelerApp extends JFrame{

    JFrame window;
    JPanel mainTextPanel;
    Container con;
    JLabel result;
    JTextArea mainTextArea;
    Font textFont = new Font("Times New Roman", Font.PLAIN, 14);
    JTextField textField;
    JButton submit;
    private String input;

    public TravelerApp() {
        window = new JFrame();
        JScrollPane scrollPane = new JScrollPane();
        window.setSize(1000,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        con = window.getContentPane();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,850,650);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100,100,650,450);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        textField = new JTextField(20);
        result = new JLabel();
        mainTextArea.add(result);
        mainTextPanel.add(textField);
        submit = new JButton("Enter");
        mainTextPanel.add(submit);
        submit.addActionListener(e -> {
            input = textField.getText();
            result.setText(input);
            setOutput(input);
            synchronized (TravelerApp.class) {
                TravelerApp.class.notifyAll();
            }
        });

        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                mainTextArea.append(""+(char)(b & 0xFF));
            }
        });
        System.setOut(out);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setOutput(String output) {
        mainTextArea.append("\n"+output);
        mainTextArea.setCaretPosition(mainTextArea.getDocument().getLength());
    }

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
        start();
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
        System.out.println(text.intro);
        room.setCurrentRoom(allRooms.get(0));
        System.out.println(text.help);
        while (!gameOver) {
            synchronized (TravelerApp.class) {
                try {
                    TravelerApp.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mainTextArea.setText("");
            input = getInput();
            // TODO: place else if statements inside switch case
            if (textParse(input).equals("help")) {
                System.out.println(text.help);
            } else if (textParse(input).equals("map")) {
                cmdMap();
            } else if (textParse(input).contains("status")) {
                playerStat(player);
            } else if (!textParse(input).contains(" ")) {
                System.out.println("You can't do that");
                System.out.println(text.help);
            } else {
                String verb = verbParse(input);
                String noun = nounParse(input);
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
                            case "losw":
                                reduceHealth();
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

    public void reduceHealth() {
        if (player.getHealth() <= 25) {
            end();
        } else {
            player.setHealth(player.getHealth() - 25);
            System.out.println("You have " + player.getHealth() + " health remaining");
        }
    }

    public void endWin() {
        setGameOver(true);
        SplashScreens.win();
        System.out.println(text.gameWin);
    }

    public void welcome() {
        SplashScreens.art();
    }

    private String textParse(String input) {
        return input.trim().toLowerCase();
    }

    // verb parser to get just the verb
    public String verbParse(String input) {
        String[] command = textParse(input).split(" ");
        return command[0];
    }

    // a noun parser to get just the noun
    public String nounParse(String input) {
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
