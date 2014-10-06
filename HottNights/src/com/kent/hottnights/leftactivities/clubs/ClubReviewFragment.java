package com.kent.hottnights.leftactivities.clubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.ClubReviewAdapter;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.leftactivities.ClubListFragment;
import com.kent.hottnights.objects.ClubReviews;

public class ClubReviewFragment extends ListFragment {

	private String[] cname;
	private String[] cdescr;
	private int[] cil;
	private int[] crate;
	private RatingBar rateme;
	private float trate = 2.0f;
	ArrayList<ClubReviews> arrayRev;

	// private View revview;

	// db stuff
	ArrayList<ClubReviews> crFrmDb;
	HottDatabaseHandler dbh;
	private static int cl_id;

	// private View replaceme;

	public ClubReviewFragment(int cl_id) {
		super();
		this.cl_id = cl_id;
	}

	public static ClubReviewFragment reviewFrag() {
		ClubReviewFragment r = new ClubReviewFragment(cl_id);
		return r;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// rateme.setRating(trate);
		// List<HashMap<String,String>> cList = new
		// ArrayList<HashMap<String,String>>();
		arrayRev = new ArrayList<ClubReviews>();
		dbh = new HottDatabaseHandler(getActivity().getBaseContext());
		crFrmDb = dbh.getClRev(cl_id);

		cname = getResources().getStringArray(R.array.cnames);
		cdescr = getResources().getStringArray(R.array.cdescr);
		cil = getResources().getIntArray(R.array.cimgs);
		crate = getResources().getIntArray(R.array.crate);

		for (int i = 0; i < crFrmDb.size(); i++) {
			Log.i("CRFRBSIZE", crFrmDb.size() + "");
			// String objN = "blue" + i;
			/*
			 * HashMap<String,String> hm = new HashMap<String,String>();
			 * hm.put("name", cname[i]); hm.put("descr", cdescr[i]);
			 * hm.put("imgs", Integer.toString(cil[i]));
			 */
			// cList.add(hm);

			// ClubReviews blue = new ClubReviews(cil[i], cname[i], cdescr[i],
			// crate[i] );
			Log.i("RATINGS", crate[i] + " ");
			// arrayRev.add(blue);

			ClubReviews blue = new ClubReviews(crFrmDb.get(i).getReviewUPic(),
					crFrmDb.get(i).getReviewUName(), crFrmDb.get(i)
							.getReviewDescr(), crFrmDb.get(i).getReviewRating());
			arrayRev.add(blue);

		}

		// String[] from = {"name","descr","imgs", "2.0f"};
		// int[] to = {R.id.tvClubReviewListName, R.id.tvClubReviewListDescr,
		// R.id.ivClubReviewListImg, R.id.ratingBar};
		// LayoutInflater inflater2 = (LayoutInflater)
		// getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// SimpleAdapter adapter = new
		// SimpleAdapter(getActivity().getBaseContext(), cList,
		// R.layout.club_review_layout, from, to);

		Log.i("OCVCRFA1", "before the adapter is set");
		ClubReviewAdapter adapter = new ClubReviewAdapter(getActivity()
				.getBaseContext(), arrayRev);
		Log.i("OCVCRFA2", "after the adapter is set");
		setListAdapter(adapter);

		Log.i("OVCCLF", "is this being called at alll");

		// return revview;

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// Log.i("CLF", "is this being callled");
	}

}
