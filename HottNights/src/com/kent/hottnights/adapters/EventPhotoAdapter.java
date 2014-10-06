package com.kent.hottnights.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kent.hottnights.R;
import com.kent.hottnights.objects.EventPosters;
import com.squareup.picasso.Picasso;

public class EventPhotoAdapter extends BaseAdapter {

	Context context;
	
	protected List<EventPosters> posterList;
	
	ImageView postUrl;
	
	
	
	public EventPhotoAdapter(Context context, List<EventPosters> posterList) {
		super();
		this.context = context;
		this.posterList = posterList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return posterList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return posterList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return posterList.get(position).getPosterId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.event_promo_layout, parent, false);
			
			postUrl = (ImageView) convertView.findViewById(R.id.ivEventPosterList);
			
			EventPosters where = posterList.get(position);
			
			 Picasso.with(context) //
		        .load(where.getPosterUrl()) //
		        .placeholder(R.drawable.ic_launcher) //
		        .error(R.drawable.icon_home) //
		        .fit() //
		        .into(postUrl);
			 
			 convertView.setTag(postUrl);
		}
		else
		{
			convertView.getTag();
		}
		return convertView;
	}

	
}
