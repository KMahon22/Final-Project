package com.kent.hottnights.leftactivities.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.squareup.picasso.Picasso;

public class EventAboutFragment extends Fragment {

	
	private View eAboutView;
	static String evAbPic;
	static String evAbAb;
	ImageView ivAbPic;
	TextView tvAbAb;
	
	
	
	public EventAboutFragment(String evAbPic, String evAbAb) {
		super();
		this.evAbPic = evAbPic;
		this.evAbAb = evAbAb;
	}

	public static EventAboutFragment eventAboutFragment()
	{
		EventAboutFragment eab = new EventAboutFragment(evAbPic, evAbAb);
		return eab;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		eAboutView = inflater.inflate(R.layout.event_about_fragment, container, false);
		ivAbPic = (ImageView) eAboutView.findViewById(R.id.ivEventImg);
		tvAbAb = (TextView) eAboutView.findViewById(R.id.tvEventAbout);
		
		tvAbAb.setText(evAbAb);
		
		Picasso.with(getActivity().getBaseContext()) //
        .load(evAbPic) //
        .placeholder(R.drawable.ic_launcher) //
        .error(R.drawable.icon_home) //
        .fit() //
        .into(ivAbPic);
		
		
		return eAboutView;
	
	}
}
