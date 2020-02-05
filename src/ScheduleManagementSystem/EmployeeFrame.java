package ScheduleManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeeFrame extends JFrame
{
	private MasterAdapter adapter;
	private MasterAdapter adapterQual;

	private JPanel mainPanel;
	private JPanel informations;
	private JPanel addingQualif;
	private JPanel nameData;
	private JButton save;
	private JLabel newEmployeeTitle;
	private JLabel firstName;
	private JLabel lastName;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JLabel initials;
	private JTextField initialsText;
	private JPanel nameBox;
	private JPanel textBoxes;
	private JLabel addingQTitle;
	private JComboBox<Qualification> qualifications;
	private JButton addQButton;
	private JPanel addChosenQ;
	private JTable qualificationsTable;
	private JPanel addingQAll;
	private String[] columnNames;
	private DefaultTableModel dtm;
	private JScrollPane tableScrollPane;
	private ArrayList<Qualification> qualificationsforfinal;

	private MyButtonListener listener;

	public EmployeeFrame()
	{
		super("Add employee");

		adapter = new MasterAdapter("employees.bin");
		adapterQual = new MasterAdapter("qualifications.bin");

		mainPanel = new JPanel();
		informations = new JPanel();
		addingQualif = new JPanel();
		save = new JButton("UPLOAD");

		qualificationsforfinal = new ArrayList<>();
		listener = new MyButtonListener();
		save.addActionListener(listener);

		newEmployeeTitle = new JLabel("Add new employee");
		firstName = new JLabel("First name");
		lastName = new JLabel("Last name");
		firstNameText = new JTextField();
		lastNameText = new JTextField();
		initials = new JLabel("Initials");
		initialsText = new JTextField();
		nameBox = new JPanel();
		textBoxes = new JPanel();
		addingQTitle = new JLabel("Adding qualifications");

		QualificationList forComboBox = adapterQual.getAllQualifications();
		qualifications = new JComboBox<>();
		for (int i = 0; i < forComboBox.size(); i++)
			qualifications.addItem(forComboBox.getQualificationByIndex(i));

		addQButton = new JButton("Add");
		addQButton.addActionListener(listener);
		addChosenQ = new JPanel();

		nameData = new JPanel();
		addingQAll = new JPanel();
		columnNames = new String[1];
		columnNames[0] = "Qualifications";
		dtm = new DefaultTableModel(columnNames, 0);

		qualificationsTable = new JTable(dtm);
		qualificationsTable.setEnabled(false);
		qualificationsTable.getTableHeader().setReorderingAllowed(false);
		qualificationsTable.getTableHeader().setResizingAllowed(false);
		qualificationsTable
				.setPreferredScrollableViewportSize(new Dimension(150, qualificationsTable.getRowHeight() * 18));

		tableScrollPane = new JScrollPane(qualificationsTable);
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableScrollPane.setPreferredSize(new Dimension(150, 150));

		nameBox.setLayout(new GridLayout(3, 1));
		nameBox.add(firstName);
		nameBox.add(lastName);
		nameBox.add(initials);
		textBoxes.setLayout(new GridLayout(3, 1));
		textBoxes.add(firstNameText);
		textBoxes.add(lastNameText);
		textBoxes.add(initialsText);
		nameData.setLayout(new BorderLayout());
		nameData.add(nameBox, BorderLayout.WEST);
		nameData.add(textBoxes, BorderLayout.CENTER);
		informations.setLayout(new BorderLayout());
		informations.add(newEmployeeTitle, BorderLayout.NORTH);
		informations.add(nameData, BorderLayout.CENTER);
		addChosenQ.setLayout(new BorderLayout());
		addChosenQ.add(qualifications, BorderLayout.CENTER);
		addChosenQ.add(addQButton, BorderLayout.EAST);
		addingQAll.setLayout(new BorderLayout());
		addingQAll.add(addingQTitle, BorderLayout.NORTH);
		addingQAll.add(addChosenQ, BorderLayout.CENTER);
		addingQualif.setLayout(new BorderLayout());
		addingQualif.add(addingQAll, BorderLayout.NORTH);
		addingQualif.add(tableScrollPane, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(informations, BorderLayout.NORTH);
		mainPanel.add(addingQualif, BorderLayout.CENTER);
		mainPanel.add(save, BorderLayout.SOUTH);

		nameBox.setPreferredSize(new Dimension(100, 0));
		informations.setPreferredSize(new Dimension(500, 150));
		newEmployeeTitle.setPreferredSize(new Dimension(500, 40));

		addingQTitle.setPreferredSize(new Dimension(500, 40));

		newEmployeeTitle.setHorizontalAlignment(JLabel.CENTER);
		newEmployeeTitle.setVerticalAlignment(JLabel.CENTER);
		firstName.setHorizontalAlignment(JLabel.CENTER);
		firstName.setVerticalAlignment(JLabel.CENTER);
		lastName.setHorizontalAlignment(JLabel.CENTER);
		lastName.setVerticalAlignment(JLabel.CENTER);
		initials.setHorizontalAlignment(JLabel.CENTER);
		initials.setVerticalAlignment(JLabel.CENTER);
		addingQTitle.setHorizontalAlignment(JLabel.CENTER);
		addingQTitle.setVerticalAlignment(JLabel.CENTER);
		addingQTitle.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.GRAY));
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.GRAY));

		nameBox.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		lastName.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY));
		initials.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

		
		Color titleColor = new Color(215, 215, 215);
		
		newEmployeeTitle.setFont(new Font("Monospace", Font.BOLD, 16));
		addingQTitle.setFont(new Font("Monospace", Font.BOLD, 14));
		addingQTitle.setBackground(titleColor);
		newEmployeeTitle.setBackground(titleColor);
		newEmployeeTitle.setOpaque(true);
		addingQTitle.setOpaque(true);
		
		

		add(mainPanel);
		setVisible(true);
		setSize(350, 500);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(85, 265);
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			if (e.getSource() == addQButton)
			{
				if (!qualificationsforfinal.contains(qualifications.getSelectedItem()))
				{
					qualificationsforfinal.add((Qualification) qualifications.getSelectedItem());
				}

				Object[][] data = new Object[qualificationsforfinal.size()][1];
				for (int i = 0; i < qualificationsforfinal.size(); i++)
				{
					data[i][0] = qualificationsforfinal.get(i);

				}
				dtm = new DefaultTableModel(data, columnNames);
				qualificationsTable.setModel(dtm);

			}

			if (e.getSource() == save)
			{
				Employee temp = new Employee(firstNameText.getText(), lastNameText.getText(), initialsText.getText(),
						true);

				for (int i = 0; i < qualificationsforfinal.size(); i++)
				{
					temp.addQualification(qualificationsforfinal.get(i));
				}
				qualificationsforfinal.clear();

				Object[][] data = new Object[qualificationsforfinal.size()][1];
				for (int i = 0; i < qualificationsforfinal.size(); i++)
					data[i][0] = qualificationsforfinal.get(i);

				dtm = new DefaultTableModel(data, columnNames);
				qualificationsTable.setModel(dtm);

				adapter.addEmployee(temp);
				firstNameText.setText("");
				lastNameText.setText("");
				initialsText.setText("");

			}
		}
	}

	public static void main(String[] args)
	{
		new EmployeeFrame();
	}

}
