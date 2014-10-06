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
import com.kent.hottnights.objects.Event;
import com.squareup.picasso.Picasso;

public class EventListAdapter extends BaseAdapter {

	Context context;
	
	protected List<Event> eventList;
	
	public EventListAdapter(Context context, List<Event> eventList) {
		super();
		this.context = context;
		this.eventList = eventList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return eventList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return eventList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return eventList.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		
		if (convertView == null)
		{
			holder = new ViewHolder();
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.event_list_layout, parent, false);
			
			holder.evPic = (ImageView) convertView.findViewById(R.id.ivEventListImg);
			holder.evName = (TextView) convertView.findViewById(R.id.tvEventListName);
			holder.evDescr = (TextView) convertView.findViewById(R.id.tvEventListDescr);
			
			Event what = eventList.get(position);
			
			Picasso.with(context).load(what.getEventPic())
			.placeholder(R.drawable.ic_launcher)
			.error(R.drawable.icon_home).fit().into(holder.evPic);
			
			holder.evName.setText(what.getEventName());
			holder.evDescr.setText(what.getEventDescr());
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	
	public class ViewHolder
	{
		ImageView evPic;
		TextView evName;
		TextView evDescr;
	}

}
