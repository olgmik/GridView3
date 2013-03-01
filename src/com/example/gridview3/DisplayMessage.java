package com.example.gridview3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

public class DisplayMessage extends Activity implements OnClickListener {

	class people {
		int image_name_small;
		int image_name_large;
		String name;
		// constructor
		public people(int _image_name_small, int _image_name_large, String _name){
			image_name_small = 	_image_name_small;
			image_name_large = 	_image_name_large;
			name = _name;
		}
	}
	// create an array of class PEOPLE, lets call this array peopleInfo
	// cause it will hold each person's photos and name
	private people [] peopleInfo = {
			new people(R.drawable.olya_150, R.drawable.olya_200, "Olya Large"),
			new people(R.drawable.olya2_150, R.drawable.olya2_200, "Olya2 Large"),
			new people(R.drawable.olya3_150, R.drawable.olya3_200, "Olya3 Large"),
			new people(R.drawable.olya4_150, R.drawable.olya4_200, "Olya4 Large")
	};
	
	ImageView imageView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// --- 1) Always set up  layout view right after super.onCreate ---//
		setContentView(R.layout.display);
		
		// --- 3) Get the intent which is our cell # in gridView ---//
		Intent intent = getIntent();
		int message = -1; 
		int indexOfFileName = intent.getIntExtra(MainActivityGrid3.EXTRA_MESSAGE, message); 
		//Toast t = Toast.makeText(this, "" + indexOfFileName, Toast.LENGTH_SHORT);
	    //t.show();
	    	
		// --- 5) This var will go into setImageResource ---//
		int myImage = peopleInfo[indexOfFileName].image_name_large;
		
		// --- 2) Standard procedure - instantiate imageView (declared right before onCreate)
		imageView = (ImageView) this.findViewById(R.id.imageView1);
		imageView.setOnClickListener(this);
		
		// --- 5)  set image source, setImageResource takes an int argument of the image
		// indexOfFileName doesn't work, cause it's an index of object in peoplInfo, thus 
		// go to step 4 and make a var that contains only the image to display
		// --- 6) now setImageResource 
		imageView.setImageResource(myImage); 
		
		// create the text view for name to put inside of the display.xml layout
		TextView textView = (TextView) this.findViewById(R.id.textView1);
		textView.setTextSize(40);
		// this is the same procedure, only setting text, and we don't make a var for an argument
		textView.setText(peopleInfo[indexOfFileName].name);
						
	}

	@Override
	public void onClick(View arg0) {
		Log.v("DisplayMessageActivity", "large image was clicked");
		Intent intent = new Intent(this, MainActivityGrid3.class);		
		startActivity(intent);
	}
}
