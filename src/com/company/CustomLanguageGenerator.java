package com.company;

class CustomLanguageGenerator extends LanguageGenerator {

     CustomLanguageGenerator() {


	    //Data Arrays


        String[] consonantsA = new String[] {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "qu", "r", "s", "t", "v", "z", "w", "ş", "ç"};
        String[] vowelsA = new String[] {"a", "e", "i", "o", "u"};
        String[] wordStartFav = new String[] {"ş", "g", "p", "e"};
        String[] wordEndFav = new String[] {"ur", "m", "r", "d"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy"};

        wordLengthDistribution = new int[] {2, 3, 4, 5, 5, 5, 6, 6, 7};
        vowelFrequencyDistribution = new double[] {0.27, 0.33, 0.33, 0.39, 0.45};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 1; //Chance for start pref to be chosen
        lastPreferChance = 0.9; //Chance for end pref to be chosen


        //Frequency objects (Uses above data, not customizable)


	    //Consonants
		constants = new Frequency(consonantsA);
		//Vowels
		vowels = new Frequency(vowelsA);
        //Starting Letter Favorabilities
        wordStartFavorabilities = new Frequency(wordStartFav);
        //Ending Letter Favorabilities
        wordEndFavorabilities = new Frequency(wordEndFav);
	}
}
