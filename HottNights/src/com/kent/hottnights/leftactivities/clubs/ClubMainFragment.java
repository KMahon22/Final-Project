package com.kent.hottnights.leftactivities.clubs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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

public class ClubMainFragment extends Fragment {

	private View parentView;
	private ResideMenu resideMenu;
	
	private final Handler handler = new Handler();
	
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	
	private Drawable oldBackground = null;
	private int currentColor = 0xFF666666;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		parentView = inflater.inflate(R.layout.club_main_fragment, container, false);
		setUpViews();
	
		
		return parentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		pager = (ViewPager) view.findViewById(R.id.pager);
		
		adapter = new MyPagerAdapter(getFragmentManager());
		
		pager.setAdapter(adapter);
		
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
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
	}); */
		

	LinearLayout ignored_view = (LinearLayout) parentView.findViewById(R.id.ignoredthisplz);
	resideMenu.addIgnoredView(ignored_view);
	
	
	}


	public class MyPagerAdapter extends FragmentPagerAdapter 
	{

		private final String[] TITLES = { "About", "Photos", "Features",
				"Reviews", "Map", "Contact" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return CardsFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return TITLES.length;
		}

		public CharSequence getPageTitle(int position) {
				return TITLES[position];
		}
	}
}
