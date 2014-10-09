package com.kent.hottnights.leftactivities.events;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.squareup.picasso.Picasso;

public class EventAboutFragment extends Fragment {

	private View eAboutView;
	static String evAbPic;
	static String evAbAb;
	ImageView ivAbPic;
	TextView tvAbAb;
	static EventAboutFragment eab;

	public EventAboutFragment() {
		
	}

	public EventAboutFragment(String evAbPic, String evAbAb) {
		super();
		this.evAbPic = evAbPic;
		this.evAbAb = evAbAb;
	}

	public static EventAboutFragment eventAboutFragment() {
		 eab = new EventAboutFragment(evAbPic, evAbAb);
		return eab;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		if(savedInstanceState != null)
		{
			evAbPic = savedInstanceState.getString("EVENTPIC");
			evAbAb = savedInstanceState.getString("EVENTABOUT");
		}
		
		eAboutView = inflater.inflate(R.layout.event_about_fragment, container,
				false);
		ivAbPic = (ImageView) eAboutView.findViewById(R.id.ivEventImg);
		tvAbAb = (TextView) eAboutView.findViewById(R.id.tvEventAbout);

		tvAbAb.setText(evAbAb);

		Picasso.with(getActivity().getBaseContext()) //
				.load(evAbPic) //
				.placeholder(R.drawable.ic_launcher) //
				.error(R.drawable.icon_home) //
				.fit() //
				.into(ivAbPic);

		eAboutView.setBackgroundResource(R.drawable.evasmall1);
		// eAboutView.setBackgroundResource(new
		// BitmapDrawable(getActivity().getBaseContext().getResources(),
		// "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTZuJHccuSxdxx4kAOVoGyt-5niG3mC6SdDGKe79ifGFNzkTCkizQ"));;
		// eAboutView.setBackgroundDrawable(new
		// BitmapDrawable(getActivity().getBaseContext().getResources(),
		// "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTZuJHccuSxdxx4kAOVoGyt-5niG3mC6SdDGKe79ifGFNzkTCkizQ"));
		MainMenuActivity.currentFragmentOnScreen("EventAboutFragment", eab);
		return eAboutView;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view.setBackgroundResource(R.drawable.evasmall1);
	}
	
	/*
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("EVENTPIC", evAbPic);
		outState.putString("EVENTABOUT", evAbAb);
	} */
}
