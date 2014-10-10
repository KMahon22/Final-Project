package com.kent.hottnights.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kent.hottnights.objects.Club;
import com.kent.hottnights.objects.ClubContacts;
import com.kent.hottnights.objects.ClubReviews;
import com.kent.hottnights.objects.Dress;
import com.kent.hottnights.objects.Drinks;
import com.kent.hottnights.objects.Event;
import com.kent.hottnights.objects.EventContact;
import com.kent.hottnights.objects.Features;
import com.kent.hottnights.objects.Food;
import com.kent.hottnights.objects.GasStations;
import com.kent.hottnights.objects.Photos;

public class HottDatabaseHandler extends SQLiteOpenHelper {

	private static final int DB_VERSION = 16;
	private static final String DB_NAME = "hottnights";

	// CLUBTABLE NAME AND FIELDNAMES
	private static final String TABLE_CLUBS = "Clubs";
	private static final String CLUB_ID = "club_id";
	private static final String CLUB_NAME = "club_name";
	private static final String CLUB_PIC = "club_pic_url";
	private static final String CLUB_ABOUT = "club_about";
	private static final String CLUB_LAT = "club_lat";
	private static final String CLUB_LONG = "club_long";
	private static final String FEATURE_ID = "feature_id";
	private static final String CONTACT_ID = "contact_id";
	private static final String DRINKS_ID = "drinks_id";
	private static final String DRESS_ID = "dress_id";
	private final ArrayList<Club> clubList = new ArrayList<Club>();

	// EVENTTABLE NAME AND FIELDNAMES
	private static final String TABLE_EVENT = "Events";
	private static final String EVENT_ID = "event_id";
	private static final String EVENT_NAME = "event_name";
	private static final String EVENT_PIC = "event_pic";
	private static final String EVENT_DESCR = "event_descr";
	private static final String EVENT_ABOUT = "event_about";
	private static final String EVENT_LAT = "event_lat";
	private static final String EVENT_LONG = "event_long";
	// private static final String FEATURE_ID = "feature_id";
	// private static final String CONTACT_ID = "contact_id";
	// private static final String DRINKS_ID = "drinks_id";
	// private static final String DRESS_ID = "dress_id";
	private static final ArrayList<Event> eventList = new ArrayList<Event>();

	// CLUBCONTACTS NAME AND FIELDNAMEs
	private static final String TABLE_CLUB_CONTACTS = "ClubContacts";
	// private static final String CONTACT_ID = "contact_id"; //defined already
	// in clubtable
	private static final String CONTACT_ADDRESS = "contact_address";
	private static final String CONTACT_NUMBER1 = "contact_number1";
	private static final String CONTACT_NUMBER2 = "contact_number2";
	private static final String CONTACT_WEBSITE = "contact_website";
	private static final String CONTACT_EMAIL = "contact_email";
	private static final String CONTACT_FACEBOOK = "contact_facebook";
	private static final String CONTACT_TWITTER = "contact_twitter";
	private final ArrayList<ClubContacts> clubConList = new ArrayList<ClubContacts>();

	// FEATURES AND TABLE FIELDS
	private static final String TABLE_FEATURES = "Features";
	// private static final String FEATURE_ID = "feature_id";
	private static final String FEATURE_ADM = "features_adm";
	private static final String FEATURE_DATE = "features_date";
	private static final String FEATURE_TIME = "features_time";
	private static final String FEATURE_PROMO = "features_promo";
	private final List<Features> featuresList = new ArrayList<Features>();

	// DRINKS AND TABLE FIELDS
	private static final String TABLE_DRINKS = "Drinks";
	// private static final String DRINKS_ID = "drinks_id";
	private static final String DRINK1 = "drink1";
	private static final String DRINK2 = "drink2";
	private static final String DRINK3 = "drink3";
	private static final String DRINK4 = "drink4";
	private static final String DRINK5 = "drink5";
	private static final String DRINK6 = "drink6";
	private static final String DRINK7 = "drink7";
	private final List<Drinks> drinksList = new ArrayList<Drinks>();

	// DRESS AND TABLE FIELDS
	private static final String TABLE_DRESS = "Dress";
	// private static final String DRESS_ID = "dress_id";
	private static final String DRESS_HATS = "dress_hat";
	private static final String DRESS_HOODIES = "dress_hoodies";
	private static final String DRESS_SHIRTS = "dress_shirts";
	private static final String DRESS_PANTS = "dress_pants";
	private static final String DRESS_SHOES = "dress_shoes";
	private final List<Dress> dressList = new ArrayList<Dress>();

	// GAS STATION AND TABLE FIELDS
	private static final String TABLE_GS = "GasStations";
	private static final String GS_ID = "gs_id";
	private static final String GS_NAME = "gs_name";
	private static final String GS_OH = "gs_OpeningHours";
	private static final String GS_LAT = "gs_lat";
	private static final String GS_LONG = "gs_long";
	private final ArrayList<GasStations> gsList = new ArrayList<GasStations>();
	// private final ArrayList<GasStations>

	// FOOD AND TABLE FIELDS
	private static final String TABLE_FOOD = "Food";
	private static final String FOOD_ID = "food_id";
	private static final String FOOD_NAME = "food_name";
	private static final String FOOD_OH = "food_OpeningHours";
	private static final String FOOD_LAT = "food_lat";
	private static final String FOOD_LONG = "food_long";
	private final ArrayList<Food> foodList = new ArrayList<Food>();

	// DRINKS AND TABLE FIELDS
	private static final String TABLE_PHOTOS = "Photos";
	// private static final String DRINKS_ID = "drinks_id";
	private static final String PHOTOS_ID = "photo_id";
	private static final String PHOTO1 = "photo1";
	private static final String PHOTO2 = "photo2";
	private static final String PHOTO3 = "photo3";
	private static final String PHOTO4 = "photo4";
	private static final String PHOTO5 = "photo5";
	private static final String PHOTO6 = "photo6";
	private static final String PHOTO7 = "photo7";
	private final ArrayList<Photos> photoList = new ArrayList<Photos>();

	// CLUBREVIEWS TABLE AND FIELDS
	private static final String TABLE_CR = "ClubReviews";
	private static final String CR_ID = "cr_id";
	private static final String CR_UPIC = "cr_upic";
	private static final String CR_UNAME = "cr_uname";
	private static final String CR_DESCR = "cr_descr";
	private static final String CR_REV = "cr_review";
	private final ArrayList<ClubReviews> crList = new ArrayList<ClubReviews>();

	private static final String TABLE_EC = "EventContacts";
	private static final String EC_ID = "ec_id";
	private static final String EC_PIC = "ec_pic";
	private static final String EC_NAME = "ec_name";
	private static final String EC_NUMBER = "ec_number";
	private static final String EC_EMAIL = "ec_email";
	private static final String EC_FBK = "ec_fbk";
	private static final String EC_TWITTER = "ec_twitter";
	private final ArrayList<EventContact> ecList = new ArrayList<EventContact>();

	// private static final String CLUB_ID = "club_id";

	public HottDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// QUERY TO CREATE THE CLUBS TABLE
		String CREATE_CLUBS_TABLE = "CREATE TABLE " + TABLE_CLUBS + "("
				+ CLUB_ID + " INTEGER PRIMARY KEY," + CLUB_NAME
				+ " VARCHAR(30)," + CLUB_PIC + "TEXT," + CLUB_ABOUT + " TEXT,"
				+ CLUB_LAT + " FLOAT," + CLUB_LONG + "FLOAT," + FEATURE_ID
				+ " INTEGER," + CONTACT_ID + " INTEGER," + DRINKS_ID
				+ " INTEGER," + DRESS_ID + " INTEGER," + PHOTOS_ID + "INTEGER"
				+ ")";
		db.execSQL(CREATE_CLUBS_TABLE);

		// QUERY TO CREATE THE CLUB CONTACTS TABLE
		String CREATE_CLUB_CONTACTS_TABLE = "CREATE TABLE "
				+ TABLE_CLUB_CONTACTS + "(" + CONTACT_ID
				+ " INTEGER PRIMARY KEY," + CONTACT_ADDRESS + "TEXT,"
				+ CONTACT_NUMBER1 + " VARCHAR(15)," + CONTACT_NUMBER2
				+ " VARCHAR(15)," + CONTACT_WEBSITE + " TEXT," + CONTACT_EMAIL
				+ " VARCHAR(25)," + CONTACT_FACEBOOK + " TEXT,"
				+ CONTACT_TWITTER + " VARCHAR(25)" + ")";
		// Log.i("CCCREATE", CREATE_CLUB_CONTACTS_TABLE);
		db.execSQL(CREATE_CLUB_CONTACTS_TABLE);

		// THE QUERY TO CREATE THE FEATURES TABLE
		String CREATE_FEATURES_TABLE = "CREATE TABLE " + TABLE_FEATURES + "("
				+ FEATURE_ID + " INTEGER PRIMARY KEY," + FEATURE_ADM
				+ " VARCHAR(8)," + FEATURE_DATE + " VARCHAR(11),"
				+ FEATURE_TIME + " VARCHAR(8)," + FEATURE_PROMO + " TEXT" + ")";
		db.execSQL(CREATE_FEATURES_TABLE);

		// THE QUERY TO CREAT THE DRINKS TABLE
		String CREATE_DRINKS_TABLE = "CREATE TABLE " + TABLE_DRINKS + "("
				+ DRINKS_ID + " INTEGER PRIMARY KEY," + DRINK1
				+ " VARCHAR(20)," + DRINK2 + " VARCHAR(20)," + DRINK3
				+ " VARCHAR(20)," + DRINK4 + " VARCHAR(20)," + DRINK5
				+ " VARCHAR(20)," + DRINK6 + " VARCHAR(20)," + DRINK7
				+ " VARCHAR(20)" + ")";
		db.execSQL(CREATE_DRINKS_TABLE);

		// THE QUERY TO CREATE THE DRESS TABLE
		String CREATE_DRESS_TABLE = "CREATE TABLE " + TABLE_DRESS + "("
				+ DRESS_ID + " INTEGER PRIMARY KEY," + DRESS_HATS + " TEXT,"
				+ DRESS_HOODIES + " TEXT," + DRESS_SHIRTS + " TEXT,"
				+ DRESS_PANTS + " TEXT," + DRESS_SHOES + " TEXT " + ")";
		db.execSQL(CREATE_DRESS_TABLE);

		String CREATE_GS_TABLE = "CREATE TABLE " + TABLE_GS + "(" + GS_ID
				+ " INTEGER PRIMARY KEY," + GS_NAME + " VARCHAR(25)," + GS_OH
				+ " TEXT," + GS_LAT + " FLOAT," + GS_LONG + " FLOAT" + ")";
		db.execSQL(CREATE_GS_TABLE);

		String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "(" + FOOD_ID
				+ " INTEGER PRIMARY KEY," + FOOD_NAME + " VARCHAR(25),"
				+ FOOD_OH + " TEXT," + FOOD_LAT + " FLOAT," + FOOD_LONG
				+ " FLOAT" + ")";
		db.execSQL(CREATE_FOOD_TABLE);

		// THE QUERY TO CREAT THE PHOTOS TABLE
		String CREATE_PHOTOS_TABLE = "CREATE TABLE " + TABLE_PHOTOS + "("
				+ PHOTOS_ID + " INTEGER PRIMARY KEY," + PHOTO1 + " TEXT,"
				+ PHOTO2 + " TEXT," + PHOTO3 + " TEXT," + PHOTO4 + " TEXT,"
				+ PHOTO5 + " TEXT"// + PHOTO6 + " TEXT," + PHOTO7 + " TEXT"
				+ ")";
		db.execSQL(CREATE_PHOTOS_TABLE);

		// THE QUERY TO CREAT THE CLUBREVIEWS TABLE
		String CREATE_CR_TABLE = "CREATE TABLE " + TABLE_CR + "(" + CR_ID
				+ " INTEGER PRIMARY KEY," + CR_UPIC + " TEXT," + CR_UNAME
				+ " VARCHAR(25)," + CR_DESCR + " TEXT," + CR_REV + " INTEGER,"
				+ CLUB_ID + " INTEGER" + ")";
		db.execSQL(CREATE_CR_TABLE);

		String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
				+ EVENT_ID + " INTEGER PRIMARY KEY," + EVENT_NAME + " TEXT,"
				+ EVENT_PIC + " TEXT," + EVENT_DESCR + " TEXT," + EVENT_ABOUT
				+ " TEXT," + EVENT_LAT + " FLOAT," + EVENT_LONG + " FLOAT,"
				+ FEATURE_ID + " INTEGER," + DRINKS_ID + " INTEGER," + DRESS_ID
				+ " INTEGER," + PHOTOS_ID + " INTEGER" + ")";
		db.execSQL(CREATE_EVENT_TABLE);

		String CREATE_EVENT_CONTACT_TABLE = "CREATE TABLE " + TABLE_EC + "("
				+ EC_ID + " INTEGER PRIMARY KEY," + EC_NAME + " VARCHAR(30),"
				+ EC_PIC + " TEXT," + EC_NUMBER + " VARCHAR(16)," + EC_EMAIL
				+ " TEXT," + EC_FBK + " TEXT," + EC_TWITTER + " VARCHAR(25),"
				+ EVENT_ID + " INTEGER " + ")";
		db.execSQL(CREATE_EVENT_CONTACT_TABLE);

		// populating the Clubs table
		db.execSQL("INSERT INTO "
				+ TABLE_CLUBS
				+ " Values (1,"
				+ " 'Sempiternal',"
				+ " 'http://blog.zingrevolution.com/wp-content/uploads/14976387.jpg',"
				+ " 'This is just a mini descr for testing. Sempiternal is sung by a band called bring to to horizon',"
				+ " 51.495," + " -0.2459," + " 1," + " 1," + " 1," + "1,"
				+ "1)");

		db.execSQL("INSERT INTO "
				+ TABLE_CLUBS
				+ " Values (2,"
				+ " 'Meteora',"
				+ " 'http://blog.zingrevolution.com/wp-content/uploads/14976387.jpg',"
				+ " 'This is just a mini descr for testing. Meteora is sung by a band called Linkin Park',"
				+ " 51.5882," + " -0.0594," + " 2," + " 2," + " 2," + "2,"
				+ "2)");

		db.execSQL("INSERT INTO "
				+ TABLE_CLUBS
				+ " Values (3,"
				+ " 'Blackbird',"
				+ " 'http://blog.zingrevolution.com/wp-content/uploads/14976387.jpg',"
				+ " 'This is just a mini descr for testing. BlackBird is sung by a band called AlterBridge',"
				+ " 51.4965," + " -0.1447," + " 3," + " 3," + " 3," + "3,"
				+ "3)");

		// populating the Club Contacts table
		db.execSQL("INSERT INTO " + TABLE_CLUB_CONTACTS + " Values (1,"
				+ " '12 Grimmauld Place, London, UK'," + " '1-246-253-9203',"
				+ " '1-246-429-7352'," + " 'https://www.harrypotter.co.uk',"
				+ " 'fireboltseekerquidditch.co.uk',"
				+ " 'www.facebook.com/theboywholived'," + " 'scar_head')");

		db.execSQL("INSERT INTO " + TABLE_CLUB_CONTACTS + " Values (2,"
				+ " 'The favourite blue box, In the Jungle',"
				+ " '1-246-253-9203'," + " '1-246-429-7352',"
				+ " 'https://www.frosties.com',"
				+ " 'tonythetigerfrosties.com',"
				+ " 'www.facebook.com/frostit'," + " 'TonyFrost')");

		db.execSQL("INSERT INTO " + TABLE_CLUB_CONTACTS + " Values (3,"
				+ " '12 Tower Hill, London, UK'," + " '1-44-4938-099203',"
				+ " '1-44-4292-735225'," + " 'https://www.thisplace.co.uk',"
				+ " 'thisplace.co.uk'," + " 'www.facebook.com/tp',"
				+ " 'thisPLACE')");

		// populating the Features table
		db.execSQL("INSERT INTO " + TABLE_FEATURES + " Values (1,"
				+ " '$30.00'," + " '01/10/2014'," + " '3:00am',"
				+ " 'September borns free before 5!!!!')");

		db.execSQL("INSERT INTO " + TABLE_FEATURES + " Values (2,"
				+ " '$15.00'," + " '21/08/2014'," + " '9:00pm',"
				+ " 'Ladies 2 for 1 before 11!!!!')");

		db.execSQL("INSERT INTO " + TABLE_FEATURES + " Values (3,"
				+ " '$50.00'," + " '01/09/2014'," + " '10:00am',"
				+ " 'Drinks free all night!!!')");

		// populating the Drinks table
		db.execSQL("INSERT INTO " + TABLE_DRINKS + " Values (1,"
				+ " 'Henessey', " + " 'Moet'," + " 'Mount Gay',"
				+ " 'Heineken'," + " 'Bombay Sapphire'," + " 'Belvedere',"
				+ " 'Jack Daniels')");

		db.execSQL("INSERT INTO " + TABLE_DRINKS + " Values (2,"
				+ " 'CockSpur', " + " 'Absolut'," + " 'E.S.A.Field',"
				+ " 'Mr. T'," + " 'Bombay Sapphire'," + " 'Smirnoff',"
				+ " 'Jamesons')");

		db.execSQL("INSERT INTO " + TABLE_DRINKS + " Values (3,"
				+ " 'Absolut', " + " 'Ciroc'," + " 'Zinfadel'," + " 'Banks',"
				+ " 'Gordans'," + " 'Smirnoff'," + " 'Jamesons')");

	// populating the dress table
		db.execSQL("INSERT INTO " + TABLE_DRESS + " Values (1," + " 'no',"
				+ " 'no'," + " 'no'," + " 'no'," + " 'no')");
		db.execSQL("INSERT INTO " + TABLE_DRESS + " Values (2," + " 'yes',"
				+ " 'yes'," + " 'yes'," + " 'yes'," + " 'yes')");
		db.execSQL("INSERT INTO " + TABLE_DRESS + " Values (3," + " 'maybe',"
				+ " 'maybe'," + " 'maybe'," + " 'maybe'," + " 'maybe')"); 

		// popuating the gas station table
		db.execSQL("INSERT INTO " + TABLE_GS + " Values(1," + " 'Shell',"
				+ " '910am-300am'," + " 51.5056," + " 0.0756)");

		db.execSQL("INSERT INTO " + TABLE_GS + " Values(2," + " 'Rubis',"
				+ " '710am-200am'," + " 51.5098," + " -0.0766)");

		// popuating the gas station table
		db.execSQL("INSERT INTO " + TABLE_FOOD + " Values(1," + " 'BigGuts',"
				+ " '910am-300am'," + " 51.5904," + " -0.1028)");

		db.execSQL("INSERT INTO " + TABLE_FOOD + " Values(2," + " 'MickeyD',"
				+ " '710am-200am'," + " 51.4827," + " -0.0096)");

		db.execSQL("INSERT INTO "
				+ TABLE_PHOTOS
				+ " Values(1,"
				+ " 'http://1.bp.blogspot.com/-_hKFc0x-h-A/UPQ7FiGdA9I/AAAAAAAAA-8/K2VGZSxgGF0/s1600/76593_451693141540338_203048964_n.jpg',"
				+ " 'http://2.bp.blogspot.com/-NIayBGxkONU/UPQ7FKM_1MI/AAAAAAAAA-0/5RTpqIRxnL4/s1600/533054_394896027220050_1609823675_n.jpg',"
				+ " 'http://www.igrealty.com/wp-content/uploads/2014/08/suga.jpg',"
		//		+ " 'http://newswire.net/public/article/e0/25/25bb_ac8f.jpg',"
				+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
			//	+ " 'http://cheapedge.com/wp-content/uploads/2014/04/8820920-nissan-gtr-sport-hd-wallpaper.jpg',"
				+ " 'http://ahdcarwallpaper.com/wp-content/uploads/2014/03/nissan-gtr-wallpaper-1366x768-8.jpg')");

		db.execSQL("INSERT INTO "
				+ TABLE_PHOTOS
				+ " Values(2,"
				+ " 'http://www.blogcdn.com/slideshows/images/slides/157/696/5/S1576965/slug/l/008-2015-nissan-gtr-nismo-1.jpg',"
				+ " 'http://2.bp.blogspot.com/-NIayBGxkONU/UPQ7FKM_1MI/AAAAAAAAA-0/5RTpqIRxnL4/s1600/533054_394896027220050_1609823675_n.jpg',"
			//	+ " 'http://www.igrealty.com/wp-content/uploads/2014/08/suga.jpg',"
				+ " 'http://newswire.net/public/article/e0/25/25bb_ac8f.jpg',"
				//+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " 'http://cheapedge.com/wp-content/uploads/2014/04/8820920-nissan-gtr-sport-hd-wallpaper.jpg',"
				+ " 'http://ahdcarwallpaper.com/wp-content/uploads/2014/03/nissan-gtr-wallpaper-1366x768-8.jpg')");

		db.execSQL("INSERT INTO "
				+ TABLE_PHOTOS
				+ " Values(3,"
				+ " 'http://cdn.wallstcheatsheet.com/wp-content/uploads/2014/06/2014-Nissan-GTR.png',"
				+ " 'http://2.bp.blogspot.com/-NIayBGxkONU/UPQ7FKM_1MI/AAAAAAAAA-0/5RTpqIRxnL4/s1600/533054_394896027220050_1609823675_n.jpg',"
				//+ " 'http://www.igrealty.com/wp-content/uploads/2014/08/suga.jpg',"
				+ " 'http://newswire.net/public/article/e0/25/25bb_ac8f.jpg',"
			//	+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " 'http://cheapedge.com/wp-content/uploads/2014/04/8820920-nissan-gtr-sport-hd-wallpaper.jpg',"
				+ " 'http://ahdcarwallpaper.com/wp-content/uploads/2014/03/nissan-gtr-wallpaper-1366x768-8.jpg')");

		db.execSQL("INSERT INTO " + TABLE_CR + " Values(1,"
				+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " 'Brian Spilner'," + " 'not bad at all'," + " 4," + " 1)"

		);

		db.execSQL("INSERT INTO " + TABLE_CR + " Values(4,"
				+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " 'Brian Spilner'," + " 'not bad at all'," + " 4," + " 1)"

		);

		db.execSQL("INSERT INTO "
				+ TABLE_CR
				+ " Values(2,"
				+ " 'http://ahdcarwallpaper.com/wp-content/uploads/2014/03/nissan-gtr-wallpaper-1366x768-8.jpg',"
				+ " 'Chris Bridges'," + " 'now this is what im talking about',"
				+ " 5," + " 1)"

		);
		db.execSQL("INSERT INTO "
				+ TABLE_CR
				+ " Values(3,"
				+ " 'http://cdn.wallstcheatsheet.com/wp-content/uploads/2014/06/2014-Nissan-GTR.png',"
				+ " 'Dominic Tarreto'," + " 'You didnt have your car'," + " 2,"
				+ " 2)"

		);

		db.execSQL("INSERT INTO "
				+ TABLE_EVENT
				+ " Values(1,"
				+ " 'Gillette'," //http://ahdcarwallpaper.com/wp-content/uploads/2014/03/nissan-gtr-wallpaper-1366x768-8.jpg |
				+ " 'https://cornerbalance.files.wordpress.com/2010/07/sa55m_car01.jpg',"
				+ " 'This is the short description GILLETTEEEEEE that is used when displaying the list of events',"
				+ " 'This goes right under the the picture in the about section to blach ah cbakcblacbefkjfjebkabekjfbefbekjfbe',"
				+ " 51.507," + " -0.0203," + " 1," + " 1," + " 1," + " 1)"

		);

		db.execSQL("INSERT INTO "
				+ TABLE_EVENT
				+ " Values(2,"
				+ " 'HTC',"
				+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " 'This is the short description for HTC that is used when displaying the list of events',"
				+ " 'This goes right under the the picture in the about section to blach ah cbakcblacbefkjfjebkabekjfbefbekjfbe',"
				+ " 51.7052," + " -0.611," + " 2," + " 2," + " 2," + " 2)"

		);

		db.execSQL("INSERT INTO "
				+ TABLE_EVENT
				+ " Values(3,"
				+ " 'Destiny',"
				+ " 'http://cdn.wallstcheatsheet.com/wp-content/uploads/2014/06/2014-Nissan-GTR.png',"
				+ " 'This is the short description DESTINY that is used when displaying the list of events',"
				+ " 'This goes right under the the picture in the about section to blach ah cbakcblacbefkjfjebkabekjfbefbekjfbe',"
				+ " 51.4907," + " -0.2065," + " 3," + " 3," + " 3," + " 3)"

		);

		db.execSQL("INSERT INTO "
				+ TABLE_EC
				+ " Values(1,"
				+ " 'Fresh Prince',"
				+ " 'http://2.bp.blogspot.com/-NIayBGxkONU/UPQ7FKM_1MI/AAAAAAAAA-0/5RTpqIRxnL4/s1600/533054_394896027220050_1609823675_n.jpg',"
				+ " '1-324-2764'," + " 'freshprince.com',"
				+ " 'www.facebook.com/freshtodeath'," + " 'Fresh_Prince',"
				+ " 1)");

		db.execSQL("INSERT INTO  " + TABLE_EC + " Values(2," + " 'J Cole',"
				+ " 'http://www.gtrnissan.com/images/img_01.jpg',"
				+ " '1-934-6542'," + " 'freshprince.com',"
				+ " 'www.facebook.com/backtothetopic'," + " 'Cole_World',"
				+ " 1)");

		db.execSQL("INSERT INTO "
				+ TABLE_EC
				+ " Values(3,"
				+ " 'The Wiz',"
				+ " 'http://cdn.wallstcheatsheet.com/wp-content/uploads/2014/06/2014-Nissan-GTR.png',"
				+ " '1-543-4522'," + " 'freshprince.com',"
				+ " 'www.facebook.com/thatgood'," + " 'High_Life'," + " 2)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUBS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEATURES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRESS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EC);
		// db.execSQL("DROP TABLE IF EXISTS Drinks ");
		onCreate(db);
	}

	public ArrayList<Club> GetClubs() {
		try {
			clubList.clear();

			String selectQuery = "SELECT * FROM " + TABLE_CLUBS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
				do {
					Club club = new Club();
					// club.setClub_id(cursor.getString(0);
					club.setClub_id(cursor.getString(0));
					club.setClub_name(cursor.getString(1));
					club.setClub_pic(cursor.getString(2));
					club.setClub_about(cursor.getString(3));
					club.setClub_lat(Float.parseFloat(cursor.getString(4)));
					club.setClub_long(Float.parseFloat(cursor.getString(5)));
					club.setFeature_id(Integer.parseInt(cursor.getString(6)));
					club.setContact_id(Integer.parseInt(cursor.getString(7)));
					club.setDrinks_id(Integer.parseInt(cursor.getString(8)));
					club.setDress_id(Integer.parseInt(cursor.getString(9)));
					club.setPhoto_id(Integer.parseInt(cursor.getString(10)));
					clubList.add(club);

				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();
			return clubList;

		} catch (Exception e) {
			Log.e("ALLCLUBS", "" + e);
		}
		return clubList;

	}

	public int GetTotalClubs() {
		String coutQuery = "SELECT * FROM " + TABLE_CLUBS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(coutQuery, null);
		cursor.close();
		return cursor.getCount();
	}

	public ArrayList<ClubContacts> getContactInfo(int pcontactId) {
		try {
			clubConList.clear();

			String selectConQ = "SELECT * FROM " + TABLE_CLUB_CONTACTS
					+ " WHERE contact_id = " + pcontactId;
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectConQ, null);
			if (cursor.moveToFirst()) {
				do {
					ClubContacts clubcon = new ClubContacts();
					clubcon.setContact_id(Integer.parseInt(cursor.getString(0)));
					clubcon.setContact_address(cursor.getString(1));
					clubcon.setContact_number1(cursor.getString(2));
					clubcon.setContact_number2(cursor.getString(3));
					clubcon.setContact_website(cursor.getString(4));
					clubcon.setContact_email(cursor.getString(5));
					clubcon.setContact_facebook(cursor.getString(6));
					clubcon.setContact_twitter(cursor.getString(7));
					clubConList.add(clubcon);

				} while (cursor.moveToNext());

				cursor.close();
				db.close();
				return clubConList;
			}
		} catch (Exception e) {
			Log.i("CLUBCONTSCTSTABLE", "" + e);
		}

		return clubConList;
	}

	public List<Features> getFeatures(int fid) {
		featuresList.clear();

		try {
			String selectFeat = "SELECT * FROM " + TABLE_FEATURES
					+ " WHERE feature_id = " + fid;
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectFeat, null);

			if (cursor.moveToFirst()) {

				Features feat = new Features();
				feat.setFeature_id(Integer.parseInt(cursor.getString(0)));
				feat.setFeature_adm(cursor.getString(1));
				feat.setFeature_date(cursor.getString(2));
				feat.setFeature_time(cursor.getString(3));
				feat.setFeature_promo(cursor.getString(4));
				featuresList.add(feat);
			}

			cursor.close();
			db.close();
			return featuresList;

		} catch (Exception e) {
			Log.i("FLGF", e + "");
		}

		return featuresList;
	}

	public List<Drinks> getDrinks(int did) {
		drinksList.clear();
		try {
			String selectDrinks = "SELECT * FROM " + TABLE_DRINKS
					+ " WHERE drinks_id =" + did;
			Log.i("DID", " " + did);
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectDrinks, null);

			if (cursor.moveToFirst()) {
				Drinks dk = new Drinks();
				dk.setDrinks_id(Integer.parseInt(cursor.getString(0)));
				dk.setDrink1(cursor.getString(1));
				dk.setDrink2(cursor.getString(2));
				dk.setDrink3(cursor.getString(3));
				dk.setDrink4(cursor.getString(4));
				dk.setDrink5(cursor.getString(5));
				dk.setDrink6(cursor.getString(6));
				dk.setDrink7(cursor.getString(7));
				drinksList.add(dk);
			}
			cursor.close();
			db.close();
			return drinksList;
		} catch (Exception e) {
			Log.i("DLQ", "" + e);
		}

		return drinksList;
	}

	public List<Dress> getDress(int dressid) {
		dressList.clear();

		try {
			String selectDress = "SELECT * FROM " + TABLE_DRESS
					+ " WHERE dress_id=" + dressid;
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectDress, null);
			if (cursor.moveToFirst()) {
				Dress ds = new Dress();
				ds.setDress_id(Integer.parseInt(cursor.getString(0)));
				ds.setDress_hat((cursor.getString(1)));
				ds.setDress_hoodies(cursor.getString(2));
				ds.setDress_shirts(cursor.getString(3));
				ds.setDress_pants(cursor.getString(4));
				ds.setDress_shoes(cursor.getString(5));
				dressList.add(ds);

			}
			cursor.close();
			db.close();
			return dressList;

		} catch (Exception e) {
			Log.i("DRESSQ", " " + e);
		}

		return dressList;
	}

	public void insertDressCode(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("dress_id", Integer.parseInt(queryValues.get("dress_id")));
		values.put("dress_hat", queryValues.get("dress_hat"));
		values.put("dress_hoodies", queryValues.get("dress_hoodies"));
		values.put("dress_shirts", queryValues.get("dress_shirts"));
		values.put("dress_pants", queryValues.get("dress_pants"));
		values.put("dress_shoes", queryValues.get("dress_shoes"));
		database.insert(TABLE_DRESS, null, values);
		database.close();
	}

	public ArrayList<GasStations> getGS() {
		gsList.clear();
		try {
			String selectGS = " SELECT * FROM " + TABLE_GS;
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectGS, null);

			if (cursor.moveToFirst()) {
				do {

					GasStations ggs = new GasStations();
					ggs.setGsId(Integer.parseInt(cursor.getString(0)));
					ggs.setGsName(cursor.getString(1));
					ggs.setGsOH(cursor.getString(2));
					ggs.setGsLat(Float.parseFloat(cursor.getString(3)));
					ggs.setGsLong(Float.parseFloat(cursor.getString(4)));
					gsList.add(ggs);

				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
			return gsList;

		} catch (Exception e) {
			Log.i("GSGGS", "" + e);
		}
		return gsList;
	}

	public ArrayList<GasStations> getPlaceFromId(int id) {
		return gsList;

	}

	public ArrayList<Food> getFood() {
		foodList.clear();
		try {
			String selectGS = " SELECT * FROM " + TABLE_FOOD;
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(selectGS, null);

			if (cursor.moveToFirst()) {
				do {

					Food ggs = new Food();
					ggs.setFoodId(Integer.parseInt(cursor.getString(0)));
					ggs.setFoodName(cursor.getString(1));
					ggs.setFoodOH(cursor.getString(2));
					ggs.setFoodLat(Float.parseFloat(cursor.getString(3)));
					ggs.setFoodLong(Float.parseFloat(cursor.getString(4)));
					foodList.add(ggs);

				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
			return foodList;

		} catch (Exception e) {
			Log.i("GSGGS", "" + e);
		}
		return foodList;
	}

	public ArrayList<Food> getFoodPlaceFromId(int id) {
		return foodList;

	}

	public ArrayList<Photos> getPhotos(int pid) {

		photoList.clear();
		Log.i("INGP", "" + pid);
		try {
			String photoQuery = "SELECT * FROM " + TABLE_PHOTOS
					+ " WHERE photo_id=" + pid;

			Log.i("INGP", "" + pid);
			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(photoQuery, null);

			if (cursor.moveToFirst()) {
				do {
					Photos ph = new Photos();
					ph.setPhoto_id(Integer.parseInt(cursor.getString(0)));
					ph.setPhoto1(cursor.getString(1));
					ph.setPhoto2(cursor.getString(2));
					ph.setPhoto3(cursor.getString(3));
					ph.setPhoto4(cursor.getString(4));
					ph.setPhoto5(cursor.getString(5));
					// ph.setPhoto6(cursor.getString(6));
					// ph.setPhoto7(cursor.getString(7));
					photoList.add(ph);
				} while (cursor.moveToNext());

				cursor.close();
				db.close();
				return photoList;
			}

		} catch (Exception e) {
			Log.i("GETPHOTOQUERY", "" + e);
		}
		return photoList;
	}

	public ArrayList<ClubReviews> getClRev(int club_id) {
		crList.clear();
		try {
			String crQuery = " SELECT * FROM " + TABLE_CR + " WHERE club_id= "
					+ club_id;

			Log.i("GETCLREVID", club_id + "");

			SQLiteDatabase db = this.getWritableDatabase();

			Cursor cursor = db.rawQuery(crQuery, null);

			int i = 0;
			if (cursor.moveToFirst()) {
				do {

					ClubReviews crs = new ClubReviews();
					crs.setReviewUPic(cursor.getString(1));
					crs.setReviewUName(cursor.getString(2));
					crs.setReviewDescr(cursor.getString(3));
					crs.setReviewRating(Integer.parseInt(cursor.getString(4)));
					crs.setClub_id(Integer.parseInt(cursor.getString(5)));
					crList.add(crs);
					i++;
					Log.i("CLREVLOOP", "This looped " + i);

				} while (cursor.moveToNext());

				cursor.close();
				db.close();
				return crList;
			}

		} catch (Exception e) {
			Log.i("getCLREV", "" + e);
		}
		return crList;
	}

	public ArrayList<Event> getEvents() {
		eventList.clear();
		try {
			String eventQ = " SELECT * FROM " + TABLE_EVENT;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(eventQ, null);

			if (cursor.moveToFirst()) {
				do {
					Event ge = new Event();
					ge.setEventid(Integer.parseInt(cursor.getString(0)));
					ge.setEventName(cursor.getString(1));
					ge.setEventPic(cursor.getString(2));
					ge.setEventDescr(cursor.getString(3));
					ge.setEventAbout(cursor.getString(4));
					ge.setEventLat(Float.parseFloat(cursor.getString(5)));
					ge.setEventLong(Float.parseFloat(cursor.getString(6)));
					ge.setFeature_id(Integer.parseInt(cursor.getString(7)));
					ge.setDrinks_id(Integer.parseInt(cursor.getString(8)));
					ge.setDress_id(Integer.parseInt(cursor.getString(9)));
					ge.setPhoto_id(Integer.parseInt(cursor.getString(10)));
					eventList.add(ge);

				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();
			return eventList;

		} catch (Exception e) {
			Log.i("GEQ", "" + e);
		}

		return eventList;
	}

	public ArrayList<EventContact> getEC(int evenId) {
		ecList.clear();

		try {
			String ecQuery = "SELECT * FROM " + TABLE_EC + " WHERE event_id="
					+ evenId;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(ecQuery, null);

			if (cursor.moveToFirst()) {
				do {
					EventContact ecc = new EventContact();
					ecc.setEcId(Integer.parseInt(cursor.getString(0)));
					ecc.setEcName(cursor.getString(1));
					ecc.setEcPic(cursor.getString(2));
					ecc.setEcNumber(cursor.getString(3));
					ecc.setEcEmail(cursor.getString(4));
					ecc.setEcFbk(cursor.getString(5));
					ecc.setEcTwitter(cursor.getString(6));
					ecc.setEvent_id(Integer.parseInt(cursor.getString(7)));
					ecList.add(ecc);

				} while (cursor.moveToNext());

				cursor.close();
				db.close();
				return ecList;
			}

		} catch (Exception e) {
			Log.i("ECQ", e + "");
		}
		return ecList;
	}

	public int GetTotalEvents() {
		String countEQ = " SELECT * FROM " + TABLE_EVENT;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countEQ, null);
		cursor.close();
		return cursor.getCount();
	}
}
