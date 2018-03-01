package com.company;

public class EnglishLanguageGenerator extends LanguageGenerator {
	
	public EnglishLanguageGenerator() {
        //Data Arrays


        String[] consonantsA = new String[] {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "qu", "r", "s", "t", "v", "z", "w"};
        String[] vowelsA = new String[] {"a", "e", "i", "o", "u"};
        String[] wordStartFav = new String[] {"w", "t", "k", "i"};
        String[] wordEndFav = new String[] {"u", "i", "e", "o", "a"};
        forbiddenSequences = new String[] {};

        wordLengthDistribution = new int[] {2, 3, 4, 4, 5, 5, 5, 6, 7};
        vowelFrequencyDistribution = new double[] {0.33, 0.55, 0.66, 0.55, 0.33};


        //Preference chances (0 to 1 chance)


        firstPreferChance = 0.7; //Chance for start pref to be chosen
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

	@Override
    public void postProcessWord(String[] word) { }
}
