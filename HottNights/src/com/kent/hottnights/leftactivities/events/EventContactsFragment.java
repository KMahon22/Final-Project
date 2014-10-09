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

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.EventContactAdapter;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.EventContact;

public class EventContactsFragment extends ListFragment {

	private int[] evConImg;
	private String[] evConName;
	private String[] evConNum;
	private String[] evConEmail;
	private String[] evConFbk;
	private String[] evConTwit;
	Fragment frag;
	HottDatabaseHandler db;
	ArrayList<EventContact> ecFrmDb;
	private static int event_id;

	public EventContactsFragment(int event_id) {
		super();
		this.event_id = event_id;
	}

	public static EventContactsFragment eContacts() {
		EventContactsFragment ev = new EventContactsFragment(event_id);
		return ev;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// List<HashMap<String, String>> evCon = new ArrayList<HashMap<String,
		// String>>();
		ArrayList<EventContact> evCon = new ArrayList<EventContact>();
		db = new HottDatabaseHandler(getActivity().getBaseContext());
		ecFrmDb = db.getEC(event_id);

		evConImg = getResources().getIntArray(R.array.evConImgs);
		evConName = getResources().getStringArray(R.array.evConName);
		evConNum = getResources().getStringArray(R.array.evConNum);
		evConEmail = getResources().getStringArray(R.array.evConEmail);
		evConFbk = getResources().getStringArray(R.array.evConFbk);
		evConTwit = getResources().getStringArray(R.array.evConTwit);

		for (int e = 0; e < ecFrmDb.size(); e++) {
			HashMap<String, String> hm = new HashMap<String, String>();

			EventContact orange = new EventContact(ecFrmDb.get(e).getEcPic(),
					ecFrmDb.get(e).getEcName(), ecFrmDb.get(e).getEcNumber(),
					ecFrmDb.get(e).getEcEmail(), ecFrmDb.get(e).getEcFbk(),
					ecFrmDb.get(e).getEcTwitter());

			hm.put("img", Integer.toString(evConImg[e]));
			hm.put("name", evConName[e]);
			hm.put("number", evConNum[e]);
			hm.put("email", evConEmail[e]);
			hm.put("fbk", evConFbk[e]);
			hm.put("twitter", evConTwit[e]);
			// evCon.add(hm);
			evCon.add(orange);

		}

		String[] from = { "img", "name", "number", "email", "fbk", "twitter" };
		int[] to = { R.id.ivEventContactImg, R.id.tvEventContactName,
				R.id.tvEventContactNumber, R.id.tvEventContactEmail,
				R.id.tvEventContactFbk, R.id.tvEventContactTwitter };

		// SimpleAdapter adapter = new
		// SimpleAdapter(getActivity().getBaseContext(), evCon,
		// R.layout.event_contacts_layout, from, to);
		EventContactAdapter adapter = new EventContactAdapter(getActivity()
				.getBaseContext(), evCon);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view.setBackgroundResource(R.drawable.evasmall1);
	}

}
