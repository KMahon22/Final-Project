package com.kent.hottnights.objects;

public class Club {

	private String club_id;
	private String club_name;
	private String club_pic;
	private String club_about;
	private String club_lat;
	private String club_long;
	private int feature_id;
	private int contact_id;
	private int drinks_id;
	private int dress_id;

	public Club(String club_id, String club_name, String club_pic,
			String club_about, String club_lat, String club_long) {
		super();
		this.club_id = club_id;
		this.club_name = club_name;
		this.club_pic = club_pic;
		this.club_about = club_about;
		this.club_lat = club_lat;
		this.club_long = club_long;
	}

	public String getClub_id() {
		return club_id;
	}

	public void setClub_id(String club_id) {
		this.club_id = club_id;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getClub_pic() {
		return club_pic;
	}

	public void setClub_pic(String club_pic) {
		this.club_pic = club_pic;
	}

	public String getClub_about() {
		return club_about;
	}

	public void setClub_about(String club_about) {
		this.club_about = club_about;
	}

	public String getClub_lat() {
		return club_lat;
	}

	public void setClub_lat(String club_lat) {
		this.club_lat = club_lat;
	}

	public String getClub_long() {
		return club_long;
	}

	public void setClub_long(String club_long) {
		this.club_long = club_long;
	}

	public int getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
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

}
