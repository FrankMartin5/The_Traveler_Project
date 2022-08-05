package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traveler.view.Prompter;
import com.traveler.view.Text;

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


public class NPC {
    Prompter prompter = new Prompter(new Scanner(System.in));
    Text text = new Text();

    Quiz quiz = new Quiz();

    String name;
    List<String> talk;
    String item;
    String win;
    String defeat;


    // Creates npc which carries NPC objects
    public static List<NPC> npcList;

    public void initialize() throws IOException {
        quizzesFromJsonToArray();
    }

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
        for (NPC i : currentRoom.npc) {
            if (i.name.equals(noun) && noun.equals("elon")) {
                System.out.println(i.talk.get(rand));
                // get random question from elon quiz
                String answer = prompter.prompt(text.askQuiz);
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
            } else if (i.name.equals(noun) && noun.equals("gnome")) {
                System.out.println(i.talk.get(rand));
                String answer = prompter.prompt(text.askQuiz);
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

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", talk='" + talk + '\'' +
                '}';
    }
}


