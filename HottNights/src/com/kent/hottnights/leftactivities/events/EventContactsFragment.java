package com.kent.hottnights.leftactivities.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.kent.hottnights.R;
import com.kent.hottnights.leftactivities.ClubListFragment;

public class EventContactsFragment extends ListFragment {

	private int[] evConImg;
	private String[] evConName;
	private String[] evConNum;
	private String[] evConEmail;
	private String[] evConFbk;
	private String[] evConTwit;
	Fragment frag;

	
	public static EventContactsFragment eContacts()
	{
		EventContactsFragment ev = new EventContactsFragment();
		return ev;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		List<HashMap<String, String>> evCon = new ArrayList<HashMap<String, String>>();
		evConImg = getResources().getIntArray(R.array.evConImgs);
		evConName = getResources().getStringArray(R.array.evConName);
		evConNum = getResources().getStringArray(R.array.evConNum);
		evConEmail = getResources().getStringArray(R.array.evConEmail);
		evConFbk = getResources().getStringArray(R.array.evConFbk);
		evConTwit = getResources().getStringArray(R.array.evConTwit);

		for (int e = 0; e < 3; e++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("img", Integer.toString(evConImg[e]));
			hm.put("name", evConName[e]);
			hm.put("number", evConNum[e]);
			hm.put("email", evConEmail[e]);
			hm.put("fbk", evConFbk[e]);
			hm.put("twitter", evConTwit[e]);
			evCon.add(hm);

		}
		
		String[] from = {"img", "name", "number", "email", "fbk", "twitter"};
		int[] to = {R.id.ivEventContactImg, R.id.tvEventContactName, R.id.tvEventContactNumber, R.id.tvEventContactEmail, R.id.tvEventContactFbk, R.id.tvEventContactTwitter};
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), evCon, R.layout.event_contacts_layout, from, to);
		
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
