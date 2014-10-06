package com.kent.hottnights.objects;

public class ClubContacts {

	private int contact_id;
	private String contact_address;
	private String contact_number1;
	private String contact_number2;
	private String contact_website;
	private String contact_email;
	private String contact_facebook;
	private String contact_twitter;
	
	public ClubContacts ()
	{
		
	}

	public ClubContacts(int contact_id, String contact_address,
			String contact_number1, String contact_number2,
			String contact_website, String contact_email,
			String contact_facebook, String contact_twitter) {
		super();
		this.contact_id = contact_id;
		this.contact_address = contact_address;
		this.contact_number1 = contact_number1;
		this.contact_number2 = contact_number2;
		this.contact_website = contact_website;
		this.contact_email = contact_email;
		this.contact_facebook = contact_facebook;
		this.contact_twitter = contact_twitter;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_address() {
		return contact_address;
	}

	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}

	public String getContact_number1() {
		return contact_number1;
	}

	public void setContact_number1(String contact_number1) {
		this.contact_number1 = contact_number1;
	}

	public String getContact_number2() {
		return contact_number2;
	}

	public void setContact_number2(String contact_number2) {
		this.contact_number2 = contact_number2;
	}

	public String getContact_website() {
		return contact_website;
	}

	public void setContact_website(String contact_website) {
		this.contact_website = contact_website;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getContact_facebook() {
		return contact_facebook;
	}

	public void setContact_facebook(String contact_facebook) {
		this.contact_facebook = contact_facebook;
	}

	public String getContact_twitter() {
		return contact_twitter;
	}

	public void setContact_twitter(String contact_twitter) {
		this.contact_twitter = contact_twitter;
	}
	
	
}
