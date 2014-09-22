/**
 * @author Kent
 * @date 22/09/2014
 */


package com.kent.hottnights;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends Activity {

	@InjectView (R.drawable.splash) ImageView splash;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					startMain();
				}
			}
		};
		timer.start();
		
	
	}

	/**
	 * allows the splash screen what is the next screen to go to
	 * */
	 
	
	public void startMain()
	{
		Intent intent = new Intent(this, MainMenuActivity.class);
		startActivity(intent);
		finish();
	}
}
