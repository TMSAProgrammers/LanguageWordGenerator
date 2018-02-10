package com.company;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Frequency {
    private final double total;
    private boolean shiftOnResize;

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
            double shft = freqs[pos] - value; //Difference
            shft /= items.length - 1; //Divide equally among all

            //Apply shift
            for (int i = 0; i < items.length; i++) {
                if (i != pos) {
                    freqs[i] += shft;
                }
            }
        }

        //Update the desired freqs value
        freqs[pos] = value;
    }

    //Frequency adder
    public void addFreqs(int pos, double value) {
        this.setFreq(pos, Math.min(freqs[pos] + value, total));
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

        shiftOnResize = false;
    }

    public Frequency(Object[] itemIn, boolean doShift) {
        this(itemIn);
        shiftOnResize = doShift;
    }

    //Main payload method
    public Object chooseRandomItem() {
        double position = ThreadLocalRandom.current().nextDouble(total);

        //Current 'position' in the percentage total
        double curPercPos = 0.0;

        for (int i = 0; i < items.length; i++) {
            curPercPos += freqs[i];

            if (position < curPercPos) {
                return items[i];
            }
        }

        return "error";
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