package View;

/*
 * ShowFriendProfile: This frame occurs when the user double clicked on one of his friends and it shows some information - which are name, surname, 
 * school, phone and about - of his friends. Also there is a button to delete this user from friends. 
 * This frame also works for adding friends collaborating with search bar
 * 
 * @ Melih Ayyildiz - Tufan Turkaslan 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.MySQLFriendship;
import Model.MySQLUser;

public class ShowFriendProfile extends JFrame implements ActionListener  {
	
	private int userID;
	private int mainUserID;
	
	//Panels
	private JPanel profilePhoto;
	private JPanel friendInfo;
	private JPanel together;
	private JPanel delete;
	private JPanel addHim;
	//Buttons
	private JButton deleteFriend;
	private JButton addFriend;
	
	//Labels
	
	private JLabel name;
	private JLabel surname;
	private JLabel birthdate;
	private JLabel additional;
	private JLabel imageLabel;
	private JLabel school;
	private JLabel phone;
	private JLabel textName;
	private JLabel textSurname;
	private JLabel 	  textBirthdate;
	private JLabel   textAdditional; 
	private JLabel   textPhone; 
	private JLabel   textSchool;

	
	private MySQLFriendship friendshipDB;
	private MySQLUser userDB;
	private URL imageURL;
	private int[] friends;
	
	
	public ShowFriendProfile( int userID, int mainUserID) throws Exception
	{
		this.userID = userID;
		this.mainUserID = mainUserID;
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heigthScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 900) / 2, (heigthScreen - 560) / 2);
		
		//Connecting UserDB
		userDB = new MySQLUser();
		friendshipDB = new MySQLFriendship();
		
		friends = new int[ friendshipDB.getFriendsID(userID).length];
		
		//Setting Layout
		setLayout( new BorderLayout());
		
		
		//Creating URL
		imageURL = new URL( userDB.getUserPhotoURL( userID));
		
		
		//Creating Image
		BufferedImage img = ImageIO.read( imageURL);
		ImageObserver imgObserver = null;
		int height = img.getHeight( imgObserver);
		int width = img.getWidth( imgObserver);
			
		if( height > width )
		{
			img = img.getSubimage( (height - width) / 2, 0, width, width);
		}
		else
		{
			img = img.getSubimage( 0, (width - height) / 2, width, width);
		}
		Image buffImage = img.getScaledInstance( 100, 100, java.awt.Image.SCALE_SMOOTH); 
		ImageIcon newImage = new ImageIcon( buffImage);
	    imageLabel = new JLabel( "", newImage, JLabel.LEFT);
	    
	    //Creating Labels
	    textName = new JLabel( "Name :  ");
	    textSurname = new JLabel( "Surname :  ");
	    textBirthdate = new JLabel( "Birthday :  ");
	    textAdditional = new JLabel( "Pin Info :  ");
	    textPhone = new JLabel( "Phone :  ");
	    textSchool = new JLabel( "School :  ");
	    //Creating Panels
	    
	    //profile photo
	    profilePhoto = new JPanel();
	    profilePhoto.setBorder( BorderFactory.createTitledBorder( userDB.getUserName( userID)));
	    profilePhoto.add( imageLabel);
	    profilePhoto.setMaximumSize( new Dimension( 130, 130));
	    
	    //friend info
	    friendInfo = new JPanel();
	    friendInfo.setPreferredSize( new Dimension (260, 100));
	    name = new JLabel( userDB.getUserName( userID));
	    surname = new JLabel( userDB.getUserSurName( userID));
	    birthdate = new JLabel( userDB.getUserBirthday( userID));
	    additional = new JLabel( userDB.getUserAboutMe( userID));
	    phone = new JLabel( "" + userDB.getUserPhone( userID));
	    school = new JLabel( userDB.getUserSchool( userID));
	    friendInfo.add( textName);
	    friendInfo.add( name);
	    friendInfo.add( textSurname);
	    friendInfo.add( surname);
	    friendInfo.add( textBirthdate);
	    friendInfo.add( birthdate);
	    friendInfo.add( textPhone);
	    friendInfo.add( phone);
	    friendInfo.add( textSchool);
	    friendInfo.add( school);
	    friendInfo.add( textAdditional);
	    friendInfo.add( additional);
	    friendInfo.setLayout( new GridLayout( 7,2));
	    
	    //delete
	    delete = new JPanel();
	    deleteFriend = new JButton(" Delete this friend" );
	    delete.add( deleteFriend);
	    
	    //add
	    addHim = new JPanel();
	    addFriend = new JButton( "Add as Friend");
	    addFriend.addActionListener( this);
	    addHim.add( addFriend);
	    
	    //adding panels to together panel
	    together = new JPanel();
	    together.setPreferredSize( new Dimension( 300, 250) );
	    together.setLayout( new BorderLayout());
	    together.add( profilePhoto, BorderLayout.LINE_START);
	    together.add( friendInfo, BorderLayout.CENTER);
	    together.add( addHim, BorderLayout.PAGE_END);
	 
	    int count = 0;
	    friends = friendshipDB.getFriendsID( userID);
	    
	    while( count < friends.length)
	    {
	    	if( friends[count] == mainUserID )
	 	    {
	    		together.add( delete, BorderLayout.PAGE_END);
	 	    	break;
	 	    }
	    	else if( userID == mainUserID )
	    	{
	    		together.add( new JLabel( "         It's You!"), BorderLayout.PAGE_END);
	    	}
	 	    count++;
	    }
	   
	    
	    
	    add( together);
	    setPreferredSize( new Dimension( 400, 210));
	    setVisible(true);
	    
	    pack();
	    
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( delete) )
		{
			dispose();
		}
		if( ((JButton) e.getSource()).equals( addFriend))
		{
			try 
			{
				friendshipDB.requestFriendship( mainUserID, userID, userDB.getUserName( mainUserID), userDB.getUserName( userID));
				dispose();
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
	}	

}



	
