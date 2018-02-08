package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHandler extends JFrame implements ActionListener {

    //Fields
    String mainOutput;

    //Freqency maps
    Frequency consonants;
    Frequency vowels;
    Frequency wordStarts;
    Frequency wordEnds;
    Frequency forbiddens;


    //Constructor
    public GUIHandler() {
        initializeWindow();
    }

    private void initializeWindow() {
        setTitle("Language Word Generator");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JTextField) {

        }
    }
}
