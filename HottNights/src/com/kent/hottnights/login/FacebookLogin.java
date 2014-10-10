/**
 * @author Kent
 * @date 22/09/2014
 */

package com.kent.hottnights.login;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.kent.hottnights.MainMenuActivity;
import com.kent.hottnights.R;
import com.kent.hottnights.R.drawable;
import com.kent.hottnights.R.id;
import com.kent.hottnights.R.layout;

//import android.R;

public class FacebookLogin extends Fragment {

	@InjectView(R.drawable.splash)
	ImageView splash;

	private static final List<String> PERMISSIONS = Arrays
			.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;
	private static final String TAG = "SplashScreen";
	Button shareButton;

	String fbTitle;
	String fbDescr;
	String fbCaption;
	String fbLink;
	String fbPicture;

	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.login_layout, container, false);

		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.login_button);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays
				.asList("user_likes", "user_status"));

		shareButton = (Button) view.findViewById(R.id.shareButton);

		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				publishStory();
			}
		});

		Session session = Session.getActiveSession();
		if (session != null && session.isOpened()) {
			// Get the user's data
			makeMeRequest(session);
		}

		if (savedInstanceState != null) {
			pendingPublishReauthorization = savedInstanceState.getBoolean(
					PENDING_PUBLISH_KEY, false);
		}
		return view;
	}


	/**
	 * 
	 * @param pfbTitle This parameter stores the title of the item from the list
	 * @param pfbCaption This parameter stores the caption of the item from the list
	 * @param pfbDescr This parameter stores the description of the item from the list
	 * @param pfbLink This parameter stores the link of the item from the list
	 * @param pfbPicture This parameter stores the url of the picture of the item from the list
	 */
	public void infoToAccept(String pfbTitle, String pfbCaption, String pfbDescr, String pfbLink,
			String pfbPicture) {
		fbTitle = pfbPicture;
		fbCaption = pfbPicture;
		fbDescr = pfbPicture;
		fbLink = pfbPicture;
		fbPicture = pfbPicture;
	}

	private void makeMeRequest(final Session session) {
		// Make an API call to get user data and define a
		// new callback to handle the response.
		Request request = Request.newMeRequest(session,
				new Request.GraphUserCallback() {

					@Override
					public void onCompleted(GraphUser user, Response response) {
						// TODO Auto-generated method stub
						if (session == Session.getActiveSession()) {
							if (user != null) {
								// Set the id for the ProfilePictureView
								// view that in turn displays the profile
								// picture.
								MainMenuActivity.resideMenu.setProfilePic(user
										.getId());
								// Set the Textview's text to the user's name.
								MainMenuActivity.resideMenu.setProfileName(user
										.getName());
							}
						}
						if (response.getError() != null) {
							// Handle errors, will do so later.
						}
					}

				});

		request.executeAsync();
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		
		Log.i("SESSSTATECH", "is this whole funtion called twice?");
		if (session != null && session.isOpened()) {
			// Get the user's data.
			makeMeRequest(session);
			// MainMenuActivity.setToken(1);
			shareButton.setVisibility(View.VISIBLE);
			// ShareMainFragment.shareButton.setVisibility(View.VISIBLE);
			if (pendingPublishReauthorization
					&& state.equals(SessionState.OPENED_TOKEN_UPDATED)) {
				pendingPublishReauthorization = false;
				publishStory();
			}
		} else if (session.isClosed()) {
			// MainMenuActivity.setToken(0);
			// ShareMainFragment.shareButton.setVisibility(View.INVISIBLE);
			shareButton.setVisibility(View.INVISIBLE);
		}
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
			Log.i(TAG, "twice???");
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");

		}
	}

	public void publishStory() {
		try {
			Session session = Session.getActiveSession();

			if (session != null) {

				// Check for publish permissions
				List<String> permissions = session.getPermissions();
				if (!isSubsetOf(PERMISSIONS, permissions)) {
					pendingPublishReauthorization = true;
					Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
							this, PERMISSIONS);
					session.requestNewPublishPermissions(newPermissionsRequest);
					return;
				}

				Bundle postParams = new Bundle();
				postParams.putString("name", fbTitle);
				postParams.putString("caption", fbCaption);
				postParams.putString("description", fbDescr);
				postParams.putString("link",
						"http://www.destinythegame.com/uk/en");
				postParams
						.putString(
								"picture",
								fbPicture);

				Request.Callback callback = new Request.Callback() {
					public void onCompleted(Response response) {
						JSONObject graphResponse = response.getGraphObject()
								.getInnerJSONObject();
						String postId = null;
						try {
							postId = graphResponse.getString("id");
						} catch (JSONException e) {
							Log.i(TAG, "JSON error " + e.getMessage());
						}
						FacebookRequestError error = response.getError();
						if (error != null) {
							Toast.makeText(
									getActivity().getApplicationContext(),
									error.getErrorMessage(), Toast.LENGTH_SHORT)
									.show();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									postId, Toast.LENGTH_LONG).show();
						}
					}
				};

				Request request = new Request(session, "me/feed", postParams,
						HttpMethod.POST, callback);

				RequestAsyncTask task = new RequestAsyncTask(request);
				task.execute();
			}

		} catch (Exception e) {
			Log.i("SPLASH", e.getMessage());
		}

	}

	private boolean isSubsetOf(Collection<String> subset,
			Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		uiHelper.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
		
			/*   
			    Fragment fragment = (getFragmentManager().findFragmentById(R.id.share_main_fragment_fb));  
			    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			    ft.remove(fragment);
			    ft.commit();
			*/
		
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
		uiHelper.onSaveInstanceState(outState);
	}
	/*
	 * @Override protected void onCreate(Bundle savedInstanceState) { // TODO
	 * Auto-generated method stub super.onCreate(savedInstanceState);
	 * setContentView(R.layout.login); /* Thread timer = new Thread(){ public
	 * void run(){ try{ sleep(7000); } catch (InterruptedException e){
	 * e.printStackTrace(); }finally{ startMain(); } } }; timer.start();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * /** allows the splash screen what is the next screen to go to
	 * 
	 * 
	 * 
	 * public void startMain() { Intent intent = new Intent(this,
	 * MainMenuActivity.class); startActivity(intent); finish(); }
	 */

}
