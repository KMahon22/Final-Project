package com.kent.hottnights.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.kent.hottnights.objects.Club;
import com.squareup.picasso.Picasso;

public class ClubListAdapter extends BaseAdapter {

	Context context;

	protected List<Club> clubList;

	public ClubListAdapter(Context context, List<Club> clubList) {
		super();
		this.context = context;
		this.clubList = clubList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return clubList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return clubList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		int i = Integer.parseInt(clubList.get(position).getClub_id());
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder hold;

		if (convertView == null) {
			hold = new ViewHolder();

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.club_list_layout_fragment,
					parent, false);

			hold.clPic = (ImageView) convertView
					.findViewById(R.id.ivClubListImg);
			hold.clName = (TextView) convertView
					.findViewById(R.id.tvClubListName);
			hold.clDescr = (TextView) convertView
					.findViewById(R.id.tvClubListDescr);

			Club who = clubList.get(position);

			Picasso.with(context).load(who.getClub_pic())
					.placeholder(R.drawable.ic_launcher)
					.error(R.drawable.icon_home).fit().into(hold.clPic);
			
			hold.clName.setText(who.getClub_name());
			hold.clDescr.setText(who.getClub_about());
			
			convertView.setTag(hold);
		} else
		{
			hold = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	private class ViewHolder {
		ImageView clPic;
		TextView clName;
		TextView clDescr;
	}

}
