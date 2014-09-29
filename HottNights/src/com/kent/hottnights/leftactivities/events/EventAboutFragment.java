package com.kent.hottnights.leftactivities.events;

import com.kent.hottnights.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventAboutFragment extends Fragment {

	
	private View eAboutView;
	
	public static EventAboutFragment eventAboutFragment()
	{
		EventAboutFragment eab = new EventAboutFragment();
		return eab;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		eAboutView = inflater.inflate(R.layout.event_about_fragment, container, false);
		return eAboutView;
	
	}
}
