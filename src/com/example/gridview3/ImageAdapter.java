package com.example.gridview3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	private Integer [] photos = {
			R.drawable.olya_150, R.drawable.olya2_150,
			R.drawable.olya3_150, R.drawable.olya4_150
	}; 
	
	private Context mContext;
	public ImageAdapter(Context c){
		mContext=c;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photos.length;
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
        imageView.setImageResource(photos[position]);
        return imageView;
	}	
}
