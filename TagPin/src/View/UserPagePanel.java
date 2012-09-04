package View;

/**
 * @(#) UserPagePanel: Creates user page with right,left and center panels
 *
 * 
 *
 * @ Tufan Turkaslan
 * @version 1.00 
 */

import java.awt.*;
import javax.swing.*;

class UserPagePanel extends JPanel {

	//Properties
	int userID;
	
	public UserPagePanel( int userID) throws Exception
	{
		this.userID = userID;
		
		setLayout( new BorderLayout());
		add( new RightPanel( userID), BorderLayout.LINE_END);
		add( new LeftPanel( userID), BorderLayout.LINE_START);
		add( new CenterPanel( userID), BorderLayout.CENTER);
		
		setPreferredSize( new Dimension( 900, 500));
		setVisible( true);
		setFocusable( true);
	}
	
}
