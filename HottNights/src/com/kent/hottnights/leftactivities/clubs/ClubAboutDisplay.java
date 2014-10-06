package com.kent.hottnights.leftactivities.clubs;

import com.kent.hottnights.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClubAboutDisplay extends Fragment {
	View dispView;
	
	public static ClubAboutDisplay displayfrag()
	{
		ClubAboutDisplay d = new ClubAboutDisplay();
		return d;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		dispView = inflater.inflate(R.layout.club_about_fragment_temp, container, false);
		return dispView;
	}

}
