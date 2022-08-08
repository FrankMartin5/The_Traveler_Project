package com.traveler.util;

import com.traveler.util.Consumer;

import javax.swing.*;
import java.awt.*;

public class ScreenWriter extends JPanel implements Consumer {

    private JTextArea output;

    public ScreenWriter(){
        setLayout(new BorderLayout());
        output = new JTextArea();
        add(new JScrollPane(output));
    }


    @Override
    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            output.append(text);
            output.setCaretPosition(output.getText().length());
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });
        }
    }

}
