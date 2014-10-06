package com.kent.hottnights.objects;

public class ClubReviews {

	private int reviewId;
	private String reviewUPic; //to change to string so picasso can load them
	private String reviewUName;
	private int club_id;
	private String reviewDescr;
	private int reviewRating;

	/*public ClubReviews(int reviewUPic, String reviewDescr, int club_id,
			String reviewRating) {
		super();
		this.reviewId = reviewId;
		this.club_id = club_id;
		this.reviewDescr = reviewDescr;
		this.reviewRating = reviewRating;
	}*/
	
	
	
	public ClubReviews(String reviewUPic, String reviewUName, String reviewDescr,
			int reviewRating) {
		super();
		this.reviewUPic = reviewUPic;
		this.reviewUName = reviewUName;
		this.reviewDescr = reviewDescr;
		this.reviewRating = reviewRating;
	}

	public ClubReviews() {
	
	}

	public int getReviewId() {
		return reviewId;
	}

	

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getClub_id() {
		return club_id;
	}

	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}

	public String getReviewDescr() {
		return reviewDescr;
	}

	public void setReviewDescr(String reviewDescr) {
		this.reviewDescr = reviewDescr;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewUName() {
		return reviewUName;
	}

	public void setReviewUName(String reviewUName) {
		this.reviewUName = reviewUName;
	}

	public String getReviewUPic() {
		return reviewUPic;
	}

	public void setReviewUPic(String reviewUPic) {
		this.reviewUPic = reviewUPic;
	}

}
