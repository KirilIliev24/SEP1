package ScheduleManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SchedulesPanel extends JPanel
{
	private MasterAdapter qualAdapter;
	private MasterAdapter empAdapter;
	private MasterAdapter shedAdapter;

	private MyButtonListener buttonListener;
	private JPanel mainPanel;
	private JPanel newAnalysis;
	private JPanel timetable;
	private JPanel displayAnalysis;
	private JPanel uploadAnalysis;
	private JLabel analysisName;
	private JButton upload;
	private JPanel newAnalysisMain;
	private JComboBox<Qualification> analysisCombo;
	private JLabel dateTitle;
	private JComboBox<String> dateDay;
	private JComboBox<String> dateMonth;
	private JComboBox<String> dateYear;
	private JComboBox<String> startHour;
	private JComboBox<String> startMinute;
	private JComboBox<String> endHour;
	private JComboBox<String> endMinute;
	private JComboBox<Employee> employeeNames;
	private JButton addButton;
	private JLabel start;
	private JLabel end;
	private JPanel dateAdded;
	private JPanel startAdded;
	private JPanel endAdded;
	private JPanel nameAdded;
	private JTable allEmployeeTable;
	private String[] columnNames;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private JScrollPane allEmployeeScrollPane;
	private JPanel dayMonth;
	private DefaultTableModel dtmMain;
	private JTable mainTable;
	private String[] columnNamesMain;
	private JScrollPane mainTableScrollPane;

	private JLabel nameOfAnalysis;
	private JPanel employeeBlock;
	private JPanel commentBlock;
	private JTable taskEmployeeTable;
	private JScrollPane taskEmployeeScrollPane;
	private JPanel employeeActions;
	private JButton removeEmployee;
	private JComboBox<Employee> taskNameAdding;
	private JButton taskEmpAdd;
	private JPanel taskAdding;
	private JPanel commentTop;
	private JLabel comment;
	private JButton saveButton;
	private JTextArea commentText;
	private JScrollPane commentScroll;

	private JLabel startDay;
	private JLabel endDay;
	private JPanel dateInform;
	private JButton weekBack;
	private JButton weekForward;
	private JPanel weekButtons;
	private JPanel bottom;
	private LocalDate now = LocalDate.now();
	private LocalDate dateStart = now.with(DayOfWeek.MONDAY);
	private LocalDate dateEnd = now.with(DayOfWeek.SUNDAY);

	private EmployeeList employeesChosen;
	private Task taskDisplayed;
	
	private int selectedTaskEmployee;

	public SchedulesPanel()
	{
		
		qualAdapter = new MasterAdapter("qualifications.bin");
		empAdapter = new MasterAdapter("employees.bin");
		shedAdapter = new MasterAdapter("schedules.bin");
		employeesChosen = new EmployeeList();

		buttonListener = new MyButtonListener();
		mainPanel = new JPanel();
		newAnalysis = new JPanel();
		timetable = new JPanel();
		displayAnalysis = new JPanel();
		uploadAnalysis = new JPanel();
		analysisName = new JLabel("Add analysis");
		upload = new JButton();
		upload.addActionListener(buttonListener);
		newAnalysisMain = new JPanel();
		
	
		analysisCombo = new JComboBox<Qualification>();
		updateAnalysisForNewTask();

		dateTitle = new JLabel("DATE");
		dateDay = new JComboBox<String>();

		dateMonth = new JComboBox<String>();
		dateYear = new JComboBox<String>();
		startHour = new JComboBox<String>();
		startMinute = new JComboBox<String>();
		endHour = new JComboBox<String>();
		endMinute = new JComboBox<String>();

		employeeNames = new JComboBox<Employee>();
		Qualification tempquallist = (Qualification)analysisCombo.getSelectedItem();
		updateQualifiedEmployees(tempquallist, employeeNames);

		addButton = new JButton("ADD");
		addButton.addActionListener(buttonListener);

		start = new JLabel("START");
		end = new JLabel("END");
		dateAdded = new JPanel();
		startAdded = new JPanel();
		endAdded = new JPanel();
		startAdded = new JPanel();
		endAdded = new JPanel();
		nameAdded = new JPanel();
		dayMonth = new JPanel();
		columnNames = new String[2];
		columnNames[0] = "First name";
		columnNames[1] = "Last name";
		dtm = new DefaultTableModel(columnNames, 0);

		nameOfAnalysis = new JLabel("Name of Analysis");
		employeeBlock = new JPanel();
		commentBlock = new JPanel();
		employeeActions = new JPanel();
		removeEmployee = new JButton("Remove");
		removeEmployee.setEnabled(false);
		removeEmployee.addActionListener(buttonListener);
		
		taskNameAdding = new JComboBox<Employee>();
		taskNameAdding.setEnabled(false);
		taskEmpAdd = new JButton("Add");
		taskEmpAdd.addActionListener(buttonListener);
		taskEmpAdd.setEnabled(false);
		taskAdding = new JPanel();
		commentTop = new JPanel();
		comment = new JLabel("Comment");
		saveButton = new JButton("Save");
		saveButton.addActionListener(buttonListener);
		saveButton.setEnabled(false);
		new JPanel();
		commentText = new JTextArea(0, 0);
		commentScroll = new JScrollPane(commentText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		commentText.setLineWrap(true);
		commentText.setWrapStyleWord(true);

		// TIMETABLE
		columnNamesMain = new String[7];
		columnNamesMain[0] = "Monday";
		columnNamesMain[1] = "Tuesday";
		columnNamesMain[2] = "Wednesday";
		columnNamesMain[3] = "Thursday";
		columnNamesMain[4] = "Friday";
		columnNamesMain[5] = "Saturday";
		columnNamesMain[6] = "Sunday";
		dtmMain = new DefaultTableModel(columnNamesMain, 6);
		mainTable = new JTable(dtmMain);
		mainTable.getTableHeader().setPreferredSize(new Dimension(0, 50));
		mainTable.setRowHeight(52);

		mainTable.setEnabled(true);
		mainTable.getTableHeader().setReorderingAllowed(false);
		mainTable.getTableHeader().setResizingAllowed(false);
		mainTable.setPreferredScrollableViewportSize(new Dimension(150, mainTable.getRowHeight() * 18));

		mainTableScrollPane = new JScrollPane(mainTable);
		mainTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainTableScrollPane.setPreferredSize(new Dimension(700, 400));
		
		mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainTable.setColumnSelectionAllowed(true);
		mainTable.setRowSelectionAllowed(true);
		
		
	
		// TIMETABLE

		startDay = new JLabel();
		endDay = new JLabel();

		startDay.setText(dateStart.getDayOfMonth() + ":" + dateStart.getMonthValue() + ":" + dateStart.getYear());
		endDay.setText(dateEnd.getDayOfMonth() + ":" + dateEnd.getMonthValue() + ":" + dateEnd.getYear());
		dateInform = new JPanel();
		weekBack = new JButton("\u25C4");
		weekBack.addActionListener(buttonListener);
		weekForward = new JButton("\u25BA");
		weekForward.addActionListener(buttonListener);
		weekButtons = new JPanel();
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(0, 29));

		dateInform.setLayout(new GridLayout(1, 2));
		dateInform.add(startDay);
		dateInform.add(endDay);
		startDay.setHorizontalAlignment(JLabel.LEFT);
		startDay.setVerticalAlignment(JLabel.CENTER);
		endDay.setHorizontalAlignment(JLabel.RIGHT);
		endDay.setVerticalAlignment(JLabel.CENTER);

		weekButtons.setLayout(new GridLayout(1, 2));
		weekButtons.add(weekBack);
		weekButtons.add(weekForward);

		bottom.setLayout(new BorderLayout());
		bottom.add(dateInform, BorderLayout.CENTER);
		bottom.add(weekButtons, BorderLayout.EAST);

		timetable.setLayout(new BorderLayout());

		timetable.add(mainTableScrollPane, BorderLayout.CENTER);
		timetable.add(bottom, BorderLayout.SOUTH);

		taskAdding.setLayout(new BorderLayout());
		taskAdding.add(taskNameAdding, BorderLayout.CENTER);
		taskAdding.add(taskEmpAdd, BorderLayout.EAST);
		employeeActions.setLayout(new GridLayout(2, 1));
		employeeActions.add(removeEmployee);
		employeeActions.add(taskAdding);
		dtm2 = new DefaultTableModel(columnNames, 0);

		taskEmployeeTable = new JTable(dtm2);
		taskEmployeeTable.setEnabled(true);
		taskEmployeeTable.getTableHeader().setReorderingAllowed(false);
		taskEmployeeTable.getTableHeader().setResizingAllowed(false);
		taskEmployeeTable.setPreferredScrollableViewportSize(new Dimension(150, taskEmployeeTable.getRowHeight() * 18));

		taskEmployeeScrollPane = new JScrollPane(taskEmployeeTable);
		taskEmployeeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskEmployeeScrollPane.setPreferredSize(new Dimension(150, 150));

		commentTop.setLayout(new BorderLayout());
		commentTop.add(comment, BorderLayout.CENTER);
		commentTop.add(saveButton, BorderLayout.EAST);
		comment.setHorizontalAlignment(JLabel.CENTER);
		comment.setVerticalAlignment(JLabel.CENTER);

		commentBlock.setLayout(new BorderLayout());
		commentBlock.add(commentTop, BorderLayout.NORTH);
		commentBlock.add(commentScroll, BorderLayout.CENTER);

		employeeBlock.setLayout(new BorderLayout());
		employeeBlock.add(taskEmployeeScrollPane, BorderLayout.CENTER);
		employeeBlock.add(employeeActions, BorderLayout.SOUTH);

		nameOfAnalysis.setPreferredSize(new Dimension(150, 40));
		nameOfAnalysis.setHorizontalAlignment(JLabel.CENTER);
		nameOfAnalysis.setVerticalAlignment(JLabel.CENTER);
		commentBlock.setPreferredSize(new Dimension(150, 120));
		taskEmpAdd.setPreferredSize(new Dimension(30, 0));
		taskEmpAdd.setMargin(new Insets(0, 0, 0, 0));
		saveButton.setPreferredSize(new Dimension(50, 25));
		saveButton.setMargin(new Insets(0, 0, 0, 0));

		displayAnalysis.setLayout(new BorderLayout());
		displayAnalysis.add(nameOfAnalysis, BorderLayout.NORTH);
		displayAnalysis.add(employeeBlock, BorderLayout.CENTER);
		displayAnalysis.add(commentBlock, BorderLayout.SOUTH);

		for (int i = 0; i < 31; i++)
		{
			String temp = Integer.toString(i + 1);
			dateDay.addItem(temp);
		}
		for (int i = 0; i < 12; i++)
		{
			String temp = Integer.toString(i + 1);
			dateMonth.addItem(temp);
		}
		for (int i = 2017; i < 2050; i++)
		{
			String temp = Integer.toString(i + 1);
			dateYear.addItem(temp);
		}
		for (int i = -1; i < 24; i++)
		{
			String temp = Integer.toString(i + 1);
			if (i < 9)
			{
				startHour.addItem("0" + temp);
			} else
			{
				startHour.addItem(temp);
			}
		}
		for (int i = -1; i < 59; i++)
		{
			String temp = Integer.toString(i + 1);
			if (i < 9)
			{
				startMinute.addItem("0" + temp);
			} else
			{
				startMinute.addItem(temp);
			}
		}
		for (int i = -1; i < 24; i++)
		{
			String temp = Integer.toString(i + 1);
			if (i < 9)
			{
				endHour.addItem("0" + temp);
			} else
			{
				endHour.addItem(temp);
			}
		}
		for (int i = -1; i < 59; i++)
		{
			String temp = Integer.toString(i + 1);
			if (i < 9)
			{
				endMinute.addItem("0" + temp);
			} else
			{
				endMinute.addItem(temp);
			}
		}

		allEmployeeTable = new JTable(dtm);
		allEmployeeTable.setEnabled(false);
		allEmployeeTable.getTableHeader().setReorderingAllowed(false);
		allEmployeeTable.getTableHeader().setResizingAllowed(false);
		allEmployeeTable.setPreferredScrollableViewportSize(new Dimension(150, allEmployeeTable.getRowHeight() * 18));

		allEmployeeScrollPane = new JScrollPane(allEmployeeTable);
		allEmployeeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		newAnalysis.setPreferredSize(new Dimension(150, 400));
		displayAnalysis.setPreferredSize(new Dimension(150, 400));

		analysisName.setHorizontalAlignment(JLabel.CENTER);
		analysisName.setVerticalAlignment(JLabel.CENTER);
		uploadAnalysis.setLayout(new BorderLayout());
		uploadAnalysis.add(analysisName, BorderLayout.CENTER);
		uploadAnalysis.add(upload, BorderLayout.EAST);
		upload.setPreferredSize(new Dimension(40, 40));
		uploadAnalysis.setPreferredSize(new Dimension(150, 40));

		allEmployeeScrollPane.setPreferredSize(new Dimension(150, 150));

		newAnalysis.setLayout(new BorderLayout());
		newAnalysis.add(uploadAnalysis, BorderLayout.NORTH);
		newAnalysis.add(newAnalysisMain, BorderLayout.CENTER);
		newAnalysis.add(allEmployeeScrollPane, BorderLayout.SOUTH);

		dayMonth.setLayout(new GridLayout(1, 2));
		dayMonth.add(dateDay);
		dayMonth.add(dateMonth);
		dateAdded.setLayout(new BorderLayout());
		dateAdded.add(dayMonth, BorderLayout.CENTER);
		dateAdded.add(dateYear, BorderLayout.EAST);
		dateYear.setPreferredSize(new Dimension(60, 0));
		startAdded.setLayout(new GridLayout(1, 3));
		startAdded.add(start);
		startAdded.add(startHour);
		startAdded.add(startMinute);
		endAdded.setLayout(new GridLayout(1, 3));
		endAdded.add(end);
		endAdded.add(endHour);
		endAdded.add(endMinute);
		nameAdded.setLayout(new BorderLayout());
		nameAdded.add(employeeNames, BorderLayout.CENTER);
		nameAdded.add(addButton, BorderLayout.EAST);
		addButton.setPreferredSize(new Dimension(50, 0));
		start.setHorizontalAlignment(JLabel.CENTER);
		start.setVerticalAlignment(JLabel.CENTER);
		end.setHorizontalAlignment(JLabel.CENTER);
		end.setVerticalAlignment(JLabel.CENTER);

		Font titlesFont = new Font("Monospace", Font.PLAIN, 13);
		addButton.setMargin(new Insets(0, 0, 0, 0));
		addButton.setFont(titlesFont);
		upload.setMargin(new Insets(0, 0, 2, 0));
		Font uploadFont = new Font("Monospace", Font.PLAIN, 20);
		upload.setFont(uploadFont);
		upload.setText("\u25B2");
		Color uploadColor = new Color(50, 150, 50);
		upload.setForeground(uploadColor);

		dateTitle.setHorizontalAlignment(JLabel.CENTER);
		dateTitle.setVerticalAlignment(JLabel.CENTER);
		newAnalysisMain.setLayout(new GridLayout(6, 1));
		newAnalysisMain.add(analysisCombo);
		newAnalysisMain.add(dateTitle);
		newAnalysisMain.add(dateAdded);
		newAnalysisMain.add(startAdded);
		newAnalysisMain.add(endAdded);
		newAnalysisMain.add(nameAdded);

		dateDay.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		dateMonth.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		dateYear.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		startHour.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		startMinute.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		endHour.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		endMinute.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		endAdded.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		startAdded.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		nameAdded.setBorder(BorderFactory.createMatteBorder(6, 0, 0, 0, Color.GRAY));

		nameAdded.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY));
		dateTitle.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY));
		start.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		end.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		nameOfAnalysis.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		analysisName.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		commentBlock.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY));
		newAnalysis.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));
		bottom.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY));
		displayAnalysis.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.GRAY));
		comment.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
		mainPanel.setBorder(new LineBorder(Color.GRAY, 3));
		mainPanel.setPreferredSize(new Dimension(1000, 400));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(newAnalysis, BorderLayout.WEST);
		mainPanel.add(timetable, BorderLayout.CENTER);
		mainPanel.add(displayAnalysis, BorderLayout.EAST);

		add(mainPanel);
		startDay.setText(dateStart.getDayOfMonth() + ":" + dateStart.getMonthValue() + ":" + dateStart.getYear());
		endDay.setText(dateEnd.getDayOfMonth() + ":" + dateEnd.getMonthValue() + ":" + dateEnd.getYear());
		updateScheduleContent();
		
		ListSelectionModel model = taskEmployeeTable.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (!model.isSelectionEmpty())
				{
					selectedTaskEmployee = taskEmployeeTable.getSelectedRow();
					removeEmployee.setText("Remove " + taskDisplayed.getEmployeeByIndex(selectedTaskEmployee).getInitials());
					removeEmployee.setEnabled(true);
				}
			}
		});
		mainTable.setFocusable(false);
		mainTable.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 1) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      if(mainTable.getValueAt(row, column) != null) {
			    	  	updateScheduleContent();
						taskDisplayed = (Task)mainTable.getValueAt(row, column);
						updateInfoOfTask(taskDisplayed);
						removeEmployee.setText("Remove task");
						removeEmployee.setEnabled(true);
						saveButton.setEnabled(true);
						mainTable.changeSelection(row, column, true, false);
			      }
			      else {
			    	  removeTaskSelection();
			      }
			    }
			  }
			});
		analysisCombo.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 1) {
				    	updateAnalysisForNewTask();
				    }
				  }
				});
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == removeEmployee)
			{
				if(removeEmployee.getText().equals("Remove task"))
				{
					shedAdapter.removeTask(taskDisplayed);
					updateScheduleContent();
					removeTaskSelection();
				}
				else if(removeEmployee.getText().equals("Remove " + taskDisplayed.getEmployeeByIndex(selectedTaskEmployee).getInitials()))
				{
					shedAdapter.removeEmployeeFromTask(taskDisplayed, taskDisplayed.getEmployeeByIndex(selectedTaskEmployee));
					taskDisplayed.removeEmployeeFromTask(taskDisplayed.getEmployeeByIndex(selectedTaskEmployee));
					updateScheduleContent();
					updateInfoOfTask(taskDisplayed);
					removeEmployee.setText("Remove task");
					removeEmployee.setEnabled(true);
					saveButton.setEnabled(false);
			    	updateQualifiedEmployees(taskDisplayed.getAnalysisObject(), taskDisplayed, taskNameAdding, saveButton);
					
				}
			}
			if(e.getSource() == taskEmpAdd) 
			{
				if(taskDisplayed != null)
				{
					shedAdapter.addEmployeeToTask(taskDisplayed, (Employee)taskNameAdding.getSelectedItem());
					updateScheduleContent();
					taskDisplayed.addEmployeeToTask((Employee)taskNameAdding.getSelectedItem());
					updateInfoOfTask(taskDisplayed);
				}
			}
			
			if(e.getSource() == saveButton)
			{
				if(taskDisplayed != null)
				{
					shedAdapter.addCommentToTask(taskDisplayed, commentText.getText());
					taskDisplayed.addComment(commentText.getText());
					updateScheduleContent();
					updateInfoOfTask(taskDisplayed);
				}
			}
			if (e.getSource() == upload)
			{
				Task task = new Task((Qualification) analysisCombo.getSelectedItem(), dateDay.getSelectedIndex() + 1,
						dateMonth.getSelectedIndex() + 1, dateYear.getSelectedIndex() + 2018,
						startHour.getSelectedIndex(), startMinute.getSelectedIndex(), endHour.getSelectedIndex(),
						endMinute.getSelectedIndex());
				for (int i = 0; i < employeesChosen.size(); i++)
				{
					task.addEmployeeToTask(employeesChosen.get(i));
				}
				shedAdapter.addTaskToSchedule(task);
				employeesChosen.removeAllEmployees();
				updateEmployeesAdded();
				updateScheduleContent();
			}
			if (e.getSource() == addButton)
			{
				employeesChosen.addEmployee((Employee) employeeNames.getSelectedItem());
				updateEmployeesAdded();
			}
			if (e.getSource() == analysisCombo)
			{
				Qualification temp = (Qualification) analysisCombo.getSelectedItem();
				updateQualifiedEmployees(temp, employeeNames);
				employeesChosen.removeAllEmployees();
				updateEmployeesAdded();
			}
			if (e.getSource() == weekForward)
			{
				dateStart = dateStart.plusWeeks(1);
				dateEnd = dateEnd.plusWeeks(1);
				startDay.setText(
						dateStart.getDayOfMonth() + ":" + dateStart.getMonthValue() + ":" + dateStart.getYear());
				endDay.setText(dateEnd.getDayOfMonth() + ":" + dateEnd.getMonthValue() + ":" + dateEnd.getYear());
				updateScheduleContent();
			}
			if (e.getSource() == weekBack)
			{
				dateStart = dateStart.plusWeeks(-1);
				dateEnd = dateEnd.plusWeeks(-1);

				startDay.setText(
						dateStart.getDayOfMonth() + ":" + dateStart.getMonthValue() + ":" + dateStart.getYear());
				endDay.setText(dateEnd.getDayOfMonth() + ":" + dateEnd.getMonthValue() + ":" + dateEnd.getYear());
				updateScheduleContent();
			}
		}
	}
	
	/**
	 * A method that removes selection from mainTable
	 */
	public void removeTaskSelection()
	{
		taskDisplayed = null;
  	  nameOfAnalysis.setText("Name of Analysis");
  	  taskEmployeeTable.setModel(new DefaultTableModel(columnNames,0));
  	  taskNameAdding.removeAllItems();
  	  commentText.setText("");
  	  removeEmployee.setText("Remove");
  	  removeEmployee.setEnabled(false);
  	  saveButton.setEnabled(false);
  	  taskEmpAdd.setEnabled(false);
  	  taskNameAdding.setEnabled(false);
	}
	
	/**
	 * A method that updates analysisCombo with possible qualifications
	 */
	public void updateAnalysisForNewTask() 
	{
		if(analysisCombo.getActionListeners() != null)
		analysisCombo.removeActionListener(buttonListener);
		analysisCombo.removeAllItems();
		QualificationList forComboBoxOfAllTasks = qualAdapter.getAllQualifications();
		for (int i = 0; i < forComboBoxOfAllTasks.size(); i++) 
		analysisCombo.addItem((Qualification)forComboBoxOfAllTasks.getQualificationByIndex(i));
		analysisCombo.addActionListener(buttonListener);
	}
	
	private void updateInfoOfTask(Task tsk)
	{
		nameOfAnalysis.setText(tsk.getAnalysis());
		ArrayList<Employee> empList = tsk.getAllEmployees();
				
		Object[][] data = new Object[empList.size()][2];
		for(int i = 0; i < empList.size(); i++) {
			data[i][0] = empList.get(i).getFirstName();
			data[i][1] = empList.get(i).getLastName();
		}
		
		dtm2 = new DefaultTableModel(data, columnNames);
		taskEmployeeTable.setModel(dtm2);
		updateQualifiedEmployees(tsk.getAnalysisObject(), tsk, taskNameAdding, taskEmpAdd);
		commentText.setText(tsk.getComment());

	}

	/**
	 * A method that updates the mainTable content with all Tasks
	 */
	public void updateScheduleContent()
	{		
		Schedule schedule = shedAdapter.getAllTasks();
		Schedule contentSchedule = new Schedule();
		int stDay;
		int stMonth;
		int stYear;
		LocalDate dateHype = dateStart;
		int counter = 6;
		int[] track = new int[7];
		for (int i = 0; i < 7; i++)
		{
			stDay = dateHype.getDayOfMonth();
			stMonth = dateHype.getMonthValue();
			stYear = dateHype.getYear();
			for (int j = 0; j < schedule.size(); j++)
				if (schedule.get(j).getStDay() == stDay && schedule.get(j).getStMonth() == stMonth
						&& schedule.get(j).getStYear() == stYear)
				{
					contentSchedule.addTask(schedule.get(j));
					track[i]++;
					
				}
			if(track[i]>counter)
			{
				counter=track[i];
			}
			dateHype = dateHype.plusDays(1);
		}

		Object[][] data = new Object[counter][7];
		int tracker = 0;
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < track[i]; j++)
			{
				data[j][i] = contentSchedule.get(tracker);
				tracker++;
			}
		}
		dtmMain = new DefaultTableModel(data, columnNamesMain);
		mainTable.setModel(dtmMain);
	}

	private void updateEmployeesAdded()
	{
		if (employeesChosen.size() > 0)
		{
			Object[][] data = new Object[employeesChosen.size()][2];
			for (int i = 0; i < employeesChosen.size(); i++)
			{
				data[i][0] = employeesChosen.get(i).getFirstName();
				data[i][1] = employeesChosen.get(i).getLastName();
			}
			dtm = new DefaultTableModel(data, columnNames);
			allEmployeeTable.setModel(dtm);
		} else
		{
			dtm = new DefaultTableModel(columnNames, 0);
			allEmployeeTable.setModel(dtm);
		}
	}
	
	/**
	 * A method that updates the JComboBox<Employee> employeeNames with qualified for the task employees
	 */
	public void updateQualifiedEmployees()
	{
		employeeNames.removeAllItems();
		EmployeeList employees = empAdapter.getAllEmployees();
		Qualification temp = (Qualification)analysisCombo.getSelectedItem();
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).isQualifiedFor(temp.getTypeOfQualification()) && employees.get(i).isAvailable())
				employeeNames.addItem(employees.get(i));
	}
	
	private void updateQualifiedEmployees(Qualification qual, JComboBox<Employee> comboForEmployee2)
	{
		
		comboForEmployee2.removeAllItems();
		EmployeeList employees = empAdapter.getAllEmployees();
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).isQualifiedFor(qual.getTypeOfQualification()) && employees.get(i).isAvailable())
				comboForEmployee2.addItem(employees.get(i));
	}
	
	private void updateQualifiedEmployees(Qualification qual, Task task, JComboBox<Employee> comboForEmployee, JButton button)
	{
		button.setEnabled(true);
		comboForEmployee.setEnabled(true);
		comboForEmployee.removeAllItems();
		EmployeeList employees = empAdapter.getAllEmployees();
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).isQualifiedFor(qual.getTypeOfQualification()) && employees.get(i).isAvailable() && !(task.hasEmployee(employees.get(i))))
				comboForEmployee.addItem(employees.get(i));
		if(comboForEmployee.getSelectedItem() == null) {
			button.setEnabled(false);
			comboForEmployee.setEnabled(false);
		}
	}
}
