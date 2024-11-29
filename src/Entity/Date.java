/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

import java.text.DecimalFormat;
import java.time.LocalDate;

//This is a utility class to check dates and add dates
public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int d, int m, int y)
	{
		day = d;
		month = m;
		year = y;
	}
	
	public boolean is_before_CurrentDate() {
		LocalDate todaysDate = LocalDate.now();
		if (year > todaysDate.getYear()) {
			return true;
		}
		if (year < todaysDate.getYear()) {
			return false;
		}
		if (month > todaysDate.getMonthValue()) {
			return true;
		}
		if (month < todaysDate.getMonthValue()) {
			return false;
		}
		if (day > todaysDate.getDayOfMonth()) {
			return true;
		}
		if (day < todaysDate.getDayOfMonth()) {
			return false;
		}
		return true;
	}

	public void setDate(int d, int m, int y) {
		day = d;
		month = m;
		year = y;
	}

	// Getters and setters
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("00");
		String dayFormat = formatter.format(day);
		String monthFormat = formatter.format(month);
		String yearFormat = formatter.format(year);
		return (dayFormat + "/" + monthFormat + "/" + yearFormat);
	}

}
