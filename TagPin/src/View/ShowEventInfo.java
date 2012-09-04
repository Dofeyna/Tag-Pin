package View;

/**
 * @(#) ShowEventInfo: Creates a frame that will be appear on when the user clicks on any event pin on the map or 
 * any event on the event list
 *
 * 
 *
 * @ Melih Ayyildiz
 * @version 1.00 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MySQLEvent;
import Model.MySQLUser;

public class ShowEventInfo extends JFrame implements ActionListener {
	
	private JLabel eventName;
	private JLabel eventDate;
	private JLabel attendants;
	private JLabel eventNote;
	
	private JButton accept;
	private JButton ignore;
	private JButton creator;
	
	private JPanel infoPanel;
	private JPanel allPanels;
	private JPanel decisionPanel;
	
	private MySQLEvent eventDB;
	private MySQLUser userDB;
	
	private int eventID;
	
	public ShowEventInfo( int eventID) throws Exception
	{
		this.eventID = eventID;
		
		eventDB = new MySQLEvent();
		userDB = new MySQLUser();
		
		eventDate = new JLabel("Date: " + eventDB.getMonth( eventID) + "/" + eventDB.getDay(eventID) + "/" + eventDB.getYear(eventID));
		eventName = new JLabel( "Name: " + eventDB.getEventName( eventID));
		attendants = new JLabel( "Attendants: " + eventDB.getNumOfAttendants( eventID));
		eventNote = new JLabel( "Note: " + eventDB.getEventNote( eventID));
		
		creator = new JButton( "Creator: " + userDB.getUserName( eventDB.getCreator(eventID)));
		creator.setContentAreaFilled( false);
		creator.setBorderPainted( false);
		creator.addActionListener( this);
		
		//Creating Decision Panel
		decisionPanel = new JPanel();
		
		
		//If user attends the event, accept button will not seem
		if( eventDB.getAttendants( eventID) == null )
		{
			accept = new JButton( "Accept");
			accept.addActionListener( this);
			decisionPanel.add( accept);
		}
		else if( eventDB.getAttendants( eventID).indexOf( "" + LeftPanel.userID) == -1 )
		{
			accept = new JButton( "Accept");
			accept.addActionListener( this);
			decisionPanel.add( accept);
		}
		
		ignore = new JButton( "Ignore");
		ignore.addActionListener( this);
		decisionPanel.add( ignore);
		
		//Creating Info Info Panel
		infoPanel = new JPanel( new GridLayout( 4, 1));
		infoPanel.setPreferredSize( new Dimension( 300, 250) );
		infoPanel.add( eventName);
		infoPanel.add( eventDate);
		infoPanel.add( attendants);
		infoPanel.add( creator);
		
		
		//Creating All Panel
		allPanels = new JPanel();
		allPanels.setLayout( new BorderLayout());
		allPanels.add( infoPanel, BorderLayout.CENTER);
		allPanels.add( decisionPanel, BorderLayout.PAGE_END);
		
		add( allPanels);
		
		setLocation( 500, 300);
		setPreferredSize( new Dimension( 300, 180));
		setVisible(true);
		pack();
		setResizable( false);
	}
	public void actionPerformed( ActionEvent e)
	{
		if( e.getSource().equals(accept) )
		{
			try 
			{
				eventDB.setAttendant( eventID, LeftPanel.userID);
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			
			dispose();
		}
		else if( e.getSource().equals(ignore))
		{
			dispose();
		}
		else if( e.getSource().equals( creator) )
		{
			try 
			{
				new ShowFriendProfile( eventDB.getCreator( eventID), LeftPanel.userID);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
}
