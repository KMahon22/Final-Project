package com.kent.hottnights.login;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.ShareAdapter;
import com.kent.hottnights.communicators.HotComm;
import com.kent.hottnights.objects.ShareListObjs;

public class ShareListFragment extends ListFragment {

	
	private String[] cname;
	private String[] cdescr;
	private int[] cil;
	private String[] rbCheck;
	
	ArrayList<ShareListObjs> arrayShare;
	
	ImageView change;
	
	HotComm com;
	
	public static ShareListFragment shareFrag()
	{
		ShareListFragment r = new ShareListFragment();
		return r;
	}
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	
	arrayShare = new ArrayList<ShareListObjs>();
	
	cname = getResources().getStringArray(R.array.cnames);
	cdescr = getResources().getStringArray(R.array.cdescr);
	cil = getResources().getIntArray(R.array.cimgs);
	rbCheck = getResources().getStringArray(R.array.radioButton);
	
	for(int s = 0; s < 5; s++)
	{
		boolean toss = Boolean.parseBoolean(rbCheck[s]);
		ShareListObjs black = new ShareListObjs(cil[s], cname[s], cdescr[s], toss);
		
		
		arrayShare.add(black);
		
	}
	//arrayShare.get(1).isShareIcheck();
	
	ShareAdapter adapter = new ShareAdapter(getActivity().getBaseContext(), arrayShare);
	
	setListAdapter(adapter);
	
	com = (HotComm) getActivity();
	
	return super.onCreateView(inflater, container, savedInstanceState);
}

@Override
public void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	//change.findViewById(R.id.ivShareListOnClick);
	//arrayShare.get(position).setShareLimg(R.drawable.icon_home);
	//shareFrag();
	//change.setImageResource(R.drawable.icon_home)
	//arrayShare.get(1).getShareLname();
	com.eventInfo(arrayShare.get(position).getShareLname());
	
	
	Log.i("OLICINEVENT", "why dont i click :(");
}
	
}
