package ScheduleManagementSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Schedule_Management_System_GUI extends JFrame
{
	private NameListPanel nameListPanel;
	private SchedulesPanel schedulesPanel;
	private JTabbedPane tabPane;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuBar menuBar;
	private JMenuItem employeeEditMenuItem;
	private JMenu newFileMenuItem;
	private JMenuItem newEmployee;
	private JMenuItem newTask;
	private JMenuItem newQualificationType;
	private JMenuItem quitFileMenuItem;
	private MyTabListener tabListener;
	private MyButtonListener listener;
	
	public Schedule_Management_System_GUI()
	{
		super("Schedule_Management_System_GUI");
		
		
		nameListPanel = new NameListPanel();
		schedulesPanel = new SchedulesPanel();
		tabPane = new JTabbedPane();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		menuBar = new JMenuBar();
		tabListener = new MyTabListener();
		tabPane.addChangeListener(tabListener);
		listener = new MyButtonListener();
		
		employeeEditMenuItem = new JMenuItem("Edit employee");
		employeeEditMenuItem.addActionListener(listener);
		newFileMenuItem = new JMenu("New");
		newFileMenuItem.addActionListener(listener);
		quitFileMenuItem = new JMenuItem("Quit");
		quitFileMenuItem.addActionListener(listener);
		newEmployee = new JMenuItem("New Employee");
		newEmployee.addActionListener(listener);
		newTask = new JMenuItem("New Task");
		newTask.addActionListener(listener);
		newQualificationType = new JMenuItem("New Qualification");
		newQualificationType.addActionListener(listener);
		
		tabPane.addTab("Schedules", schedulesPanel);
		tabPane.addTab("Name list", nameListPanel);

		newFileMenuItem.add(newEmployee);
		newFileMenuItem.add(newQualificationType);
		
		editMenu.add(employeeEditMenuItem);
		fileMenu.add(newFileMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(quitFileMenuItem);
		
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		setJMenuBar(menuBar);
		
		add(tabPane);
		setSize(1050, 500);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		   if(e.getSource() == quitFileMenuItem)
         {
            System.exit(1);
         }
		   if(e.getSource() == newEmployee)
		   {
		      new EmployeeFrame();
		   }
		   if(e.getSource() == newQualificationType)
		   {
		      new NewQualificationFrame();
		   }
		   
		   if(e.getSource() == employeeEditMenuItem)
		   {
		      new EditEmployeeFrame();
		      
		   }
		}
	}

	private class MyTabListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			if(((JTabbedPane)e.getSource()).getSelectedIndex()==0)
			{
				schedulesPanel.updateAnalysisForNewTask();
				schedulesPanel.updateScheduleContent();
				schedulesPanel.removeTaskSelection();
				schedulesPanel.updateQualifiedEmployees();
			}
 
			if(((JTabbedPane)e.getSource()).getSelectedIndex()==1)
			{
				nameListPanel.UpdateNameList();
				nameListPanel.removeEmployeeSelection();
			}
		}
	}

	public static void main(String[] args)
	{
		Schedule_Management_System_GUI SmsGUI = new Schedule_Management_System_GUI();
	}
	
}
