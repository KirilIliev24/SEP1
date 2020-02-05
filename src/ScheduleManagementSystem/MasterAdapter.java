package ScheduleManagementSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class which takes care of .bin files
 * 
 * @author Group 4
 * @version 1.0
 * 
 */
public class MasterAdapter
{
	private MyFileIO mfio;
	private String fileName;

	public MasterAdapter(String fileName)
	{
		mfio = new MyFileIO();
		this.fileName = fileName;
	}

	/**
	 * A method that gives all the employees from the binary file.
	 * 
	 * @return An object of EmployeeList which contains all the Employees from the
	 *         binary file
	 */
	public EmployeeList getAllEmployees()
	{
		EmployeeList employees = new EmployeeList();

		try
		{
			employees = (EmployeeList) mfio.readObjectFromFile(fileName);
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error reading file");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Class Not Found");
		}
		return employees;
	}

	/**
	 * A method that saves the EmployeeList to a binary file
	 * 
	 * @param employees the EmployeeList
	 */
	public void saveEmployeeList(EmployeeList employees)
	{
		try
		{
			mfio.writeToFile(fileName, employees);
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error writing to file");
		}
	}

	/**
	 * A method that adds an employee to the EmployeeList(I uses the
	 * getAllEmployees() and the saveEmployees() method).
	 * 
	 * @param employee the employee to be added
	 */
	public void addEmployee(Employee employee)
	{
		EmployeeList list = getAllEmployees();
		list.addEmployee(employee);
		saveEmployeeList(list);
	}

	/**
	 * A method that deletes a certain employee from the EmployeeList.
	 * 
	 * @param employee the employee to be deleted
	 */
	public void removeEmployee(Employee employee)
	{
		EmployeeList employees = getAllEmployees();
		employees.removeEmployee(employee);
		saveEmployeeList(employees);
	}

	/**
	 * A method that removes employee from all tasks
	 * 
	 * @param emp the employee we want to remove
	 */
	public void removeEmpFromTasks(Employee emp)
	{
		Schedule sched = getAllTasks();

		for (int i = 0; i < sched.size(); i++)
			if (sched.get(i).hasEmployee(emp))
				sched.get(i).removeEmployeeFromTask(emp);

		saveSchedule(sched);
	}

	/**
	 * A method that gives all the Employees, which match the required
	 * qualification, as an EmployeeList.
	 * 
	 * @param qualification the qualification which is required
	 * @return the EmployeeList which contains all the Employees which possess the
	 *         required qualification
	 */
	public EmployeeList getEmployeeByQualification(String qualification)
	{
		EmployeeList employees = new EmployeeList();

		try
		{
			EmployeeList result = (EmployeeList) mfio.readObjectFromFile(fileName);
			for (int i = 0; i < result.size(); i++)
			{
				if (result.get(i).isQualifiedFor(qualification))
				{
					employees.addEmployee(result.get(i));
				}
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error reading file");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Class Not Found");
		}

		return employees;

	}

	/**
	 * A method which changes the given employee to new employee at same index and
	 * also updates him in tasks.
	 * 
	 * @param originalEmp the original Employee object
	 * @param newEmp      the new Employee object to which the original one will be
	 *                    changed
	 */
	public void changeEmployeeInto(Employee originalEmp, Employee newEmp)
	{
		EmployeeList employees = getAllEmployees();
		MasterAdapter adapt = new MasterAdapter("schedules.bin");
		Schedule sched = adapt.getAllTasks();
		for (int i = 0; i < employees.size(); i++)
		{
			if (employees.get(i).equals(originalEmp))
			{
				employees.setEmployeeByIndex(i, newEmp);
				break;
			}
		}
		saveEmployeeList(employees);

		for (int i = 0; i < sched.size(); i++)
		{
			ArrayList<Employee> taskEmp = sched.get(i).getAllEmployees();
			for (int j = 0; j < taskEmp.size(); j++)
				if (sched.get(i).getEmployeeByIndex(j).equals(originalEmp))
				{
					sched.get(i).setEmployeeByIndex(j, newEmp);
				}
		}

		adapt.saveSchedule(sched);

	}

	/**
	 * A method which changes the employee's initials.
	 * 
	 * @param initials    the original initials of the employee
	 * @param newInitials the new initials of the employee
	 */
	public void changeEmployeeInitials(String initials, String newInitials)
	{
		EmployeeList employees = getAllEmployees();
		for (int i = 0; i < employees.size(); i++)
		{
			if (employees.get(i).getInitials().equals(initials))
			{
				employees.get(i).setInitials(newInitials);
			}
		}
		saveEmployeeList(employees);
	}

	/**
	 * A method that returns all the qualifications from the binary file.
	 * 
	 * @return An object of QualificationList which contains all the employees
	 */
	public QualificationList getAllQualifications()
	{
		QualificationList qualifications = new QualificationList();

		try
		{
			qualifications = (QualificationList) mfio.readObjectFromFile(fileName);
		} catch (IOException e)
		{
			System.out.println("IO Error reading file");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Class Not Found");
		}
		return qualifications;
	}

	/**
	 * A method that saves the QualificationList to a binary file
	 * 
	 * @param qualifications the qualifications that are to be saved
	 */
	public void saveQualificationList(QualificationList qualifications)
	{
		try
		{
			mfio.writeToFile(fileName, qualifications);
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error writing to file");
		}

	}

	/**
	 * A method that add a qualification to the QualificationList
	 * 
	 * @param qualification the qualification to be added
	 */
	public void addQualification(Qualification qualification)
	{

		QualificationList qualifications = getAllQualifications();
		qualifications.addQualification(qualification);
		saveQualificationList(qualifications);
	}

	/**
	 * A method which removes a Qualification from the QualificationList
	 * 
	 * @param qualification the qualification to be removed
	 */
	public void removeQualification(Qualification qualification)
	{
		QualificationList qualifications = getAllQualifications();
		qualifications.removeQualification(qualification);
		saveQualificationList(qualifications);
	}

	/**
	 * A method that returns all the tasks from the Schedule.
	 * 
	 * @return A Schedule which contains a list of all tasks
	 */
	public Schedule getAllTasks()
	{
		Schedule schedule = new Schedule();

		try
		{
			schedule = (Schedule) mfio.readObjectFromFile(fileName);
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error reading file");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Class Not Found");
		}
		return schedule;

	}

	/**
	 * A method which creates a schedule with tasks that has given employee.
	 * 
	 * @param employee the employee of which schedule will be created
	 * @return a Schedule object with tasks in which the employee is involved
	 */
	public Schedule getScheduleForEmployee(Employee employee)
	{
		Schedule schedule = getAllTasks();
		Schedule employeeSchedule = new Schedule();
		for (int i = 0; i < schedule.size(); i++)
		{
			if (schedule.get(i).hasEmployee(employee))
			{
				employeeSchedule.addTask(schedule.get(i));
			}
		}
		return employeeSchedule;
	}

	/**
	 * A method that saves the Schedule into a binary file.
	 * 
	 * @param schedule the schedule to be saved
	 */
	public void saveSchedule(Schedule schedule)
	{
		try
		{
			mfio.writeToFile(fileName, schedule);
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		} catch (IOException e)
		{
			System.out.println("IO Error writing to file");
		}
	}

	/**
	 * A method that adds a Task to the Schedule
	 * 
	 * @param task the task to be added
	 */
	public void addTaskToSchedule(Task task)
	{
		Schedule schedule = getAllTasks();
		schedule.addTask(task);
		saveSchedule(schedule);
	}

	/**
	 * A method that adds an employee to given task
	 * 
	 * @param task the task to which the employee will be added
	 * @param emp  the employee which will be added
	 */
	public void addEmployeeToTask(Task task, Employee emp)
	{
		Schedule schedule = getAllTasks();
		for (int i = 0; i < schedule.size(); i++)
			if (schedule.get(i).equals(task))
			{
				schedule.get(i).addEmployeeToTask(emp);
			}
		saveSchedule(schedule);
	}

	/**
	 * A method that adds a comment to task and saves it
	 * 
	 * @param task    the task to which the comment will be added to
	 * @param comment the comment which will be added
	 */
	public void addCommentToTask(Task task, String comment)
	{
		Schedule schedule = getAllTasks();
		for (int i = 0; i < schedule.size(); i++)
			if (schedule.get(i).equals(task))
			{
				schedule.get(i).addComment(comment);
			}
		saveSchedule(schedule);
	}

	/**
	 * A method that removes employee from task and saves it
	 * 
	 * @param task the task from which the employee will be removed
	 * @param emp  the employee that will be removed
	 */
	public void removeEmployeeFromTask(Task task, Employee emp)
	{
		Schedule schedule = getAllTasks();
		for (int i = 0; i < schedule.size(); i++)
			if (schedule.get(i).equals(task))
			{
				schedule.get(i).removeEmployeeFromTask(emp);
			}
		saveSchedule(schedule);
	}

	/**
	 * A method that removes a task from the schedule.
	 * 
	 * @param task the task which is to be removed
	 */
	public void removeTask(Task task)
	{
		Schedule schedule = getAllTasks();
		schedule.removeTask(task);
		saveSchedule(schedule);
	}

	/**
	 * A method the returns a Schedule(TaskList) which contains all the tasks which
	 * have an analysis as the one given as an argument.
	 * 
	 * @param analysis the type of analysis that is given as an argument
	 * @return A Schedule(TaskList) which contains all the tasks as the one given as
	 *         an argument
	 */
	public Schedule getTaskFromScheduleByAnalysis(String analysis)
	{
		Schedule schedule = getAllTasks();
		Schedule result = new Schedule();

		for (int i = 0; i < schedule.size(); i++)
		{
			if (schedule.get(i).getAnalysis().equals(analysis))
			{
				result.addTask(schedule.get(i));
			}
		}
		return result;
	}
}
