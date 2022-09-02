package com.tylu.bean;

public class MemBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String userID;
	private String userAccount;
	private String userPassword;
	private String userGender;
	private String userHeight;
	private String userWeight;
	private String photoPath;
	
	public String getUserID() {
		return userID;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserGender() {
		return userGender;
	}
	public String getUserHeight() {
		return userHeight;
	}
	public String getUserWeight() {
		return userWeight;
	}	
	public String getPhotoPath() {
		return photoPath;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public void setUserHeight(String userHeight) {
		this.userHeight = userHeight;
	}
	public void setUserWeight(String userWeight) {
		this.userWeight = userWeight;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	

}