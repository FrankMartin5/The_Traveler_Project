package com.traveler.controller;

import com.traveler.view.SplashScreens;
import com.traveler.view.Swing;

import java.io.IOException;

// Main class that creates new TravelerApp and initializes
class Main {
    public static void main(String[] args){
        try {
            TravelerApp app = new TravelerApp();
            app.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}