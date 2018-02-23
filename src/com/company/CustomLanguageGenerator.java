package com.company;

import java.util.concurrent.ThreadLocalRandom;

class CustomLanguageGenerator extends LanguageGenerator {

     private int chanceForVerb;
     private Frequency verbEndings;

     CustomLanguageGenerator() {

	    //Data Arrays


        String[] consonantsA = new String[] {"n", "g", "k", "l", "ş", "v", "d", "r", "m", "h", "w", "ç", "b", "z", "f", "j", "p"};
        String[] vowelsA = new String[] {"a", "i", "e", "u", "o", "y"};
        String[] wordStartFav = new String[] {"d", "k", "j", "i", "t", "v", "ş", "e", "b", "c", "l", "f"};
        String[] wordEndFav = new String[] {"n", "k", "t", "r", "c", "m", "a", "d"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy", "şş", "çç", "gj", "gb", "pz", "kp", "kg", "gk", "dg", "gd", "dk", "kd", "dp"};

        String[] verbEndingsA = new String[] {"am", "ur", "ez"};

        wordLengthDistribution = new int[] {2, 3, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9, 12};
        vowelFrequencyDistribution = new double[] {0.33, 0.44, 0.44, 0.44, 0.55, 0.55};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.9; //Chance for start pref to be chosen
        lastPreferChance = 0.9; //Chance for end pref to be chosen


        chanceForVerb = 15; //Chance for the ending to be verb-like


        //Frequency objects (Uses above data, not customizable)


	    //Consonants
		constants = new SortedFrequency(consonantsA, 10);
		//Vowels
		vowels = new SortedFrequency(vowelsA, 20);
        //Starting Letter Favorabilities
        wordStartFavorabilities = new SortedFrequency(wordStartFav, 25);
        //Ending Letter Favorabilities
        wordEndFavorabilities = new SortedFrequency(wordEndFav, 50);
        //Verb endings
        verbEndings = new Frequency(verbEndingsA);
	}


	@Override
    public void postProcessWord(String[] word) {

         //Change double consonants to different consonants
         String[] consonantArray = (String[]) constants.getItems();

         //Main body pass
         for (int i = 1; i < word.length - 1; i++) {
             if (Language.arrayContains(consonantArray, word[i]) && word[i] == word[i - 1]) {
                 do {
                     word[i] = (String) constants.chooseRandomItem();
                 } while (word[i] == word[i - 1]);
             }
         }
         //Last character avoidance pass
         if (word.length > 2 &&
                 Language.arrayContains(consonantArray, word[word.length - 1]) &&
                 word[word.length - 1] == word[word.length - 2]) {

             do {
                 word[word.length - 2] = (String) constants.chooseRandomItem();
             } while (word[word.length - 2] == word[word.length - 1]);

         }


         //Chance to make a verb
         if (word.length > 2 && ThreadLocalRandom.current().nextInt(100) < chanceForVerb) {
             word[word.length - 1] = (String) verbEndings.chooseRandomItem();
         }
    }
}