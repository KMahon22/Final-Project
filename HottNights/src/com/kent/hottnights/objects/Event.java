package com.kent.hottnights.objects;

import com.google.android.gms.maps.model.LatLng;

public class Event {

	private int eventid;
	private String eventName;
	private String eventPic;
	private String eventDescr;
	private String eventAbout;
	private float eventLat;
	private float eventLong;
	private int feature_id;
	private int drinks_id;
	private int dress_id;
	private int photo_id;

	public Event() {
		super();
	}
	
	

	public Event(String eventName, String eventPic, String eventDescr) {
		super();
		this.eventName = eventName;
		this.eventPic = eventPic;
		this.eventDescr = eventDescr;
	}



	public Event(int eventid, String eventName, String eventPic,
			String eventDescr, String eventAbout, float eventLat,
			float eventLong, int feature_id, int drinks_id, int dress_id,
			int photo_id) {
		super();
		this.eventid = eventid;
		this.eventName = eventName;
		this.eventPic = eventPic;
		this.eventDescr = eventDescr;
		this.eventAbout = eventAbout;
		this.eventLat = eventLat;
		this.eventLong = eventLong;
		this.feature_id = feature_id;
		this.drinks_id = drinks_id;
		this.dress_id = dress_id;
		this.photo_id = photo_id;
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPic() {
		return eventPic;
	}

	public void setEventPic(String eventPic) {
		this.eventPic = eventPic;
	}

	public String getEventDescr() {
		return eventDescr;
	}

	public void setEventDescr(String eventDescr) {
		this.eventDescr = eventDescr;
	}

	public String getEventAbout() {
		return eventAbout;
	}

	public void setEventAbout(String eventAbout) {
		this.eventAbout = eventAbout;
	}

	public float getEventLat() {
		return eventLat;
	}

	public void setEventLat(float eventLat) {
		this.eventLat = eventLat;
	}

	public float getEventLong() {
		return eventLong;
	}

	public void setEventLong(float eventLong) {
		this.eventLong = eventLong;
	}

	public int getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}

	public int getDrinks_id() {
		return drinks_id;
	}

	public void setDrinks_id(int drinks_id) {
		this.drinks_id = drinks_id;
	}

	public int getDress_id() {
		return dress_id;
	}

	public void setDress_id(int dress_id) {
		this.dress_id = dress_id;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	
	public LatLng evLatLang()
	{
		return new LatLng(eventLat, eventLong);
	}

}
