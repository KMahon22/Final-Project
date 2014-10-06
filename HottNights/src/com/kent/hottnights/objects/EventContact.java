package com.kent.hottnights.objects;

public class EventContact {

	private int ecId;
	private String ecName;
	private String ecPic;
	private String ecNumber;
	private String ecEmail;
	private String ecFbk;
	private String ecTwitter;
	private int event_id;

	public EventContact() {

	}

	public EventContact(String ecPic, String ecName, String ecNumber,
			String ecEmail, String ecFbk, String ecTwitter) {
		super();
		this.ecPic = ecPic;
		this.ecName = ecName;
		this.ecNumber = ecNumber;
		this.ecEmail = ecEmail;
		this.ecFbk = ecFbk;
		this.ecTwitter = ecTwitter;
	}

	

	public EventContact(String ecName, String ecPic, String ecNumber,
			String ecEmail, String ecFbk, String ecTwitter, int event_id) {
		super();
		this.ecName = ecName;
		this.ecPic = ecPic;
		this.ecNumber = ecNumber;
		this.ecEmail = ecEmail;
		this.ecFbk = ecFbk;
		this.ecTwitter = ecTwitter;
		this.event_id = event_id;
	}

	public int getEcId() {
		return ecId;
	}

	public void setEcId(int ecId) {
		this.ecId = ecId;
	}

	public String getEcPic() {
		return ecPic;
	}

	public void setEcPic(String ecPic) {
		this.ecPic = ecPic;
	}

	public String getEcName() {
		return ecName;
	}

	public void setEcName(String ecName) {
		this.ecName = ecName;
	}

	public String getEcNumber() {
		return ecNumber;
	}

	public void setEcNumber(String ecNumber) {
		this.ecNumber = ecNumber;
	}

	public String getEcEmail() {
		return ecEmail;
	}

	public void setEcEmail(String ecEmail) {
		this.ecEmail = ecEmail;
	}

	public String getEcFbk() {
		return ecFbk;
	}

	public void setEcFbk(String ecFbk) {
		this.ecFbk = ecFbk;
	}

	public String getEcTwitter() {
		return ecTwitter;
	}

	public void setEcTwitter(String ecTwitter) {
		this.ecTwitter = ecTwitter;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	

}
