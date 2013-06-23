package com.example.gridview3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends Activity {
	
	public static final int CAMERA_RESULT = 0;
	String imageFilePath;
	Button takePhoto;
	Button saveAll;
	List<NameValuePair> nvps;
	// pairs list
	
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
			// put the list of key value pairs
			List<NameValuePair> nvps = new ArrayList <NameValuePair>();
			nvps.add(new BasicNameValuePair("name", "blah"));
			nvps.add(new BasicNameValuePair("email", "blah@bla"));
			nvps.add(new BasicNameValuePair("url", "http://blah"));
			nvps.add(new BasicNameValuePair("skills", "http://blah"));

			// create PostJason
			PostJSON send = new PostJSON("http://myflashcards111.herokuapp.com/create/", nvps);
			
			// call execute method
			send.execute();
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
	
	// class PostJASON	
	class PostJSON extends AsyncTask<Void, Void, String>{
		private String url;
		private List<NameValuePair> nvps ;
		
		public PostJSON(String _url, List<NameValuePair> _nvps ) {
			url = _url;
			nvps = _nvps;
		}	

		@Override
		protected String doInBackground(Void... params) {
			String responseString = null;
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			try {				
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
				HttpResponse response = httpclient.execute(httpPost);
				HttpEntity responseEntity = response.getEntity();
				responseString = EntityUtils.toString(responseEntity);
			} catch (IOException e) {
				e.printStackTrace();
			}			
			return responseString;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
		}
		
		protected void onPostExecute(String result) {
			Log.v("RESULT", result);
		}
	}	
	// my server for POSTJasonSend is http://myflashcards111.herokuapp.com/create/
	// new URL encode entity, change arg to nvps
	// Request the JSON and do the parsing in the background
		
	//RequestJSON request = new RequestJSON();
	//request.execute(new String[] { twitterURL });

		class RequestJSON extends AsyncTask<String, Void, String>
		{

			@Override
			protected String doInBackground(String... urls) {
				String jsonString = "";

				// Here is the setup of our network request, we create an HttpClient and an HttpGet request object with the URL.
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet(urls[0]);

				try {
				
					// Once we execute the request, we get an HttpResponse
					HttpResponse getResponse = client.execute(getRequest);
					
					// If the status is OK then we move on.
					final int statusCode = getResponse.getStatusLine().getStatusCode();
					if (statusCode == HttpStatus.SC_OK) { 
						
						// Get the content of the response as an InputStream and construct a reader
						HttpEntity getResponseEntity = getResponse.getEntity();
						InputStream inputStream = getResponseEntity.getContent();

						BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputStream));
						StringBuilder stringbuilder = new StringBuilder();
						String currentline = null;

						try {
							while ((currentline = bufferedreader.readLine()) != null) {
								stringbuilder.append(currentline + "\n");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						jsonString = stringbuilder.toString();
		
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return jsonString;
			}
			
			@Override
			protected void onProgressUpdate(Void... values) {
			}
			
			@Override
			protected void onPostExecute(String result) {		
			}			
		}		
}
