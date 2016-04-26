package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

import net.greenbeansit.jobtracker.shared.exceptions.InvalidInput;

/**
 * Implements a simple date class which can compare it's objects.
 * 
 * @author Alex & Mike Hukiewitz
 */
public class Date implements Serializable {

	private int day;
	private int month;
	private int year;

	/* -- Constructors -- */
	public Date(int year, int month, int day) {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}

	public Date() {
		this.day = 1;
		this.month = 1;
		this.year = 1970;
	}

	/* -- Logic -- */
	/**
	 * Returns true if 'anotherDate' chronologically occurred before 'this' date.
	 * 
	 * @param anotherDate
	 * @return
	 */
	public boolean isAfter(Date anotherDate) {
		if (this.year > anotherDate.getYear() || this.month > anotherDate.getMonth() || this.day > anotherDate.getDay())
			return true;
		else
			return false;
	}

	/**
	 * Returns true if 'anotherDate' chronologically occurred after 'this' date.
	 * 
	 * @param anotherDate
	 * @return
	 */
	public boolean isBefore(Date anotherDate) {
		if (this.year < anotherDate.getYear() || this.month < anotherDate.getMonth() || this.day > anotherDate.getDay())
			return true;
		else
			return false;
	}

	/**
	 * Returns true if 'anotherDate' equals 'this' date.
	 * 
	 * @param anotherDate
	 * @return
	 */
	public boolean equals(Date anotherDate) {
		if (this.year == anotherDate.getYear() && this.month == anotherDate.getMonth()
				&& this.day == anotherDate.getDay()) {
			return true;
		}
		return false;
	}

	/* -- Getter/Setter -- */
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year < 1970)
			throw(new InvalidInput());
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if(month > 12 || month < 1)
			throw(new InvalidInput());
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if(day > 31 || day < 1)
			throw(new InvalidInput());
		this.day = day;
	}
}
