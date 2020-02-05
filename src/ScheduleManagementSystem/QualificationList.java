package ScheduleManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of all Qualification objects.
 * 
 * @author Group 4
 * @version 1.0
 */
public class QualificationList implements Serializable {
	private ArrayList<Qualification> qualifications;

	/**
	 * No-argument constructor initializing the QualificationList.
	 */
	public QualificationList() {
		qualifications = new ArrayList<Qualification>();
	}

	/**
	 * Adds qualification to the list.
	 * 
	 * @param qualification the qualification to add to the list
	 */
	public void addQualification(Qualification qualification) {
		qualifications.add(qualification);
	}

	/**
	 * Returns the qualification thats is equal to the type.
	 * 
	 * @param type the type qualification that we are looking for
	 * @return the qualification which is equal to the type/returns null if nothing
	 *         is equal to the type
	 */
	public Qualification getQualificationByType(String type) {
		for (int i = 0; i < qualifications.size(); i++) {
			if (qualifications.get(i).getTypeOfQualification().equals(type)) {
				return qualifications.get(i);
			}
		}
		return null;
	}
	
	/**
	 * A method that returns the Qualification object at given index.
	 * @param index the index
	 * @return Qualification object from ArrayList at given index
	 */
	public Qualification getQualificationByIndex(int index) {

		return qualifications.get(index);
	}

	/**
	 * Removes qualification from the list.
	 * 
	 * @param type the type you remove from the list
	 */
	public void removeQualification(String type) {
		for (int i = 0; i < qualifications.size(); i++) {
			if (qualifications.get(i).getTypeOfQualification().equals(type)) {
				qualifications.remove(i);
			}
		}
	}

	/**
	 * A method that removes the qualification from the list
	 * 
	 * @param qualification an object of Qualification which is to be removed
	 */
	public void removeQualification(Qualification qualification) {
		qualifications.remove(qualification);
	}

	/**
	 * Returns the number of types in the list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return qualifications.size();
	}

	/**
	 * A method that checks the existence of Qualification object in qualifications ArrayList
	 * @param qualification the qualification
	 * @return true if Qualification object exists in ArrayList and false otherwise
	 */
	public boolean checkExistence(Qualification qualification) {
		return qualifications.contains(qualification);
	}
	
	/**
	 * Returns all qualifications as a String object.
	 * 
	 * @return a String which contains all the qualifications
	 */
	public String toString() {
		String returnStr = " ";

		for (int i = 0; i < qualifications.size(); i++) {
			Qualification temp = qualifications.get(i);

			returnStr += temp + " ";
		}
		return returnStr;
	}

}
