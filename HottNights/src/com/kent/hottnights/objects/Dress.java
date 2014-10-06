package com.kent.hottnights.objects;

public class Dress {

	private int dress_id;
	private String dress_hat;
	private String dress_hoodies;
	private String dress_shirts;
	private String dress_pants;
	private String dress_shoes;
	
	public Dress() {
		
	}
	
	public Dress(int dress_id, String dress_hat, String dress_hoodies,
			String dress_shirts, String dress_pants, String dress_shoes) {
		super();
		this.dress_id = dress_id;
		this.dress_hat = dress_hat;
		this.dress_hoodies = dress_hoodies;
		this.dress_shirts = dress_shirts;
		this.dress_pants = dress_pants;
		this.dress_shoes = dress_shoes;
	}
	public int getDress_id() {
		return dress_id;
	}
	public void setDress_id(int dress_id) {
		this.dress_id = dress_id;
	}
	public String getDress_hat() {
		return dress_hat;
	}
	public void setDress_hat(String dress_hat) {
		this.dress_hat = dress_hat;
	}
	public String getDress_hoodies() {
		return dress_hoodies;
	}
	public void setDress_hoodies(String dress_hoodies) {
		this.dress_hoodies = dress_hoodies;
	}
	public String getDress_shirts() {
		return dress_shirts;
	}
	public void setDress_shirts(String dress_shirts) {
		this.dress_shirts = dress_shirts;
	}
	public String getDress_pants() {
		return dress_pants;
	}
	public void setDress_pants(String dress_pants) {
		this.dress_pants = dress_pants;
	}
	public String getDress_shoes() {
		return dress_shoes;
	}
	public void setDress_shoes(String dress_shoes) {
		this.dress_shoes = dress_shoes;
	}
	
	
}
