package com.kent.hottnights.objects;

public class Features {
	
	private int feature_id;
	private String feature_adm;
	private String feature_date;
	private String feature_time;
	private String feature_promo;
	
	public Features()
	{
		
	}

	public Features(int feature_id, String feature_adm, String feature_date,
			String feature_time, String feature_promo) {
		super();
		this.feature_id = feature_id;
		this.feature_adm = feature_adm;
		this.feature_date = feature_date;
		this.feature_time = feature_time;
		this.feature_promo = feature_promo;
	}

	public int getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}

	public String getFeature_adm() {
		return feature_adm;
	}

	public void setFeature_adm(String feature_adm) {
		this.feature_adm = feature_adm;
	}

	public String getFeature_date() {
		return feature_date;
	}

	public void setFeature_date(String feature_date) {
		this.feature_date = feature_date;
	}

	public String getFeature_time() {
		return feature_time;
	}

	public void setFeature_time(String feature_time) {
		this.feature_time = feature_time;
	}

	public String getFeature_promo() {
		return feature_promo;
	}

	public void setFeature_promo(String feature_promo) {
		this.feature_promo = feature_promo;
	}

	
}
