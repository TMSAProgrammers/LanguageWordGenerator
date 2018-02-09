package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get words to generate
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter number of new items to generate"); //Request number of new items

        int wordsToGenerate = inputScanner.nextInt(); //Grab next int
        inputScanner.nextLine(); //Clear the \n dangle at the end for next input check


        //Instantiate language object
        Language language = new Language(new CustomLanguageGenerator());

        //Generate words
        for (int i = 0; i < wordsToGenerate; i++) {
            String artificialWord = language.generateWord();

            System.out.println(artificialWord);
        }


        //Pause to allow user to view results before exiting
        System.out.println("\nPress ENTER to close");
        inputScanner.nextLine(); //Does nothing, throw-away
        inputScanner.close();
    }

}
