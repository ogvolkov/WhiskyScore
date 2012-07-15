package org.whiskyscore.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class WhiskyScoreActivity extends Activity {
    private Iterable<WhiskyScore> scores;
    
    private ArrayList<WhiskyScore> results;
    
    private String searchTerm;
    
    private final String searchTermKey = "searchTerm"; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        // load data        
        ScoresLoader loader = new ScoresLoader(getAssets());
        loader.load();
        scores = loader.getScores();                      
        
        // prepare result
        results = new ArrayList<WhiskyScore>();
        final ListView result = (ListView) findViewById(R.id.result);
        result.setAdapter(new ScoreAdapter(getApplicationContext(), results));
        
        // restore previous state (e.g. when screen orientation changes)
        if (savedInstanceState != null) {
        	searchTerm = savedInstanceState.getString(searchTermKey);
        	FilterScores(searchTerm);
        }
                     
        // update results when search text is changed
        final EditText whiskyName = (EditText) findViewById(R.id.whiskyName);
        
        whiskyName.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {						
				searchTerm = whiskyName.getText().toString().toLowerCase();
				FilterScores(searchTerm);
				return false;
			}
		});         
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState){
    	if (searchTerm != null && searchTerm != "") {
    		outState.putString(searchTermKey, searchTerm);
    	}
    }
    
    private void FilterScores(String searchString)
    {
    	results.clear();
    	
    	for(WhiskyScore score: scores) {
	    	if(score.getName().toLowerCase().startsWith(searchString)){
	    		results.add(score);
	    	}
	    }
    }
}