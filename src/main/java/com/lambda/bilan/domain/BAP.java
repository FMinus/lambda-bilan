package com.lambda.bilan.domain;

public class BAP {

	private String dateBAP;
	private String status;
	
	
	public BAP() {
		super();
	}
	public BAP(String dateBAP, String status) {
		super();
		this.dateBAP = dateBAP;
		this.status = status;
	}
	public String getdateBAP() {
		return dateBAP;
	}
	public void setdateBAP(String dateBAP) {
		this.dateBAP = dateBAP;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
