package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Riddle {

    private String question;
    private String hint;
    private String answer;

    public static List<Riddle> allRiddles;

    public static void riddlesFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type riddleListType = new TypeToken<List<Riddle>>() {
            }.getType();
            Reader reader = new InputStreamReader(Riddle.class.getResourceAsStream("/riddle.json"));
            allRiddles = new Gson().fromJson(reader, riddleListType);
            reader.close();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHint() {
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Riddle: ");
        sb.append("question =").append(question);
        sb.append(", hint =").append(hint);
        sb.append(", answer =").append(answer);
        sb.append('}');
        return sb.toString();
    }
}
