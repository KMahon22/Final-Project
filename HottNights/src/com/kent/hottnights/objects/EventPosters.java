package com.kent.hottnights.objects;

public class EventPosters {

	private int posterId;
	private String posterUrl;

	public EventPosters(int posterId, String posterUrl) {
		super();
		this.posterId = posterId;
		this.posterUrl = posterUrl;
	}

	public EventPosters(String posterUrl) {
		super();
		this.posterUrl = posterUrl;
	}

	public int getPosterId() {
		return posterId;
	}

	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

}
