package org.whiskyscore.android;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ScoreAdapter implements ListAdapter {
	private Context context;
	
	private List<WhiskyScore> scores;	
	
	public ScoreAdapter(Context context, List<WhiskyScore> scores){
		this.context = context;
		this.scores = scores;
	}
	
	public int getCount() {
		return scores.size();
	}

	public Object getItem(int position) {
		return scores.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getItemViewType(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.result_row, null);
		}
		
		TextView name = (TextView)view.findViewById(R.id.name);
		TextView score = (TextView)view.findViewById(R.id.score);
		
		WhiskyScore item = scores.get(position);
		name.setText(item.getName());
		score.setText(item.getRating());
		
		return view;
	}

	public int getViewTypeCount() {	
		return 1;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isEmpty() { 
		return getCount() == 0;
	}

	public void registerDataSetObserver(DataSetObserver arg0) {
	}

	public void unregisterDataSetObserver(DataSetObserver arg0) {
	}

	public boolean areAllItemsEnabled() {	
		return true;
	}

	public boolean isEnabled(int position) { 
		return true;
	}
}
