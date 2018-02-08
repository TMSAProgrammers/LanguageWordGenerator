package com.company;

public class EnglishLanguageGenerator extends LanguageGenerator {
	
	private final static double[] vfdPremade = generateDistribution(0.66, 5); //Saves on computation if we do this at compile time
	
	public EnglishLanguageGenerator() {
		constants = new String[] {"b", "c", "ch", "d", "d", "f", "g", "g", "h", "j", "k", "k", "l", "m", "m", "n", "p", "p", "p", "qu", "r", "s", "t", "v", "z", "w", "y", "y"};
		vowels = new String[] {"a", "e", "i", "o", "u"};
		
		forbiddenSequences = new String[] {"dk", "uu", "tb", "kg", "zr", "rz", "yr", "vc", "yz", "tv", "ii", "eiy", "kc", "jp", "dc", "vh", "hv", "pj", "kd", "gs", "gf", "xr", "aa", "rkm", "rmk", "nz", "zn", "gb", "rx", "xq", "pc", "hv", "dsv", "xj", "jx"};
		
		wordLengthDistribution = new double[] {2, 3, 4, 4, 5, 5, 5, 6, 7};
		vowelFrequencyDistribution = vfdPremade;
		wordStartFavorabilities = new String[] {"w", "h", "s", "t"};
		wordEndFavorabilities = new String[] {"s", "r", "r", "e", "n", "t"};
		
		firstPreferChance = 0.9;
		lastPreferChance = 0.9;
	}
}
