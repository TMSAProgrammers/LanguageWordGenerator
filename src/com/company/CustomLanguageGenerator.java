package com.company;

class CustomLanguageGenerator extends LanguageGenerator {

     CustomLanguageGenerator() {


	    //Data Arrays


        String[] consonantsA = new String[] {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "r", "t", "v", "z", "w", "ş", "ç"};
        String[] vowelsA = new String[] {"a", "e", "i", "o", "u"};
        String[] wordStartFav = new String[] {"ş", "c", "k"};
        String[] wordEndFav = new String[] {"ur", "l", "n", "r", "d"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy", "şş", "çç", "gj", "gb", "pz", "kp"};

        wordLengthDistribution = new int[] {2, 3, 4, 4, 5, 5, 5, 5, 6, 6, 7, 8, 9, 10};
        vowelFrequencyDistribution = new double[] {0.3, 0.33, 0.44, 0.44, 0.44, 0.55};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.9; //Chance for start pref to be chosen
        lastPreferChance = 0.9; //Chance for end pref to be chosen


        //Frequency objects (Uses above data, not customizable)


	    //Consonants
		constants = new SortedFrequency(consonantsA, 25);
		//Vowels
		vowels = new SortedFrequency(vowelsA, 25);
        //Starting Letter Favorabilities
        wordStartFavorabilities = new SortedFrequency(wordStartFav, 25);
        //Ending Letter Favorabilities
        wordEndFavorabilities = new SortedFrequency(wordEndFav, 25);
	}
}
