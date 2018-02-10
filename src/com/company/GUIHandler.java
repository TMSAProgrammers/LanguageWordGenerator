package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHandler extends JFrame implements ActionListener {

    //Fields
    String mainOutput;

    //Direct access to a language generator
    LanguageGenerator languageSettings;

    //Constructor
    public GUIHandler(LanguageGenerator langSettings) {
        initializeWindow();

        languageSettings = langSettings;
    }

    private void initializeWindow() {
        setTitle("Language Word Generator");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void generateComponents(Frequency group) {
        String[] captions = (String[]) group.getItems();
        double[] freqs = group.getFrequencies();
        for (int i = 0; i < captions.length; i++) {
            JTextField freqControl = new JTextField();
            freqControl.setName(captions[i]);
            freqControl.setText(String.valueOf(freqs[i]));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JTextField) {

        }
    }
}
