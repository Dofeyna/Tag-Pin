package View;

/**
 * @(#) EventCreateFrame: Creates a panel that enables users to create events by 
 * determining time, date and name
 *
 * 
 *
 * @ Tufan Turkaslan 
 * @version 1.00 
 */

import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventCreateFrame extends JFrame implements ActionListener
{
	//Properties
	private int userID;
	private double x;
	private double y;
	private JPanel informationPanel;
	private JPanel timePanel;
	private JPanel datePanel;
	private JLabel nameLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;
	private JLabel noteLabel;
	private JTextField nameTF;
	private JTextField noteTF;
	private JSpinner daySpinner;
	private JSpinner monthSpinner;
	private JSpinner yearSpinner;
	private JSpinner hourSpinner;
	private JSpinner minuteSpinner;
	private SpinnerModel dayModel;
	private SpinnerModel monthModel;
	private SpinnerModel yearModel;
	private SpinnerModel hourModel;
	private SpinnerModel minuteModel;
	private JButton createButton;
	private JButton cancelButton;
	private JPanel buttonPanel;
	private MySQLEvent eventDB;
	
	//Constructor
	public EventCreateFrame( int userID, double x, double y)
	{
		this.userID = userID;
		this.x = x;
		this.y = y;
		
		//Connecting Event DB
		eventDB = new MySQLEvent();
		
		//Frame' s Layout
		setLayout( new BorderLayout());
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heightScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 300) / 2, (heightScreen - 200) / 2);
		
		//Creating informationPanel
		informationPanel = new JPanel();
		informationPanel.setLayout( new GridLayout( 4, 2));
		
		//Creating labels
		nameLabel = new JLabel( "\t Name: ");
		dateLabel = new JLabel( "\t Date: ");
		timeLabel = new JLabel( "\t Time: ");
		noteLabel = new JLabel( "\t Note: ");
		
		//Creating textFields
		nameTF = new JTextField();
		noteTF = new JTextField();
		
		//Creating spinners
		dayModel = new SpinnerNumberModel( 1, 1, 31, 1);
		monthModel = new SpinnerNumberModel( 1, 1, 12, 1);
		yearModel = new SpinnerNumberModel( 2011, 1990, 2015, 1);
		daySpinner = new JSpinner( dayModel);
		monthSpinner = new JSpinner( monthModel);
		yearSpinner = new JSpinner( yearModel);
		hourModel = new SpinnerNumberModel( 00, 00, 23, 1);
		minuteModel = new SpinnerNumberModel( 00, 00, 59, 1);
		hourSpinner = new JSpinner( hourModel);
		minuteSpinner = new JSpinner( minuteModel);
		
		//Creating buttons
		createButton = new JButton( "Create");
		createButton.addActionListener( this);
		cancelButton = new JButton ( "Cancel");
		cancelButton.addActionListener( this);
		
		//Creating panels
		datePanel = new JPanel();
		datePanel.add( daySpinner);
		datePanel.add( monthSpinner);
		datePanel.add( yearSpinner);
		datePanel.setLayout( new FlowLayout());
		timePanel = new JPanel();
		timePanel.add( hourSpinner);
		timePanel.add( minuteSpinner);
		timePanel.setLayout( new FlowLayout());
		buttonPanel = new JPanel();
		buttonPanel.add( createButton);
		buttonPanel.add( cancelButton);
		
		
		//Adding everything to panel
		informationPanel.add( nameLabel);
		informationPanel.add( nameTF);
		informationPanel.add( dateLabel);
		informationPanel.add( datePanel);
		informationPanel.add( timeLabel);
		informationPanel.add( timePanel);
		informationPanel.add( noteLabel);
		informationPanel.add( noteTF);
		
		//Setting Visible
		informationPanel.setVisible( true);
		add( informationPanel);
		add( buttonPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible( true);
		setFocusable( true);
	}
	
	//Overwriting actionPerformed Method
	public void actionPerformed( ActionEvent e)
	{
		//If user enters all the information and clicks the createButton
		//it will create a new database row and enters all the given varibles in it
		if( ((JButton) e.getSource()).equals( createButton) )
		{
			String year = "" + yearModel.getValue();
			try 
			{
				eventDB.createEvent( nameTF.getText(), "" + yearModel.getValue(), "" + monthModel.getValue(),
									 "" + dayModel.getValue(), noteTF.getText(), userID, x, y);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			this.dispose();
		}
		//If user clicks cancelButton it closes the window
		else if( ((JButton) e.getSource()).equals( cancelButton) )
		{
			this.dispose();
		}
	}
	
}
