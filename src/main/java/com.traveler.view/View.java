package com.traveler.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {

    JFrame window;
    JPanel titlePanel, startBtnPanel, mainTextPanel, optionButtonPanel;
    Container con;
    JLabel titleLabel;
    JTextArea mainTextArea;
    JButton startBtn, viewStoryBtn,option1, option2, option3, option4;
    Font titleFont = new Font("Impact", Font.BOLD, 50);
    Font textFont = new Font("Times New Roman", Font.PLAIN, 20);

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    TravelerView optionHandler = new TravelerView();

    public View(){
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

        optionButtonPanel = new JPanel();
        optionButtonPanel.setBounds(250, 350, 300, 150);
        optionButtonPanel.setBackground(Color.black);
        optionButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(optionButtonPanel);

        option1 = new JButton("option 1");
        option1.setBackground(Color.black);
        option1.setForeground(Color.white);
        option1.setFont(textFont);
        option1.setFocusPainted(false);
        option1.addActionListener(optionHandler);
        option1.setActionCommand("c1");
        optionButtonPanel.add(option1);
        option2 = new JButton("option 2");
        option2.setBackground(Color.black);
        option2.setForeground(Color.white);
        option2.setFont(textFont);
        option2.setFocusPainted(false);
        option2.addActionListener(optionHandler);
        option2.setActionCommand("c2");
        optionButtonPanel.add(option2);
        option3 = new JButton("option 3");
        option3.setBackground(Color.black);
        option3.setForeground(Color.white);
        option3.setFont(textFont);
        option3.setFocusPainted(false);
        option3.addActionListener(optionHandler);
        option3.setActionCommand("c3");
        optionButtonPanel.add(option3);
        option4 = new JButton("option 4");
        option4.setBackground(Color.black);
        option4.setForeground(Color.white);
        option4.setFont(textFont);
        option4.setFocusPainted(false);
        option4.addActionListener(optionHandler);
        option4.setActionCommand("c4");
        optionButtonPanel.add(option4);

    }

    public class TitleScreenHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            gameScreen();
        }
    }

}
