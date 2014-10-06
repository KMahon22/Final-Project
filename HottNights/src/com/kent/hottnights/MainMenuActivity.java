package com.kent.hottnights;

//import android.R;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.kent.hottnights.communicators.ClubListComm;
import com.kent.hottnights.communicators.EventListComm;
import com.kent.hottnights.communicators.HotComm;
import com.kent.hottnights.leftactivities.CalendarMainFragment;
import com.kent.hottnights.leftactivities.ClubListFragment;
import com.kent.hottnights.leftactivities.EventListFragment;
import com.kent.hottnights.leftactivities.MapMainFragment;
import com.kent.hottnights.leftactivities.ShareMainFragment;
import com.kent.hottnights.leftactivities.clubs.ClubAboutFragment;
import com.kent.hottnights.leftactivities.clubs.ClubContactFragment;
import com.kent.hottnights.leftactivities.clubs.ClubFeaturesFragment;
import com.kent.hottnights.leftactivities.clubs.ClubPhotoFragment;
import com.kent.hottnights.leftactivities.clubs.ClubReviewFragment;
import com.kent.hottnights.leftactivities.events.EventAboutFragment;
import com.kent.hottnights.leftactivities.events.EventContactsFragment;
import com.kent.hottnights.leftactivities.events.EventFeaturesFragment;
import com.kent.hottnights.leftactivities.events.EventMapFragment;
import com.kent.hottnights.leftactivities.events.EventPromoFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainMenuActivity extends FragmentActivity implements
		View.OnClickListener, HotComm, ClubListComm, EventListComm {

	public static ResideMenu resideMenu; //this used to be private
	public static int token;
	private MainMenuActivity mContext;
	private ResideMenuItem itemEvents;
	private ResideMenuItem itemClubs;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemMap;
	private ResideMenuItem itemShare;
	
	//public SplashScreen s2;
	
	
	private ResideMenuItem itemMedia;
	public static FragmentManager fragmentManager;
	public static GoogleMap googleMap;
	//@InjectView(R.id.title_bar_left_menu)
	//Button leftMenu;
	
	public String[] sendstuff;
	private TextView header;
	
	//variables for the interface communication
	public String clcClubId; 
	public String clcClubName;
	public String clcClubPic;
	public String clcClubAbout;
	public float clcClubLat;
	public float clcClubLong;
	public int clcFeatureId;
	public int clcContactId;
	public int clcDrinksId;
	public int clcDressId;
	public int clcPhotoId;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main_activity);
		header= (TextView) findViewById(R.id.tvHeader);
		mContext = this;
		token = 0;
		setUpMenu();
		Log.i("Oncreate", "is this called");
		changeFragment(new EventListFragment());
		
		fragmentManager = getSupportFragmentManager();
		
		
		
	}

	private void setUpMenu() {
		// TODO Auto-generated method stub
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.turntables);
		resideMenu.attachToActivity(this);
		resideMenu.setProfilePic(" ");
		resideMenu.setProfileName("BackFlip");
	
		//resideMenu.setMenuListener(menuListener);
		resideMenu.setScaleValue(0.6f);

		itemEvents = new ResideMenuItem(this, R.drawable.ic_launcher, "Events");
		itemClubs = new ResideMenuItem(this, R.drawable.icon_profile, "Clubs");
		itemCalendar = new ResideMenuItem(this, R.drawable.calendar2, "Calendar");
		itemMap = new ResideMenuItem(this, R.drawable.icon_home, "Map");
		itemMedia = new ResideMenuItem(this, R.drawable.icon_home, "Media");
		itemShare = new ResideMenuItem(this, R.drawable.ic_launcher, "Share");

		itemEvents.setOnClickListener(this);
		itemClubs.setOnClickListener(this);
		itemCalendar.setOnClickListener(this);
		itemMap.setOnClickListener(this);
		itemShare.setOnClickListener(this);
		itemMedia.setOnClickListener(this);

		resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemClubs, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemMap, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemShare, ResideMenu.DIRECTION_LEFT);
		
		resideMenu.addMenuItem(itemMedia, ResideMenu.DIRECTION_RIGHT);

		findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

			}
		});
		
		findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);

			}
		});

	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);

	}

	@Override
	public void onClick(View view)
	{
		if (view == itemEvents)
		{
			changeFragment(new EventListFragment());
			header.setText("Events");
			
		} else if (view == itemClubs)
		{
			changeFragment(new ClubListFragment());
			header.setText("Clubs");
		} else if ( view == itemCalendar)
		{
			changeFragment(new CalendarMainFragment());
			header.setText("Calendar");
		} else if (view == itemMap)
		{
			changeFragment(new MapMainFragment());
			header.setText("Map");
		}else if (view == itemShare)
		{
			changeFragment(new ShareMainFragment());
			header.setText("Share");
		}else if (view == itemMedia)
		{
			changeFragment(new SplashScreen());
			header.setText("Media");
		}
		
		resideMenu.closeMenu();
	}

	public void changeHead(String head)
	{
		header.setText(head);
	}
	public void changeFragment (Fragment targetFragment) {
		// TODO Auto-generated method stub
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.main_fragment, targetFragment, "fragment")
		.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		.addToBackStack(null)
		.commit();
		
	}


	public ResideMenu getResideMenu()
	{
		return resideMenu;
		
	}
	
	public static void setToken (int tokee)
	{
		token = tokee;
	}
	
	public static int getToken()
	{
		return token;
	}
	
	public void dothedew()
	{
		
	}

	///HottComm interface method
	public void eventInfo(String a) {
		// TODO Auto-generated method stub
		//String apple = a;
		//String pear = b;
		//String grape = c;
	String title = a;
		
	SplashScreen s2 = (SplashScreen) fragmentManager.findFragmentById(R.id.share_main_fragment_fb);
		s2.infoToAccept(title);
	
	}

	////ClubListComm interface method
	@Override
	public void allClubInfo(String pClubId, String pClubName, String pClubPic,
			String pClubAbout, float pClubLat, float pClubLong, int pFeatureId,
			int pContactId, int pDrinksId, int pDressId, int pPhotoId) {
		// TODO Auto-generated method stub
		 clcClubId = pClubId;
		 clcClubName = pClubName;
		 clcClubPic = pClubPic;
		 clcClubAbout = pClubAbout;
		 clcClubLat = pClubLat;
		 clcClubLong = pClubLong;
		 clcFeatureId = pFeatureId;
		 clcContactId = pContactId;
		 clcDrinksId = pDrinksId;
		 clcDressId = pDressId;
		 clcPhotoId = pPhotoId;
		
		//ClubAboutFragment caf = (ClubAboutFragment) fragmentManager.findFragmentById(R.id.club_about_fragment);
		ClubAboutFragment caf = new ClubAboutFragment(clcClubAbout, clcClubPic);
		// caf.GetAboutInfo(clcClubAbout);
		ClubReviewFragment crf = new ClubReviewFragment(Integer.parseInt(clcClubId));
		ClubPhotoFragment cpf = new ClubPhotoFragment(clcPhotoId);
		ClubFeaturesFragment cfc = new ClubFeaturesFragment(clcFeatureId, clcDrinksId, clcDressId);
		ClubContactFragment ccf = new ClubContactFragment(clcContactId);
		
	}
	
	//EVENT LIST INTERFACE
	@Override
	public void allEventInfo(int pEventId, String pEventName, String pEventPic,
			String pEventDescr, String pEventAbout, float pEventLat,
			float pEventLong, int pFeatureId, int pDrinksId, int pDressId,
			int pPhotoId) {
		
		EventAboutFragment eaf = new EventAboutFragment(pEventPic, pEventAbout);
		EventPromoFragment epf = new EventPromoFragment(pPhotoId);
		EventFeaturesFragment eff = new EventFeaturesFragment(pFeatureId, pDrinksId, pDressId);
		EventMapFragment emp = new EventMapFragment(pEventLat, pEventLong);
		EventContactsFragment cf = new EventContactsFragment( pEventId);
		// TODO Auto-generated method stub
		Log.i("INSIDEMAININTE", pEventName);
		
	}

}
