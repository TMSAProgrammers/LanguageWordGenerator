package com.company;

abstract class LanguageGenerator {
	
	//Class definition

	public WeightedLists constants;
	public WeightedLists vowels;

    public WeightedLists wordStartFavorabilities;
    public WeightedLists wordEndFavorabilities;

    public int[] wordLengthDistribution;
    public double[] vowelFrequencyDistribution;

	public String[] forbiddenSequences;
	
	public double firstPreferChance;
	public double lastPreferChance;


	//Special language features
    abstract public void postProcessWord(String[] word);



	/*protected static double[] generateDistribution(double center, int resolution) {
		//Error checking
		if (resolution % 2 == 0) {
			throw new java.lang.RuntimeException("Can only accept odd values for resolution sizes in generateDistribution");
		}
		
		double[] distArray = new double[resolution];
		
		double heightValueUp = center;
		double heightValueDown = center;
		for (int i = 0; i <= resolution / 2; i++) {
			distArray[resolution / 2 + i] = heightValueUp;
			distArray[resolution / 2 - i] = heightValueDown;
			
			double x = (i / (resolution / 2.0));
			heightValueUp = ((x*x) + 1) * center;
			heightValueDown = (-(x*x) + 1) * center;
		}
		
		
		return distArray;
	}*/
}
