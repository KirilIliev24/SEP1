package ScheduleManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that stores Task objects
 * @author Group 4
 * @version 1.0
 */
public class Schedule implements Serializable
{
	private ArrayList<Task> tasks;
	
	/**
	 * A no-argument constructor initializing the ArrayList<Task>.
	 */
	public Schedule() {
		tasks = new ArrayList<Task>();
	}
	
	/**
	 * A method that adds a Task to the ArrayList<Task> if it doesn't already have it.
	 * @param task the task to be added
	 */
	public void addTask(Task task)
	{
		if(!tasks.contains(task))
			tasks.add(task);
	}
	
	/**
	 * A method that removes a task from the ArrayList<Task>.
	 * @param task the task to be removed
	 */
	public void removeTask(Task task)
	{
		tasks.remove(task);
	}
	
	/**
	 * A method that returns a task according to the analysis given as an argument. 
	 * @param analysis the analysis given as an argument
	 * @return the task that matches the statement
	 */
	public Task getTaskByAnalysis(String analysis)
	{
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getAnalysis().equals(analysis)) {
				return tasks.get(i);
			}
		}
		return null;
	}
	
	/**
	 * A method that gives all the tasks.
	 * @return An Array which contains all the tasks from the ArrayList<Task>
	 */
	public Task[] getAllTasks()
	{
		return tasks.toArray(new Task[tasks.size()]);
	}
	
	/**
	 * A method that returns the size of the ArrayList<Task>.
	 * @return An integer which displays the size of the ArrayList<Task>(the number of tasks in the ArrayList<Task>
	 */
	public int size()
	{
		return tasks.size();
	}
	
	/**
	 * A method that gives a task at a certain position in the ArrayList<Task>.
	 * @param index the position
	 * @return the task at the position(index)/null there is no task at the position
	 */
	public Task get(int index)
	{
		if(index<tasks.size())
		{
			return tasks.get(index);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * A method that gives all the tasks as a String object
	 * @return A String object with info of all tasks
	 */
	public String toString()
	{
		String temp ="";
		for (int i = 0; i < tasks.size(); i++) {
			temp+=tasks.get(i).toString() + "\n";
		}
		return temp;
	}
}
