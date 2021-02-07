package com.example.LMS.model;

public class LoginInfo {
	private String ID;
	private String Password;
	
	public LoginInfo(String userID, String userPW) {
		ID = userID;
		Password = userPW;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}