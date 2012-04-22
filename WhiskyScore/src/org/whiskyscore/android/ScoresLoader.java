package org.whiskyscore.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.AssetManager;

public class ScoresLoader{
	private ArrayList<WhiskyScore> scores = new ArrayList<WhiskyScore>();
	
	private final AssetManager assetManager;
	
	public ScoresLoader(AssetManager assetManager){
		this.assetManager = assetManager;
	}
	
	public void load()
	{
		try {
			loadClassicDram();
		} catch (IOException e) {
			e.printStackTrace();
		}	        
	}
	
	public Iterable<WhiskyScore> getScores(){
		return scores;
	}
	
	private void loadClassicDram() throws IOException
	{
		InputStream classicDramFile = assetManager.open("classicdram");
		BufferedReader reader = new BufferedReader(new InputStreamReader(classicDramFile));
					
		for(;;){
			String line = reader.readLine();
			if(line == null) break;
			
			int separatorPosition = line.indexOf(';');
			if (separatorPosition != -1){
				String name = line.substring(0, separatorPosition-1);
				String rating = line.substring(separatorPosition+1);
							
				scores.add(new WhiskyScore(name, rating));
			}
		}
	}
}