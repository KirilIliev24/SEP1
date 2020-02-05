package ScheduleManagementSystem;

import java.io.Serializable;

/**
 * A class used to save comments to task.
 * 
 * @author Group 4
 * @version 1.0
 */
public class Comment implements Serializable
{
	private String comment;

	/**
	 * String argument constructor setting the value of comment
	 * 
	 * @param comment the String object which the will be set to
	 */
	public Comment(String comment)
	{
		this.comment = comment;
	}
	
	/**
	 * Returns the comment
	 * @return is the class String variable
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Used to change class comment variable
	 * @param comment the argument which will replace class comment variable
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	
	/**
	 * Returns comment variable as a String object
	 * @return String object
	 */
	public String toString()
	{
		return comment;
	}
}
