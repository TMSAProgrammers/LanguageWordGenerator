package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Language {
    //Fields
    private LanguageGenerator languageTemplate;

	private WeightedLists constants;
	private WeightedLists vowels;
	
	private WeightedLists startFavorabilities;
	private WeightedLists endFavorabilities;
	
	private String[] forbiddenSequences;
	
	private int[] lengthDistribution;

	private double[] vowelDistribution;
	
	private double firstPreferChance;
	private double lastPreferChance;
	
	
	//Constructor
	Language(LanguageGenerator langGen) {
	    this.languageTemplate = langGen;

		this.constants = langGen.constants;
		this.vowels = langGen.vowels;
		
		this.forbiddenSequences = langGen.forbiddenSequences;
		
		this.lengthDistribution = langGen.wordLengthDistribution;
		this.vowelDistribution = langGen.vowelFrequencyDistribution;
		
		this.firstPreferChance = langGen.firstPreferChance;
		this.lastPreferChance = langGen.lastPreferChance;
		
		this.startFavorabilities = langGen.wordStartFavorabilities;
		this.endFavorabilities = langGen.wordEndFavorabilities;
	}

	//Generates a full word using the provided settings
	public String generateWord() {
		//Stores a random value between minLen (inclusive) and maxLen (inclusive)
        int wordLength = getWeightedRandom(lengthDistribution);

		//Calculates and stores the frequency of vowels of this word
		double vowelFrequency = getWeightedRandom(vowelDistribution);
		//Calculates total vowels in the word
		int vowelCount = (int) (vowelFrequency * wordLength);
		vowelCount = Math.max(1, vowelCount); //Atleast one vowel in every word

        //Make "word" structure
        String[] generatedWord = new String[wordLength];

		//Fill with constants
		constantFill(generatedWord);

		//Preference update
		preferenceFill(generatedWord);

		//Fill with vowels
		vowelFill(generatedWord, vowelCount);

		//Final check if the word is appropriate in this language (recursive)
		if (isForbidden(generatedWord)) {
			return generateWord();
		}

        //Apply special language-specific post processing to the word
        languageTemplate.postProcessWord(generatedWord);

		//Convert to a full string and return
        return String.join("", generatedWord);
	}



    //Encapsulated methods


	
	//Fills a string array with random consonants
	private void constantFill(String[] word) {
	    for (int i = 0; i < word.length; i++) {
            word[i] = (String) constants.chooseRandomItem();
        }
	}
	
	
	//Adds random vowels to random positions in the string array
	private void vowelFill(String[] word, int vowelNumber) {
		int position; //Possible word placements
		int ending = Math.max(2, word.length - 1); //Ending position to place a word
		int tries; //Tries taken for one vowel placement

		for (int i = 0; i < vowelNumber; i++) {
			tries = 0;
			do {
			    //Randomize the position
				position = ThreadLocalRandom.current().nextInt(1, ending);

				// Exit after a ridiculous amount of tries
				if (++tries > 1000) {
					break;
				}
			}
			//Continue while position is occupied by a vowel
			while (arrayContains(
			        (String[]) vowels.getItems(), word[position]  ));

			//Choose a vowel to insert
			String vowel = (String) vowels.chooseRandomItem();

            //Splice the vowel into the string
			word[position] = vowel;
		}
	}


    //Chance to change first and last letters to the preferences
	private void preferenceFill(String[] word) {
        //Preference check for first word preference
        if (ThreadLocalRandom.current().nextDouble() <= firstPreferChance) {
            word[0] = (String) startFavorabilities.chooseRandomItem();
        }

        //Preference check for last word preference
        if (ThreadLocalRandom.current().nextDouble() <= lastPreferChance) {
            word[word.length - 1] = (String) endFavorabilities.chooseRandomItem();
        }
    }


    //Returns if the string contains any forbidden sequences or not
    private boolean isForbidden(String[] toAnalyzeStrA) {
        //Convert to a real string for sequence check
        String toAnalyzeString = String.join("", toAnalyzeStrA);

        //Forbidden sequence check
		for (String forbiddenSeq : forbiddenSequences) {
            if (toAnalyzeString.contains(forbiddenSeq)) {
                return true;
            }
        }

        //No triple consonant check
		String[] consonantA = (String[]) constants.getItems();

        for (int i = 0; i < toAnalyzeStrA.length - 2; i++) {
			boolean inConsPos = arrayContains(consonantA, toAnalyzeStrA[i]);
			boolean inConsPos2 = arrayContains(consonantA, toAnalyzeStrA[i + 1]);
			boolean inConsPos3 = arrayContains(consonantA, toAnalyzeStrA[i + 2]);

			if (inConsPos && inConsPos2 && inConsPos3) {
				return true;
			}
		}

        return false;
    }



	//Helper Methods



	//Weighting implementations

	//Weighted randomness using random value to array mapping
	private String getWeightedRandom(String[] distribution) {
		return distribution[ThreadLocalRandom.current().nextInt(distribution.length)];
	}

	//Weighted randomness using random value to array mapping
	private double getWeightedRandom(double[] distribution) {
		return distribution[ThreadLocalRandom.current().nextInt(distribution.length)];
	}

    //Weighted randomness using random value to array mapping
    private int getWeightedRandom(int[] distribution) {
        return distribution[ThreadLocalRandom.current().nextInt(distribution.length)];
    }

	//Checks if an array contains a value
	public static boolean arrayContains(String[] array, String value) {
		for (String valIndx : array) {
			if (valIndx.equals(value)) {
				return true;
			}
		}
		return false;
	}
}
