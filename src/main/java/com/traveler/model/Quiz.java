package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Quiz {

    private String question;

    private String option1;
    private String option2;
    private String option3;

    private String answer;

    public static List<Quiz> elonQuiz;
    public static List<Quiz> gnomeQuiz;

    public static void quizzesFromJsonToArray() {
        try {
            Gson gson = new Gson();
            Type quizListType = new TypeToken<List<Quiz>>() {
            }.getType();
            Reader reader1 = new InputStreamReader(Quiz.class.getResourceAsStream("/elonquiz.json"));
            Reader reader2 = new InputStreamReader(Quiz.class.getResourceAsStream("/gnomequiz.json"));

            elonQuiz = new Gson().fromJson(reader1, quizListType);
            reader1.close();
            gnomeQuiz = new Gson().fromJson(reader2, quizListType);
            reader2.close();

        } catch (JsonIOException | IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getQuestion() {
        return question;
    }

    public String getOptions() {
        return "1. " + option1 + "\n2. " + option2 + "\n3. " + option3;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}