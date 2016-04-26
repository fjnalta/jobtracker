package net.greenbeansit.jobtracker.shared;

public class User {
	private String userID;
	private float hourlyPay;

	public User(String userID, float hourlyPay) {
		this.setUserID(userID);
		this.setHourlyPay(hourlyPay);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public float getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(float hourlyPay) {
		if(hourlyPay < 0)
			throw(new IllegalArgumentException());
		this.hourlyPay = hourlyPay;
	}
	
	
}
