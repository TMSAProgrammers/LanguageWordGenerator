package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class SortedFrequency extends Frequency {
    private int selectChance;

    //Constructors

    /*
        * Accepts a sorted item list and a reoccuring chance that a letter will be chosen
    */
    public SortedFrequency(Object[] sortedItems, int selectChance) {
        super(sortedItems);

        this.selectChance = selectChance;
    }

    public SortedFrequency(Object[] sortedItems) {
        super(sortedItems);

        this.selectChance = 50; //Default value of select Chance
    }

    //Payload method for SortedFrequency
    public Object chooseRandomItem() {
        while (true) {
            for (Object item : items) {
                if (ThreadLocalRandom.current().nextInt(100) < selectChance) {
                    return item;
                }
            }
        }
    }
}
