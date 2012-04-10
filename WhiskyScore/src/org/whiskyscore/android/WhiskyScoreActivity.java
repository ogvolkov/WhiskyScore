package org.whiskyscore.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class WhiskyScoreActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText whiskyName = (EditText) findViewById(R.id.whiskyName);
        final TextView result = (TextView) findViewById(R.id.result);
        
        whiskyName.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				result.setText("Searching for " + whiskyName.getText());
				return false;
			}
		});
    }
}