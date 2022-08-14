package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traveler.controller.TravelerApp;
import com.traveler.view.Prompter;
import com.traveler.view.Text;
import com.traveler.view.TravelerView;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.traveler.model.Quiz.*;
import static com.traveler.model.Riddle.riddlesFromJsonToArray;
import static com.traveler.model.Room.currentRoom;
import static com.traveler.model.Room.getCurrentRoom;


public class NPC {
    Prompter prompter = new Prompter(new Scanner(System.in));
    Text text = new Text();
    TravelerView gui = TravelerView.getInstance();

    private String name;
    private List<String> talk;
    private String item;
    private String win;
    private String defeat;


    // Creates npc which carries NPC objects
    public static List<NPC> npcList;

    // method that reads from json file and loads npc with NPC objects
    public static void NPCArray() {
        try {
            Gson gson = new Gson();
            Type itemListType = new TypeToken<List<NPC>>() {}.getType();
            Reader reader = new InputStreamReader(NPC.class.getResourceAsStream("/npc.json"));
            npcList = new Gson().fromJson(reader, itemListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // when command is "talk <npc>" returns talk
    public void cmdTalk(String noun) {
        Quiz elon = elonQuiz.get((int) (Math.random() * elonQuiz.size()));
        Quiz gnome = gnomeQuiz.get((int) (Math.random() * gnomeQuiz.size()));

        Random rn = new Random();
        int maxNum = 3;
        int rand = rn.nextInt(maxNum);
        for (NPC i : getCurrentRoom().getNpc()) {
            if (i.getName().equals(noun) && noun.equals("elon")) {
                System.out.println(i.getTalk().get(rand));
                // get random question from elon quiz
                synchronized (NPC.class) {
                    try {
                        NPC.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gui.mainTextArea.setText("");
                String answer = gui.getInput();
                if (answer.equals("y")) {
                    System.out.println(elon.getQuestion());
                    System.out.println(elon.getOptions());
                    String answertoQuiz = prompter.prompt(text.answerQuiz);
                    if (answertoQuiz.equals(elon.getAnswer())) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect!");
                    }

                } else {
                    System.out.println("You hesitated and left the conversation.");
                }
                return;
            } else if (i.getName().equals(noun) && noun.equals("gnome")) {
                System.out.println(i.getTalk().get(rand));
                synchronized (TravelerApp.class) {
                    try {
                        TravelerApp.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gui.mainTextArea.setText("");
                String answer = gui.getInput();
//                String answer = prompter.prompt(text.askQuiz);
                if (answer.equals("y")) {
                    System.out.println(gnome.getQuestion());
                    System.out.println(gnome.getOptions());
                    String answertoQuiz = prompter.prompt(text.answerQuiz);
                    if (answertoQuiz.equals(gnome.getAnswer())) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTalk() {
        return talk;
    }

    public void setTalk(List<String> talk) {
        this.talk = talk;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDefeat() {
        return defeat;
    }

    public void setDefeat(String defeat) {
        this.defeat = defeat;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", talk='" + talk + '\'' +
                '}';
    }
}
