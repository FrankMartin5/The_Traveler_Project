package com.traveler.controller;

import com.traveler.view.Prompter;
import com.traveler.view.Intro;
import com.traveler.view.SplashScreens;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

class TravelerApp {
    private boolean gameOver = false;
    Prompter prompter = new Prompter(new Scanner(System.in));
    SplashScreens screen = new SplashScreens();
    Intro intro = new Intro();
    String help = "List of available commands: \nlook <item/room>\ngo <direction>\nget <item>\n";


//    initialize calls methods
    public void initialize(){
        welcome();
        promptForNewGame(); // sets gameOver
        intro.introduction();
    }

    // start called from promptForNewGame(), main part of game
    public void start() {
        while (!gameOver) {
            String command = prompter.prompt("What would you like to do?");
            if (command.equals("quit game")) {
                end();
            }
            else if (command !=null) {
                switch (verbParse(command)){
                    case "go":
                        System.out.println("go command");
                        System.out.println(nounParse(command));
                        break;
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
//        screen.splashScreens();
        System.out.println("WELCOME");
    }

    // prompts for new game or saved game
    private void promptForNewGame(){
        String start = prompter.prompt("Would you like to start a new game or continue from save? [N]ew game or [S]saved game: ");
        if (textParse(start).equals("n")) {
            System.out.println("STARTING NEW GAME");
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
        String[] command = input.split(" ");
        return command[0];
    }

    //TODO: create noun parser
    private String nounParse(String input) {
        String[] command = input.split(" ");
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
