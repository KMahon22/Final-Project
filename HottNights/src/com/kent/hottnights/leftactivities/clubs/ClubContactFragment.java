package com.kent.hottnights.leftactivities.clubs;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.ClubContacts;

public class ClubContactFragment extends Fragment {
	private View contactview;
	private static int random;
	TextView tryme;
	HottDatabaseHandler db;
	ArrayList<ClubContacts> clubConFrmDb;
	TextView contactAddy;
	TextView contactnum1;
	TextView contactNum2;
	TextView contactWeb;
	TextView contactEmail;
	TextView contactFbk;
	TextView contactTwit;

	public ClubContactFragment(int random) {
		super();
		this.random = random;
	}

	public static ClubContactFragment contactFrag() {
		ClubContactFragment c = new ClubContactFragment(random);
		Log.i("CAF", "club about fragments is called");
		return c;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		contactview = inflater.inflate(R.layout.club_contact_fragment,
				container, false);
		contactAddy = (TextView) contactview.findViewById(R.id.tvCCAddress);
		contactnum1 = (TextView) contactview.findViewById(R.id.tvCCNum1);
		contactNum2 = (TextView) contactview.findViewById(R.id.tvCCNum2);
		contactWeb = (TextView) contactview.findViewById(R.id.tvCCWS);
		contactEmail = (TextView) contactview.findViewById(R.id.tvCCEmail);
		contactFbk = (TextView) contactview.findViewById(R.id.tvCCFbk);
		contactTwit = (TextView) contactview.findViewById(R.id.tvCCTwit);
		
		
		db = new HottDatabaseHandler(getActivity().getBaseContext());
		clubConFrmDb = db.getContactInfo(random);

		contactAddy.setText(clubConFrmDb.get(0).getContact_address());
		contactnum1.setText(clubConFrmDb.get(0).getContact_number1());
		contactNum2.setText(clubConFrmDb.get(0).getContact_number2());
		contactWeb.setText(clubConFrmDb.get(0).getContact_website());
		contactEmail.setText(clubConFrmDb.get(0).getContact_email());
		contactFbk.setText(clubConFrmDb.get(0).getContact_facebook());
		contactTwit.setText(clubConFrmDb.get(0).getContact_twitter());

		// tryme=(TextView) contactview.findViewById(R.id.tvCCAddressTag);
		// tryme.setText(random) ;

		return contactview;
	}

}
