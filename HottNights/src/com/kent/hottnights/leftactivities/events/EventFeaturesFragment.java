package com.kent.hottnights.leftactivities.events;

import com.kent.hottnights.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventFeaturesFragment extends Fragment{

	private View eFeatView;
	
	public static EventFeaturesFragment eventFeaturesFragment()
	{
		EventFeaturesFragment efb = new EventFeaturesFragment();
		return efb;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		eFeatView = inflater.inflate(R.layout.event_features_fragment, container, false);
		return eFeatView;
	
	}
	
}
