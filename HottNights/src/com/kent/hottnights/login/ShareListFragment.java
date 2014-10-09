package com.kent.hottnights.login;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.EventListAdapter;
import com.kent.hottnights.communicators.HotComm;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.Event;
import com.kent.hottnights.objects.ShareListObjs;

public class ShareListFragment extends ListFragment {

	
	private String[] cname;
	private String[] cdescr;
	private int[] cil;
	private String[] rbCheck;
	
	//ArrayList<ShareListObjs> arrayShare;
	ArrayList<Event> arrayShare;
	ArrayList<Event> eventFrmDb;
	ImageView change;
	private static View rootView;
	HotComm com;
	
	HottDatabaseHandler db;
	
	public static ShareListFragment shareFrag()
	{
		ShareListFragment r = new ShareListFragment();
		return r;
	}
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	
//	arrayShare = new ArrayList<ShareListObjs>();
	arrayShare = new ArrayList<Event>();
	db = new HottDatabaseHandler(getActivity().getBaseContext());
	
	eventFrmDb = db.getEvents();
	cname = getResources().getStringArray(R.array.cnames);
	cdescr = getResources().getStringArray(R.array.cdescr);
	cil = getResources().getIntArray(R.array.cimgs);
	rbCheck = getResources().getStringArray(R.array.radioButton);
	
	for(int s = 0; s < eventFrmDb.size(); s++)
	{
		boolean toss = Boolean.parseBoolean(rbCheck[s]);
		ShareListObjs black = new ShareListObjs(cil[s], cname[s], cdescr[s], toss);
		Event purple = new Event(eventFrmDb.get(s).getEventName(), eventFrmDb.get(s).getEventPic(), eventFrmDb.get(s).getEventDescr());
		
	//	arrayShare.add(black);
		arrayShare.add(purple);
		
	}
	//arrayShare.get(1).isShareIcheck();
	
	EventListAdapter adapter = new EventListAdapter(getActivity().getBaseContext(), arrayShare);
	
	setListAdapter(adapter);
	
	com = (HotComm) getActivity();
	
	return super.onCreateView(inflater, container, savedInstanceState);
}

View selectedView = null;

@Override
public void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	//change.findViewById(R.id.ivShareListOnClick);
	//arrayShare.get(position).setShareLimg(R.drawable.icon_home);
	//shareFrag();
	//change.setImageResource(R.drawable.icon_home)
	//arrayShare.get(1).getShareLname();
	
	
	
		if(selectedView != null)
		{
			selectedView.setBackgroundColor(Color.TRANSPARENT);
		}
		Log.d("test",selectedView + "");
		selectedView = v;
		Log.d("test",selectedView + "");
		v.setBackgroundColor(Color.parseColor("#A74F14"));
	
	
	
	com.eventInfo(arrayShare.get(position).getEventName(), arrayShare.get(position).getEventDescr(), arrayShare.get(position).getEventAbout(), arrayShare.get(position).getEventPic(),arrayShare.get(position).getEventPic());
	
	
	
	Log.i("OLICINEVENT", "why dont i click :(");
}
	
}
