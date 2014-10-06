package com.kent.hottnights.objects;

import com.google.android.gms.maps.model.LatLng;

public class GasStations {

	private int gsId;
	private String gsName;
	private String gsOH;// opening hours
	private float gsLat;
	private float gsLong;

	public GasStations() {

	}

	public GasStations(int gsId, String gsName, String gsOH, float gsLat,
			float gsLong) {
		super();
		this.gsId = gsId;
		this.gsName = gsName;
		this.gsOH = gsOH;
		this.gsLat = gsLat;
		this.gsLong = gsLong;
	}

	public int getGsId() {
		return gsId;
	}

	public void setGsId(int gsId) {
		this.gsId = gsId;
	}

	public String getGsName() {
		return gsName;
	}

	public void setGsName(String gsName) {
		this.gsName = gsName;
	}

	public String getGsOH() {
		return gsOH;
	}

	public void setGsOH(String gsOH) {
		this.gsOH = gsOH;
	}

	public float getGsLat() {
		return gsLat;
	}

	public void setGsLat(float gsLat) {
		this.gsLat = gsLat;
	}

	public float getGsLong() {
		return gsLong;
	}

	public void setGsLong(float gsLong) {
		this.gsLong = gsLong;
	}

	public LatLng getLatLng() {
		return new LatLng(gsLat, gsLong);
	}

	// pic

}
