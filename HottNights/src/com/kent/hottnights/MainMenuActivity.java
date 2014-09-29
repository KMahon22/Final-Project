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
import com.kent.hottnights.leftactivities.CalendarMainFragment;
import com.kent.hottnights.leftactivities.ClubListFragment;
import com.kent.hottnights.leftactivities.EventListFragment;
import com.kent.hottnights.leftactivities.MapMainFragment;
import com.kent.hottnights.leftactivities.ShareMainFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainMenuActivity extends FragmentActivity implements
		View.OnClickListener {

	public static ResideMenu resideMenu; //this used to be private
	public static int token;
	private MainMenuActivity mContext;
	private ResideMenuItem itemEvents;
	private ResideMenuItem itemClubs;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemMap;
	private ResideMenuItem itemShare;
	
	
	private ResideMenuItem itemMedia;
	public static FragmentManager fragmentManager;
	public static GoogleMap googleMap;
	//@InjectView(R.id.title_bar_left_menu)
	//Button leftMenu;
	
	
	private TextView header;

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

}
