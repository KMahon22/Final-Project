package com.kent.hottnights.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.kent.hottnights.objects.ClubReviews;

public class ClubReviewAdapter extends BaseAdapter {

	Context context;
	Float conv;
	
	protected List<ClubReviews> listRev;
	//LayoutInflater inflate;
	
	
	public ClubReviewAdapter(Context context, List<ClubReviews> listRev) {
		super();
		this.context = context;
		this.listRev = listRev;
		//this.inflate = inflate;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listRev.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listRev.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listRev.get(position).getReviewId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null)
		{
			holder = new ViewHolder();
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.club_review_layout, parent, false);
			
			holder.revPic = (ImageView) convertView.findViewById(R.id.ivClubReviewListImg);
			holder.revUName = (TextView) convertView.findViewById(R.id.tvClubReviewListName);
			holder.revDescr = (TextView) convertView.findViewById(R.id.tvClubReviewListDescr);
			holder.revRate = (RatingBar) convertView.findViewById(R.id.ratingBar);
			
			
			
			
			ClubReviews which = listRev.get(position);
			//int a =5;
			//float b = (int) a;
			
			
			conv = (float) which.getReviewRating();
			
			holder.revPic.setImageResource(which.getReviewUPic());
			
			holder.revUName.setText(which.getReviewUName());
			
			Log.i("REVNAME", which.getReviewUName());
			
			
			
			holder.revDescr.setText(which.getReviewDescr());
			holder.revRate.setRating(conv);
			
			Log.i("REVRATE", which.getReviewRating()+ "");
			
			
			Log.i("RA", "before convert view");
			
			
			
			
			convertView.setTag(holder);
			Log.i("RA2", "after convert view");
			
			
		
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}
	
	private class ViewHolder
	{
		ImageView revPic;
		TextView revUName;
		TextView revDescr;
		RatingBar revRate;
	}

}
