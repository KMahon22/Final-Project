package com.kent.hottnights.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;

import com.kent.hottnights.SplashScreen;

public class TempFragmentActivity extends FragmentActivity {
	SplashScreen mainFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new SplashScreen();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, mainFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (SplashScreen) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
	    
	    
	 /*   try {
	        PackageInfo info = getPackageManager().getPackageInfo(
	                "com.kent.hottnights", 
	                PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	            }
	    } catch (NameNotFoundException e) {
	    	Log.i("hash", "shit didnt work");

	    } catch (NoSuchAlgorithmException k) {
	    	Log.i("hash", "shit didnt work2222");
	    } */
	}
	
	

}