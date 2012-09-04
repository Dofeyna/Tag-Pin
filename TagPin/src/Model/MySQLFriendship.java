package Model;

/**
 * @(#) MySQLFriendship Class holds accessors,mutators and methods for database records related to friend requests
 *
 * 
 *
 * @ Semih Akar - Melih Ayyildiz
 * @version 1.00 
 */

import View.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class MySQLFriendship {
	
	//Properties
	static Connection conn = null;
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "tagpindatabase";
	static String properties= "?useUnicode=true&characterEncoding=utf8";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
    static String password = "";
    static ResultSet result; 
    
    private MySQLUser users;
    
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
    
    //Sending Friend request to the given ID
    public void requestFriendship( int userID1, int userID2, String ID1fullName, String ID2fullName) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID1);
    	
		if( checkFriendship( userID1, userID2) == false )
		{
	    	st = connectDB();
			resultSet = st.executeQuery("select * from friendship");
			
			resultSet.moveToInsertRow();		
			resultSet.updateInt( "ID1", userID1);
			resultSet.updateInt( "ID2", userID2);
			resultSet.updateString( "ID2fullName", ID2fullName);
			resultSet.updateInt( "condition", 0);
			resultSet.insertRow();
			
			resultSet.moveToInsertRow();
			resultSet.updateInt( "ID1", userID2);
			resultSet.updateInt( "ID2", userID1);
			resultSet.updateString( "ID2fullName", ID1fullName);
			resultSet.updateInt( "condition", 2);
			resultSet.insertRow();
		}
    }
    
    //Checking the ID if the user is friend with that ID or not
    public boolean checkFriendship( int userID1, int userID2) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID1);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "ID2") == userID2 )
			{
				return true;
			}
		}
		
		return false;
    }
    
    //
    public void acceptFriendship( int userID1, int userID2) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID1);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "ID2") == userID2 )
			{
				resultSet.updateInt( "condition", 1);
				resultSet.updateRow();
			}
		}
		
		st = connectDB();
		resultSet = st.executeQuery("select * from friendship where ID1 = " + userID2);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "ID2") == userID1 )
			{
				resultSet.updateInt( "condition", 1);
				resultSet.updateRow();
			}
		}
	}
    
    //
    public void denyFriendship( int userID1, int userID2) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID1);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "ID2") == userID2 )
			{
				resultSet.deleteRow();
			}
		}
		
		st = connectDB();
		resultSet = st.executeQuery("select * from friendship where ID1 = " + userID2);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "ID2") == userID1 )
			{
				resultSet.deleteRow();
			}
		}
	}
    
    //Getter Methods
    public int[] getFriendsID( int userID) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID);
		
		int numberOfFriends = 0;
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "condition") == 1 )
			{
				numberOfFriends += 1;
			}
		}
		
		int[] friendsID = new int[ numberOfFriends];
		
		resultSet.first();
		
		int count = 0;
		
		while( count < numberOfFriends  )
		{
			if( resultSet.getInt( "condition") == 1 )
			{
				friendsID[ count] = resultSet.getInt( "ID2");
				count++;
			}
			
			resultSet.next();
		}
		
		return friendsID;
    }
    
    public String[] getFriendsName( int userID) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID);
		resultSet.first();
		
		users = new MySQLUser();
    	
    	int[] friendsID = getFriendsID( userID);
    	String[] friendsName = new String[ friendsID.length];
    	
    	int count = 0;
    	
    	while( count < friendsID.length )
    	{
    		friendsName[ count] = users.getUserName( friendsID[ count]) + " " + users.getUserSurName( friendsID[ count]);
    		count++;
    		resultSet.next();
    	}
    	
    	return friendsName;
    }  
    
    //
    public void checkRequests( int userID) throws Exception
    {
    	Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from friendship where ID1 = " + userID);
		
		while( resultSet.next() )
		{
			if( resultSet.getInt( "condition") == 2 )
			{
				new NotificationFrame( resultSet.getInt( "ID2"), userID);
			}
		}
    }
    
}
