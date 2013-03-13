package com.example.gridview3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter { 
	
	private people [] peopleInfo = {
			new people(R.drawable.olya_150, R.drawable.olya_200, "Olya Large",""),
			new people(R.drawable.olya2_150, R.drawable.olya2_200, "Olya2 Large",""),
			new people(R.drawable.olya3_150, R.drawable.olya3_200, "Olya3 Large",""),
			new people(R.drawable.olya4_150, R.drawable.olya4_200, "Olya4 Large","")
	};
	
	/*
	private Integer [] photos = {
			R.drawable.olya_150, R.drawable.olya2_150,
			R.drawable.olya3_150, R.drawable.olya4_150
	}; 
	*/
	private Context mContext;
	public ImageAdapter(Context c){
		mContext=c;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return peopleInfo.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	// here we are setting which image will go into which cell of the gridView
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(mContext); 
			imageView.setLayoutParams(new GridView.LayoutParams(120,120));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(peopleInfo[position].image_name_small);
        return imageView;
	}	
}
