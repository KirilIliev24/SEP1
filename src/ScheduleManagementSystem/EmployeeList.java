package ScheduleManagementSystem;

import java.util.ArrayList;



import java.io.Serializable;

/**
 * 
 * @author Group 4
 * @version 1.1
 *
 */
public class EmployeeList implements Serializable {
	private ArrayList<Employee> employees;

	/**
	 * A no-argument constructor initializing the ArrayLists<Employee>.
	 */
	public EmployeeList() {
		employees = new ArrayList<Employee>();
	}

	/**
	 * A method that adds an employee to the ArrayList of employees if it doesn't contain that employee already.
	 * 
	 * @param employee the employee that is added
	 */
	public void addEmployee(Employee employee) {
		if(!employees.contains(employee))
		employees.add(employee);
	}

	/**
	 * A method that removes an employee from the ArrayList employees.
	 * 
	 * @param employee the employee that is removed
	 */
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}
	
	
	/**
	 * A method that clears the ArrayList employees
	 */
	public void removeAllEmployees() {
		employees.clear();
	}

	/**
	 * A method that returns a list of all the employees
	 * 
	 * @return A Array of type Employee that contains all the employees
	 */
	public Employee[] getAllEmployees() {
		return employees.toArray(new Employee[employees.size()]);
	}

	/**
	 * A method that gives the employee according to his initials or null(if no
	 * employee with such initials is found).
	 * 
	 * @param initials the initials of the employee
	 * @return An object of type employee that has the same initials as the one
	 *         given/returns null if a match is not found
	 */
	public Employee getEmployeeByInitials(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				return employees.get(i);
			}
		}
		return null;
	}

	/**
	 * A method that removes an employee according to his initials
	 * 
	 * @param initials the initials of the employee
	 */
	public void removeEmployeeByInitials(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				employees.remove(i);
			}
		}
	}

	/**
	 * A method that returns the employyee's availability according to his initials
	 * 
	 * @param initials the initials of the employee
	 * @return the availability of the employee true(if he is available)/false(if he
	 *         is not) it also returns a false value if no employee with the given
	 *         initials is found
	 */
	public boolean isEmployeeAvailableByInitials(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				return employees.get(i).isAvailable();
			}
		}
		return false;
	}

	/**
	 * A method that sets the employee to "vacation"(to unavailable, to false) according to the initials given.
	 * @param initials the initials of the employee
	 */
	public void setEmployeeToVacation(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				employees.get(i).setToUnavailable();
			}
		}
	}
	
	/**
	 * A method that sets the employee to "train"(to unavailable, to false) according to the initials given.
	 * @param initials the initials of the employee
	 */
	public void setEmployeeToTrainByInitials(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				employees.get(i).setToUnavailable();
			}
		}
	}
	
	/**
	 * A method that sets the employee to available(to true) according to the initials given.
	 * @param initials the initials of the employee
	 */
	public void setEmployeeToAvailableByInitials(String initials) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getInitials().equals(initials)) {
				employees.get(i).setToAvailable();
			}
		}
	}
	
	/**
	 * A method that changes employee into given employee at given index
	 * @param index represents a place in the ArrayList of employees
	 * @param emp the employee to which the old employee will be changed to
	 */
	public void setEmployeeByIndex(int index, Employee emp)
	{
		employees.set(index, emp);
	}

	/**
	 * A method that returns all the employees.
	 * @return A String with the employees
	 */
	public String toString() {
		String returnStr = " ";

		for (int i = 0; i < employees.size(); i++) {
			Employee temp = employees.get(i);

			returnStr += temp + "\n";
		}
		return returnStr;
	}
	
	/**
	 * A method that returns the number of Employee objects in the ArrayList.
	 * @return an integer which represents the number of Employee objects in the list
	 */
	public int size() {
		return employees.size();
	}
	
	/**
	 * A method that returns an Employee object from the given index
	 * @param index the given index.
	 * @return The object of Employee at index (if one exists)/returns null
	 */
	public Employee get(int index)
	{
		if(index<employees.size())
		{
			return employees.get(index);
		}
		else
		{
			return null;
		}
	}
}
