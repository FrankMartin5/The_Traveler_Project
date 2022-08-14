package com.traveler.view;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveler.controller.TravelerApp;
import com.traveler.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TravelerView extends JFrame{

    private JFrame window;
    private JPanel mainTextPanel;
    private Container con;
    private JLabel result;
    public JTextArea mainTextArea;
    private Font textFont = new Font("Times New Roman", Font.PLAIN, 14);
    private JTextField textField;
    private JButton submit;

    private String input = "";

    public static TravelerView viewInstance = null;

    TravelerApp app = TravelerApp.getInstance();

    public TravelerView() {
        window = new JFrame();
        window.setSize(1000,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        con = window.getContentPane();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,850,650);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100,100,650,450);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        mainTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField = new JTextField(20);
        result = new JLabel();
        mainTextArea.add(result);
        mainTextPanel.add(textField);
        submit = new JButton("Enter");
        mainTextPanel.add(submit);
        submit.addActionListener(e -> {
            input = textField.getText();
            result.setText(input);
            setOutput(input);
            // Wakes up all threads that are waiting on this object's monitor. A thread waits on an object's monitor by calling one of the wait methods.
            synchronized (TravelerView.class) {
                TravelerView.class.notifyAll();
            }
            synchronized (NPC.class) {
                NPC.class.notifyAll();
            }
            synchronized (Combat.class) {
                Combat.class.notifyAll();
            }
        });

        // Allows All System.out to be printed to GUI
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                mainTextArea.append(""+(char)(b & 0xFF));
            }
        });
        System.setOut(out);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static TravelerView getInstance() {
        if (viewInstance == null) {
            viewInstance = new TravelerView();
        }
        return viewInstance;
    }



    // Sets output to be printed on the GUI
    public void setOutput(String output) {
        mainTextArea.append("\n"+output);
        mainTextArea.setCaretPosition(mainTextArea.getDocument().getLength());
    }

    public String getInput() {
        return input;
    }

}
