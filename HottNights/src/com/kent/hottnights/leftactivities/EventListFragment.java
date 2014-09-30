package com.kent.hottnights.leftactivities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.kent.hottnights.leftactivities.events.EventMainFragment;
import com.special.ResideMenu.ResideMenu;

public class EventListFragment  extends ListFragment{

	private View parentView;
	private ResideMenu resideMenu;
	private String[] evName;
	private String[] evDescr;
	private int[] evImgs;
	
	Fragment fragger;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//parentView = inflater.inflate(R.layout.event_main_fragment, container, false);
		//setUpViews();
		//return parentView;
		
		List<HashMap<String,String>> eList = new ArrayList<HashMap<String,String>>();
		
		evName = getResources().getStringArray(R.array.evListnames);
		evDescr = getResources().getStringArray(R.array.evListdescr);
		evImgs = getResources().getIntArray(R.array.evListimgs);
		
		for (int e = 0; e < 5; e++)
		{
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("name", evName[e]);
			hm.put("descr", evDescr[e]);
			hm.put("posters", Integer.toString(evImgs[e]));
			eList.add(hm);
		}
		
		String[] from = {"name", "descr", "posters"};
		
		int[] to ={R.id.tvEventListName, R.id.tvEventListDescr, R.id.ivEventListImg};
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), eList, R.layout.event_list_layout, from, to);
		
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void setUpViews() {
		// TODO Auto-generated method stub
		MainMenuActivity parentActivity = (MainMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
		
	/*parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
		}
	}) ; */
		
	
	LinearLayout ignored_view = (LinearLayout) parentView.findViewById(R.id.ignoredthisplzevent);
	resideMenu.addIgnoredView(ignored_view);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Fragment frag = new EventMainFragment();
		MainMenuActivity.fragmentManager.beginTransaction().replace(R.id.main_fragment, frag).addToBackStack(null).commit();
	}

}