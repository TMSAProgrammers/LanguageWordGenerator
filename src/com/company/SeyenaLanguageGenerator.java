package com.company;

import java.util.concurrent.ThreadLocalRandom;

class SeyenaLanguageGenerator extends LanguageGenerator {

     private int chanceForVerb;
     private Frequency verbEndings;

     SeyenaLanguageGenerator() {

	    //Data Arrays


        String[] consonantsA = new String[] {"n", "g", "k", "l", "ş", "v", "d", "r", "m", "h", "w", "ç", "b", "z", "f", "c", "j", "p"};
        String[] vowelsA = new String[] {"a", "i", "e", "u", "o", "y"};
        String[] wordStartFav = new String[] {"d", "k", "j", "i", "t", "v", "ş", "e", "b", "c", "l", "f"};
        String[] wordEndFav = new String[] {"n", "k", "t", "r", "c", "m", "a", "d"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy", "şş", "çç", "gj", "gb", "pz", "kp", "kg", "gk", "dg", "gd", "dk", "kd", "dp"};

        String[] verbEndingsA = new String[] {"am", "ur", "ez"};

        wordLengthDistribution = new int[] {2, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9};
        vowelFrequencyDistribution = new double[] {0.33, 0.44, 0.44, 0.44, 0.55, 0.55};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.5; //Chance for start pref to be chosen
        lastPreferChance = 0.8; //Chance for end pref to be chosen


        chanceForVerb = 30; //Chance for the ending to be verb-like


        //Frequency objects (Uses above data, not customizable)


	    //Consonants
		constants = new SortedFrequency(consonantsA, 5);
		//Vowels
		vowels = new SortedFrequency(vowelsA, 15);
        //Starting Letter Favorabilities
        wordStartFavorabilities = new SortedFrequency(wordStartFav, 10);
        //Ending Letter Favorabilities
        wordEndFavorabilities = new SortedFrequency(wordEndFav, 10);
        //Verb endings
        verbEndings = new Frequency(verbEndingsA);
	}


	@Override
    public void postProcessWord(String[] word) {
         //Chance to make a verb
         if (word.length > 2 && ThreadLocalRandom.current().nextInt(100) < chanceForVerb) {
             word[word.length - 1] = (String) verbEndings.chooseRandomItem();
         }
    }
}