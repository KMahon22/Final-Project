package com.kent.hottnights.leftactivities.clubs;

//import android.R;
//import android.R;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.R;

public class ClubCardsFragment extends Fragment
{

	private static final String ARG_POSITION = "position";
	
	private int position;
	
	public static ClubCardsFragment newInstance(int position) //LOOK HERE TO CHANGE FRAGMENT TYPES CONSTRUCTOPR IS CALLED OVER AND OVER
	{
		ClubCardsFragment f = new ClubCardsFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		Log.i("CCF", "club cards fragment is called");
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		position = getArguments().getInt(ARG_POSITION);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//	return super.onCreateView(inflater, container, savedInstanceState);
	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	
	FrameLayout fl = new FrameLayout(getActivity());
	fl.setLayoutParams(params);
	
	final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
	
	TextView v = new TextView(getActivity());
	ImageView q = new ImageView(getActivity());
	params.setMargins(margin, margin, margin, margin);
	v.setLayoutParams(params);
	v.setLayoutParams(params);
	v.setGravity(Gravity.CENTER);
	v.setBackgroundResource(R.drawable.icon_home);
	v.setText("I UP IN HAY" + (position+ 1));
	
	
	fl.addView(v);
	return fl;
	}

}
