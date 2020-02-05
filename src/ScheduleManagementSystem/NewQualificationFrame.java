package ScheduleManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class NewQualificationFrame extends JFrame
{

	private MasterAdapter adapter;

	private JPanel mainPanel;
	private JLabel windowTitle;
	private JTextField newName;

	private String[] columnNames;
	private DefaultTableModel dtm;
	private JScrollPane tableScrollPane;
	private JTable qualificationsTable;
	private JButton upload;

	private JPanel top;

	private MyButtonListener listener;
	private QualificationList list;

	public NewQualificationFrame()
	{
		super("New qualification");

		adapter = new MasterAdapter("qualifications.bin");
		list = adapter.getAllQualifications();

		mainPanel = new JPanel();
		windowTitle = new JLabel("Add new qualification");
		newName = new JTextField();

		columnNames = new String[1];
		columnNames[0] = "Qualifications";
		Object[][] data = new Object[list.size()][1];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = list.getQualificationByIndex(i);
		}
		dtm = new DefaultTableModel(data, columnNames);
		upload = new JButton("UPLOAD");

		top = new JPanel();

		qualificationsTable = new JTable(dtm);
		qualificationsTable.setEnabled(false);
		qualificationsTable.getTableHeader().setReorderingAllowed(false);
		qualificationsTable.getTableHeader().setResizingAllowed(false);
		qualificationsTable
				.setPreferredScrollableViewportSize(new Dimension(150, qualificationsTable.getRowHeight() * 18));

		tableScrollPane = new JScrollPane(qualificationsTable);
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableScrollPane.setPreferredSize(new Dimension(150, 150));

		top.setLayout(new BorderLayout());
		top.add(windowTitle, BorderLayout.NORTH);
		top.add(newName, BorderLayout.CENTER);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(top, BorderLayout.NORTH);
		mainPanel.add(tableScrollPane, BorderLayout.CENTER);
		mainPanel.add(upload, BorderLayout.SOUTH);

		top.setPreferredSize(new Dimension(300, 75));
		windowTitle.setPreferredSize(new Dimension(300, 40));
		upload.setPreferredSize(new Dimension(300, 40));

		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.GRAY));
		
		windowTitle.setHorizontalAlignment(JLabel.CENTER);
		windowTitle.setVerticalAlignment(JLabel.CENTER);

		Color titleColor = new Color(215, 215, 215);

		windowTitle.setFont(new Font("Monospace", Font.BOLD, 16));
		windowTitle.setBackground(titleColor);
		windowTitle.setOpaque(true);

		add(mainPanel);
		setVisible(true);
		setSize(200, 500);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(1485, 265);
		listener = new MyButtonListener();
		upload.addActionListener(listener);
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == upload)
			{
				list = adapter.getAllQualifications();
				Qualification temp = new Qualification(newName.getText());

				if (!list.checkExistence(temp))
				{
					list.addQualification(temp);
					adapter.saveQualificationList(list);

				}

				Object[][] data = new Object[list.size()][1];
				for (int i = 0; i < list.size(); i++)
				{
					data[i][0] = list.getQualificationByIndex(i);
				}
				dtm = new DefaultTableModel(data, columnNames);
				qualificationsTable.setModel(dtm);
				newName.setText("");
			}
		}

	}

	public static void main(String[] args)
	{
		new NewQualificationFrame();
	}
}
