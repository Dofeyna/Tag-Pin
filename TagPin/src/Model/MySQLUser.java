package Model;

/**
 * @(#) MySQLUser: Class holds accessors,mutators and methods for database records related to user
 *
 * 
 *
 * @Semih Akar - Melih Ayyildiz
 * @version 1.00 
 */

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.TextArea;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MySQLUser
{
	//Properties
	static Connection conn = null;
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "tagpindatabase";
	static String properties= "?useUnicode=true&characterEncoding=utf8";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
    static String password = "";
    static ResultSet reseult; 
    
    private MySQLFriendship friendshipDB;
    private MySQLEvent eventDB;
	
    //Connecting to database
	public Statement connectDB() throws Exception
	{
		Class.forName(driver).newInstance();
        conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);

        return (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	//Disconnecting from database
	public void disconnectDB() throws Exception
    {

        conn.close();
    }
	
	//Setting the columns of the database by getting the parameters and creating the user with given name
	public void createUser( String name, String surName, String email, String password,
							int secretQuestion, String answer, String birthday) throws Exception
	{
		Statement st = connectDB();
		ResultSet resultSet = st.executeQuery( "select * from users");
		resultSet.moveToInsertRow();
		
		resultSet.updateString( "name", name);
		resultSet.updateString( "surName", surName);
		resultSet.updateString( "email", email);
		resultSet.updateString( "password", password);
		resultSet.updateInt( "secretQuestion", secretQuestion);
		resultSet.updateString( "answer", answer);
		resultSet.updateString( "birthday", birthday);
		resultSet.insertRow();
	}
	
	//Getter Methods
	public int getUserID( String email) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where email = '"+email+"'");
        
        if( !resultSet.next() )
        {
        	return -1;
        }
        else
        {
        	return resultSet.getInt( "ID");
        }	
    }
	
	public String getUserName( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID = " + userID);
        resultSet.first();
        
        return resultSet.getString( "name");
    }
	
	public String getUserSurName( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID = " + userID);
        resultSet.first();
        
        return resultSet.getString( "surName");
    }
	
	public String getUserEmail( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "email");
    }
	
	public String getUserPassword( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "password");
    }
	
	public int getUsersecretQuestion( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getInt( "secretQuestion");
    }
	
	public String getUserAnswer( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "answer");
    }
	
	public String getUserBirthday( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "birthday");
    }
	
	public String getUserAboutMe( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "aboutMe");
    }
	
	public String getUserPhone( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "phone");
    }
	
	public String getUserSchool( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "school");
    }
	
	public double getUserCoordinateX( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getDouble( "coordinateX");
    }
	
	public double getUserCoordinateY( int userID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getDouble( "coordinateY");
    }
	
	public String getUserPhotoURL( int userID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "photoURL");
	}
	
	public String getUserCreatedEvents( int userID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        return resultSet.getString( "createdEvents");
	}
	
	public int[] getVisibleEvents( int userID) throws Exception
	{
		String allEvents = "";
		
		friendshipDB = new MySQLFriendship();
		
		int[] friends = friendshipDB.getFriendsID( userID);
		
		for( int count = 0; count < friends.length; count++ )
		{
			if( getUserCreatedEvents( friends[ count]) != null )
			{
				allEvents += getUserCreatedEvents( friends[ count]);
			}
		}
		
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        allEvents += getUserCreatedEvents( userID);
        
        int[] events;
        
        int numOfEvents = allEvents.replaceAll("[^,]", "").length();
        
        events = new int[numOfEvents];
        
        for( int count = 0; allEvents.indexOf( ',') != -1; count++ )
        {
        	events[ count] = Integer.parseInt( allEvents.substring( 0, allEvents.indexOf( ',')));
        	allEvents = allEvents.substring( allEvents.indexOf( ',') + 1);
        }
        
        return events;
	}
	
	public String[] getVisibleEventsName( int userID) throws Exception
	{		
		eventDB = new MySQLEvent();
		
		int[] events = getVisibleEvents( userID);
		
		String[] eventNames = new String[ events.length];
			
		for( int count = 0; count < events.length; count++ )
		{
			eventNames[ count] = eventDB.getEventName( events[ count]); 
		}
		
		return eventNames;
	}
	
	//
	public int[] findUserIDByName( String name) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where name like '%" + name + "%'");
        
        int numberOfUsers = 0;
		
		while( resultSet.next() )
		{
			numberOfUsers += 1;
		}
		
		int[] friendsID = new int[ numberOfUsers];
		
		resultSet.first();
		
		int count = 0;
		
		while( count < numberOfUsers )
		{
			friendsID[ count] = resultSet.getInt( "ID");
			resultSet.next();
			count++;
		}
		
		return friendsID;
	}
	
	//
	public String[] findUserByName( String name) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where name like '" + name + "%'");
        resultSet.first();
    	
    	int[] friendsID = findUserIDByName( name);
    	String[] friendsName = new String[ friendsID.length];
    	
    	int count = 0;
    	
    	while( count < friendsID.length )
    	{
    		friendsName[ count] = getUserName( friendsID[ count]) + " " + getUserSurName( friendsID[ count]);
    		count++;
    		resultSet.next();
    	}
    	
    	return friendsName;
	}
	
	//Setter Methods
	public void setUserPassword( int userID, String password) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateString( "password", password);
        resultSet.updateRow();
	}
	
	public void setUserAboutMe( int userID, String info) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateString( "aboutMe", info);
        resultSet.updateRow();
	}
	
	public void setUserPhone( int userID, String phone) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateString( "phone", phone);
        resultSet.updateRow();
	}
	
	public void setUserSchool( int userID, String school) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateString( "school", school);
        resultSet.updateRow();
	}
	
	public void setUserCoordinateX( int userID, double coordinateX ) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateDouble( "coordinateX", coordinateX);
        resultSet.updateRow();
	}
	
	public void setUserCoordinateY( int userID, double coordinateY) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateDouble( "coordinateY", coordinateY);
        resultSet.updateRow();
	}
	
	public void setUserPhotoURL( int userID, String URL) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        resultSet.updateString( "photoURL", URL);
        resultSet.updateRow();
	}
	
	//
	public void addUserAttendEvents( int userID, int eventID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        if( resultSet.getString( "attendEvents") == null )
        {
        	resultSet.updateString( "attendEvents", eventID + ",");
        }
        else
        {
        	resultSet.updateString( "attendEvents", resultSet.getString( "attendEvents") + eventID + ",");
        }
        resultSet.updateRow();
	}
	
	//
	public void addUserCreatedEvents( int userID, int eventID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where ID =  " + userID);
        resultSet.first();
        
        if( resultSet.getString( "createdEvents") == null )
        {
        	resultSet.updateString( "createdEvents", eventID + ",");
        }
        else
        {
        	resultSet.updateString( "createdEvents", resultSet.getString( "createdEvents") + eventID + ",");
        }
        resultSet.updateRow();
	}
	
	//
	public boolean checkPassword( int userID, String password) throws Exception
	{
		if( userID == -1 )
		{
			return false;
		}
		else
		{
			Statement st = connectDB();
	        ResultSet resultSet = st.executeQuery("select * from users where ID = "+userID);
	        resultSet.first();
	        
	        String realPassword;
	        realPassword = resultSet.getString( "password");
	        
	        if( realPassword.equals( password) )
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
		}
	}
	
	//
	public boolean checkEmail( String email) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from users where email = '"+email+"'");
        
        if( !resultSet.next() && email.indexOf( "@ug.bilkent.edu.tr") != -1 )
        {
        	return true;
        }
        else
        {
        	return false;
        }
	}
	
}
