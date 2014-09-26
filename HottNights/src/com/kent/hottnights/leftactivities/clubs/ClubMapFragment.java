package com.kent.hottnights.leftactivities.clubs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;

public class ClubMapFragment extends Fragment{

	private View mapView;
	SupportMapFragment mapFragment;
	MainMenuActivity Ma;
	//private GoogleMap googleMap;
	
	public static ClubMapFragment mapFrag()
	{
		ClubMapFragment m = new ClubMapFragment();
		Log.i("CAF","club about fragments is called");
		return m;
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		mapView = inflater.inflate(R.layout.club_map_fragment, container, false);
		
		Ma = (MainMenuActivity) getActivity();
		mapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
		fragmenttransaction.add(R.id.map, mapFragment);
		fragmenttransaction.commit();
		
		/*try {
            // Loading map
            initiliseMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
		return mapView;
	}

	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	
	
}
