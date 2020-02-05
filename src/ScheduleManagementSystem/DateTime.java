package ScheduleManagementSystem;

import java.io.Serializable;

/**
 * A class used to save a date and time.
 * 
 * @author Group 4
 * @version 1.0
 */
public class DateTime implements Serializable
{
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;

	/**
	 * Five argument constructor sets up 5 variables.
	 * @param day the day
	 * @param month the month
	 * @param year the year
	 * @param hour the hour
	 * @param minute the minute
	 */
	public DateTime(int day, int month, int year, int hour, int minute)
	{
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}
	
	/**
	 * Returns day
	 * @return an int variable "day"
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * Returns month
	 * @return an int variable "month"
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * Returns year
	 * @return an int variable "year"
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Returns hour
	 * @return an int variable "hour"
	 */
	public double getHour()
	{
		return hour;
	}

	/**
	 * Returns minute
	 * @return an int variable "minute"
	 */
	public double getMinute()
	{
		return minute;
	}

	/**
	 * Changes day variable
	 * @param day the day
	 */
	public void setDay(int day)
	{
		this.day = day;
	}

	/**
	 * Changes month variable
	 * @param month the month
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * Changes year variable
	 * @param year the year
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * Changes hour variable
	 * @param hour the hour
	 */
	public void setHour(int hour)
	{
		this.hour = hour;
	}

	/**
	 * Changes minute variable
	 * @param minute the minute
	 */
	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	/**
	 * Returns all variables as a String
	 * @return day, month, year, hour, minute variable as a single String
	 */
	public String toString()
	{
		return day + "/" + month + "/" + year + " " + hour + ":" + minute;
	}
	
	/**
	 * Checks if the class is equal to other object
	 * @return returns true if two objects are the same, and false if they are not
	 */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof DateTime))
			return false;
		
		DateTime other = (DateTime)obj;
		
		return other.year == year && other.month == month && other.day == day && other.hour == hour && other.minute == minute;
	}

}