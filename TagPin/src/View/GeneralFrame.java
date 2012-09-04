package View;

/**
 * @(#) GeneralFrame: Creates starting page
 * 
 *
 * 
 *
 * @ Can Bayar
 * @version 1.00 
 */

import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeneralFrame extends JFrame implements ActionListener
{	
	static MainPagePanel myMainPage;
	private MySQLFriendship friendshipDB;
	
	public GeneralFrame()
	{
		//Connecting Database
		friendshipDB = new MySQLFriendship();
		
		myMainPage = new MainPagePanel();
		add( myMainPage);
		
		setLocation( 90, 50);
		
		setPreferredSize( new Dimension( 1200, 650));
		pack();
		setVisible( true);
		setFocusable( true);
		setResizable( false);
		
		MainPagePanel.logInButton.addActionListener( this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void closeFrame()
	{
		this.dispose();
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( myMainPage.logInButton) )
		{
			try 
			{
				if( myMainPage.checkLogIn() )
				{
					myMainPage.visibility();
					friendshipDB.checkRequests( myMainPage.userID);
				}
				else
				{
					myMainPage.logInWarning.setText( "Not Found!");
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

}