package ScheduleManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class NameListPanel extends JPanel
{
	private MasterAdapter employeeAdapter;
	private MasterAdapter shedAdapter;

	private MyButtonListener buttonListener;
	private JPanel mainPanel;
	private JPanel displayEmployee;
	private JTable allEmployeeTable;
	private String[] columnNames;
	private DefaultTableModel dtm;
	private JScrollPane allEmployeeScrollPane;

	private JLabel nameTitle;
	private JButton availability;
	private JPanel top;
	private JPanel listMain;

	private JTable analysisToDo;
	private DefaultTableModel forAnalysisToDo;
	private String[] columnNameOfEmpSche;
	private JScrollPane scrollPaneForTasks;
	//----
	
	private JTable empTable;
	private JScrollPane qualScrollPane;
	private String[] columnNameQual;
	private DefaultTableModel dtmQual;
	private JButton removeButton;
	private int selectedRow;

	private EmployeeList employees;

	public NameListPanel()
	{
		shedAdapter = new MasterAdapter("schedules.bin");
		
		buttonListener = new MyButtonListener();
		nameTitle = new JLabel("Name of Employee");
		availability = new JButton();
		availability.addActionListener(buttonListener);
		availability.setEnabled(false);
		top = new JPanel();
		listMain = new JPanel();
		
		columnNameOfEmpSche = new String[1];
		columnNameOfEmpSche[0] = "Tasks to do:";
		forAnalysisToDo = new DefaultTableModel(columnNameOfEmpSche, 0);
		analysisToDo = new JTable(forAnalysisToDo);
		analysisToDo.setEnabled(false);
		analysisToDo.getTableHeader().setReorderingAllowed(false);
		analysisToDo.getTableHeader().setResizingAllowed(false);
		analysisToDo.setPreferredScrollableViewportSize(new Dimension(150, analysisToDo.getRowHeight() * 18));
		scrollPaneForTasks = new JScrollPane(analysisToDo);
		scrollPaneForTasks.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneForTasks.setPreferredSize(new Dimension(250, 210));
	
		scrollPaneForTasks.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));

		
		removeButton = new JButton("Remove employee");
		removeButton.setEnabled(false);

		columnNameQual = new String[1];
		columnNameQual[0] = "Qualifications";

		dtmQual = new DefaultTableModel(columnNameQual, 0);
		empTable = new JTable(dtmQual);

		empTable.setEnabled(true);
		empTable.getTableHeader().setReorderingAllowed(false);
		empTable.getTableHeader().setResizingAllowed(false);
		empTable.setPreferredScrollableViewportSize(new Dimension(750, empTable.getRowHeight() * 18));

		qualScrollPane = new JScrollPane(empTable);
		qualScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		employeeAdapter = new MasterAdapter("employees.bin");


		employees = employeeAdapter.getAllEmployees();

		Object[][] data = new Object[employees.size()][3];
		for (int i = 0; i < employees.size(); i++)
		{
			data[i][0] = employees.get(i).getFirstName();
			data[i][1] = employees.get(i).getLastName();
			data[i][2] = employees.get(i).isAvailable();
		}

		mainPanel = new JPanel();
		displayEmployee = new JPanel();
		columnNames = new String[3];
		columnNames[0] = "First name";
		columnNames[1] = "Last name";
		columnNames[2] = "Availability";
		dtm = new DefaultTableModel(data, columnNames);

		allEmployeeTable = new JTable(dtm);
		allEmployeeTable.setEnabled(true);
		allEmployeeTable.getTableHeader().setReorderingAllowed(false);
		allEmployeeTable.getTableHeader().setResizingAllowed(false);
		allEmployeeTable.setPreferredScrollableViewportSize(new Dimension(750, allEmployeeTable.getRowHeight() * 18));

		allEmployeeScrollPane = new JScrollPane(allEmployeeTable);
		allEmployeeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		top.setPreferredSize(new Dimension(0, 40));
		availability.setPreferredSize(new Dimension(80, 0));

		top.setLayout(new BorderLayout());
		top.add(nameTitle, BorderLayout.CENTER);
		top.add(availability, BorderLayout.EAST);

		listMain.setLayout(new BorderLayout());
		listMain.add(scrollPaneForTasks, BorderLayout.CENTER);
		listMain.add(qualScrollPane, BorderLayout.SOUTH);
		qualScrollPane.setPreferredSize(new Dimension(0, 110));
		

		displayEmployee.setLayout(new BorderLayout());
		displayEmployee.add(top, BorderLayout.NORTH);
		displayEmployee.add(listMain, BorderLayout.CENTER);
		displayEmployee.add(removeButton, BorderLayout.SOUTH);
		removeButton.setPreferredSize(new Dimension(0, 30));

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(allEmployeeScrollPane, BorderLayout.CENTER);
		mainPanel.add(displayEmployee, BorderLayout.EAST);
		displayEmployee.setPreferredSize(new Dimension(250, 400));
		allEmployeeScrollPane.setPreferredSize(new Dimension(750, 395));
		
		
		displayEmployee.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		top.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		listMain.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.GRAY));
		removeButton.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 3, Color.GRAY));
		allEmployeeScrollPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		
		nameTitle.setHorizontalAlignment(JLabel.CENTER);
		nameTitle.setVerticalAlignment(JLabel.CENTER);
		removeButton.addActionListener(buttonListener);
		
		ListSelectionModel model = allEmployeeTable.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (!model.isSelectionEmpty())
				{
					employees = employeeAdapter.getAllEmployees();
					selectedRow = allEmployeeTable.getSelectedRow();
					UpdateNameList();
					nameTitle.setText("   " + employees.get(selectedRow).getFirstName() + " "
							+ employees.get(selectedRow).getLastName());
					updateQualOfEmployee(employees.get(selectedRow));
					if (employees.get(selectedRow).isAvailable())
					{
						availability.setEnabled(true);
						availability.setText("True");
					}
					if (!employees.get(selectedRow).isAvailable())
					{
						availability.setEnabled(true);
						availability.setText("False");
					}
					removeButton.setEnabled(true);
					updateAnalysisToDo((Employee)employees.get(selectedRow));
				}
			}
		});

	
		mainPanel.setPreferredSize(new Dimension(1000, 400));

		add(mainPanel);
	}

	private class MyButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == availability)
			{
				
				EmployeeList employees = employeeAdapter.getAllEmployees();
				if (availability.getText().equals("True"))
				{
					employees.get(selectedRow).setToUnavailable();
					availability.setText("False");
				}
				else
				{
					employees.get(selectedRow).setToAvailable();
					availability.setText("True");
				}
				employeeAdapter.saveEmployeeList(employees);
				UpdateNameList();
			}
			if(e.getSource() == removeButton) 
			{
				EmployeeList list = employeeAdapter.getAllEmployees();
				employeeAdapter.removeEmployee(list.get(selectedRow));
				shedAdapter.removeEmpFromTasks(list.get(selectedRow));
				UpdateNameList();
				removeEmployeeSelection();
			}
		}
	}
	
	private void updateAnalysisToDo(Employee employee)
	{ 
		DecimalFormat df = new DecimalFormat("00");
		
		Schedule schedule = shedAdapter.getScheduleForEmployee(employee);

		Object[][] data = new Object[schedule.size()][1];

		for(int i=0; i<schedule.size(); i++) 
		{
			data[i][0] =  schedule.get(i).getAnalysis() + " " + schedule.get(i).getStartingDate().getDay() +
					"." + schedule.get(i).getStartingDate().getMonth() + "." + schedule.get(i).getStartingDate().getYear() +" "  + 
					df.format(schedule.get(i).getStartingDate().getHour()) + ":" +
					df.format(schedule.get(i).getStartingDate().getMinute())+ " -> " +
					df.format(schedule.get(i).getEndingDate().getHour()) + ":" + df.format(schedule.get(i).getEndingDate().getMinute());
		}
		forAnalysisToDo = new DefaultTableModel(data, columnNameOfEmpSche);
		analysisToDo.setModel(forAnalysisToDo);
	}
	
	private void updateQualOfEmployee(Employee emp)
	{
		Object[][] data = new Object[emp.getQualificationSize()][1];
		for(int i = 0; i<emp.getQualificationSize(); i++)
			data[i][0] = emp.getNameOfQualByIndex(i);
		
		dtmQual = new DefaultTableModel(data, columnNameQual);
		empTable.setModel(dtmQual);	
	}
	
	/**
	 * A method that updates the name list of all employees
	 */
	public void UpdateNameList()
	{
		EmployeeList employees = employeeAdapter.getAllEmployees();

		Object[][] data = new Object[employees.size()][3];

		for (int i = 0; i < employees.size(); i++)
		{
			data[i][0] = employees.get(i).getFirstName();
			data[i][1] = employees.get(i).getLastName();
			data[i][2] = employees.get(i).isAvailable();
		}

		dtm = new DefaultTableModel(data, columnNames);
		allEmployeeTable.setModel(dtm);
	}
	
	/**
	 * A method that removes the selection of employee in name list table
	 */
	public void removeEmployeeSelection() {
		allEmployeeTable.clearSelection();
		removeButton.setEnabled(false);
		availability.setEnabled(false);
		nameTitle.setText("    Name of Employee");
		analysisToDo.setModel(new DefaultTableModel(columnNameOfEmpSche, 0));
		empTable.setModel(new DefaultTableModel(columnNameQual, 0));
	}

}
