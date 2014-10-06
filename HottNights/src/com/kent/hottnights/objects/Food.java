package com.kent.hottnights.objects;

import com.google.android.gms.maps.model.LatLng;

public class Food {

	private int foodId;
	private String foodName;
	private String foodOH;
	private float foodLat;
	private float foodLong;

	public Food() {

	}

	public Food(int foodId, String foodName, String foodOH, float foodLat,
			float foodLong) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodOH = foodOH;
		this.foodLat = foodLat;
		this.foodLong = foodLong;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodOH() {
		return foodOH;
	}

	public void setFoodOH(String foodOH) {
		this.foodOH = foodOH;
	}

	public float getFoodLat() {
		return foodLat;
	}

	public void setFoodLat(float foodLat) {
		this.foodLat = foodLat;
	}

	public float getFoodLong() {
		return foodLong;
	}

	public void setFoodLong(float foodLong) {
		this.foodLong = foodLong;
	}
	
	public LatLng foodLatLng()
	{
		return new LatLng(foodLat, foodLong);
	}

}
