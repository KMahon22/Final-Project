package com.kent.hottnights.leftactivities;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.special.ResideMenu.ResideMenu;

public class EventMainFragment  extends Fragment{

	private View parentView;
	private ResideMenu resideMenu;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		parentView = inflater.inflate(R.layout.event_main_fragment, container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {
		// TODO Auto-generated method stub
		MainMenuActivity parentActivity = (MainMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
		
	/*parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
		}
	}) ; */
		
	
	FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
	resideMenu.addIgnoredView(ignored_view);
	}
	
	/*@Override
	public void onDestroy() {
	Fragment fragment = getFragmentManager().findFragmentById(R.id.main_fragment);
	if (fragment.isResumed()) {
	    getFragmentManager().beginTransaction().remove(fragment).commit();
	}
	super.onDestroy();
	}*/

}
