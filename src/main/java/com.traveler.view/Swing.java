package com.traveler.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {

    JFrame window;
    JPanel titlePanel, startBtnPanel, mainTextPanel;
    Container con;
    JLabel titleLabel;
    JTextArea mainTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();

    JButton startBtn, viewStoryBtn;

    Font titleFont = new Font("Impact", Font.BOLD, 50);
    Font textFont = new Font("Times New Roman", Font.PLAIN, 20);

    public Swing(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        con = window.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100,175,550,150);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.white));
        titlePanel.setBackground(Color.BLACK);
        titleLabel = new JLabel("THE TRAVELER", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        startBtnPanel = new JPanel();
        startBtnPanel.setBounds(275,450,200,200);
        startBtnPanel.setBackground(Color.black);

        startBtn = new JButton("START GAME");
        startBtn.setBackground(Color.black);
        startBtn.setForeground(Color.white);
        startBtn.addActionListener(tsHandler);


        titlePanel.add(titleLabel);
        startBtnPanel.add(startBtn);

        con.add(titlePanel);
        con.add(startBtnPanel);

        window.setVisible(true);
    }

    public void gameScreen(){
        titlePanel.setVisible(false);
        startBtnPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,650,250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea(new Text().intro);
        mainTextArea.setBounds(100,100,650,250);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        startBtnPanel = new JPanel();
        startBtnPanel.setBounds(275,450,200,200);
        startBtnPanel.setBackground(Color.BLACK);

        startBtn = new JButton("BEGIN YOUR JOURNEY");
        startBtn.setBackground(Color.black);
        startBtn.setForeground(Color.white);

        startBtnPanel.add(startBtn);
        con.add(startBtnPanel);


    }

    public class TitleScreenHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gameScreen();
        }
    }

}