package net.greenbeansit.jobtracker.shared;

public class User {
	private int userID;
	private float hourlyPay;

	public User(int userID, float hourlyPay) {
		this.setUserID(userID);
		this.setHourlyPay(hourlyPay);
	}

	//TODO: Exceptions
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public float getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(float hourlyPay) {
		this.hourlyPay = hourlyPay;
	}
	
	
}
