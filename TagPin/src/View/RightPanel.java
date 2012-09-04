package View;

/**
 * @(#) RightPanel: Creates right side of the user page bearing friend list and search field
 * 
 * 
 *
 * @ Tufan Turkaslan
 * @version 1.00 
 */

import java.awt.*;
import Model.*;
import javax.swing.*;
import java.awt.event.*;


class RightPanel extends JPanel implements ActionListener
{
	//PROPERTIES
	private final int userIDFinal;
	static int userID;
	private JPanel friendsList;
	private JPanel friendsSearchPanel;
	private JTextField friendsSearchBar;
	private JButton friendsSearchBarButton;
	private JList list;
	private JScrollPane listScroller;
	private String[] friends;
	private JButton findFriend;
	private MySQLFriendship friendshipDB;
	private MySQLUser userDB;
	
	//CONSTRUCTOR
	public RightPanel( int userID) throws Exception
	{
		this.userID = userID;
		userIDFinal = userID;
		
		//Connecting Database
		friendshipDB = new MySQLFriendship();
		userDB = new MySQLUser();
		
		//Creating the panels which are in the LeftPanel 
		friendsList = new JPanel();
		friendsSearchPanel = new JPanel();
		list = new JList();
		friendsList.setBorder( BorderFactory.createTitledBorder( "Friends"));
		findFriend = new JButton( "Find Friends");
		findFriend.addActionListener( this);
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
						new ShowFriendProfile( friendshipDB.getFriendsID( userIDFinal)[ index], userIDFinal);
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
		
		//Creating Friends Array
		friends = friendshipDB.getFriendsName( userID);
		
		//Creating lists
		list.setListData( friends);
		list.setBorder(BorderFactory.createLoweredBevelBorder());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount( -1);
		listScroller = new JScrollPane( list);
		listScroller.setPreferredSize( new Dimension( 300, 100));
	
				
		//Setting the layouts of panels
		friendsList.setLayout( new BorderLayout( 5, 5));
		friendsSearchPanel.setLayout( new FlowLayout());
		setLayout( new BorderLayout());
		
		//Creating the properties of friendsList panel
		friendsSearchBar = new JTextField( " search for friends");
		friendsSearchBarButton = new JButton( "Search");
		
		//Adding buttons, text fields, labels to panels
		friendsSearchPanel.add( friendsSearchBar);
		friendsSearchPanel.add( friendsSearchBarButton);
		friendsList.add( listScroller);
		friendsList.add( friendsSearchPanel, BorderLayout.SOUTH);
		add( friendsList);
		add( findFriend, BorderLayout.PAGE_END);
		setPreferredSize( new Dimension( 250, 600));
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( findFriend) )
		{
			new SearchUserFrame( userID);
		}
	}
	
}
