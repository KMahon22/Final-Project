package com.kent.hottnights.leftactivities.clubs;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.ClubPhotoAdapter;
import com.kent.hottnights.objects.ClubPhotos;

public class ClubPhotoFragment extends ListFragment{

	private String[] photourl;
	private int[] photoid;
	ArrayList<ClubPhotos> arrayPhoto;
	
	public static ClubPhotoFragment photoFrag()
	{
		ClubPhotoFragment p = new ClubPhotoFragment();
		return p;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		arrayPhoto = new ArrayList<ClubPhotos>();
		
		photourl = getResources().getStringArray(R.array.photourls);
		photoid = getResources().getIntArray(R.array.photoid);
		for(int pl = 0; pl < 4 ; pl++)
		{
			ClubPhotos bang = new ClubPhotos(photoid[pl], photourl[pl]);
			
			arrayPhoto.add(bang);
		}
		
		ClubPhotoAdapter adapter = new ClubPhotoAdapter(getActivity().getBaseContext(), arrayPhoto);
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	
}
