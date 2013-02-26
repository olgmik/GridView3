package com.example.gridview3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivityGrid3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_grid3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_grid3, menu);
		return true;
	}

}
