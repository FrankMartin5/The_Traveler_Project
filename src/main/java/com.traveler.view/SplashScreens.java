package com.traveler.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SplashScreens {
    JFrame frame;
    JLabel image=new JLabel(new ImageIcon("traveler.png"));
    JLabel text=new JLabel("The Traveler.....");//display text
    public SplashScreens()
    {
        createGUI();
        addImage();
    }
    public void createGUI(){

        var lab = new JLabel("MY LABEL HERE");


        frame=new JFrame();
        frame.pack();
        frame.getContentPane().setLayout(null);
//        frame.setUndecorated(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.gray);
        frame.setVisible(true);
        frame.setTitle("The Travel...your adventure awaits");
    }
    public void addImage(){
        image.setSize(400,400);
        frame.add(image);
        image.setVisible(true);
        image.setIcon(new ImageIcon("src/main/resources/mountainTraveler.jpg"));
        image.setText("Background failed to load");
        image.setBorder(new EmptyBorder(20,10,50,0));
    }
    public void addText()
    {
        text.setFont(new Font("arial",Font.BOLD,30));//font setting
        text.setBounds(170,220,600,40);//Size and location
        text.setForeground(Color.black);//Setting font color
        frame.add(text);// text for the frame
    }
}