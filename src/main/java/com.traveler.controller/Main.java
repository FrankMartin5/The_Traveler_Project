package com.traveler.controller;

import com.traveler.view.TravelerView;
import com.traveler.view.View;

// Main class that creates new TravelerApp and initializes
class Main {
    public static void main(String[] args){
//        TravelerApp app = new TravelerApp();
//        app.initialize();
//        TravelerView view = new TravelerView();
//        view.initialize();
        new TravelerView().initialize();
    }

}
