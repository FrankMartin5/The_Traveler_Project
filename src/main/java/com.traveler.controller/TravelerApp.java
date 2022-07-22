package com.traveler.controller;

import com.traveler.model.Items;
import com.traveler.model.Rooms;
import com.traveler.view.Prompter;
import com.traveler.view.Intro;
import com.traveler.view.SplashScreens;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

class TravelerApp {
    private boolean gameOver = false;
    Prompter prompter = new Prompter(new Scanner(System.in));
//    Rooms room = new Rooms();

    String help = "List of available commands: \nlook <item/room>: get information\ngo <direction>: enter room in that direction" +
            "\nget <item>: adds item to inventory\nquit game: exit the game without saving";

    ArrayList<String> dir= new ArrayList<String>();

//    initialize calls methods
    public void initialize(){
        dir.add("north");
        dir.add("south");
        dir.add("west");
        dir.add("east");
        welcome();
        promptForNewGame(); // sets gameOver
    }

    // start called from promptForNewGame(), main part of game
    public void start() {
        while (!gameOver) {
            // command is the main prompt that dictates flow of game
            String command = prompter.prompt("\nWhat would you like to do?");
            if (textParse(command).equals("quit game")) {
                end();
            }
            else if (textParse(command).equals("help")) {
                System.out.println(help);
            }
            else if (!textParse(command).contains(" ")) {
                System.out.println("You can't do that");
                System.out.println(help);
            }
            else if (command !=null) {
                String verb = verbParse(command);
                String noun = nounParse(command);
                switch (verb){
                    // go verb calls the cmdGo in Rooms class
                    case "go":
                        // room.cmdGo(noun);
                        System.out.println("recognized verb go, this should call room.cmdGo(noun)");
                        break;
                    // look verb can be Items or Rooms, calls items if not 'north, west, south, east'
                    case "look":
                        //if noun is in dir arraylist
                        if (dir.contains(noun)) {
                            System.out.println("recognized verb look, this should call room.cmdLook(noun)");
                        }
                        //else call item.cmdLook(noun)
                        else {
                            Items.cmdLook(noun);
                            System.out.println("recognized verb look, this should call item.cmdLook(noun)");
                        }
                        break;
                    case "help":
                        System.out.println(help);
                    default:
                        System.out.println("You can't do that");
                        System.out.println(help);
                        break;
                }
            }
            else {
                System.out.println("else");
                end();
            }
        }
    }

    // game end method that handles end of game
    public void end() {
        setGameOver(true);
        System.out.println("GAME OVER");
    }

    private void welcome() {
        SplashScreens.art();
        System.out.println("Welcome");
    }

    // prompts for new game or saved game
    private void promptForNewGame(){
        String start = prompter.prompt("Would you like to start a new game or continue from save? [N]ew game or [S]saved game: ");
        if (textParse(start).equals("n")) {
            System.out.println("STARTING NEW GAME");
            Intro.introduction();
            start();
        } else if (textParse(start).equals("s")) {
            System.out.println("STARTING SAVED GAME");
            start();
        }
        //error handling
        else {
            System.out.println("Please enter valid response, n or s");
            promptForNewGame();
        }
    }

    private String textParse(String input){
        return input.trim().toLowerCase();
    }

    // TODO: create verb Parser
    private String verbParse(String input) {
        String[] command = textParse(input).split(" ");
        return command[0];
    }

    //TODO: create noun parser
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
