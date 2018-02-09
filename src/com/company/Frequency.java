package com.company;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Frequency {
    private final double total;
    private boolean shiftOnResize = false;

    private Object[] items;
    private double[] freqs;
    private double unit; //Value for even distribution of freqs to equal total

    //Array getters
    public Object[] getItems() {
        return items;
    }
    public double[] getFrequencies() {
        return freqs;
    }

    //Frequency setter
    public void setFreq(int pos, double value) {
        //Adjust the other frequencies based on how much this frequency was added to or dropped
        if (shiftOnResize) {
            double shft = value - freqs[pos]; //Difference
            shft /= items.length - 1; //Divide equally among all

            //Apply shift
            for (int i = 0; i < items.length; i++) {
                if (i != pos) {
                    freqs[pos] += shft;
                }
            }
        }

        //Update the desired freqs value
        freqs[pos] = value;
    }

    //Constructor
    public Frequency(Object[] itemIn) {
        //Set total to default (total is constant)
        total = 100;

        //Set items to the in item array (initialize items)
        items = itemIn;

        //Initialize frequencies
        freqs = new double[items.length];

        //Fill frequencies
        unit = total / items.length;
        freqsDefault();
    }

    //Main payload method
    public Object chooseRandomItem() {
        double position = ThreadLocalRandom.current().nextDouble(total);

        //The last freq/item closest to the position
        int lastDuoPos = 0;
        //The latest freq - position value
        double lastDist = Double.POSITIVE_INFINITY;
        //Current 'position' in the percentage total
        double curPercPos = 0.0;

        for (int i = 0; i < items.length; i++) {
            //Distance between a position and frequency map pos
            double curDist = Math.abs((freqs[i] + curPercPos) - position);

            //Adding as the return result
            if (curDist < lastDist) {
                lastDist = curDist;
                lastDuoPos = i;
            }

            //Add the current percentage position
            curPercPos += freqs[i];
        }

        return items[lastDuoPos];
    }

    public boolean verifyTotal() {
        float sum = 0;
        for (double freq : freqs) {
            sum += freq;
        }

        if (Math.abs(sum - total) < 0.01) { //0.01 is cutoff point for decimal rounding error
            return true;
        }
        return false;
    }

    private void freqsDefault() {
        Arrays.fill(freqs, unit);
    }
}