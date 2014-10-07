package com.kent.hottnights.database;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;

public class MyService extends Service {

	int numMessages = 0;
	
	
	public MyService() {
		
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("MYS", "my service was created");
	}
	
	@Override
	public void onStart(Intent intent, int startId)
	{
		//Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		Intent resultIntent = new Intent(this, MainMenuActivity.class);
		
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder mNotifiyBuilder;
		NotificationManager mNotificationManager;
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		int notifiyID = 9001;
		mNotifiyBuilder = new NotificationCompat.Builder(this).setContentTitle("HottNights").setContentText("New parties are available." ).setSmallIcon(R.drawable.ic_launcher);
		
		mNotifiyBuilder.setContentIntent(resultPendingIntent);
		
		int defaults = 0;
		defaults = defaults | Notification.DEFAULT_LIGHTS;
		defaults = defaults | Notification.DEFAULT_VIBRATE;
		defaults = defaults | Notification.DEFAULT_SOUND;
		mNotifiyBuilder.setDefaults(defaults);
		
	//	Log.i("itntdata", intent.getStringExtra("intntdata"));
	
		mNotifiyBuilder.setContentText(intent.getStringExtra("intntdata"));
		
		mNotifiyBuilder.setAutoCancel(true);
		
		mNotificationManager.notify(notifiyID, mNotifiyBuilder.build());
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

	}
}
