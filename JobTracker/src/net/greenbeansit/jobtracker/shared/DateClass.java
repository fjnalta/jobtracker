package net.greenbeansit.jobtracker.shared;

public class DateClass {

	private int minute;
	private int hour;
	private int day;
	private int month;
	private int year;

	public DateClass(int minute, int hour, int day, int month, int year) {
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public DateClass() {
		this.minute = 0;
		this.hour = 0;
		this.day = 0;
		this.month = 0;
		this.year = 0;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public boolean isBevore(DateClass anotherDate) {
		if (this.year < anotherDate.getYear()) {
			return true;
		} else {
			if (this.month < anotherDate.getMonth()) {
				return true;
			} else {
				if (this.day < anotherDate.getDay()) {
					return true;
				} else {
					if (this.hour < anotherDate.getHour()) {
						return true;
					} else {
						if (this.minute < anotherDate.getMinute()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isAfter(DateClass anotherDate) {
		if (this.year > anotherDate.getYear()) {
			return true;
		} else {
			if (this.month > anotherDate.getMonth()) {
				return true;
			} else {
				if (this.day > anotherDate.getDay()) {
					return true;
				} else {
					if (this.hour > anotherDate.getHour()) {
						return true;
					} else {
						if (this.minute > anotherDate.getMinute()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean equals(DateClass anotherDate) {
		if (this.year == anotherDate.getYear() && this.month == anotherDate.getMonth()
				&& this.day == anotherDate.getDay() && this.hour == anotherDate.getHour()
				&& this.minute == anotherDate.getMinute()) {
			return true;
		}
		return false;
	}
}
