 package com.example.gridview3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivityGrid3 extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.gridview3.MESSAGE";
	
	int position;
	
	public void sendMessage(int position) {
		Intent intent = new Intent(this, DisplayMessage.class);		
		intent.putExtra(EXTRA_MESSAGE, position); // position = cell#
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_grid3);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this)); 
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	sendMessage(position); 
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_grid3, menu);
		return true;
	}

}
