package View;

/**
 * @(#) MainPagePanel: Creates starting page bearing login and sign up properties
 * 
 *
 * 
 *
 * @ Semih Akar - Melih Ayyildiz 
 * @version 1.00 
 */

import Controller.SendEmail;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainPagePanel extends JPanel implements ActionListener {
	
	//MySQL Connection
	private MySQLUser userDB;
	private MySQLFriendship friendshipDB;
	
	//Send Email
	private SendEmail myMail; 
	
	static int userID;
	
	//PROPERTIES
	static JPanel startingPanel;
	private JPanel birthdayPanel;
	private JPanel signUpPanel;
	private JPanel labelsPanel;
	private JPanel textFieldsPanel;
	private JPanel logInPanel1;
	private JPanel logInPanel;
	private JPanel togetherPanel;
	private JPanel termsOfLicencePanel;
	private JPanel activationPanel;
	private JPanel bottomPanel;
	private JComboBox secretQuestionCB;
	private JCheckBox termsOfLicence;
	private final String[] SECRETQUESTIONS = { "What is your dog's name?", 
											   "What is your mother's surname?",
											   "What is your first teacher's name?",
											   "What is your favorite word?"};
	
	//Text Fields
	private JTextField nameTF;
	private JTextField surNameTF;
	private JTextField emailTF;
	private JPasswordField passwordTF;
	private JPasswordField rePasswordTF;
	private JTextField secretAnswerTF;
	private JTextField logEmailTF;
	private JPasswordField logPasTF;
	private JTextField activationTF;
	
	//Button
	static JButton logInButton;
	private JButton signUpButton;
	private JButton forgotPassButton;
	private JButton termsOfLicenceButton;
	private JButton activationButton;
	
	//JLabels
	static JLabel logInWarning;
	private JLabel signUpWarning;
	private JLabel activationCode; 
	
	//Spinners
	private SpinnerModel dayModel;
	private SpinnerModel monthModel;
	private SpinnerModel yearModel;
	private JSpinner daySpinner;
	private JSpinner monthSpinner;
	private JSpinner yearSpinner;
	
	
	public MainPagePanel()
	{
		//Creating MySQL Connection
		userDB = new MySQLUser();
		friendshipDB = new MySQLFriendship();
		
		//Creating SendEmail
		myMail = new SendEmail();
		
		//Creating Label Panel
		labelsPanel = new JPanel();
		labelsPanel.setPreferredSize( new Dimension( 120, 310));
		labelsPanel.add( new JLabel( "                        Name"));
		labelsPanel.add( new JLabel( "                  Surname"));
		labelsPanel.add( new JLabel( "       E-mail address"));
		labelsPanel.add( new JLabel( "                Password"));
		labelsPanel.add( new JLabel( " Re-type Password"));
		labelsPanel.add( new JLabel( "      Secret question"));
		labelsPanel.add( new JLabel( "        Secret answer"));
		labelsPanel.add( new JLabel( "      Birthday (D/M/Y)"));
		labelsPanel.setLayout( new GridLayout( 8, 1, 0, 4));
		
		//Creating Birthday Panel
		birthdayPanel = new JPanel();
		birthdayPanel.setPreferredSize( new Dimension( 240, 40));
		dayModel = new SpinnerNumberModel( 1, 1, 31, 1);
		monthModel = new SpinnerNumberModel( 1, 1, 12, 1);
		yearModel = new SpinnerNumberModel( 2011, 1900, 2015, 1);
		daySpinner = new JSpinner( dayModel);
		monthSpinner = new JSpinner( monthModel);
		yearSpinner = new JSpinner( yearModel);
		birthdayPanel.add( daySpinner);
		birthdayPanel.add( monthSpinner);
		birthdayPanel.add( yearSpinner);
		birthdayPanel.setLayout( new FlowLayout());
		
		//Creating TextFields of Sing Up Panel
		nameTF = new JTextField( "");
		surNameTF = new JTextField( "");
		emailTF = new JTextField( "");
		passwordTF = new JPasswordField( "");
		rePasswordTF = new JPasswordField( "");
		secretAnswerTF = new JTextField( "");
		
		//Creating Combo Box of Sign Up Panel
		secretQuestionCB = new JComboBox( SECRETQUESTIONS);
		
		//Creating Text Field Panel
		textFieldsPanel = new JPanel();
		textFieldsPanel.setPreferredSize( new Dimension( 200, 300));
		textFieldsPanel.add( nameTF);
		textFieldsPanel.add( surNameTF);
		textFieldsPanel.add( emailTF);
		textFieldsPanel.add( passwordTF);
		textFieldsPanel.add( rePasswordTF);
		textFieldsPanel.add( secretQuestionCB);
		textFieldsPanel.add( secretAnswerTF);
		textFieldsPanel.add( birthdayPanel);
		textFieldsPanel.setLayout( new GridLayout( 8, 1, 0, 12));
		
		//Creating Together Panel
		togetherPanel = new JPanel();
		togetherPanel.setPreferredSize( new Dimension( 410, 320));
		togetherPanel.add( labelsPanel);
		togetherPanel.add( textFieldsPanel);
		togetherPanel.setLayout( new FlowLayout());
		
		
		//Creating Checkbox for Terms and Conditions of Licence
		termsOfLicence = new JCheckBox( "I agree with Terms and Conditions of Licence", true);
		
		
		//Creating Sign Up Button
		signUpButton = new JButton( "Sign Up");
		signUpButton.addActionListener( this);
		
		
		//Creating Sign Up Warning Label
		signUpWarning = new JLabel( "");
		
		
		//Creating Terms Of License Text
		termsOfLicenceButton = new JButton( "Details?");
		termsOfLicenceButton.setForeground( Color.BLUE);
		termsOfLicenceButton.setBorderPainted( false);
		termsOfLicenceButton.setContentAreaFilled( false);
		termsOfLicenceButton.addActionListener( this);
		
		
		//Creating Activation Code Part
		activationCode = new JLabel( "Activation Code");
		activationTF = new JTextField();
		activationTF.setPreferredSize( new Dimension( 150, 25));
		
		
		//Creating Activation Panel
		activationPanel = new JPanel();
		activationPanel.add( activationCode);
		activationPanel.add( activationTF);
		activationPanel.add( signUpButton);
		activationPanel.setLayout( new FlowLayout());
		
		
		//Creating Panel of Terms Of License
		termsOfLicencePanel = new JPanel();
		termsOfLicencePanel.add( termsOfLicence);
		termsOfLicencePanel.add( termsOfLicenceButton);
		termsOfLicencePanel.setLayout( new FlowLayout());
		
		
		//Creating Activation Button
		activationButton = new JButton( "Send Activation Code");
		activationButton.addActionListener( this);
		
		
		//Creating Bottom Panel
		bottomPanel = new JPanel();
		bottomPanel.add( activationPanel);
		bottomPanel.add( termsOfLicencePanel);
		bottomPanel.setLayout( new GridLayout( 2, 1, 5, 5));
		
		
		//Creating Sign Up Panel
		signUpPanel = new JPanel();
		signUpPanel.setPreferredSize( new Dimension( 420, 490));
		signUpPanel.setLayout( new BorderLayout( 20, 20));
		signUpPanel.add( togetherPanel, BorderLayout.PAGE_START);
		signUpPanel.add( bottomPanel, BorderLayout.PAGE_END);
		signUpPanel.add( signUpWarning, BorderLayout.CENTER);
		signUpPanel.add( activationButton, BorderLayout.EAST);
		
		//Bordering Sign Up Panel
		signUpPanel.setBorder( BorderFactory.createTitledBorder( "Sign Up"));
		
		
		//Creating Text Fields of Log In Panel
		logEmailTF = new JTextField();
		logPasTF = new JPasswordField();
		
		
		//Creating Part 1 of Log In Panel
		logInPanel1 = new JPanel();
		logInPanel1.setPreferredSize( new Dimension( 280, 130));
		logInPanel1.add( new JLabel( "E-mail"));
		logInPanel1.add( logEmailTF);
		logInPanel1.add( new JLabel( "Password"));
		logInPanel1.add( logPasTF);
		logInPanel1.setLayout( new GridLayout( 4, 1));
		
		
		//Creating Buttons of Log In Panel
		logInButton = new JButton( "Log In");
		forgotPassButton = new JButton( "Forgot Your Password?");
		forgotPassButton.addActionListener( this);
		
		
		//Creating Warning Labels
		logInWarning = new JLabel( "");
		
		
		//Creating Log In Panel
		logInPanel = new JPanel();
		logInPanel.setPreferredSize( new Dimension( 300, 250));
		logInPanel.setLayout( new BorderLayout( 20, 20));
		logInPanel.add( logInPanel1, BorderLayout.PAGE_START);
		logInPanel.add( logInButton, BorderLayout.EAST);
		logInPanel.add( logInWarning, BorderLayout.CENTER);
		logInPanel.add( forgotPassButton, BorderLayout.PAGE_END);
		logInPanel.setBorder( BorderFactory.createTitledBorder( "Log In"));
		
		
		//Creating startingPanel
		startingPanel = new JPanel();
		startingPanel.setLayout( new FlowLayout());
		startingPanel.add( signUpPanel);
		startingPanel.add( logInPanel);
		
		
		//Adding staringPanel
		add( startingPanel);
		
	}
	
	public boolean checkLogIn( ) throws Exception
	{
		int userID;
		userID = userDB.getUserID( logEmailTF.getText());
		
		this.userID = userID;
		
		return userDB.checkPassword( userID, logPasTF.getText());
	}
	
	public boolean verifyEmail() throws Exception
	{
		return userDB.checkEmail( emailTF.getText());
	}
	
	public boolean checkPasSuit()
	{
		if( (passwordTF.getText()).length() < 6 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkRePas()
	{
		if( passwordTF.getText().equals( rePasswordTF.getText()) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkNameSurname()
	{
		if( nameTF.getText().length() > 0 && surNameTF.getText().length() > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkSecretAnswer()
	{
		if( secretAnswerTF.getText().length() >= 1 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkTermsOfLicence()
	{
		return termsOfLicence.isSelected();
	}
	
	public void visibility() throws Exception
	{	
		startingPanel.setVisible( false);
		setLayout( new BorderLayout());
		add( new RightPanel( userDB.getUserID( logEmailTF.getText())), BorderLayout.LINE_END);
		add( new LeftPanel( userDB.getUserID( logEmailTF.getText())), BorderLayout.LINE_START);
		add( new CenterPanel( userDB.getUserID( logEmailTF.getText())), BorderLayout.CENTER);
	}
	
	public String getActivationCode()
	{
		int code;
		code = 0;

		for( int count = 0; count < emailTF.getText().length(); count++ )
		{
			code += (int) emailTF.getText().charAt( count);
		}
		
		code = code * 1768 + 534;
		
		return "" + code;
	}
	
	public int getNumOfComboBox()
	{
		if( secretQuestionCB.getSelectedItem() == "What is your dog's name?" )
		{
			return 1;
		}
		else if( secretQuestionCB.getSelectedItem() == "What is your mother's surname?" )
		{
			return 2;
		}
		else if( secretQuestionCB.getSelectedItem() == "What is your first teacher's name?" )
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	public String getStringOfBirthday()
	{
		return "" + dayModel.getValue() + "/" + monthModel.getValue() + "/" +
				yearModel.getValue();
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( activationButton) )
		{
			try 
			{
				if( verifyEmail() && checkRePas() && checkPasSuit() && checkNameSurname()
					&& checkSecretAnswer() )
				{
					nameTF.setEditable( false);
					surNameTF.setEditable( false);
					emailTF.setEditable( false);
					passwordTF.setEditable( false);
					rePasswordTF.setEditable( false);
					secretQuestionCB.setEnabled( false);
					secretAnswerTF.setEditable( false);
					daySpinner.setEnabled( false);
					monthSpinner.setEnabled( false);
					yearSpinner.setEnabled( false);
					myMail.sendActivationCode( emailTF.getText(), getActivationCode());
					signUpWarning.setText( "Your activation code has been sent!");
				}
				else
				{
					signUpWarning.setText( "*Error has been occured!");
				}
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		else if( ((JButton) e.getSource()).equals( termsOfLicenceButton) )
		{
			new TermsOfLicenceFrame();
		}
		else if( ((JButton) e.getSource()).equals( signUpButton) )
		{
			try 
			{
				if( activationTF.getText().equals( getActivationCode())
					&& checkTermsOfLicence() )
				{
					userDB.createUser( nameTF.getText(), surNameTF.getText(),
									   emailTF.getText(), passwordTF.getText(),
									   getNumOfComboBox(), secretAnswerTF.getText(),
									   getStringOfBirthday());
					signUpWarning.setText( "Congratulations! You Can Log In.");
				}
				else
				{
					signUpWarning.setText( "Code is Wrong!");
				}
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		else if( ((JButton) e.getSource()).equals( forgotPassButton) )
		{
			new ForgotPasswordFrame();
		}
	}

}
