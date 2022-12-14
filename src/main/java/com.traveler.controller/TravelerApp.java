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

import static com.traveler.model.Item.itemsFromJsonToArray;
import static com.traveler.model.NPC.NPCArray;
import static com.traveler.model.Quiz.*;
import static com.traveler.model.Quiz.gnomeQuiz;
import static com.traveler.model.Riddle.allRiddles;
import static com.traveler.model.Room.*;
import static com.traveler.view.Map.cmdMap;

public class TravelerApp extends JFrame {

    // Fields
    private String input;
    private boolean gameOver = false;
    Room room = new Room();
    Item item = new Item();
    Combat combat = new Combat();
    Text text = new Text();
    Player player = new Player();
    HashMap<String, String> enemyDrops = new HashMap<String, String>();

    // Swing Fields
    public JPanel mainTextPanel;
    public Container con;
    public JLabel result;
    public JTextArea mainTextArea;
    public Font textFont = new Font("Times New Roman", Font.PLAIN, 14);
    public JTextField textField;
    public JButton submit;


    // dir carries directions for parsing
    ArrayList<String> dir = new ArrayList<String>();

    // Class constructor impl Swing
    public TravelerApp() {
        setSize(700,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        con = getContentPane();

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
        mainTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
            // Wakes up all threads that are waiting on this object's monitor. A thread waits on an object's monitor by calling one of the wait methods.
            synchronized (TravelerApp.class) {
                TravelerApp.class.notifyAll();
            }
            synchronized (NPC.class) {
                NPC.class.notifyAll();
            }
            synchronized (Combat.class) {
                Combat.class.notifyAll();
            }
        });

        // Allows All System.out to be printed to GUI
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                mainTextArea.append(""+(char)(b & 0xFF));
            }
        });
        System.setOut(out);

        setLocationRelativeTo(null);
        setVisible(true);
    }

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
            // Causes the current thread to wait until it is awakened, typically by being notified or interrupted.
            System.out.println(text.prompt);
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
                        }
                        break;
                    case "talk":
                        cmdTalk(noun);
                        break;
                    case "fight":
                        String combatResult = cmdFight(noun);
                        switch (combatResult) {
                            case "win":
                                room.removeNPC(noun);
                                awardXP();
                                room.refreshCurrentRoom();
                                break;
                            case "lose":
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
                        room.refreshCurrentRoom();
                        break;

                    case "heal":
                        cmdHeal(noun);
                        break;

                    default:
                        wrongCmd();
                        break;
                }
            }
        }
    }

    private void cmdHeal(String noun) {
        noun = noun.substring(0, 1).toUpperCase() + noun.substring(1);

        if (noun.equals(player.getName())) {
            for (Item potion : Player.getInventory()) {
                if (potion.getName().equals("health-potion")) {
                    player.setHealth(player.getHealth() + 25);
                    Player.getInventory().remove(potion);
                    System.out.println("You drink the health potion and get healed for 25 health.");
                    break;
                } else {
                    System.out.println("You don't have any health potions.");
                }
            }
        } else {
            System.out.println("You can't heal " + noun + ".");
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
        System.out.println("Inventory: " + Player.getInventory());
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

    // Needs refactor. Possible fix is finding a way to implement input variable
    public void cmdTalk(String noun) {
        Quiz elon = elonQuiz.get((int) (Math.random() * elonQuiz.size()));
        Quiz gnome = gnomeQuiz.get((int) (Math.random() * gnomeQuiz.size()));

        Random rn = new Random();
        int maxNum = 3;
        int rand = rn.nextInt(maxNum);

        for (NPC i : getCurrentRoom().getNpc()) {
            if (i.getName().equals(noun) && noun.equals("elon")) {
                System.out.println(i.getTalk().get(rand) + "\n");
                System.out.println("Do you want to play a quiz? (y/n)");
                synchronized (TravelerApp.class) {
                    try {
                        TravelerApp.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mainTextArea.setText("");
                input = getInput();
                if (input.equals("y")) {
                    System.out.println(elon.getQuestion());
                    System.out.println(elon.getOptions());
                    System.out.println(text.answerQuiz);
                    synchronized (TravelerApp.class) {
                        try {
                            TravelerApp.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mainTextArea.setText("");
                    input = getInput();
                    if (input.equals(elon.getAnswer())) {
                        System.out.println("Correct! You are awarded with a health potion to your inventory.");
                        //add "health-potion" as Item object to player inventory
                        Player.getInventory().add(new Item("health-potion",
                                "A health potion that restores 25 health"));
                    } else {
                        System.out.println("Incorrect!");
                    }

                } else {
                    System.out.println("You hesitated and left the conversation.");
                }
                return;
            } else if (i.getName().equals(noun) && noun.equals("gnome")) {
                System.out.println(i.getTalk().get(rand));
                System.out.println(text.askQuiz);
                synchronized (TravelerApp.class) {
                    try {
                        TravelerApp.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mainTextArea.setText("");
                input = getInput();
                if (input.equals("y")) {
                    System.out.println(gnome.getQuestion());
                    System.out.println(gnome.getOptions());
                    System.out.println(text.answerQuiz);
                    synchronized (TravelerApp.class) {
                        try {
                            TravelerApp.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mainTextArea.setText("");
                    input = getInput();
                    if (input.equals(gnome.getAnswer())) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect!");
                    }
                } else {
                    System.out.println("You hesitated and left the conversation.");
                }
                return;
            }
        }
        System.out.println(noun + " not found");
    }

    // Needs refactor. Possible fix is finding a way to implement input variable
    public String cmdFight(String enemy) { // method that passes an enemy noun to start combat
        String result = "no fight";
        Riddle riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
        if (getCurrentRoom().getNpc().size() > 0 && getCurrentRoom().getNpc().get(0).getName().equals(enemy)) {
            NPC enemyInRoom = getCurrentRoom().getNpc().get(0);
            //check to verify enemy is not friendly
            switch (enemyInRoom.getName()) {
                case "racumen":
                    int boss_round = 3;
                    int win_1 = 0;
                    int lose_1 = 0;

                    while (boss_round > 0 ) {
                        System.out.println("You have " + boss_round + " rounds to complete.");
                        System.out.println("Riddle: " + riddle.getQuestion());
                        System.out.println("Hint: " + riddle.getHint());
                        synchronized (TravelerApp.class) {
                            try {
                                TravelerApp.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mainTextArea.setText("");
                        input = getInput();

                        if (input.equals(riddle.getAnswer())) {
                            System.out.println("You win the round!");
                            win_1++;
                            boss_round--;
                            riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
                            if (win_1 == 2) {
                                System.out.println("You win the combat!");
                                result = "bosswin";
                                break;
                            }
                        } else {
                            System.out.println("You lose the round!");
                            Taunts.npcTaunts();
                            boss_round--;
                            lose_1++;
                            if (lose_1 == 2) {
                                System.out.println("You lost two times in a row, you lose the combat!");
                                result = "lose";
                                break;
                            }
                            riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
                        }

                    }
                    break;

                case "orc":
                case "troll":
                    int regular_round = 3;
                    int win_2 = 0;
                    int lose_2 = 0;

                    while (regular_round > 0 ) {
                        System.out.println("You have " + regular_round + " rounds to complete.");
                        System.out.println("Riddle: " + riddle.getQuestion());
                        System.out.println("Hint: " + riddle.getHint());
                        synchronized (TravelerApp.class) {
                            try {
                                TravelerApp.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mainTextArea.setText("");
                        input = getInput();

                        if (input.equals(riddle.getAnswer())) {
                            System.out.println("You win the round!");
                            win_2++;
                            regular_round--;
                            riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
                            if (win_2 == 2) {
                                System.out.println("You win the combat!");
                                result = "win";
                                Player.getInventory().add(new Item("key",
                                        "Oh it might open a locked room"));
                                break;
                            }
                        } else {
                            System.out.println("You lose the round!");
                            Taunts.npcTaunts();
                            regular_round--;
                            lose_2++;
                            if (lose_2 == 2) {
                                System.out.println("You lost two times in a row, you lose the combat!");
                                result = "lose";
                                break;
                            }
                            riddle = allRiddles.get((int) (Math.random() * allRiddles.size()));
                        }

                    }
                    break;

                default:
                    result = "win";
                    break;
            }
        } else { // either no one in the room or enemy in the room does not match given name
            System.out.println(enemy + " is not in the room");
        }
        return result;
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


    //Getters and setters
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setOutput(String output) {
        mainTextArea.append("\n"+output);
        mainTextArea.setCaretPosition(mainTextArea.getDocument().getLength());
    }

    public String getInput() {
        return input;
    }

}
