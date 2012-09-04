package View;

/**
 * @(#) LeftPanel: Creates left panel of the user page which holds event list and user profile
 *
 * 
 *
 * @ Tufan Turkaslan 
 * @version 1.00 
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.*;

import Model.*;
import java.net.URL;


class LeftPanel extends JPanel implements ActionListener 
{
	//JPanels
	private JPanel profilePanel;
	private JPanel profilePhotoPanel;
	private JPanel profileBottomPanel;
	private JPanel eventsList;
	private JPanel eventSearchPanel;
	
	//JButtons
	private JButton eventsSearchBarButton;
	private JButton editProfileButton;
	
	private final int userIDFinal;
	static int userID;
	private JTextField eventsSearchBar;
	private JList list;
	private JScrollPane listScroller;
	private String[] events;
	
	private URL imageURL;
	private JLabel imageLabel;
	private MySQLUser userDB;
	
	//CONSTRUCTOR
	public LeftPanel( int userID) throws Exception
	{
		this.userID = userID;
		userIDFinal = userID;
		
		//Connecting to User Database
		userDB = new MySQLUser();
		
		
		//Creating the panels which are in the LeftPanel 
		profilePanel = new JPanel();
		eventsList = new JPanel();
		eventSearchPanel = new JPanel();
		profilePhotoPanel = new JPanel();
		list = new JList();
		eventsList.setBorder( BorderFactory.createTitledBorder( "Events"));
		profilePanel.setBorder( BorderFactory.createTitledBorder( "Profile"));
		
		
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
		Image buffImage = img.getScaledInstance( 70, 70, java.awt.Image.SCALE_SMOOTH); 
		ImageIcon newImage = new ImageIcon( buffImage);
	    imageLabel = new JLabel( "", newImage, JLabel.LEFT);
		
	    
	    //Creating Edit Profile and Log Off Button
	    editProfileButton = new JButton( "Edit Profile");
	    editProfileButton.addActionListener( this);
	    
	    //Creating Bottom Panel
	    profileBottomPanel = new JPanel();
	    profileBottomPanel.setLayout( new BorderLayout());
	    profileBottomPanel.add( editProfileButton, BorderLayout.LINE_START);
	    profileBottomPanel.setPreferredSize( new Dimension( 100, 20));
	    
	    
		
		//Setting size of profilePhotoPanel
		profilePanel.setPreferredSize( new Dimension( 100, 150));
		profilePhotoPanel.setBorder( BorderFactory.createTitledBorder( userDB.getUserName( userID)));
		profilePhotoPanel.add( imageLabel,BorderLayout.CENTER);
		profilePhotoPanel.setMaximumSize( new Dimension( 100, 100));
		
		
		//Creating events
		events = userDB.getVisibleEventsName( userID);
			
		
		//Creating lists
		list.setListData( events);
		list.setBorder(BorderFactory.createLoweredBevelBorder());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount( -1);
		listScroller = new JScrollPane( list);
		listScroller.setPreferredSize( new Dimension( 250, 100));
		MouseListener mouseListener = new MouseAdapter() 
		{
		      public void mouseClicked(MouseEvent mouseEvent) 
		      {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2)
		        {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) 
		          {
		            Object o = theList.getModel().getElementAt(index);
		            try 
		            {
						new ShowEventInfo( userDB.getVisibleEvents( userIDFinal)[ index]);
					} 
		            catch (Exception e) 
		            {
						e.printStackTrace();
					}
		          }
		        }
		      }
		    };
		list.addMouseListener( mouseListener);
		
	
		//Setting the layouts of panels
		eventsList.setLayout( new BorderLayout( 5, 5));
		profilePanel.setLayout( new BorderLayout());
		eventSearchPanel.setLayout( new FlowLayout());
		setLayout( new BorderLayout());
		
		
		//Creating the properties of eventsList panel
		eventsSearchBar = new JTextField( " search for events ");
		eventsSearchBarButton = new JButton( "Search");
		
		
		//Adding buttons, text fields, labels to panels
		eventSearchPanel.add( eventsSearchBar);
		eventSearchPanel.add( eventsSearchBarButton);
		eventsList.add( listScroller);
		eventsList.add( eventSearchPanel, BorderLayout.SOUTH);
		profilePanel.add( profilePhotoPanel, BorderLayout.LINE_START);
		profilePanel.add( profileBottomPanel, BorderLayout.PAGE_END);
		add( eventsList, BorderLayout.CENTER);
		add( profilePanel, BorderLayout.NORTH);
		setPreferredSize( new Dimension( 250, 600));
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( editProfileButton) )
		{
			try 
			{
				new EditProfileFrame( userID);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		
	}
	
}
