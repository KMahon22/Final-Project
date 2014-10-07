package com.kent.hottnights.database;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.kent.hottnights.utilities.WebServiceURLS;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SyncBC extends BroadcastReceiver {

	static int noOfTimes = 0;
	WebServiceURLS wsu;
	public String drc;

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("SAMPLEBC", "DO I GET IN HERE??");
		noOfTimes++;
		Log.i("BCR", "broadcastrecevier has been running " + noOfTimes);
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		drc = wsu.IP_ADDRESS + wsu.WSFOLDER + wsu.getDRESSROWCOUNT();
		Log.i("DRC", drc);

		client.post(drc, params, new AsyncHttpResponseHandler() { // "http://192.168.1.129/TheAppExperts/HottNights/getDressRowCount.php"

					@Override
					public void onSuccess(String response) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg0, arg1, arg2);
						System.out.println(response);

						try {
							JSONObject obj = new JSONObject(response);
							System.out.println(obj.get("count"));

							if (obj.getInt("count") != 0) {
								try {
									final Intent intnt = new Intent(context,
											MyService.class);
									intnt.putExtra(
											"intntdata",
											"Unsynched Rows Count "
													+ obj.getInt("count"));

									context.startService(intnt);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else {
								Toast.makeText(context, "Sync not needed",
										Toast.LENGTH_SHORT).show();
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					@Override
					@Deprecated
					public void onFailure(int statusCode, Throwable error,
							String content) {
						// TODO Auto-generated method stub
						Log.i("ERROR", error + "");
						if (statusCode == 404) {
							Toast.makeText(context, "404", Toast.LENGTH_SHORT)
									.show();
						} else if (statusCode == 500) {
							Toast.makeText(context, "500", Toast.LENGTH_SHORT)
									.show();
						} else {
							Toast.makeText(context, "Error occured!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

	}

}
