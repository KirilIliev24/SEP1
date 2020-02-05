package ScheduleManagementSystem;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A class that serves to create tasks
 * @author Group4
 * @version 2.0
 */
public class Task implements Serializable
{
	private Comment comment;
	private Qualification analysis;
	private ArrayList<Employee> employees;
	private DateTime startingDate;
	private DateTime endingDate;

	/**
	 * A 8-argument constructor that takes the starDay, startMonth, startYear,
	 * startHour, startMinute and the end variables which bear the same name. It
	 * initializes the analysis, the ArrayList<Employee>, the ArrayList<Comment> as
	 * well.
	 * 
	 * @param analysis the task type of analysis
	 * @param stMonth  the month the task starts
	 * @param stYear   the year the task starts
	 * @param stHour   the hour the task starts
	 * @param stMinute the minute the task starts
	 * @param enHour   the hour the task ends
	 * @param enMinute the minute the task ends
	 */
	public Task(Qualification analysis, int stDay, int stMonth, int stYear, int stHour, int stMinute, int enHour, int enMinute) {
		comment = new Comment("");
		this.analysis = new Qualification(analysis);
		employees = new ArrayList<Employee>();
		startingDate = new DateTime(stDay, stMonth, stYear, stHour, stMinute);
		endingDate = new DateTime(stDay, stMonth, stYear, enHour, enMinute);
	}

	/**
	 * A method that initializes a comment of type String to class Comment object.
	 * 
	 * @param comment the comment that is set
	 */
	public void addComment(String comment) {
		this.comment = new Comment(comment);
	}

	/**
	 * A method that initializes class Comment object to the Comment object given.
	 * 
	 * @param comment the comment that is set
	 */
	public void addComment(Comment comment) {
		this.comment = comment;
	}
	
	/**
	 * A method that returns the Comment as a String.
	 * 
	 * @return A Comment object as a String
	 */
	public String getComment(){
		return comment.toString();
	}

	/**
	 * A method that returns the startDate, startMonth... startMinute of the task.
	 * 
	 * @return A DateTime object that contains the startDate...startMinute
	 */
	public DateTime getStartingDate() {
		return startingDate;
	}

	/**
	 * A method that sets the startDate, startMonth... startMinute of the task.
	 * 
	 * @param startingDate the startingDate of the task(startDate, startMonth...
	 *                     startMinute)
	 */
	public void setStartingDate(DateTime startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * A method that returns the endDate...endMinute of the task.
	 * 
	 * @return A DateTime object that contains the endDate...endMinute
	 */
	public DateTime getEndingDate() {
		return endingDate;
	}

	/**
	 * A method that sets the endDate...endMinute.
	 * 
	 * @param endingDate the endingDate of the task(endDate...endMinute)
	 */
	public void setEndingDate(DateTime endingDate) {
		this.endingDate = endingDate;
	}

	/**
	 * A method that returns an ArrayList of type Employee which contain all the
	 * employees working on the task.
	 * 
	 * @return An ArrayList of all the employees which are working on the task
	 */
	public ArrayList<Employee> getAllEmployees() {
		return employees;
	}

	/**
	 * A method that adds an employee to the ArrayList<Employee> if the certain
	 * employee is available.
	 * 
	 * @param employee the employee to be added
	 */
	public void addEmployeeToTask(Employee employee) {
		if (employee.isAvailable()) {
			employees.add(employee);

		}
	}
	
	/**
	 * A method that checks if the Task object has a given employee inside it's ArrayList<Employee>.
	 * @param emp the employee we are looking for
	 * @return true if the employee is found in ArrayList and false if it's not found
	 */
	public boolean hasEmployee(Employee emp)
	{	
		for(int i = 0; i<employees.size(); i++) 
			if(emp.equals(employees.get(i)))
				return true;
		
		return false;
	}

	/**
	 * A method that removes an employee from the task.
	 * 
	 * @param employee the employee which is to be removed
	 */
	public void removeEmployeeFromTask(Employee employee) {
		employees.remove(employee);
	}
	
	/**
	 * A method that returns an Employee object from employees ArrayList by position.
	 * @param index the position in ArrayList
	 * @return Employee object from ArrayList at given analysis if the object exists
	 */
	public Employee getEmployeeByIndex(int index)
	{
		if(employees.size()>index)
		return employees.get(index);
		System.out.println("Out of bounds employee by index");
		return null;
	}
	
	/**
	 * A method which replaces Employee object in ArrayList to new given Employee object.
	 * @param index position in ArrayList
	 * @param emp the new Employee object
	 */
	public void setEmployeeByIndex(int index, Employee emp)
	{
		employees.set(index, emp);
	}

	/**
	 * A method that sets the type of the analysis.
	 * 
	 * @param type the type of the analysis
	 */
	public void setAnalysis(String type) {
		analysis.setTypeOfQualification(type);
	}

	/**
	 * A method that returns the type of the analysis.
	 * 
	 * @return the type of the anlysis
	 */
	public String getAnalysis() {
		return analysis.getTypeOfQualification();
	}	
	
	/**
	 * A method that returns the Qualification object of Task class.
	 * @return the Qualification object "analysis"
	 */
	public Qualification getAnalysisObject()
	{
		return analysis;
	}
	
	/**
	 * A method that returns the day on which the task is set
	 * @return the day
	 */
	public int getStDay()
	{
		return startingDate.getDay();
	}
	
	/**
	 * A method that returns the month on which the task is set
	 * @return the month
	 */
	public int getStMonth()
	{
		return startingDate.getMonth();
	}
	
	/**
	 * A method that returns the year on which the task is set
	 * @return the year
	 */
	public int getStYear()
	{
		return startingDate.getYear();
	}

	/**
	 * A method that returns all the information about the task. The method creates
	 * an array of type Employee which has the size of the ArrayList<Employee>, it
	 * goes trough the ArrayList<Employee> and adds the employee's names and
	 * initials(which are working on the task) to the array.
	 * @return A String that contains all the information about the task
	 */
	public String toStringAll() {
		String temp = analysis + "\nComments : " + comment.toString() + "\nStarting date : " + startingDate
				+ "\nEnding date : " + endingDate + "\nEmployees working on task : ";
		for (int i = 0; i < employees.size(); i++)
			temp += employees.get(i).getFirstName() + " " + employees.get(i).getLastName() + " ("
					+ employees.get(i).getInitials() + ") ";
		return temp;
	}
	
	/**
	 * A method that returns the date and time on which the task is happening and analysis name
	 * @return A String that contains the date, time and analysis name of the Task object
	 */
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("00");
		
		String temp = "<html>" + "<b>" + analysis + "</b>" + "<br>" + df.format(startingDate.getHour()) + ":" + df.format(startingDate.getMinute()) + "->" +
				df.format(endingDate.getHour()) + ":" + df.format(endingDate.getMinute()) + "<br>";
		for(int i=0; i<employees.size(); i++)
			temp += employees.get(i).getInitials() + " ";
		return temp;
	}
	
	/**
	 * A method that returns basic information about employees which will work on task
	 * @return A String with information of employees that will be working on the task
	 */
	public String toStringEmployeeName()
	{
		String temp ="";
		for (int i = 0; i < employees.size(); i++) {
			temp+=employees.get(i).getFirstName() + " " + employees.get(i).getLastName() + " ("
					+ employees.get(i).getInitials() + ") ";
			
		}
		return temp;
	}
	
	/**
	   * Checks if the class is equal to other object
	   * @return returns true if two objects are the same, and false if they are not
	   */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Task))
			return false;
		
		Task other = (Task)obj;
		
		return other.getAnalysis().equals(getAnalysis()) &&
				other.startingDate.equals(startingDate) && other.endingDate.equals(endingDate) && employees.equals(other.employees);
	}
}