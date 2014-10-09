package com.kent.hottnights.leftactivities.events;

import java.util.List;

import com.kent.hottnights.R;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.Dress;
import com.kent.hottnights.objects.Drinks;
import com.kent.hottnights.objects.Features;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventFeaturesFragment extends Fragment{

	private View eFeatView;
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
	
	
	
	public EventFeaturesFragment() {
		
	}

	public EventFeaturesFragment(int featid, int did, int dsid) {
		super();
		this.featid = featid;
		this.did = did;
		this.dsid = dsid;
	}

	public static EventFeaturesFragment eventFeaturesFragment()
	{
		EventFeaturesFragment efb = new EventFeaturesFragment(featid, did, dsid);
		return efb;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		eFeatView = inflater.inflate(R.layout.event_features_fragment, container, false);
		
		//main features
				featAdm =(TextView) eFeatView.findViewById(R.id.tvEvAdm);
				featDate =(TextView) eFeatView.findViewById(R.id.tvEvDate);
				featTime  =(TextView) eFeatView.findViewById(R.id.tvEvTime);
				featPromo =(TextView) eFeatView.findViewById(R.id.tvEvPromo);
				//drinks list
				drink1 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks1);
				drink2 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks2);
				drink3 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks3);
				drink4 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks4);
				drink5 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks5);
				drink6 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks6);
				drink7 = (TextView) eFeatView.findViewById(R.id.tvEvDrinks7);
				//dress list
				dHat = (TextView) eFeatView.findViewById(R.id.tvEvHat);
				dHoodies = (TextView) eFeatView.findViewById(R.id.tvEvHoodies);
				dShirts = (TextView) eFeatView.findViewById(R.id.tvEvShirts);
				dPants = (TextView) eFeatView.findViewById(R.id.tvEvPants);
				dShoes = (TextView) eFeatView.findViewById(R.id.tvEvShoes);
				
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
				
		eFeatView.setBackgroundResource(R.drawable.evasmall1);
		
		return eFeatView;
	
	}
	
}
