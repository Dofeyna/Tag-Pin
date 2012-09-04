package View;

/**
 * @(#) SearchUserFrame: Creates a frame that will be appear on user page if the user wishes to 
 * search friend by name
 *
 * 
 *
 * @ Semih Akar
 * @version 1.00 
 */

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import Model.*;

public class SearchUserFrame extends JFrame implements ActionListener, MouseListener {
	
	//Properties
	private int userID;
	private final int userIDFinal;
	private String[] friends;
	private MySQLUser userDB;
	
	//JPanels
	private JPanel searchPanel;
	private JPanel resultPanel;
	
	//JTextFields
	private JTextField searchTF;
	
	//JButtons
	private JButton searchButton;
	
	//JList
	private JList resultList;
	private JScrollPane listScroller;
	
	
	public SearchUserFrame( int userID)
	{
		this.userID = userID;
		userIDFinal = userID;
	
		//Connecting UserDB
		userDB = new MySQLUser();
		
		
		//Setting Location to Center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int heigthScreen = (int) screenSize.getHeight();
		int widthScreen = (int) screenSize.getWidth();
		setLocation( (widthScreen - 400) / 2, (heigthScreen - 300) / 2);
		
		
		//Creating JTextFields and JButtons
		searchTF = new JTextField( "by email or name");
		searchButton = new JButton( "Search");
		searchButton.addActionListener( this);
		resultList = new JList();
		
		
		//Creating lists
		resultList.setBorder(BorderFactory.createLoweredBevelBorder());
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultList.setLayoutOrientation(JList.VERTICAL);
		resultList.setVisibleRowCount( -1);
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
						new ShowFriendProfile( userDB.findUserIDByName( searchTF.getText())[ index], userIDFinal);
					} 
		            catch (Exception e) 
		            {
						e.printStackTrace();
					}
		          }
		        }
		      }
		    };
		resultList.addMouseListener( mouseListener);
		listScroller = new JScrollPane( resultList);
		listScroller.setPreferredSize( new Dimension( 350, 100));
		
		
		//Creating Result Panel
		resultPanel = new JPanel();
		resultPanel.setBorder( BorderFactory.createTitledBorder( "Results"));
		resultPanel.add( listScroller);
		resultPanel.setVisible( false);
		add( resultPanel, BorderLayout.CENTER);
		
		
		//Creating Search Panel
		searchPanel = new JPanel();
		searchPanel.setBorder( BorderFactory.createTitledBorder( "Search"));
		searchPanel.setLayout( new BorderLayout( 10, 10));
		searchPanel.add( searchTF);
		searchPanel.add( searchButton, BorderLayout.LINE_END);
		searchPanel.setMaximumSize( new Dimension( 60, 250));
		add( searchPanel, BorderLayout.PAGE_START);
		
		
		setPreferredSize( new Dimension( 380, 300));
		pack();
		setVisible( true);
		setFocusable( true);
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( ((JButton) e.getSource()).equals( searchButton) )
		{
			friends = null;
			
			try 
			{
				friends = userDB.findUserByName( searchTF.getText());
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			
			resultList.setListData( friends);
			resultPanel.setVisible( true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
