package com.kent.hottnights.leftactivities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.kent.hottnights.leftactivities.clubs.ClubMainFragment;
//import android.widget.ListView;

public class ClubListFragment extends ListFragment {

	//private View clublistview;
	private String[] cname;
	private String[] cdescr;
	private int[] cil;
	private View replaceme;
	Fragment frag;
	
	
	public static ClubListFragment listFraggg()
	{
		ClubListFragment k = new ClubListFragment();
		return k;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//clublistview = inflater.inflate(R.layout.club_list_fragment,
			//	container, false);
		
		List<HashMap<String,String>> cList = new ArrayList<HashMap<String,String>>();
		
		cname = getResources().getStringArray(R.array.cnames);
		cdescr = getResources().getStringArray(R.array.cdescr);
		cil = getResources().getIntArray(R.array.cimgs);
		
		for(int i = 0; i<5;i++)
		{
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("name", cname[i]);
			hm.put("descr", cdescr[i]);
			hm.put("imgs", Integer.toString(cil[i]));
			cList.add(hm);
		}
		
		String[] from = {"name","descr","imgs"};
		
		int[] to = {R.id.tvClubListName, R.id.tvClubListDescr, R.id.ivClubListImg};
		
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), cList, R.layout.club_list_layout_fragment, from, to);
		
		setListAdapter(adapter);
		
		Log.i("OVCCLF", "is this being called at alll");
		//Log.i("CLF", "is this being callled");
		
		
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Fragment frag =new  ClubMainFragment();
		ClubMainFragment blue = new ClubMainFragment();
		String name;
		name = blue.getName();
		//MainMenuActivity fme =  new MainMenuActivity();
		//fme.changeHead(name);
		
		//int testthis;
       // Bundle bundle = new Bundle();
        //bundle.putString("file_name", fname);
         //frag.setArguments(bundle);
		Log.i("CLICKED", "before fme is craeted");
		//replaceme = v.findViewById(R.id.club_list_fragment);
		
		MainMenuActivity.fragmentManager.beginTransaction().replace(R.id.main_fragment, frag).addToBackStack(null).commit();
		Log.i("CLICKED2", "after fme is craeted before getsupport");
		//fme.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, frag).addToBackStack(null).commit();
		Log.i("CLICKED2", "after fme is craeted after getsupport");
		//FragmentManager blue = getFragmentManager();		
		//fme.changeFragment(frag);
        
       /* FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(replaceme, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); */
	}
	
	@Override
	public void onDestroy() {
	Fragment fragment = getChildFragmentManager().findFragmentById(R.id.clublistcontainer);
	if (fragment.isResumed()) {
	    getChildFragmentManager().beginTransaction().remove(fragment).commit();
	}
	super.onDestroy();
	} 
	
	
 

}
