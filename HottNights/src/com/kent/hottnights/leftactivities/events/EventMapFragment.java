package com.kent.hottnights.leftactivities.events;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class EventMapFragment extends Fragment {

	private View mView;
	SupportMapFragment mapFragment;
	MainMenuActivity mma;

	private static float eLat;
	private static float eLong;
	
	//LocationManager locationManager;
	String provider;
	Button drive;
	Button walk;
	
	

	public EventMapFragment() {
		
	}

	public EventMapFragment(float eLat, float eLong) {
		super();
		this.eLat = eLat;
		this.eLong = eLong;
	}

	LocationManager locationManager;
	Navigator nav;
	GoogleMap googleMap;

	public static EventMapFragment mappyFrag() {
		EventMapFragment em = new EventMapFragment(eLat, eLong);
		return em;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);

		mView = inflater.inflate(R.layout.event_map_fragment, container, false);
		mma = (MainMenuActivity) getActivity();
		mapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		fragmenttransaction.add(R.id.mapEvent, mapFragment);
		fragmenttransaction.commit();
		
		drive = (Button) mView.findViewById(R.id.btnDrive);
		walk = (Button) mView.findViewById(R.id.btnWalk);
	 locationManager = (LocationManager) getActivity().getSystemService(Activity.LOCATION_SERVICE);

		  // Creating a criteria object to retrieve provider
		  Criteria criteria = new Criteria();

		  // Getting the name of the best provider
		  provider = locationManager.getBestProvider(criteria, true);
		
		
		drive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				findPath(0);
			}
		});
		
		walk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				findPath(3);
				
				
			}
		});
		
		mView.setBackgroundResource(R.drawable.evasmall1);

		return mView;
	}
	
	public void findPath(int mode)
	{
		if(nav != null )
			nav.removePath();
		
		Location l = locationManager.getLastKnownLocation(provider);
		LatLng yourlocation = new LatLng(l.getLatitude(), l.getLongitude());
		nav = new Navigator(googleMap, yourlocation, evLatLng());
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
		});
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
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(savedInstanceState);

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

		mo.position(evLatLng());
		// mo.icon(gs.getIcon());
		// mo.title(gs.getGsName());
		// mo.snippet(gs.getGsOH());

		Marker marker = googleMap.addMarker(mo);

	}

	public LatLng evLatLng() {
		return new LatLng(eLat, eLong);
	}

}
