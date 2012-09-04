package View;

/**
 * @(#) ForgotPasswordFrame: If user push the forgot password button, this frame will be appear
 *
 * 
 *
 * @ Tufan Turkaslan
 * @version 1.00 
 */

import Controller.*;
import Model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ForgotPasswordFrame extends JFrame implements ActionListener {
	
	//Properties
	private JTextField emailTF;
	private JButton sendPassButton;
	private JLabel warningLabel;
	private JPanel forPassPanel;
	
	//Mail
	private SendEmail myMail;
	
	//User Database
	MySQLUser userDB;
	
	public ForgotPasswordFrame()
	{
		//Creating MySQL Connection
		userDB = new MySQLUser();
		
		
		//Creating SendEmail
		myMail = new SendEmail();
		
		
		//Creating Panel
		forPassPanel = new JPanel();
		
		
		//Creating TextField, Label and Button
		emailTF = new JTextField( "");
		sendPassButton = new JButton( "Send Password");
		sendPassButton.addActionListener( this);
		warningLabel = new JLabel( " ");
		
		
		forPassPanel.setLayout( new BorderLayout( 20, 10));
		forPassPanel.add( new JLabel( "Email Adress"), BorderLayout.PAGE_START);
		forPassPanel.add( emailTF, BorderLayout.CENTER);
		forPassPanel.add( sendPassButton, BorderLayout.LINE_END);
		forPassPanel.add( warningLabel, BorderLayout.PAGE_END);
		forPassPanel.setBorder( BorderFactory.createTitledBorder( "Forgot Password?"));
		
		add( forPassPanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heigthScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();

		setLocation( (widthScreen - 350) / 2, (heigthScreen - 130) / 2);
		
		setPreferredSize( new Dimension( 350, 130));
		pack();
		setVisible( true);
		setFocusable( true);
		setResizable( false);
	}
	
	//Overwriting the actionPerformed Method
	public void actionPerformed( ActionEvent e)
	{
		try
		{
			
			if( ((JButton) e.getSource()).equals( sendPassButton) )
			{
				if( userDB.getUserID( emailTF.getText()) != -1 )
				{
					myMail.sendPassword( emailTF.getText());
					warningLabel.setText( "Your Password has been sent.");
				}
				else
				{
					warningLabel.setText( "Invalid Email Adress!");
				}
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

}
