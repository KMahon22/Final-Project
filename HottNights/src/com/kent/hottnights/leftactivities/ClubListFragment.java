package com.kent.hottnights.leftactivities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.kent.hottnights.adapters.ClubListAdapter;
import com.kent.hottnights.communicators.ClubListComm;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.leftactivities.clubs.ClubMainFragment;
import com.kent.hottnights.objects.Club;
import com.special.ResideMenu.ResideMenu;

//import android.widget.ListView;

public class ClubListFragment extends ListFragment {

	// private View clublistview;
	private ResideMenu resideMenu;
	private String[] cname;
	private String[] cdescr;
	// private TypedArray cil;
	private int[] cil;
	private View replaceme;
	Fragment frag;
	
	
	HottDatabaseHandler db;
	ArrayList<Club> clubsFromDb;
	ClubListComm clcomm;

	public int[] flags = new int[] { R.drawable.ic_launcher,
			R.drawable.calendar, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher };

	public static ClubListFragment listFraggg() {
		ClubListFragment k = new ClubListFragment();
		return k;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		//replaceme = inflater.inflate(R.layout.event_list_layout, container,
			//	false);

		// setUpViews();
		
		
		
		// List<HashMap<String, String>> cList = new
		// ArrayList<HashMap<String,String>>();

		cname = getResources().getStringArray(R.array.cnames);
		cdescr = getResources().getStringArray(R.array.cdescr);
		cil = getResources().getIntArray(R.array.cimgs);// obtainTypedArray(R.array.cimgs);
		if (cil == null) {
			Log.i("CIL", "aint shit here");
		}
		List<Club> cList = new ArrayList<Club>();
		
		db = new HottDatabaseHandler(getActivity().getBaseContext());
		clubsFromDb = db.GetClubs();

		for (int i = 0; i < clubsFromDb.size(); i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			ArrayList stuff = new ArrayList();
			// stuff ={cname[i], cdescr[i], cil[i]);

			hm.put("name", clubsFromDb.get(i).getClub_name());
			hm.put("descr", clubsFromDb.get(i).getClub_about());
			hm.put("imgs", Integer.toString(cil[i]));

			Club black = new Club(clubsFromDb.get(i).getClub_name(),
					clubsFromDb.get(i).getClub_pic(), clubsFromDb.get(i)
							.getClub_about());

			/*
			 * hm.put("name", cname[i]); hm.put("descr", cdescr[i]);
			 * hm.put("imgs", Integer.toString(cil[i])); Log.i("SBAIMG",
			 * Integer.toString(cil[i]));
			 */
			cList.add(black);
			// cList.add(hm);
		}
		db.close();

		HashMap<String, Club> hm;

		String[] from = { "imgs", "name", "descr" };

		int[] to = { R.id.ivClubListImg, R.id.tvClubListName,
				R.id.tvClubListDescr };

		// SimpleAdapter adapter = new
		// SimpleAdapter(getActivity().getBaseContext(), cList,
		// R.layout.club_list_layout_fragment, from, to);
		ClubListAdapter adapter = new ClubListAdapter(getActivity()
				.getBaseContext(), cList);
		setListAdapter(adapter);

		Log.i("OVCCLF", "is this being called at alll");
		// Log.i("CLF", "is this being callled");

		clcomm = (ClubListComm) getActivity();

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setUpViews(view);
		
	}
	private void setUpViews(View view) {
		// TODO Auto-generated method stub
		MainMenuActivity parentActivity = (MainMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();

		/*
		 * parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub resideMenu.openMenu(ResideMenu.DIRECTION_LEFT); } }) ;
		 */

		LinearLayout ignored_view = (LinearLayout) view
				.findViewById(R.id.llclubList);
		resideMenu.addIgnoredView(ignored_view);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.i("LIC", "is this called??");
		Fragment frag = new ClubMainFragment();
		ClubMainFragment blue = new ClubMainFragment();
		String name;
		name = blue.getName();
		// MainMenuActivity fme = new MainMenuActivity();
		// fme.changeHead(name);

		// int testthis;
		// Bundle bundle = new Bundle();
		// bundle.putString("file_name", fname);
		// frag.setArguments(bundle);
		Log.i("CLICKED", "before fme is craeted");
		// replaceme = v.findViewById(R.id.club_list_fragment);

		MainMenuActivity.fragmentManager.beginTransaction()
				.replace(R.id.main_fragment, frag).addToBackStack(null)
				.commit();

		Log.i("CLFPID", "" + clubsFromDb.get(position).getPhoto_id());

		clcomm.allClubInfo(clubsFromDb.get(position).getClub_id(), clubsFromDb
				.get(position).getClub_name(), clubsFromDb.get(position)
				.getClub_pic(), clubsFromDb.get(position).getClub_about(),
				clubsFromDb.get(position).getClub_lat(),
				clubsFromDb.get(position).getClub_long(),
				clubsFromDb.get(position).getFeature_id(),
				clubsFromDb.get(position).getContact_id(),
				clubsFromDb.get(position).getDrinks_id(),
				clubsFromDb.get(position).getDress_id(),
				clubsFromDb.get(position).getPhoto_id());

		Log.i("CLICKDID", "" + clubsFromDb.get(position).getDrinks_id());
		// fme.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
		// frag).addToBackStack(null).commit();
		Log.i("CLICKDIDCLUBNAME", clubsFromDb.get(position).getClub_name());
		// FragmentManager blue = getFragmentManager();
		// fme.changeFragment(frag);

		/*
		 * FragmentTransaction fragmentTransaction =
		 * fragmentManager.beginTransaction();
		 * fragmentTransaction.replace(replaceme, frag);
		 * fragmentTransaction.addToBackStack(null);
		 * fragmentTransaction.commit();
		 */
	}

	/*
	 * @Override public void onDestroy() { Fragment fragment =
	 * getChildFragmentManager().findFragmentById(R.id.clublistcontainer); if
	 * (fragment.isResumed()) {
	 * getChildFragmentManager().beginTransaction().remove(fragment).commit(); }
	 * super.onDestroy(); }
	 */

}
