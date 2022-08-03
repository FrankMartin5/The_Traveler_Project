package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import static com.traveler.model.Room.currentRoom;

public class Riddle {
    private String question;
    private String hint;
    private String answer;

    public Riddle(String question, String hint, String answer) {
        this.question = question;
        this.hint = hint;
        this.answer = answer;
    }

    // Creates riddle which carries riddle objects
    public static List<Item> riddles;

    public Riddle() {

    }

    public String getQuestion() {
        return question;
    }

    public String getHint() {
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public static List<Item> getRiddles() {
        return riddles;
    }

    // method that reads from json file and loads inventory with Item objects
    public static void riddlesFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type itemListType = new TypeToken<List<Item>>() {}.getType();
            Reader reader = new InputStreamReader(Item.class.getResourceAsStream("/riddle.json"));
            riddles = gson.fromJson(reader, itemListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Riddle{" +
                "question='" + question + '\'' +
                ", hint='" + hint + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
