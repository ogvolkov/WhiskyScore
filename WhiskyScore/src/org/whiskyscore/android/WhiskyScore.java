package org.whiskyscore.android;

public class WhiskyScore {
	private String name;
	
	private String ratingString;
	
	public WhiskyScore(String name, String ratingString)
	{
		this.name = name;
		this.ratingString = ratingString;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRating() {
		return ratingString;
	}
}