package View;

/**
 * @(#) CenterPanel Class: Creates center panel with map and pins
 *
 * 
 *
 * @ Semih Akar - Melih Ayyildiz - Tufan Turkaslan
 * @version 1.00 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CenterPanel extends JPanel implements ActionListener
{
	//Properties
	private int userID;
	private MapPanel myMap;
	private JPanel controlPanel;
	static boolean userBoolean;
	static boolean eventBoolean;
	static double x;
	static double y;
	private JButton userPin;
	private JButton eventPin;
	private JButton showMe;
	static Icon userPinImage;
	static Icon eventPinImage;

	//Constructor
	public CenterPanel( int userID) throws Exception
	{	
		this.userID = userID;
		
		myMap = new MapPanel( userID);
		
		//Creating the icons for the pin buttons
		userPinImage = new ImageIcon( "ArrowDown.png");
		eventPinImage = new ImageIcon( "Flag.png");
		
		//Creating buttons on the panel
		userPin = new JButton( userPinImage);
		userPin.addActionListener( this);
		userPin.setContentAreaFilled( false);
		userPin.setBorderPainted( false);
		userPin.setToolTipText( "To use the user pin click this button and then double click on the map.");
		eventPin = new JButton( eventPinImage);
		eventPin.addActionListener(this);
		eventPin.setContentAreaFilled( false);
		eventPin.setBorderPainted( false);
		eventPin.setToolTipText( "To use the event pin click this button and then double click on the map.");
		showMe = new JButton( "Show Me!");
		
		//Creating Control Panel
		controlPanel = new JPanel();
		controlPanel.setPreferredSize( new Dimension( 100, 80));
		controlPanel.setLayout( null);
		controlPanel.add( userPin);
		userPin.setBounds( 20, 30, 40, 40);
		controlPanel.add( eventPin);
		eventPin.setBounds( 80, 30, 40, 40);
		controlPanel.add( showMe);
		showMe.setBounds( 160, 35, 90, 25);
		controlPanel.setBorder( BorderFactory.createTitledBorder( "Buttons"));
	
		//Setting the layout of the CenterPanel
		setLayout( new BorderLayout());
		
		//Adding properties to the CenterPanel
		add( controlPanel, BorderLayout.PAGE_START);
		add( myMap, BorderLayout.CENTER);
		
		//Initializing userBoolean and  eventBoolean
		userBoolean = false;
		eventBoolean = false;
		
		//Adding MouseListener to the myMap object
		HandlerClass handler = new HandlerClass();
		myMap.addMouseListener(handler);
	}
	
	//Overwriting actionPerformed Method
	public void actionPerformed( ActionEvent e)
	{
		//If user clicks the userPin button, it chooses the userPin button and disables eventPin by changing the eventBoolean to false
		//and after that user can double click on the map and can add his userPin
		if( e.getSource().equals( userPin))
		{
			userBoolean = true;
			if( eventBoolean )
			{
				eventBoolean = false;
			}
		}
		//If user clicks the eventPin button, it chooses the eventPin button and disables userPin by changing the userBoolean to false
		//and after that user can double click on the map and can add his userPin
		if( e.getSource().equals( eventPin))
		{
			eventBoolean = true;
			if( userBoolean )
			{
				userBoolean = false;
			}
		}
	}

	//Inner class
	private class HandlerClass implements MouseListener
	{
		
		//Overwriting mouseClicked Method
		public void mouseClicked( MouseEvent e)
		{
			//If user double clicks on the map while userBoolean is true ( that means he clicked userPin button)
			//this method gets the coordinate x and y of the double clicked place and creates a new userPin on the map
			//it also adds those coordinates to the database
			if( userBoolean == true && e.getClickCount() == 2)
			{
				x = e.getX();
				y = e.getY();
				userBoolean = false;
				
				//Calculating Difference
				double differenceX = 0;
				double differenceY = 0;
				
				if( MapPanel.zoom == 13 )
				{
					differenceX = 0.000110;
					differenceY = 0.000148;
				}
				else if( MapPanel.zoom == 14 )
				{
					differenceX = 0.000055;
					differenceY = 0.000074;
				}
				else if( MapPanel.zoom == 15 )
				{
					differenceX = 0.0000275;
					differenceY = 0.000037;
				}
				else if( MapPanel.zoom == 16 )
				{
					differenceX = 0.0000137;
					differenceY = 0.0000185;
				}
				else if( MapPanel.zoom == 17 )
				{
					differenceX = 0.0000068;
					differenceY = 0.0000092;
				}
				
				x = MapPanel.coordinateX - ( (347 - x) * differenceX );
				y = MapPanel.coordinateY - ( (y - 273) * differenceY );
				new UserPinCreateFrame( userID, x, y);
			}
			//If user double clicks on the map while eventBoolean is true ( that means he clicked eventPin button)
			//this method gets the coordinate x and y of the double clicked place and creates a new eventPin on the map
			//it also adds those coordinates to the database
			else if( eventBoolean == true && e.getClickCount() == 2)
			{
				x = e.getX();
				y = e.getY();
				eventBoolean = false;
				
				//Calculating Difference
				double differenceX = 0;
				double differenceY = 0;
				
				if( MapPanel.zoom == 13 )
				{
					differenceX = 0.000110;
					differenceY = 0.000148;
				}
				else if( MapPanel.zoom == 14 )
				{
					differenceX = 0.000055;
					differenceY = 0.000074;
				}
				else if( MapPanel.zoom == 15 )
				{
					differenceX = 0.0000275;
					differenceY = 0.000037;
				}
				else if( MapPanel.zoom == 16 )
				{
					differenceX = 0.0000137;
					differenceY = 0.0000185;
				}
				else if( MapPanel.zoom == 17 )
				{
					differenceX = 0.0000068;
					differenceY = 0.0000092;
				}
				
				x = MapPanel.coordinateX - ( (347 - x) * differenceX );
				y = MapPanel.coordinateY - ( (y - 273) * differenceY );
				
				x = MapPanel.coordinateX - ( (347 - x) * 0.000148 );
				y = MapPanel.coordinateY - ( (y - 273) * 0.000110 );
				new EventCreateFrame( userID, x, y);
			}
		}
		
		public void mousePressed( MouseEvent e){}
		public void mouseReleased( MouseEvent e){}
		public void mouseEntered( MouseEvent e){}
		public void mouseExited( MouseEvent e){}
	}
	
}
