package View;

/**
 * @(#) NotificationFrame: Creates a frame that will be appear on user page 
 * 							if the user receives any friend request
 *
 * 
 *
 * @ Melih Ayyildiz
 * @version 1.00 
 */

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import Model.*;

public class NotificationFrame extends JFrame implements ActionListener {

	//Variables
	private JLabel notificationMsg;
	private JLabel imageLabel;
	
	private JPanel picturePanel;
	private JPanel notifyPanel;
	private JPanel allTogether;
	
	private JButton accept;
	private JButton deny;
	
	private URL imageURL;
	private int userID;
	private int realUserID;
	
	private MySQLUser userDB;
	private MySQLFriendship friendshipDB;

	public NotificationFrame( int userID, int realUserID) throws Exception
	{
		this.userID = userID;
		this.realUserID = realUserID;
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heigthScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 900) / 2, (heigthScreen - 560) / 2);
		
		//Connecting UserDB
		userDB = new MySQLUser();
		friendshipDB = new MySQLFriendship();
		
		
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
	    
	    //Profile Photo Panel
	    picturePanel = new JPanel();
	    picturePanel.setBorder( BorderFactory.createTitledBorder( userDB.getUserName( userID)));
	    picturePanel.add( imageLabel);
	    picturePanel.setMaximumSize( new Dimension( 130, 130));
	    
	    // Notification Panel
	    notifyPanel = new JPanel();
	    notifyPanel.setPreferredSize( new Dimension (200, 100));
	    notifyPanel.setLayout( new FlowLayout());
	    notificationMsg = new JLabel();
	    notificationMsg.setText( userDB.getUserName( userID)+ " " + userDB.getUserSurName( userID) + " wants to be your friend.");
	    notifyPanel.add( notificationMsg);
	    accept = new JButton( "Accept");
	    accept.addActionListener( this);
	    deny = new JButton( "Ignore");
	    deny.addActionListener( this);
	    notifyPanel.add( accept);
	    notifyPanel.add( deny);
	    
	    allTogether = new JPanel();
	    allTogether.setPreferredSize( new Dimension (200,200));
	    allTogether.setLayout( new BorderLayout());
	    allTogether.add( notifyPanel, BorderLayout.EAST);
	    allTogether.add( picturePanel, BorderLayout.WEST);
	    
	    add( allTogether);
	    setPreferredSize( new Dimension( 350, 170));
	    setVisible(true);
	    pack();
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( accept) )
		{
			try 
			{
				friendshipDB.acceptFriendship( userID, realUserID);
				dispose();
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		else if( ((JButton) e.getSource()).equals( deny) )
		{
			try 
			{
				friendshipDB.denyFriendship( userID, realUserID);
				dispose();	
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
}
