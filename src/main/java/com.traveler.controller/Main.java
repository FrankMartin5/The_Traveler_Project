package com.traveler.controller;

import com.traveler.view.SplashScreens;
import com.traveler.view.Swing;

import java.io.IOException;

// Main class that creates new TravelerApp and initializes
class Main {
    public static void main(String[] args) throws IOException {
        TravelerApp app = new TravelerApp();
        app.initialize();
    }

}
