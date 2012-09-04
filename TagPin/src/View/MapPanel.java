package View;

/**
 * @(#) MapPanel: Creates map and performs zoom-in, zoom-out and dragging. 
 * We found a mathematical function to compare Google Maps coordinates and 
 * Java coordinates then the program performs these actions according to that function.
 * 
 *
 * 
 *
 * @ Semih Akar - Melih Ayyildiz - Tufan Türkaslan
 * @version 1.00 
 */

import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MapPanel extends JPanel implements ActionListener, MouseListener, MouseWheelListener {
	
	private int userID;
	private URL mapURL;
	private BufferedImage img;
	private ImageObserver imageObserver;
	private MySQLEvent eventDB;
	private MySQLUser userDB;
	private MySQLFriendship friendshipDB;
	
	private double firstX;
	private double firstY;
	
	static int zoom;
	static double coordinateX;
	static double coordinateY;
	private double differenceX; 
	private double differenceY;
	
	private JButton zoomInButton;
	private JButton zoomOutButton;
	private JButton rightButton;
	private JButton leftButton;
	private JButton upButton;
	private JButton downButton;
	private JPanel mapDrawPanel;
	
	private Icon eventImage;
	private Icon userImage;
	
	public MapPanel( int userID) throws Exception
	{	
		this.userID = userID;

		
		//Setting Layout
		setLayout( null);
		
		//Creating Images
		eventImage = new ImageIcon( "Flag.png");
		userImage = new ImageIcon( "Arrow.png");
		
		//Connectting Database
		eventDB = new MySQLEvent();
		userDB = new MySQLUser();
		friendshipDB = new MySQLFriendship();
		
		//Creating Map Draw Panel
		mapDrawPanel = new JPanel();
		
		//Creating Buttons
		zoomInButton = new JButton( new ImageIcon( "Plus.png"));
		zoomInButton.addActionListener( this);
		zoomInButton.setBounds( 10, 90, 25, 25);
		zoomOutButton = new JButton( new ImageIcon( "Minus.png"));
		zoomOutButton.addActionListener( this);
		zoomOutButton.setBounds( 10, 120, 25, 25);
		rightButton = new JButton( new ImageIcon( "Right.png"));
		rightButton.addActionListener( this);
		rightButton.setBounds( 60, 30, 25, 25);
		add( rightButton);
		leftButton = new JButton( new ImageIcon( "Left.png"));
		leftButton.addActionListener( this);
		leftButton.setBounds( 10, 30, 25, 25);
		upButton = new JButton( new ImageIcon( "Up.png"));
		upButton.addActionListener( this);
		upButton.setBounds( 35, 5, 25, 25);
		downButton = new JButton( new ImageIcon( "Down.png"));
		downButton.addActionListener( this);
		downButton.setBounds( 35, 55, 25, 25);
		
		
		//Initializing zoom and coordiantes
		zoom = 13;
		coordinateY = 39.870000;
		coordinateX = 32.750000;
		
		//Creating Map URL
		mapURL = new URL( "http://maps.google.com/maps/api/staticmap?" + 
				          "center=" + coordinateY + "," + coordinateX + "&zoom=" + zoom + "&size=600x450&maptype=hybrid&sensor=false");
		

		//Creating Map Image
		img = ImageIO.read( mapURL);
		ImageIcon newImage = new ImageIcon( img);
		imageObserver = newImage.getImageObserver();
		
		
		//Calculating Differences and bounds
		differenceX = 0.051356;
		differenceY = 0.02950;
		
		
		drawPins();
		
		
		addMouseListener( this);
		addMouseWheelListener( this);
	}
	
	public void paintComponent( Graphics g)
	{
		//Drawing Google Map to the Panel
		mapDrawPanel.paint( g);
		g.drawImage( img, 0, 0, this.getWidth(), this.getHeight(), imageObserver );
	}
	
	//The Method for zoom in the map
	public void zoomInMap()
	{
		if( zoom != 17 )
		{
			//When user zoom ýn the map, the map will be drawn again
			zoom += 1;
			try 
			{
				mapURL = new URL( "http://maps.google.com/maps/api/staticmap?" + 
				                  "center=" + coordinateY + "," + coordinateX + "&zoom=" + zoom + "&size=600x450&maptype=hybrid&sensor=false");
			} 
			catch (MalformedURLException e1) 
			{
				e1.printStackTrace();
			}
			try
			{
				img = ImageIO.read( mapURL);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			ImageIcon newImage = new ImageIcon( img);
			imageObserver = newImage.getImageObserver();
			repaint();
			removeAll();
			drawPins();
		}
	}
	
	//The method for zoom out the map
	public void zoomOutMap()
	{
		if( zoom != 12 )
		{
			//When user zoom out the map, the map will be drawn againg
			zoom += -1;
			try 
			{
				mapURL = new URL( "http://maps.google.com/maps/api/staticmap?" + 
				          		  "center=" + coordinateY + "," + coordinateX + "&zoom=" + zoom + "&size=600x450&maptype=hybrid&sensor=false");
			} 
			catch (MalformedURLException e1) 
			{
				e1.printStackTrace();
			}
			try
			{
				img = ImageIO.read( mapURL);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			ImageIcon newImage = new ImageIcon( img);
			imageObserver = newImage.getImageObserver();
			repaint();
			removeAll();
			drawPins();
		}
	}
	
	public void actionPerformed( ActionEvent e)
	{	
		//Zoom ýn
		if( ((JButton) e.getSource()).equals( zoomInButton) )
		{
			zoomInMap();
		}
		//Zoom out
		else if( ((JButton) e.getSource()).equals( zoomOutButton) )
		{
			zoomOutMap();
		}
		
	}
	
	public void mouseClicked(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

	//Getting first location of the Mouse
	public void mousePressed(MouseEvent m) 
	{
		firstX = m.getX();
		firstY = m.getY();
	}

	//Getting last location of the Mouse and draw pins again
	public void mouseReleased(MouseEvent m) 
	{
		removeAll();
		
		//Calculating Change of the Coordinates according to zoom value
		if( zoom == 13 )
		{
			coordinateY += ( m.getY() - firstY ) * 0.000110;
			coordinateX -= ( m.getX() - firstX ) * 0.000148;
		}
		else if( zoom == 14 )
		{
			coordinateY += ( m.getY() - firstY ) * 0.000055;
			coordinateX -= ( m.getX() - firstX ) * 0.000074;
		}
		else if( zoom == 15 )
		{
			coordinateY += ( m.getY() - firstY ) * 0.0000275;
			coordinateX -= ( m.getX() - firstX ) * 0.000037;
		}
		else if( zoom == 16 )
		{
			coordinateY += ( m.getY() - firstY ) * 0.0000137;
			coordinateX -= ( m.getX() - firstX ) * 0.0000185;
		}
		else if( zoom == 17 )
		{
			coordinateY += ( m.getY() - firstY ) * 0.0000068;
			coordinateX -= ( m.getX() - firstX ) * 0.0000092;
		}
		
		//Drawing the map again
		try 
		{
			mapURL = new URL( "http://maps.google.com/maps/api/staticmap?" + 
			          		  "center=" + coordinateY + "," + coordinateX + "&zoom=" + zoom + "&size=600x450&maptype=hybrid&sensor=false");
		} 
		catch (MalformedURLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			img = ImageIO.read( mapURL);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		ImageIcon newImage = new ImageIcon( img);
		imageObserver = newImage.getImageObserver();
		repaint();		
		
		drawPins();
	}
	
	public void drawPins()
	{
		//Calculating difference of changing values of coordinates according to zoom
		if( zoom == 13 )
		{
			differenceX = 0.051356;
			differenceY = 0.02950;
		}
		else if( zoom == 14 )
		{
			differenceX = 0.026;
			differenceY = 0.015;
		}
		else if( zoom == 15 )
		{
			differenceX = 0.051356/4;
			differenceY = 0.02950/4;
		}
		else if( zoom == 16 )
		{
			differenceX = 0.051356/8;
			differenceY = 0.02950/8;
		}
		else if( zoom == 17 )
		{
			differenceX = 0.051356/16;
			differenceY = 0.02950/16;
		}
		
		//Getting Pins
		try 
		{
			//Getting Visible Events for the User
			int[] events = userDB.getVisibleEvents( userID);
		
			for( int count = 0; count < events.length; count++ )
			{	
				{
					//Calculating Place of the Pins
					double x = ((eventDB.getCoordinateX( events[ count]) - coordinateX) / differenceX) * 347 + 337;
					double y = ((coordinateY - eventDB.getCoordinateY( events[ count])) / differenceY) * 273 + 263;
					
					//Adding Pin on the map
					JButton b = new JButton( eventImage);
					b.setBounds( (int) x, (int) y, 20, 20);
					b.setContentAreaFilled( false);
					b.setBorderPainted( false);
					b.setToolTipText( eventDB.getEventName( events[ count]));
					this.add( b);
				}
			}
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
		try 
		{
			//Getting friends of the user
			int[] friends = friendshipDB.getFriendsID( userID);
			
			for( int count = 0; count < friends.length; count++ )
			{
				{
					//Calculating location of the Friends Pin
					double x = ((userDB.getUserCoordinateX( friends[ count]) - coordinateX) / differenceX) * 347 + 337;
					double y = ((coordinateY - userDB.getUserCoordinateY( friends[ count])) / differenceY) * 273 + 263;
					
					//Addin Pins on the map
					JButton b = new JButton( userImage);
					b.setBounds( (int) x, (int) y, 20, 20);
					b.setContentAreaFilled( false);
					b.setBorderPainted( false);
					b.setToolTipText( userDB.getUserName( friends[ count]));
					this.add( b);
				}
			}
			
			//Calculating location of the user
			double x = ((userDB.getUserCoordinateX( userID) - coordinateX) / differenceX) * 347 + 337;
			double y = ((coordinateY - userDB.getUserCoordinateY( userID)) / differenceY) * 273 + 263;
			
			//Adding the pin of the user
			JButton b = new JButton( userImage);
			b.setBounds( (int) x, (int) y, 20, 20);
			b.setContentAreaFilled( false);
			b.setBorderPainted( false);
			b.setToolTipText( userDB.getUserName( userID));
			this.add( b);
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}

	public void mouseWheelMoved(MouseWheelEvent mW) 
	{
		if( mW.getWheelRotation() < 0 )
		{
			zoomInMap();
		}
		else if( mW.getWheelRotation() > 0 )
		{
			zoomOutMap();
		}
	}

}
