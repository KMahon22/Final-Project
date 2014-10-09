package com.kent.hottnights.leftactivities.clubs;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.tyczj.mapnavigator.Navigator;

public class ClubMapFragment extends Fragment{

	private View mapView;
	SupportMapFragment mapFragment;
	MainMenuActivity Ma;
	//private GoogleMap googleMap;
	
	private static float cLat;
	private  static float cLong;
	
	String provider;
	Button btnWalk;
	Button btnDrive;
	
	
	
	public ClubMapFragment(float cLat, float cLong) {
		super();
		this.cLat = cLat;
		this.cLong = cLong;
	}
	
	LocationManager locationManager;
	GoogleMap googleMap;
	Navigator nav;

	public static ClubMapFragment mapFrag()
	{
		ClubMapFragment m = new ClubMapFragment(cLat, cLong);
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
		fragmenttransaction.add(R.id.mapClub, mapFragment);
		fragmenttransaction.commit();
		
		btnWalk = (Button) mapView.findViewById(R.id.btnClubWalk);
		btnDrive = (Button) mapView.findViewById(R.id.btnClubDrive);
		
		locationManager = (LocationManager) getActivity().getSystemService(Activity.LOCATION_SERVICE);

		  // Creating a criteria object to retrieve provider
		  Criteria criteria = new Criteria();

		  // Getting the name of the best provider
		  provider = locationManager.getBestProvider(criteria, true);
		
		  btnDrive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				findPath(0);
				
			}
		});
		  
		  btnWalk.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				findPath(3);
				
			}
		});
		  
		  /*try {
            // Loading map
            initiliseMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
		return mapView;
	}

	public void findPath(int mode)
	{
		if(nav != null )
			nav.removePath();
		
		Location l = locationManager.getLastKnownLocation(provider);
		LatLng yourlocation = new LatLng(l.getLatitude(), l.getLongitude());
		nav = new Navigator(googleMap, yourlocation, clLatLng());
		nav.setMode(mode, 0, -1);
		nav.findDirections(false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		getView().addOnLayoutChangeListener(new OnLayoutChangeListener() {

			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				// TODO Auto-generated method stub

				if (mapFragment.getMap() != null && googleMap == null) {
					OnMapInitialized();
					getView().removeOnLayoutChangeListener(this);
				}
			}

			private void OnMapInitialized() {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.setBackgroundResource(R.drawable.clback1);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		googleMap = null;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		OnMapInitialized();
	}
	
	public void OnMapInitialized() {
		googleMap = mapFragment.getMap();

		double latitude = 51.474847210935415;
		double longitude = -0.13504654169082642;
		float zoom = 9.243613f;
		zoom = 13f;

		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// Showing / hiding your current location
		googleMap.setMyLocationEnabled(true);

		// Enable / Disable zooming controls
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		// Enable / Disable my location button
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Enable / Disable Compass icon
		googleMap.getUiSettings().setCompassEnabled(true);

		// Enable / Disable Rotate gesture
		googleMap.getUiSettings().setRotateGesturesEnabled(false);

		// Enable / Disable zooming functionality
		googleMap.getUiSettings().setZoomGesturesEnabled(true);

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
				latitude, longitude), zoom));

		locationManager = (LocationManager) getActivity().getSystemService(
				Activity.LOCATION_SERVICE);

		googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {

			@Override
			public View getInfoWindow(Marker arg0) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {
				View v = getActivity().getLayoutInflater().inflate(
						R.layout.map_info_window, null);

				;

				TextView tv = (TextView) v.findViewById(R.id.tvMapInfoName);
				tv.setText(marker.getTitle());
				TextView tb = (TextView) v.findViewById(R.id.tvMapInfoOH);
				tb.setText(marker.getSnippet());

				// markName.findViewById(R.id.tvMapInfoName);
				// markOH.findViewById(R.id.tvMapInfoOH);

				// markName.setText(marker.getTitle());
				// markOH.setText(marker.getSnippet());

				return v;
			}
		});
		
		MarkerOptions mo = new MarkerOptions();

		mo.position(clLatLng());
		// mo.icon(gs.getIcon());
		// mo.title(gs.getGsName());
		// mo.snippet(gs.getGsOH());

		Marker marker = googleMap.addMarker(mo);

	}
	
	public LatLng clLatLng() {
		return new LatLng(cLat, cLong);
	}
	
	
}
