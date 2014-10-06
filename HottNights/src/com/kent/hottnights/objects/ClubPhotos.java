package com.kent.hottnights.objects;

public class ClubPhotos {

	private int photoId;
	private String photoUrl;
	
	public ClubPhotos(int photoId, String photoUrl) {
		super();
		this.photoId = photoId;
		this.photoUrl = photoUrl;
	}
	
	

	public ClubPhotos(String photoUrl) {
		super();
		this.photoUrl = photoUrl;
	}



	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
