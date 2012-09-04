package View;

/**
 * @(#) UserPinCreateFrame: Creates user pins on map 
 *  *
 * 
 *
 * @ Semih Akar 
 * @version 1.00 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Model.MySQLUser;

public class UserPinCreateFrame extends JFrame implements ActionListener{
	
	//Properties
	private int userID;
	
	private JPanel showMe;
	private JPanel buttonPanel;
	private TextArea info;
	private JButton userPin;
	private JButton cancelButton;
	private MySQLUser userDB;
	private double x;
	private double y;
	
	//Constructor
	public UserPinCreateFrame( int userID, double x, double y)
	{
		this.userID = userID;
		this.x = x;
		this.y = y;
		buttonPanel = new JPanel();
		userDB = new MySQLUser();
		info = new TextArea("Say something:", 5, 40);
		userPin = new JButton( "Pin me!");
		userPin.addActionListener( this);
		cancelButton = new JButton( "Cancel");
		showMe = new JPanel();
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heightScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 300) / 2, (heightScreen - 200) / 2);
		
		//adding components to UserPinCreateFrame
		buttonPanel.add( userPin);
		buttonPanel.add( cancelButton);
		
		showMe.setLayout( new BorderLayout());
		showMe.add(info, BorderLayout.CENTER);
		showMe.add( buttonPanel, BorderLayout.SOUTH);
		
		add( showMe);
		
		pack();
		setVisible(true);
	}
	public void actionPerformed( ActionEvent e)
	{
		//If the clicked button is userPin, it takes the coordinates from clicked place
		//and sets the information in databes, like coordinate x and y
		if( ((JButton) e.getSource()).equals( userPin) )
		{
			try 
			{
				userDB.setUserCoordinateX( userID, x);
				userDB.setUserCoordinateY( userID, y);
				userDB.setUserAboutMe( userID, info.getText());
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			this.dispose();
		}
		else if( ((JButton) e.getSource()).equals( cancelButton) )
		{
			this.dispose();
		}
	}
}