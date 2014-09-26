package com.kent.hottnights.leftactivities.clubs;

import com.kent.hottnights.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClubContactFragment extends Fragment {
private View contactview;
	
	public static ClubContactFragment contactFrag()
	{
		ClubContactFragment c = new ClubContactFragment();
		Log.i("CAF","club about fragments is called");
		return c;
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		contactview = inflater.inflate(R.layout.club_contact_fragment, container, false);
		return contactview;
	}

}
