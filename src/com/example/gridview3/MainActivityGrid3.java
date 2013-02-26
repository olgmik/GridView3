package com.example.gridview3;

import java.io.IOException;
import java.io.InputStream;

import com.example.switchingviews.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivityGrid3 extends Activity {
	
	ImageView imageView;
	
	public Integer [] pictures = {
			R.drawable.olya_200, R.drawable.olya2_200,
			R.drawable.olya3_200, R.drawable.olya4_200
	}; 
	
	imageView = (ImageView) this.findViewById(R.id.imageView2);
	imageView.setImageResource(pictures[position]);
	
	private String[] names={
			"olya_150", "olya2_150",
			"olya3_150", "olya4_150"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_grid3);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this)); 
		
		//final ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2);
		//scaleAnimation.setDuration(3000);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	setContentView(R.layout.activity_second);

	        	//v.startAnimation(scaleAnimation);
	        	//Toast.makeText(MainActivityGrid3.this, "" + names[position], Toast.LENGTH_SHORT).show();
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
