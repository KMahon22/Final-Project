package com.kent.hottnights.leftactivities;

import java.util.ArrayList;

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
import android.widget.LinearLayout;
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
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.Club;
import com.kent.hottnights.objects.Event;
import com.kent.hottnights.objects.Food;
import com.kent.hottnights.objects.GasStations;
import com.special.ResideMenu.ResideMenu;

public class MapMainFragment extends Fragment {

	private View parentView;
	private ResideMenu resideMenu;
	SupportMapFragment mapFragment;
	GoogleMap googleMap;

	MainMenuActivity parentActivity;

	HottDatabaseHandler db;
	Float[] lls;
	// buttons for the actual fragment
	Button btnEvents;
	Button btnClubs;
	Button btnFood;
	Button btnGS;

	// view for on marker click
	TextView markName;
	TextView markOH;

	// map shit
	LocationManager locationManager;
	ArrayList<GasStations> gsFrmDb;
	ArrayList<Club> clubFrmDb;
	ArrayList<Food> foodsFrmDb;
	ArrayList<Event> eventFrmDb;
	
	ArrayList<Marker> listOfMarkers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		parentView = inflater.inflate(R.layout.map_main_fragment, container,
				false);
		setUpViews();
		parentActivity = (MainMenuActivity) getActivity();
		mapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		fragmenttransaction.add(R.id.mapmain, mapFragment);
		fragmenttransaction.commit();

		db = new HottDatabaseHandler(getActivity().getBaseContext());
		gsFrmDb = db.getGS();
		clubFrmDb = db.GetClubs();
		foodsFrmDb = db.getFood();
		eventFrmDb = db.getEvents();

		btnEvents = (Button) parentView.findViewById(R.id.btnMapEvents);
		btnClubs = (Button) parentView.findViewById(R.id.btnMapClubs);
		btnFood = (Button) parentView.findViewById(R.id.btnMapFood);
		btnGS = (Button) parentView.findViewById(R.id.btnMapStations);
		listOfMarkers = new ArrayList<Marker>();
		
		btnClubs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClubMarkers();
			}
		});
		
		btnGS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GasStationMarkers();
			}
		});

		btnFood.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FoodMarkers();
			}
		});
		
		btnEvents.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventMarkers();
			}
		});
		return parentView;
	}

	private void setUpViews() {
		// TODO Auto-generated method stub
		parentActivity = (MainMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();

		/*
		 * parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub resideMenu.openMenu(ResideMenu.DIRECTION_LEFT); } });
		 */

		LinearLayout ignored_view = (LinearLayout) parentView
				.findViewById(R.id.mapmainlay);
		resideMenu.addIgnoredView(ignored_view);
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
		googleMap.getUiSettings().setZoomControlsEnabled(false);

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

		GasStationMarkers();

	}
	
	public void GasStationMarkers()
	{
		for (Marker mk : listOfMarkers) {
			mk.remove();
		}
		
		listOfMarkers.clear();
		
		for (GasStations gs : gsFrmDb) {
			MarkerOptions mo = new MarkerOptions();
			mo.position(gs.getLatLng());
			// mo.icon(gs.getIcon());
			mo.title(gs.getGsName());
			mo.snippet(gs.getGsOH());

			Marker marker = googleMap.addMarker(mo);
			Log.i("MPAMAR", marker.getPosition().toString());
			listOfMarkers.add(marker);
		}
	}
	
	public void ClubMarkers()
	{
		for (Marker mk : listOfMarkers) {
			mk.remove();
		}
		
		listOfMarkers.clear();
		
		for (Club cl : clubFrmDb) {
			MarkerOptions mo = new MarkerOptions();
			mo.position(cl.clubLatLng());
			// mo.icon(gs.getIcon());
			mo.title(cl.getClub_name());
			mo.snippet(cl.getClub_about());

			Marker marker = googleMap.addMarker(mo);
			Log.i("MPAMAR", marker.getPosition().toString());
			listOfMarkers.add(marker);
		}
	}
	
	public void FoodMarkers()
	{
		for (Marker mk : listOfMarkers) {
			mk.remove();
		}
		
		listOfMarkers.clear();
		
		for (Food ff : foodsFrmDb) {
			MarkerOptions mo = new MarkerOptions();
			mo.position(ff.foodLatLng());
			// mo.icon(gs.getIcon());
			mo.title(ff.getFoodName());
			mo.snippet(ff.getFoodOH());

			Marker marker = googleMap.addMarker(mo);
			Log.i("MPAMAR", marker.getPosition().toString());
			listOfMarkers.add(marker);
		}
	}
	
	public void EventMarkers()
	{
		for (Marker mk : listOfMarkers) {
			mk.remove();
		}
		
		listOfMarkers.clear();
		
		for (Event ee : eventFrmDb) {
			MarkerOptions mo = new MarkerOptions();
			mo.position(ee.evLatLang());
			// mo.icon(gs.getIcon());
			mo.title(ee.getEventName());
			mo.snippet(ee.getEventDescr());

			Marker marker = googleMap.addMarker(mo);
			Log.i("MPAMAR", marker.getPosition().toString());
			listOfMarkers.add(marker);
		}
	}
	
	
	private Location getLastKnownLocation() {

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		return locationManager.getLastKnownLocation(provider);

	}

}
