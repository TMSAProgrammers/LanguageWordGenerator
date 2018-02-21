package com.company;

class CustomLanguageGenerator extends LanguageGenerator {

     CustomLanguageGenerator() {


	    //Data Arrays


        String[] consonantsA = new String[] {"n", "g", "k", "l", "ş", "v", "d", "r", "m", "h", "w", "ç", "b", "z", "f", "j", "p"};
        String[] vowelsA = new String[] {"a", "i", "e", "u", "o"};
        String[] wordStartFav = new String[] {"d", "k", "j", "i", "t", "v", "ş", "e", "b", "c", "l", "f"};
        String[] wordEndFav = new String[] {"n", "k", "t", "r", "c", "m", "a", "d"};
        forbiddenSequences = new String[] {"uu", "aa", "ii", "ee", "oo", "yy", "şş", "çç", "gj", "gb", "pz", "kp", "kg", "gk"};

        wordLengthDistribution = new int[] {2, 3, 3, 3, 4, 5, 5, 5, 6, 6, 7, 7, 7, 8, 9, 10,};
        vowelFrequencyDistribution = new double[] {0.33, 0.44, 0.44, 0.44, 0.55, 0.55};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.9; //Chance for start pref to be chosen
        lastPreferChance = 0.9; //Chance for end pref to be chosen


        //Frequency objects (Uses above data, not customizable)


	    //Consonants
		constants = new SortedFrequency(consonantsA, 20);
		//Vowels
		vowels = new SortedFrequency(vowelsA, 50);
        //Starting Letter Favorabilities
        wordStartFavorabilities = new SortedFrequency(wordStartFav, 50);
        //Ending Letter Favorabilities
        wordEndFavorabilities = new SortedFrequency(wordEndFav, 50);
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

    }
}