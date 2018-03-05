package com.company;

import java.util.concurrent.ThreadLocalRandom;

class SeyenaLanguageGenerator extends LanguageGenerator {

     private int chanceForVerb;
     private Frequency verbEndings;

     SeyenaLanguageGenerator() {

	    //Data Arrays


        String[] consonantsA = new String[] {"m", "p", "v", "f", "r", "l", "b", "x", "w", "g", "n", "q", "j", "z", "d", "h"};
        String[] vowelsA = new String[] {"e", "o", "i", "y", "a", "u"};
        String[] wordStartFav = new String[] {"w", "b", "p" ,"s" ,"f" ,"m" ,"y" ,"l"};
        String[] wordEndFav = new String[] {"a", "e", "i", "u", "o", "y"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy", "şş", "çç", "gj", "gb", "pz", "kp", "kg", "gk", "dg", "gd", "dk", "kd", "dp"};

        String[] verbEndingsA = new String[] {"am", "ur", "ez"};

        wordLengthDistribution = new int[] {2, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9};
        vowelFrequencyDistribution = new double[] {0.44, 0.44, 0.55, 0.55, 0.66};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.5; //Chance for start pref to be chosen
        lastPreferChance = 0.8; //Chance for end pref to be chosen


        chanceForVerb = 0; //Chance for the ending to be verb-like


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