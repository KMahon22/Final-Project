package com.kent.hottnights.leftactivities.clubs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.squareup.picasso.Picasso;

public class ClubAboutFragment extends Fragment {

	private View aboutview;
	TextView aboutText;
	static String aboutTextInfo;
	static String aboutPic;
	ImageView clubImg;

	public ClubAboutFragment(String aboutTextInfo, String aboutPic) {
		super();
		this.aboutTextInfo = aboutTextInfo;
		this.aboutPic = aboutPic;
	}

	public static ClubAboutFragment aboutFrag() {
		ClubAboutFragment n = new ClubAboutFragment(aboutTextInfo, aboutPic);
		Log.i("CAF", "club about fragments is called");
		return n;

	}

	public void GetAboutInfo(String stuffage) {
		aboutTextInfo = stuffage;
		Log.i("GETABINF", stuffage);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		aboutview = inflater.inflate(R.layout.club_about_layout, container,
				false);
		aboutText = (TextView) aboutview.findViewById(R.id.tvClubAbout);
		clubImg = (ImageView) aboutview.findViewById(R.id.ivClubImg);
		aboutText.setText(aboutTextInfo);

		Picasso.with(getActivity().getBaseContext()) //
        .load(aboutPic) //
        .placeholder(R.drawable.ic_launcher) //
        .error(R.drawable.icon_home) //
        .fit() //
        .into(clubImg);
		
		return aboutview;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		// aboutText = (TextView) view.findViewById(R.id.tvClubAbout);
		// aboutText.setText(aboutTextInfo);
		view.setBackgroundResource(R.drawable.clback1);
	}

}
