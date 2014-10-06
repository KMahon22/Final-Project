package com.kent.hottnights.leftactivities.events;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kent.hottnights.adapters.EventPhotoAdapter;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.EventPosters;
import com.kent.hottnights.objects.Photos;

public class EventPromoFragment extends ListFragment {

	private ArrayList<EventPosters> postersList;
	
	ArrayList<Photos> postersFrmDb;
	HottDatabaseHandler db;
	private static int photoId;
	public EventPromoFragment(int photoId) {
		super();
		this.photoId = photoId;
	}
	
	public static EventPromoFragment posterFrag()
	{
		EventPromoFragment ep = new EventPromoFragment(photoId);
		return ep;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		postersList = new ArrayList<EventPosters>();
		
		db = new HottDatabaseHandler(getActivity().getBaseContext());
		postersFrmDb = db.getPhotos(photoId);
		
		EventPosters boom1 = new EventPosters(postersFrmDb.get(0).getPhoto1());
		EventPosters boom2 = new EventPosters(postersFrmDb.get(0).getPhoto2());
		EventPosters boom3 = new EventPosters(postersFrmDb.get(0).getPhoto3());
		EventPosters boom4 = new EventPosters(postersFrmDb.get(0).getPhoto4());
		EventPosters boom5 = new EventPosters(postersFrmDb.get(0).getPhoto5());
		EventPosters boom6 = new EventPosters(postersFrmDb.get(0).getPhoto6());
		EventPosters boom7 = new EventPosters(postersFrmDb.get(0).getPhoto7());
		
		postersList.add(boom1);
		postersList.add(boom2);
		postersList.add(boom3);
		postersList.add(boom4);
		postersList.add(boom5);
		postersList.add(boom6);
		postersList.add(boom7);
		
		EventPhotoAdapter adapter = new EventPhotoAdapter(getActivity().getBaseContext(), postersList);
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	
	
	
	}
	
	
}
