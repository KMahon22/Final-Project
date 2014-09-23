package com.kent.hottnights;

//import android.R;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kent.hottnights.leftactivities.CalendarMainFragment;
import com.kent.hottnights.leftactivities.EventMainFragment;
import com.kent.hottnights.leftactivities.MapMainFragment;
import com.kent.hottnights.leftactivities.clubs.ClubMainFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainMenuActivity extends FragmentActivity implements
		View.OnClickListener {

	private ResideMenu resideMenu;
	private MainMenuActivity mContext;
	private ResideMenuItem itemEvents;
	private ResideMenuItem itemClubs;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemMap;

	//@InjectView(R.id.title_bar_left_menu)
	//Button leftMenu;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main_activity);
		mContext = this;
		setUpMenu();
		Log.i("Oncreate", "is this called");
		changeFragment(new EventMainFragment());
	}

	private void setUpMenu() {
		// TODO Auto-generated method stub
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.turntables);
		resideMenu.attachToActivity(this);
		//resideMenu.setMenuListener(menuListener);
		resideMenu.setScaleValue(0.6f);

		itemEvents = new ResideMenuItem(this, R.drawable.ic_launcher, "Events");
		itemClubs = new ResideMenuItem(this, R.drawable.icon_profile, "Clubs");
		itemCalendar = new ResideMenuItem(this, R.drawable.calendar2, "Calendar");
		itemMap = new ResideMenuItem(this, R.drawable.icon_home, "Map");

		itemEvents.setOnClickListener(this);
		itemClubs.setOnClickListener(this);
		itemCalendar.setOnClickListener(this);
		itemMap.setOnClickListener(this);

		resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemClubs, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemMap, ResideMenu.DIRECTION_LEFT);

		findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

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
			changeFragment(new EventMainFragment());
		} else if (view == itemClubs)
		{
			changeFragment(new ClubMainFragment());
		} else if ( view == itemCalendar)
		{
			changeFragment(new CalendarMainFragment());
		} else if (view == itemMap)
		{
			changeFragment(new MapMainFragment());
		}
		
		resideMenu.closeMenu();
	}

	private void changeFragment (Fragment targetFragment) {
		// TODO Auto-generated method stub
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.main_fragment, targetFragment, "fragment")
		.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		.commit();
		
	}


	public ResideMenu getResideMenu()
	{
		return resideMenu;
		
	}

}
