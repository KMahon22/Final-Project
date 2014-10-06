package com.kent.hottnights.leftactivities.clubs;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kent.hottnights.R;
import com.kent.hottnights.adapters.ClubPhotoAdapter;
import com.kent.hottnights.database.HottDatabaseHandler;
import com.kent.hottnights.objects.ClubPhotos;
import com.kent.hottnights.objects.Photos;

public class ClubPhotoFragment extends ListFragment {

	private String[] photourl;
	private int[] photoid;
	ArrayList<ClubPhotos> arrayPhoto;

	// from db
	ArrayList<Photos> photosFrmDb;
	HottDatabaseHandler hottie;
	private static int photId;

	public ClubPhotoFragment(int photId) {
		super();
		this.photId = photId;
	}

	public static ClubPhotoFragment photoFrag() {
		ClubPhotoFragment p = new ClubPhotoFragment(photId);
		return p;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		arrayPhoto = new ArrayList<ClubPhotos>();

		photourl = getResources().getStringArray(R.array.photourls);
		photoid = getResources().getIntArray(R.array.photoid);

		hottie = new HottDatabaseHandler(getActivity().getBaseContext());
		photosFrmDb = hottie.getPhotos(photId);

		// for(int pl = 1; pl < 8 ; pl++)
		// {
		ClubPhotos bang1 = new ClubPhotos(photosFrmDb.get(0).getPhoto1());
		ClubPhotos bang2 = new ClubPhotos(photosFrmDb.get(0).getPhoto2());
		ClubPhotos bang3 = new ClubPhotos(photosFrmDb.get(0).getPhoto3());
		ClubPhotos bang4 = new ClubPhotos(photosFrmDb.get(0).getPhoto4());
		ClubPhotos bang5 = new ClubPhotos(photosFrmDb.get(0).getPhoto5());
		ClubPhotos bang6 = new ClubPhotos(photosFrmDb.get(0).getPhoto6());
		ClubPhotos bang7 = new ClubPhotos(photosFrmDb.get(0).getPhoto7());

		arrayPhoto.add(bang1);
		arrayPhoto.add(bang2);
		arrayPhoto.add(bang3);
		arrayPhoto.add(bang4);
		arrayPhoto.add(bang5);
		arrayPhoto.add(bang6);
		arrayPhoto.add(bang7);
		// }

		ClubPhotoAdapter adapter = new ClubPhotoAdapter(getActivity()
				.getBaseContext(), arrayPhoto);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
