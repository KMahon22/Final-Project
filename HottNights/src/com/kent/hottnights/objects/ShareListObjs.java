package com.kent.hottnights.objects;

public class ShareListObjs {

	private int shareLid;
	private int shareLimg;
	private String shareLname;
	private String shareDescr;
	private boolean shareIcheck;

	public ShareListObjs(int shareLimg, String shareLname, String shareDescr,
			boolean shareIcheck) {
		super();
		this.shareLimg = shareLimg;
		this.shareLname = shareLname;
		this.shareDescr = shareDescr;
		this.shareIcheck = shareIcheck;
	}

	
	
	public int getShareLid() {
		return shareLid;
	}



	public void setShareLid(int shareLid) {
		this.shareLid = shareLid;
	}



	public int getShareLimg() {
		return shareLimg;
	}

	public void setShareLimg(int shareLimg) {
		this.shareLimg = shareLimg;
	}

	public String getShareLname() {
		return shareLname;
	}

	public void setShareLname(String shareLname) {
		this.shareLname = shareLname;
	}

	public String getShareDescr() {
		return shareDescr;
	}

	public void setShareDescr(String shareDescr) {
		this.shareDescr = shareDescr;
	}

	public boolean isShareIcheck() {
		return shareIcheck;
	}

	public void setShareIcheck(boolean shareIcheck) {
		this.shareIcheck = shareIcheck;
	}

}
