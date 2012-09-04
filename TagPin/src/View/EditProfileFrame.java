package View;

/**
 * @(#) EditProfileFrame: Generate a frame in order to enable users to change 
 * their password, photo and additional information
 *
 * 
 *
 * @ Semih Akar - Tufan Turkaslan
 * @version 1.00 
 */

import Model.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.*;
import java.awt.event.*;

public class EditProfileFrame extends JFrame implements ActionListener {
	
	//Properties
	private int userID;
	
	//JPanels
	private JPanel editPhotoPanel;
	private JPanel editAccountPanel;
	private JPanel editAdditionalPanel;
	private JPanel photoPanel;
	private JPanel photoURLPanel;
	private JPanel photoLoadPanel;
	private JPanel leftAdditionalPanel;
	private JPanel rightAdditionalPanel;
	private JPanel leftPanel1;
	private JPanel leftPanel2;
	private JPanel passwordPanel;
	
	//JButtons
	private JButton photoURLButton;
	private JButton howLoadPhotoButton;
	private JButton facebookButton;
	private JButton saveNewPasswordButton;
	
	//JTextFields
	private JTextField phoneTF;
	private JTextField schoolTF;
	private JTextField photoURLTF;
	private JTextArea aboutMeTF;
	private JTextField oldPasswordTextField;
	private JTextField newPasswordTextField;
	private JTextField reNewPasswordTextField;
	
	//JLabels
	private JLabel oldPassword;
	private JLabel newPassword;
	private JLabel reNewPassword;
	private JLabel imageLabel;
	private JLabel passwordChangeLabel;
	
	private MySQLUser userDB;
	private URL imageURL;
	private JButton saveButton;
	
	//Constructor
	public EditProfileFrame( int userID) throws Exception
	{
		this.userID = userID;
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heigthScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 900) / 2, (heigthScreen - 560) / 2);
		
		//Connecting UserDB
		userDB = new MySQLUser();
		
		//Setting Layout
		setLayout( new BorderLayout());
		
		//Creating URL
		imageURL = new URL( userDB.getUserPhotoURL( userID));
		
		//Creating Image
		BufferedImage img = ImageIO.read( imageURL);
		ImageObserver imgObserver = null;
		int height = img.getHeight( imgObserver);
		int width = img.getWidth( imgObserver);
		
		//If the height is bigger than with it subtracts the width from height and divides with two
		//after that gets the subimage as square
		if( height > width )
		{
			img = img.getSubimage( (height - width) / 2, 0, width, width);
		}
		
		//If the width is bigger than height it subtracts the width from height and divides with two
		//after that gets the subimage as square
		else
		{
			img = img.getSubimage( 0, (width - height) / 2, width, width);
		}
		
		//Adding the img to the Label
		Image buffImage = img.getScaledInstance( 100, 100, java.awt.Image.SCALE_SMOOTH); 
		ImageIcon newImage = new ImageIcon( buffImage);
	    imageLabel = new JLabel( "", newImage, JLabel.LEFT);
		
		//Creating Photo Panel
		photoPanel = new JPanel();
		photoPanel.setBorder( BorderFactory.createTitledBorder( userDB.getUserName( userID)));
		photoPanel.add( imageLabel, BorderLayout.CENTER);
		photoPanel.setMaximumSize( new Dimension( 130, 130));
		
		//Creating Photo URL TF and Button
		photoURLTF = new JTextField();
		photoURLButton = new JButton( "Change Photo");
		photoURLButton.addActionListener( this);
		howLoadPhotoButton = new JButton( "How to Load Your Facebook Profile Photo Here?");
		howLoadPhotoButton.setForeground( Color.BLUE);
		howLoadPhotoButton.setBorderPainted( false);
		howLoadPhotoButton.setContentAreaFilled( false);
		howLoadPhotoButton.addActionListener( this);
		facebookButton = new JButton( "Facebook Support (Facebook Graph API)");
		facebookButton.setForeground( Color.BLUE);
		facebookButton.setBorderPainted( false);
		facebookButton.setContentAreaFilled( false);
		facebookButton.addActionListener( this);
		
		//Creating Photo URL Panel
		photoURLPanel = new JPanel();
		photoURLPanel.setLayout( new BorderLayout( 30, 30));
		photoURLPanel.setPreferredSize( new Dimension( 400, 150));
		photoURLPanel.setBorder( BorderFactory.createTitledBorder( "Change Photo"));
		photoURLPanel.add( new JLabel( "Paste URL of Your Photo (from WEB)"), BorderLayout.PAGE_START);
		photoURLPanel.add( photoURLTF, BorderLayout.CENTER);
		photoURLPanel.add( photoURLButton, BorderLayout.LINE_END);
		photoURLPanel.add( howLoadPhotoButton, BorderLayout.PAGE_END);
		
		//Creating How to Load Photo Panel
		photoLoadPanel = new JPanel();
		photoLoadPanel.setPreferredSize( new Dimension( 500, 125));
		photoLoadPanel.setBorder( BorderFactory.createTitledBorder( "How to Load Photo From Facebook"));
		photoLoadPanel.add( new JLabel( "1) Go to the Facebook Support Page"));
		photoLoadPanel.add( new JLabel( "2) Find \"Profile pictures: (There will be an url here)\""));
		photoLoadPanel.add( new JLabel( "3) Click and then Copy the URL of Your Picture"));
		photoLoadPanel.add( facebookButton);
		photoLoadPanel.setVisible( false);
		
		//Creating Edit Photo Panel
		editPhotoPanel = new JPanel();
		editPhotoPanel.setBorder( BorderFactory.createTitledBorder( "Profile Picture"));
		editPhotoPanel.add( photoPanel);
		editPhotoPanel.add( photoURLPanel);
		editPhotoPanel.add( photoLoadPanel, BorderLayout.PAGE_END);
		add( editPhotoPanel, BorderLayout.CENTER);
	
		//Creating Edit Account Panel
		editAccountPanel = new JPanel();
		editAccountPanel.setPreferredSize( new Dimension( 300, 200));
		editAccountPanel.setLayout( new BorderLayout());
		editAccountPanel.setBorder( BorderFactory.createTitledBorder( "Account Informations"));
		add( editAccountPanel, BorderLayout.LINE_END);
		
		//Creating Properties of Edit Account Panel
		passwordPanel = new JPanel();
		passwordPanel.setLayout( new GridLayout( 3,2));
		passwordPanel.setPreferredSize( new Dimension( 290, 100));
		
		oldPassword = new JLabel( "\t Old password: ");
		newPassword = new JLabel( "\t New password: ");
		reNewPassword = new JLabel( "\t Retype new password: ");
		
		oldPasswordTextField = new JTextField();
		newPasswordTextField = new JTextField();
		reNewPasswordTextField = new JTextField();
		
		passwordChangeLabel = new JLabel();
		
		saveNewPasswordButton = new JButton( "Change My Password");
		saveNewPasswordButton.addActionListener( this);
		
		//Adding Properties to the Account Pannel
		passwordPanel.add( oldPassword);
		passwordPanel.add( oldPasswordTextField);
		passwordPanel.add( newPassword);
		passwordPanel.add( newPasswordTextField);
		passwordPanel.add( reNewPassword);
		passwordPanel.add( reNewPasswordTextField);
		editAccountPanel.add( passwordPanel, BorderLayout.NORTH);
		editAccountPanel.add( passwordChangeLabel, BorderLayout.CENTER);
		editAccountPanel.add( saveNewPasswordButton, BorderLayout.SOUTH);
		
		//Creating Labels
		phoneTF = new JTextField( "" + userDB.getUserPhone( userID));
		schoolTF = new JTextField( userDB.getUserSchool( userID));
		
		//Creating Left1 Panel
		leftPanel1 = new JPanel();
		leftPanel1.setPreferredSize( new Dimension( 100, 50));
		leftPanel1.setLayout( new GridLayout( 2, 1));
		leftPanel1.add( new JLabel( "Phone :"));
		leftPanel1.add( new JLabel( "School :"));
		
		//Creating Left2 Panel
		leftPanel2 = new JPanel();
		leftPanel2.setPreferredSize( new Dimension( 100, 50));
		leftPanel2.setLayout( new GridLayout( 2, 1));
		leftPanel2.add( phoneTF);
		leftPanel2.add( schoolTF);
		
		//Creating Left Additional Panel
		leftAdditionalPanel = new JPanel();
		leftAdditionalPanel.add( leftPanel1);
		leftAdditionalPanel.add( leftPanel2);
		leftAdditionalPanel.setLayout( new FlowLayout());
		
		//Creating JTextField and Save button
		aboutMeTF = new JTextArea( userDB.getUserAboutMe( userID));
		aboutMeTF.setPreferredSize( new Dimension( 350, 60));
		aboutMeTF.setBackground( Color.lightGray);
		aboutMeTF.setLineWrap( true);
		aboutMeTF.setEditable( false);
		saveButton = new JButton( "Save");
		
		//Creating Right Additional Panel
		rightAdditionalPanel = new JPanel();
		rightAdditionalPanel.setPreferredSize( new Dimension( 400, 200));
		rightAdditionalPanel.add( new JLabel( "Pin Info: "));
		rightAdditionalPanel.add( aboutMeTF);
		rightAdditionalPanel.add( saveButton);
		
		//Creating Edit Additional Panel
		editAdditionalPanel = new JPanel();
		editAdditionalPanel.setLayout( new BorderLayout());
		editAdditionalPanel.setPreferredSize( new Dimension( 200, 150));
		editAdditionalPanel.setBorder( BorderFactory.createTitledBorder( "Additional Informations"));
		editAdditionalPanel.add( leftAdditionalPanel, BorderLayout.LINE_START);
		editAdditionalPanel.add( rightAdditionalPanel, BorderLayout.LINE_END);
		add( editAdditionalPanel, BorderLayout.PAGE_END);
		
		//Setting size of the frame
		setPreferredSize( new Dimension( 900, 500));
		pack();
		setVisible( true);
		setFocusable( true);
	}
	
	//
	public void actionPerformed( ActionEvent e)
	{
		
		//If the clicked one is  howLoadPhotoButton and
		//it enables the photoLoadPanel which explains how to upload photo to program
		if( ((JButton) e.getSource()).equals( howLoadPhotoButton) )
		{
			photoLoadPanel.setVisible( true);
		}
		
		//If the clicked one is facebookButton
		//it opens a new browser page and enters the new URI which is facebook's api
		else if( ((JButton) e.getSource()).equals( facebookButton) )
		{
			Desktop myDesktop = Desktop.getDesktop();
			try 
			{
				myDesktop.browse( new URI( "http://developers.facebook.com/docs/reference/api/"));
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			} 
			catch (URISyntaxException e1) 
			{
				e1.printStackTrace();
			}
		}
		
		//If the clicked one is facebookButton
		//it takes the photo from URL and makes it new profile picture
		//it also adds this URL to the database
		else if( ((JButton) e.getSource()).equals( photoURLButton) )
		{
			try 
			{
				userDB.setUserPhotoURL( userID, photoURLTF.getText());
				photoURLTF.setText( "Succesfull!");
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		
		//If user clicks the saveNewPasswordButton
		else if( ((JButton) e.getSource()).equals( saveNewPasswordButton) )
		{
			try {
				//If user enters a wrong password to the first text field or leaves it blank
				//this method will prompt error message
				if( !oldPasswordTextField.getText().equals( userDB.getUserPassword( userID)))
				{
					passwordChangeLabel.setText( "\tYou entered wrong password!");
				}
				//If user enters a new password which has less than  6 letters
				//this method will prompt error message
				else if( (newPasswordTextField.getText().length() < 6))
				{
					passwordChangeLabel.setText( "\tNew password is less then 6 letters, please enter a new password.");
				}
				//If new passwords that user will enter does not match with each other
				//this method will prompt error message
				else if( !newPasswordTextField.getText().equals( reNewPasswordTextField.getText()))
				{
					passwordChangeLabel.setText( "\tNew password does not match!");
				}
				//Else the password will change to the new one
				else
				{
					userDB.setUserPassword( userID, newPasswordTextField.getText());
					passwordChangeLabel.setText( "\tPassword changed correctly!");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
