package org.whiskyscore.android;

import java.util.ArrayList;

import android.app.Activity;
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
                
        // load data        
        ScoresLoader loader = new ScoresLoader(getAssets());
        loader.load();
        final Iterable<WhiskyScore> scores = loader.getScores();
        
        // prepare event handlers
        final EditText whiskyName = (EditText) findViewById(R.id.whiskyName);
        final ListView result = (ListView) findViewById(R.id.result);
        
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
				return false;
			}
		});         
    }
}