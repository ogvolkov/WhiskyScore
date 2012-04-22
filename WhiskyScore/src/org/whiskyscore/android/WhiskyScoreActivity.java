package org.whiskyscore.android;

<<<<<<< HEAD
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.res.AssetManager;
>>>>>>> 0d14a1040d046067b575d088228d77c97f963316
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class WhiskyScoreActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
<<<<<<< HEAD
        // load data        
        ScoresLoader loader = new ScoresLoader(getAssets());
        loader.load();
        final Iterable<WhiskyScore> scores = loader.getScores();
=======
        // load data
        final ArrayList<WhiskyScore> scores = new ArrayList<WhiskyScore>();
        AssetManager assetManager = getAssets();        
        try {
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
>>>>>>> 0d14a1040d046067b575d088228d77c97f963316
        
        // prepare event handlers
        final EditText whiskyName = (EditText) findViewById(R.id.whiskyName);
        final ListView result = (ListView) findViewById(R.id.result);
        
<<<<<<< HEAD
        
        whiskyName.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				ArrayList<WhiskyScore> results = new ArrayList<WhiskyScore>();
				
				String searchTerm = whiskyName.getText().toString().toLowerCase();
			    
				for(WhiskyScore score: scores){
			    	if(score.getName().toLowerCase().startsWith(searchTerm)){
			    		results.add(score);
			    	}
			    }
				
			    result.setAdapter(new ScoreAdapter(getApplicationContext(), results));
=======
        whiskyName.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				ArrayList<String> results = new ArrayList<String>();
				String searchTerm = whiskyName.getText().toString().toLowerCase();
			    
				for(WhiskyScore score: scores){
			    	String name = score.getName();
			    	String rating = score.getRating();			    
			    	if(name.toLowerCase().startsWith(searchTerm)){
			    		results.add(name + " [" + rating + "]");
			    	}
			    }
				
			    result.setAdapter(new ArrayAdapter<String>(WhiskyScoreActivity.this, android.R.layout.simple_list_item_1, results));
>>>>>>> 0d14a1040d046067b575d088228d77c97f963316
				return false;
			}
		});         
    }
}