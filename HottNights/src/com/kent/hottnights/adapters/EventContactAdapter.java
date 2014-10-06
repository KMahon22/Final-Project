package com.kent.hottnights.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.kent.hottnights.objects.EventContact;
import com.squareup.picasso.Picasso;

public class EventContactAdapter extends BaseAdapter {

	Context context;
	ArrayList<EventContact> ecl;
	
	
	
	public EventContactAdapter(Context context, ArrayList<EventContact> ecl) {
		super();
		this.context = context;
		this.ecl = ecl;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ecl.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ecl.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return ecl.get(position).getEcId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		EcHolder holder;
		
		if (convertView == null)
		{
			holder = new EcHolder();
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.event_contacts_layout, parent, false);
			
			holder.ecImg = (ImageView) convertView.findViewById(R.id.ivEventContactImg);
			holder.ecName = (TextView) convertView.findViewById(R.id.tvEventContactName);
			holder.ecNum = (TextView) convertView.findViewById(R.id.tvEventContactNumber);
			holder.ecEmail = (TextView) convertView.findViewById(R.id.tvEventContactEmail);
			holder.ecFbk = (TextView) convertView.findViewById(R.id.tvEventContactFbk);
			holder.ecTwitter = (TextView) convertView.findViewById(R.id.tvEventContactTwitter);
			
			EventContact how = ecl.get(position);
			
			Picasso.with(context).load(how.getEcPic())
			.placeholder(R.drawable.ic_launcher)
			.error(R.drawable.icon_home).fit().into(holder.ecImg);
			
			holder.ecName.setText(how.getEcName());
			holder.ecNum.setText(how.getEcNumber());
			holder.ecEmail.setText(how.getEcEmail());
			holder.ecFbk.setText(how.getEcFbk());
			holder.ecTwitter.setText(how.getEcTwitter());
			
			convertView.setTag(holder);
		}else
		{
			holder = (EcHolder) convertView.getTag();
		}
		
		return convertView;
	}
	
	public class EcHolder
	{
		ImageView ecImg;
		TextView ecName;
		TextView ecPic;
		TextView ecNum;
		TextView ecEmail;
		TextView ecFbk;
		TextView ecTwitter;
	}

}
