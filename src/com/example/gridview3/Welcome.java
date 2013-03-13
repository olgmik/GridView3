package com.example.gridview3;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends Activity {
	
	public static final int CAMERA_RESULT = 0;
	String imageFilePath;
	Button takePhoto;
	Button saveAll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		takePhoto = (Button) findViewById(R.id.button_photo);
		saveAll = (Button) findViewById(R.id.button_save_all);
	}
	
	public void takePhoto(View clickedView){
		
		if(clickedView == takePhoto){
			// save data about name, email, URL
			Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			File imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg");
			//imageFile.exists()
			imageFilePath = imageFile.getAbsolutePath();
			Uri imageFileUri = Uri.fromFile(imageFile);
			takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,imageFileUri); 
			
			startActivityForResult(takePictureIntent,CAMERA_RESULT); 
		}	
		
		if(clickedView == saveAll){
			// bundle up contents of EditName, EditEmail, EditUrl and Photo credentials
			// and send to the database
			// + populate grid view
		}
	}	
	// Camera application encodes the photo in the return Intent delivered to 
	// onActivityResult() as a small Bitmap in the extras, under the key "data". 
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if (requestCode == CAMERA_RESULT){
			
			BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options(); 
			bmpFactoryOptions.inSampleSize = 8; 
			Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
			
			ImageView imageView = (ImageView) this.findViewById(R.id.imageView_new_photo); 
			
			imageView.setImageBitmap(bmp);						
		}
	}
}
