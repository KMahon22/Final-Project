/**
 * Activity for loading layout resources
 * 
 * This activity is used to display different layout resources for a tutorial on user interface design.
 * 
 * @author Kent Mahon
 * @version 2014.1009
 * @since 1.0
 */


package com.kent.hottnights;

//import android.R;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kent.hottnights.communicators.ClubListComm;
import com.kent.hottnights.communicators.EventListComm;
import com.kent.hottnights.communicators.HotComm;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.database.SyncBC;
import com.kent.hottnights.leftactivities.CalendarMainFragment;
import com.kent.hottnights.leftactivities.ClubListFragment;
import com.kent.hottnights.leftactivities.EventListFragment;
import com.kent.hottnights.leftactivities.MapMainFragment;
import com.kent.hottnights.leftactivities.ShareMainFragment;
import com.kent.hottnights.leftactivities.clubs.ClubAboutFragment;
import com.kent.hottnights.leftactivities.clubs.ClubContactFragment;
import com.kent.hottnights.leftactivities.clubs.ClubFeaturesFragment;
import com.kent.hottnights.leftactivities.clubs.ClubMapFragment;
import com.kent.hottnights.leftactivities.clubs.ClubPhotoFragment;
import com.kent.hottnights.leftactivities.clubs.ClubReviewFragment;
import com.kent.hottnights.leftactivities.events.EventAboutFragment;
import com.kent.hottnights.leftactivities.events.EventContactsFragment;
import com.kent.hottnights.leftactivities.events.EventFeaturesFragment;
import com.kent.hottnights.leftactivities.events.EventMapFragment;
import com.kent.hottnights.leftactivities.events.EventPromoFragment;
import com.kent.hottnights.utilities.WebServiceURLS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
//import com.prgguru.example.SampleBC;

public class MainMenuActivity extends FragmentActivity implements
		View.OnClickListener, HotComm, ClubListComm, EventListComm {

	
	
	public static ResideMenu resideMenu; // this used to be private
	public static int token;
	private MainMenuActivity mContext;
	private ResideMenuItem itemEvents;
	private ResideMenuItem itemClubs;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemMap;
	private ResideMenuItem itemShare;

	// public SplashScreen s2;

	private ResideMenuItem itemMedia;
	public static FragmentManager fragmentManager;
	public static GoogleMap googleMap;
	// @InjectView(R.id.title_bar_left_menu)
	// Button leftMenu;

	public String[] sendstuff;
	private TextView header;

	// variables for the interface communication
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

	// database sync shit
	ProgressDialog prgDialog;
	HashMap<String, String> queryValues;
	HottDatabaseHandler hots;

	//WEbservice shit
	WebServiceURLS wsu;
	public String getDressUrl; 
	public String updatesyncs;
	
	//stupid shit for screen orientation changes
	static String currentFragment;
	static public Fragment fraggy;
	
	Button leftBut;
	Button rightBut;
	/**
	 * onCreate(Bundle arg0) is called when activity is first started.
	 * Resize menu is set up in here
	 * The variables used to store the web addresses are intialised here
	 * This is also where the timing for the call to the broadcast receiver is set
	 */
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main_activity);
		header = (TextView) findViewById(R.id.tvHeader);
		mContext = this;
		token = 0;
		setUpMenu();
		fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.main_fragment, new EventListFragment()).commit();
		Log.i("Oncreate", "is this called");
		//fragmentManager = getSupportFragmentManager();
		//fraggy = new EventListFragment();
		
		//changeFragment(new EventListFragment());

	
		/*
		if(arg0 != null)
		{
			currentFragment = arg0.getString("currentfragment");
			fraggy = fragmentManager.getFragment(arg0, currentFragment);
			fragmentManager.beginTransaction().replace(R.id.main_fragment, fraggy);
		}else
		{
			fragmentManager.beginTransaction().add(R.id.main_fragment, new EventListFragment()).commit();
		}
		
		*/
		
		

		prgDialog = new ProgressDialog(this);
		prgDialog
				.setMessage("Transferring Data from Remote MySQL DB and Syncing SQLite. Please wait...");
		prgDialog.setCancelable(false);
		
		Intent alarmIntent = new Intent(getApplicationContext(), SyncBC.class);
		// Pending Intent Object
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Alarm Manager Object
		AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		// Alarm Manager calls BroadCast for every Ten seconds (10 * 1000), BroadCase further calls service to check if new records are inserted in 
		// Remote MySQL DB
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 1000 * 1000, pendingIntent);

		
		wsu = new WebServiceURLS();
		getDressUrl = wsu.getIP_ADDRESS() + wsu.getWSFOLDER() + wsu.GET_DRESS;
		updatesyncs = wsu.IP_ADDRESS + wsu.WSFOLDER + wsu.UPDATESYNCSTATUS;
		Log.i("WSURL", updatesyncs);
	}

	/**
	 * setUpMenu()
	 * This function is contains the information for the set up of the Resize menu
	 * used for navigation through out the app
	 */
	private void setUpMenu() {
		// TODO Auto-generated method stub
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.turntables);
		resideMenu.attachToActivity(this);
		resideMenu.setProfilePic(" ");
		resideMenu.setProfileName("BackFlip");

		// resideMenu.setMenuListener(menuListener);
		resideMenu.setScaleValue(0.6f);

		itemEvents = new ResideMenuItem(this, R.drawable.ic_launcher, "Events");
		itemClubs = new ResideMenuItem(this, R.drawable.icon_profile, "Clubs");
		itemCalendar = new ResideMenuItem(this, R.drawable.calendar2,
				"Calendar");
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

		leftBut = (Button) findViewById(R.id.title_bar_left_menu);
		rightBut = (Button) findViewById(R.id.title_bar_right_menu);
		leftBut.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

					}
				});

		rightBut.setOnClickListener(
				new View.OnClickListener() {
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

	/**
	 * onClick(View view)
	 * This is used to switch views throughout the app and to set 
	 * up the different themes. 
	 * 
	 */
	@Override
	public void onClick(View view) {
		if (view == itemEvents) {
			changeFragment(new EventListFragment());
			header.setText("Events");
			header.setTextColor(Color.RED);
			header.setBackgroundColor(Color.BLACK);
			resideMenu.setBackground(R.drawable.evenback1); //triallll
			leftBut.setBackgroundResource(R.drawable.redbutton);
			rightBut.setBackgroundResource(R.drawable.redbutton);
			

		} else if (view == itemClubs) {
			changeFragment(new ClubListFragment());
			header.setText("Clubs");
			header.setTextColor(Color.parseColor("#0000E6"));
			header.setBackgroundColor(Color.BLACK);
			resideMenu.setBackground(R.drawable.turntables);
			leftBut.setBackgroundResource(R.drawable.bluebutton);
			rightBut.setBackgroundResource(R.drawable.bluebutton);
		} else if (view == itemCalendar) {
			changeFragment(new CalendarMainFragment());
			header.setText("Calendar");
		} else if (view == itemMap) {
			changeFragment(new MapMainFragment());
			header.setText("Map");
			header.setTextColor(Color.parseColor("#006600"));
			header.setBackgroundColor(Color.BLACK);
			resideMenu.setBackground(R.drawable.mapback1);
			leftBut.setBackgroundResource(R.drawable.greenbutton);
			rightBut.setBackgroundResource(R.drawable.greenbutton);
		} else if (view == itemShare) {
			changeFragment(new ShareMainFragment());
			header.setText("Share");
			header.setTextColor(Color.parseColor("#CC5200"));
			header.setBackgroundColor(Color.BLACK);
			resideMenu.setBackground(R.drawable.shareback1);
			leftBut.setBackgroundResource(R.drawable.orangebutton);
			rightBut.setBackgroundResource(R.drawable.orangebutton);
		} else if (view == itemMedia) {
			//changeFragment(new SplashScreen());
			syncSQLiteMySQLDB();
			header.setText("Media");
		}

		resideMenu.closeMenu();
	}

	public void changeHead(String head) {
		header.setText(head);
	}

	public void changeFragment(Fragment targetFragment) {
		// TODO Auto-generated method stub
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "fragment")
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.addToBackStack(null).commit();

	}

	public ResideMenu getResideMenu() {
		return resideMenu;

	}

	public static void setToken(int tokee) {
		token = tokee;
	}

	public static int getToken() {
		return token;
	}

	public void dothedew() {

	}

	/**
	 * This function is an interface for communication between fragments
	 * 
	 * @param a The title of the event being sent from the event list fragment to the facebook login fragment
	 * @param b The description of the event being sent from the event list fragment to the facebook login fragment
	 * @param c The caption of the event being sent from the event list fragment to the facebook login fragment
	 * @param d The link of the event being sent from the event list fragment to the facebook login fragment
	 * @param e The picture of the event being sent from the event list fragment to the facebook login fragment
	 * 
	 */
	// /HottComm interface method
	public void eventInfo(String a, String b, String c, String d, String e) {
		// TODO Auto-generated method stub
		// String apple = a;
		// String pear = b;
		// String grape = c;
		//String title = a;

		FacebookLogin s2 = (FacebookLogin) fragmentManager
				.findFragmentById(R.id.share_main_fragment_fb);
		s2.infoToAccept(a, b, c, d, e);

	}
	
	/**
	 * This method is the interface used to communication information from 
	 * the club list fragment to the other realted fragments
	 * 
	 */

	// //ClubListComm interface method
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

		// ClubAboutFragment caf = (ClubAboutFragment)
		// fragmentManager.findFragmentById(R.id.club_about_fragment);
		ClubAboutFragment caf = new ClubAboutFragment(clcClubAbout, clcClubPic);
		// caf.GetAboutInfo(clcClubAbout);
		ClubReviewFragment crf = new ClubReviewFragment(
				Integer.parseInt(clcClubId));
		ClubPhotoFragment cpf = new ClubPhotoFragment(clcPhotoId);
		ClubFeaturesFragment cfc = new ClubFeaturesFragment(clcFeatureId,
				clcDrinksId, clcDressId);
		ClubMapFragment cmf = new ClubMapFragment(clcClubLat, clcClubLong);
		ClubContactFragment ccf = new ClubContactFragment(clcContactId);

	}
	
	/**
	 * This method is the interface used to communication information from 
	 * the event list fragment to the other realted fragments
	 * 
	 */

	// EVENT LIST INTERFACE
	@Override
	public void allEventInfo(int pEventId, String pEventName, String pEventPic,
			String pEventDescr, String pEventAbout, float pEventLat,
			float pEventLong, int pFeatureId, int pDrinksId, int pDressId,
			int pPhotoId) {

		EventAboutFragment eaf = new EventAboutFragment(pEventPic, pEventAbout);
		EventPromoFragment epf = new EventPromoFragment(pPhotoId);
		EventFeaturesFragment eff = new EventFeaturesFragment(pFeatureId,
				pDrinksId, pDressId);
		EventMapFragment emp = new EventMapFragment(pEventLat, pEventLong);
		EventContactsFragment cf = new EventContactsFragment(pEventId);
		// TODO Auto-generated method stub
		Log.i("INSIDEMAININTE", pEventName);

	}

	/**
	 * This method makes the call to the MYSQL database to get all of the dress info it did not 
	 * previously have
	 */
	// SYNCING SHIT
	public void syncSQLiteMySQLDB() {
		AsyncHttpClient client = new AsyncHttpClient();

		RequestParams params = new RequestParams();

		prgDialog.show();

		client.post(getDressUrl, //http://192.168.1.129/TheAppExperts/HottNights/getDress.php
				params, new AsyncHttpResponseHandler() {

					public void onSuccess(String response) {
						prgDialog.hide();

						Log.i("RESPONSE", response);
						updateSQLite(response);
					}

					public void onFailure(int statusCode, Throwable error,
							String content) {
						prgDialog.hide();
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						} else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(
									getApplicationContext(),
									"Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
									Toast.LENGTH_LONG).show();
						}
					}

				});

	}

	
	/**
	 *
	 * @param response The information retrieved from the php page 
	 * 
	 * This function inputs the the information retrieved from the MYSQLserver and puts it in the SQLiteDatabase
	 * 		
	 * 
	 */
	private void updateSQLite(String response) {
		// TODO Auto-generated method stub

		ArrayList<HashMap<String, String>> dresssynclist;
		dresssynclist = new ArrayList<HashMap<String, String>>();

		Gson gson = new GsonBuilder().create();
		try {
			JSONArray arr = new JSONArray(response);
			System.out.println(arr.length());

			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				System.out.println("dress_id");
				System.out.println("dress_hat");
				System.out.println("dress_hoodies");
				System.out.println("dress_shirts");
				System.out.println("dress_pants");
				System.out.println("dress_shoes");

				queryValues = new HashMap<String, String>();

				queryValues.put("dress_id", obj.get("dress_id").toString());
				queryValues.put("dress_hat", obj.get("dress_hat").toString());
				queryValues.put("dress_hoodies", obj.getString("dress_hoodies")
						.toString());
				queryValues.put("dress_shirts", obj.getString("dress_shirts")
						.toString());
				queryValues.put("dress_pants", obj.getString("dress_pants")
						.toString());
				queryValues.put("dress_shoes", obj.getString("dress_shoes")
						.toString());

				hots = new HottDatabaseHandler(this);
				hots.insertDressCode(queryValues);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dress_id", obj.getString("dress_id").toString());
				map.put("status", "1");
				dresssynclist.add(map);

			}

			updateMySQLSyncSts(gson.toJson(dresssynclist));

			reloadActivity();

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
/**
 * 
 * @param json This parameter contains the id of each row which is currently updated 
 * 
 * This function passes this information back to the server so the MYSQLDatabase would know 
 * which rows are replicated/synced with the SQLiteDatabase on the phone
 */
	private void updateMySQLSyncSts(String json) {
		// TODO Auto-generated method stub
		System.out.println(json);
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("apple", json);
		Log.i("WHATARETHSE", json + "");
		Log.i("SYNCCHECK", "is this sync status caled?");
		client.post(updatesyncs, params, new AsyncHttpResponseHandler(){ //"http://192.168.1.129/TheAppExperts/HottNights/updatesyncsts.php"
			
			public void onSuccess(String response) {
				Toast.makeText(getApplicationContext(), "MySQL DB has been informed about Sync activity", Toast.LENGTH_LONG).show();
			}
			
			public void onFailure ( int statusCode, Throwable error, String content)
			{
				Toast.makeText(getApplicationContext(), "Error Ocurred", Toast.LENGTH_LONG).show();
			}
		});

	}

	private void reloadActivity() {
		// TODO Auto-generated method stub
		Intent objIntent = new Intent(getApplicationContext(), MainMenuActivity.class);
		startActivity(objIntent);

	}
	
	public static void currentFragmentOnScreen(String currFrag, Fragment eab)
	{
		currentFragment = currFrag;
		fraggy = eab;
	}
	/*
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("currentfragment", currentFragment);
		FragmentManager fragmentManager2;
		fragmentManager2 = getSupportFragmentManager();
		Log.i("ISCFEMPTY", currentFragment + "");
		Log.i("ISFRAGGYEMPTY", fraggy + "");
		fragmentManager2.putFragment(outState, currentFragment, fraggy);
		
	}
 */
}
