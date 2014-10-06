package com.kent.hottnights.leftactivities.clubs;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.Dress;
import com.kent.hottnights.objects.Drinks;
import com.kent.hottnights.objects.Features;

public class ClubFeaturesFragment extends Fragment {

	private View aboutview;
	private static int featid;
	private static int did;
	private static int dsid;
	//FEATURES TV
	TextView featAdm;
	TextView featDate;
	TextView featTime;
	TextView featPromo;
	//DRINKS TV
	TextView drink1;
	TextView drink2;
	TextView drink3;
	TextView drink4;
	TextView drink5;
	TextView drink6;
	TextView drink7;
	//DRESS TV
	TextView dHat;
	TextView dHoodies;
	TextView dShirts;
	TextView dPants;
	TextView dShoes;
	
	HottDatabaseHandler db;
	List<Features> featFrmDb;
	List<Drinks> drinkFrmDb;
	List<Dress> dressFrmDb;
	

	public ClubFeaturesFragment(int featid, int did, int dsid) {
		super();
		this.featid = featid;
		this.did = did;
		this.dsid = dsid;
	}

	public static ClubFeaturesFragment featuresFrag() {
		ClubFeaturesFragment n = new ClubFeaturesFragment(featid, did, dsid);
		Log.i("CAF", "club about fragments is called");
		return n;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		aboutview = inflater.inflate(R.layout.club_features_fragment,
				container, false);
		
		//main features
		featAdm =(TextView) aboutview.findViewById(R.id.tvClAdm);
		featDate =(TextView) aboutview.findViewById(R.id.tvClDate);
		featTime  =(TextView) aboutview.findViewById(R.id.tvClTime);
		featPromo =(TextView) aboutview.findViewById(R.id.tvClPromo);
		//drinks list
		drink1 = (TextView) aboutview.findViewById(R.id.tvClDrinks1);
		drink2 = (TextView) aboutview.findViewById(R.id.tvClDrinks2);
		drink3 = (TextView) aboutview.findViewById(R.id.tvClDrinks3);
		drink4 = (TextView) aboutview.findViewById(R.id.tvClDrinks4);
		drink5 = (TextView) aboutview.findViewById(R.id.tvClDrinks5);
		drink6 = (TextView) aboutview.findViewById(R.id.tvClDrinks6);
		drink7 = (TextView) aboutview.findViewById(R.id.tvClDrinks7);
		//dress list
		dHat = (TextView) aboutview.findViewById(R.id.tvClHat);
		dHoodies = (TextView) aboutview.findViewById(R.id.tvClHoodies);
		dShirts = (TextView) aboutview.findViewById(R.id.tvClShirts);
		dPants = (TextView) aboutview.findViewById(R.id.tvClPants);
		dShoes = (TextView) aboutview.findViewById(R.id.tvClShoes);
		
		
		
		db = new HottDatabaseHandler(getActivity().getBaseContext());
		featFrmDb = db.getFeatures(featid);
		drinkFrmDb = db.getDrinks(did);
		dressFrmDb = db.getDress(dsid);
		
		featAdm.setText(featFrmDb.get(0).getFeature_adm());
		featDate.setText(featFrmDb.get(0).getFeature_date());
		featTime.setText(featFrmDb.get(0).getFeature_time());
		featPromo.setText(featFrmDb.get(0).getFeature_promo());
		drink1.setText(drinkFrmDb.get(0).getDrink1());
		drink2.setText(drinkFrmDb.get(0).getDrink2());
		drink3.setText(drinkFrmDb.get(0).getDrink3());
		drink4.setText(drinkFrmDb.get(0).getDrink4());
		drink5.setText(drinkFrmDb.get(0).getDrink5());
		drink6.setText(drinkFrmDb.get(0).getDrink6());
		drink7.setText(drinkFrmDb.get(0).getDrink7());
		dHat.setText(dressFrmDb.get(0).getDress_hat());
		dHoodies.setText(dressFrmDb.get(0).getDress_hoodies());
		dShirts.setText(dressFrmDb.get(0).getDress_shirts());
		dPants.setText(dressFrmDb.get(0).getDress_pants());
		dShoes.setText(dressFrmDb.get(0).getDress_shoes());
		
		
		
		
		
		return aboutview;
	}

}
