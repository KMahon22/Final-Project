package com.kent.hottnights.leftactivities.clubs;

import com.kent.hottnights.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClubFeaturesFragment extends Fragment {

private View aboutview;
	
	public static ClubFeaturesFragment featuresFrag()
	{
		ClubFeaturesFragment n = new ClubFeaturesFragment();
		Log.i("CAF","club about fragments is called");
		return n;
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		aboutview = inflater.inflate(R.layout.club_features_fragment, container, false);
		return aboutview;
	}

}
