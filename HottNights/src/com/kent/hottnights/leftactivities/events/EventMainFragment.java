package com.kent.hottnights.leftactivities.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;
import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.special.ResideMenu.ResideMenu;
import com.kent.hottnights.leftactivities.clubs.ClubCardsFragment;

public class EventMainFragment extends Fragment {

	
	private View parentView;
	private ResideMenu resideMenu;
	
	
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	
	private MyPagerAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		parentView = inflater.inflate(R.layout.event_main_fragment, container, false);
		SetUpViews();
		
		return parentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.eventtabs);
		pager = (ViewPager) view.findViewById(R.id.eventpager);
		
		adapter = new MyPagerAdapter(getFragmentManager());
		
		pager.setAdapter(adapter);
		
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
	}
	
	private void SetUpViews() {
		// TODO Auto-generated method stub
		MainMenuActivity parentActivity = (MainMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
		
		LinearLayout ignored_view = (LinearLayout) parentView.findViewById(R.id.ignoredthisplzevent);
		resideMenu.addIgnoredView(ignored_view);
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter
	{
		private final String[] EVENTTABS = {"About","Promos", "Features", "Map", "Contact"};

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			if (position == 0 ){
				return EventAboutFragment.eventAboutFragment();
			}
			else if (position == 1){
				return EventPromoFragment.posterFrag();
			}
			else if (position == 2 ){
				return EventFeaturesFragment.eventFeaturesFragment();
			}
			else if (position == 3 ){
				return EventMapFragment.mappyFrag();
			}
			else if (position == 4 ){
				return EventContactsFragment.eContacts();
			}else
			{
				return ClubCardsFragment.newInstance(position);
			}
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return EVENTTABS.length;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return EVENTTABS[position];
		}
		
	}
}
