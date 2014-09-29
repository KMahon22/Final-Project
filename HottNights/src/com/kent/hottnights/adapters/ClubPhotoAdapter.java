package com.kent.hottnights.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kent.hottnights.R;
import com.kent.hottnights.objects.ClubPhotos;
import com.squareup.picasso.Picasso;

public class ClubPhotoAdapter extends BaseAdapter{

	Context context;
	
	protected List<ClubPhotos> listPhoto;
	
	/*public ClubPhotoAdapter(Context context) {
		super();
		this.context = context;
		
	} */

	public ClubPhotoAdapter(Context context, List<ClubPhotos> listPhoto) {
		super();
		this.context = context;
		this.listPhoto = listPhoto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listPhoto.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listPhoto.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listPhoto.get(position).getPhotoId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PhotoHolder holder;
		
		if (convertView == null)
		{
			holder = new PhotoHolder();
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.club_photo_layout, parent, false);
			
			holder.phUrl = (ImageView) convertView.findViewById(R.id.ivClubPhotoList);
			
			ClubPhotos which = listPhoto.get(position);
			
			 Picasso.with(context) //
		        .load(which.getPhotoUrl()) //
		        .placeholder(R.drawable.ic_launcher) //
		        .error(R.drawable.icon_home) //
		        .fit() //
		        .into(holder.phUrl);
			 
			 convertView.setTag(holder);
			 }
		else{
			holder = (PhotoHolder) convertView.getTag();
		}
		return convertView;
	}
	
	private class PhotoHolder
	{
		int phId;
		ImageView phUrl;
	}

}
