package ScheduleManagementSystem;
import java.io.Serializable;
/**
 * A class that serves to create qualifications.
 * @author Group 4
 * @version 1.0
 */
public class Qualification implements Serializable
{
  private String typeOfQualification;
  
  /**
   * A 1-argument constructor that takes the type of qualification.
   * @param typeOfQualification the type of qualification
   */
  public Qualification (String typeOfQualification)
  {
     this.typeOfQualification=typeOfQualification;
  }
  
  /**
   * A 1-argument constructor that takes the Qualification object and sets typeOfQualification to it's typeOfQualification
   * @param qual the qualification object
   */
  public Qualification (Qualification qual)
  {
	  typeOfQualification = qual.getTypeOfQualification();
  }
  
  /**
   * Returns the type of qualification.
   * @return a String which contains the type of qualification
   */
  public String getTypeOfQualification()
  {
	  return typeOfQualification;
  }

  /**
   * Sets the type of qualification.
   * @param typeOfQualification the type of qualification
   */
  public void setTypeOfQualification(String typeOfQualification)
  {
	  this.typeOfQualification = typeOfQualification;
  }

  /**
   * Returns the String version of qualification.
   * @return a String which contains the type of qualification
   */
  public String toString()
  {
	  return   typeOfQualification;
  }
  
  /**
   * Checks if the class is equal to other object
   * @return returns true if two objects are the same, and false if they are not
   */
  public boolean equals(Object obj)
  {
	  if(!(obj instanceof Qualification))
	  {
		  return false;
	  }
	  Qualification temp = (Qualification)obj;
	  return typeOfQualification.equals(temp.typeOfQualification);
  }
}
