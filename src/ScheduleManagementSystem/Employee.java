package ScheduleManagementSystem;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class that serves to create Employees.
 * 
 * @author Group 4
 * @version 1.1
 */
public class Employee implements Serializable {
	private String firstName;
	private String lastName;
	private String initials;
	private boolean isAvailable;
	private ArrayList<Qualification> qualificationList;

	/**
	 * A 4-argument constructor that takes the first name, last name, initials, availability and initializes qualificationList.
	 * 
	 * @param firstName         the first name of the employee
	 * @param lastName          the last name of the employee
	 * @param initials          the initials of the employee
	 * @param isAvailable       the availability of the employee
	 */
	public Employee(String firstName, String lastName, String initial, boolean isAvailable) {

		qualificationList = new ArrayList<Qualification>();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setInitials(initial);
		this.isAvailable = isAvailable;
	}

	/**
	 * A method that returns the first name of the employee.
	 * 
	 * @return a String with the firstName of the employee
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * A method that sets the first name of the employee.
	 * 
	 * @param firstName the first name of the employee
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * A method that returns the last name of the employee.
	 * 
	 * @return a String with the LastName of the employee
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * A method that sets the last name of the employee.
	 * 
	 * @param lastName the last name of te employee
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * A method that returns the initials of the employee.
	 * 
	 * @return a String with the initials of the employee
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * A method that sets the initials of the employee.
	 * 
	 * @param initials the initials of the employee
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}

	/**
	 * A method that sets the availability to true(available).
	 */
	public void setToAvailable() {
		this.isAvailable = true;
	}

	/**
	 * A method that sets the availability to false(not available).
	 */
	public void setToUnavailable() {
		this.isAvailable = false;
	}

	/**
	 * A method that returns the availability of the employee: true (if he is
	 * available) / false (if he is not).
	 * 
	 * @return a boolean with the value of availability: true/false
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	
	/**
	 * A method that checks if a certain employee is qualified for the given task. 
	 * @param qualification the qualification that has to be checked
	 * @return a boolean value of true/false depending on the statement
	 */
	public boolean isQualifiedFor(String qualification) 
	{
		for (int i = 0; i < qualificationList.size(); i++) {
			if (qualificationList.get(i).getTypeOfQualification().equals(qualification)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A method that ads a type of qualification to the qualification ArrayList 
	 * of the employee if he doesn't have the qualification already.
	 * @param qualification the type of qualification
	 */
	private void addQualification(Qualification qualification)
	{
		if(!isQualifiedFor(qualification.getTypeOfQualification()))
			qualificationList.add(qualification);

	}
	
	/**
	 * A method that returns the number of qualifications employee already has.
	 * @return int of qualificationList size
	 */
	public int getQualificationSize() {
		return qualificationList.size();
	}
	
	/**
	 * A method that adds only Qualification object to employee qualificationList
	 * @param obj the Object which will be checked and added to qualificationList if it's an
	 * object of type (Qualification)
	 */
	public void addQualification(Object obj)
	{
		if(!(obj instanceof Qualification))
			System.out.println("NOT_Qualification_object");
		else {
			Qualification temp = (Qualification)obj;
			addQualification(temp);
		}
	}
	
	/**
	 * A method which returns Qualification object if the name of that object is the same as given
	 * @param qualification the name of the qualification
	 * @return	returns Employee Qualification object with the same name as given or null if employee doesn't have that qualification
	 */
	public Qualification getQualification(String qualification)
	{
		for (int i = 0; i < qualificationList.size(); i++) {
			if (qualificationList.get(i).getTypeOfQualification().equals(qualification)) {
				return qualificationList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Returns the name of Qualification from employees qualificationList by given index
	 * @param index the index of qualificationList
	 * @return the name of qualification as a String
	 */
	public String getNameOfQualByIndex(int index)
	{
		return qualificationList.get(index).getTypeOfQualification();
	}

	/**
	 * A method that returns the basic information about the employee.
	 * 
	 * @return A String with the information about the employee (first name, last name, initials)
	 */
	public String toString() {
		return firstName + " " + lastName + " (" + initials+")";
	}
	
	
	/**
	 * Checks if the class is equal to other object
	 * @return returns true if two objects are the same, and false if they are not
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Employee))
			return false;
		
		Employee other = (Employee)obj;
		
		return firstName.equals(other.firstName) && lastName.equals(other.lastName) && initials.equals(other.initials) && isAvailable == other.isAvailable && qualificationList.equals(other.qualificationList);
	}

}
