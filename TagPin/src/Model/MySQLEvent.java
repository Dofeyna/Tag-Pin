package Model;

/**
 * @(#) MySQLEvent Class holds accessors,mutators and methods for database records related to events
 *
 * 
 *
 * @ Semih Akar - Melih Ayyildiz
 * @version 1.00 
 */

import View.GeneralFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MySQLEvent
{
	static Connection conn = null;
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "tagpindatabase";
	static String properties= "?useUnicode=true&characterEncoding=utf8";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
    static String password = "";
    static ResultSet result; 
	
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
	
	//Setting the columns of the database by getting the parameters and creating the event with given name
	public void createEvent( String eventName, String year, String month, String day,
							 String eventNote, int creatorID, double coordinateX, double coordinateY) throws Exception
	{
		Statement st = connectDB();
		ResultSet resultSet = st.executeQuery("select * from events");
		resultSet.moveToInsertRow();
		resultSet.updateString("name", eventName);
		resultSet.updateString("eventNote", eventNote);
		resultSet.updateString( "year", year);
		resultSet.updateString( "month", month);
		resultSet.updateString( "day", day);
		resultSet.updateInt( "creatorID", creatorID);
		resultSet.updateDouble( "coordinateX", coordinateX);
		resultSet.updateDouble( "coordinateY", coordinateY);
		resultSet.insertRow();
	}
	
	//Getter Methods
	public String getEventName( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet = st.executeQuery("select * from events where ID = "+eventID);
        resultSet.first();
        
        return resultSet.getString( "name");
    }
	
	public int getYear( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID= "+eventID);
        resultSet.first();
        
        return resultSet.getInt( "year");
    }
	
	public int getMonth( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID= "+eventID);
        resultSet.first();
        
        return resultSet.getInt( "month");
    }
	
	public int getDay( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID= "+eventID);
        resultSet.first();
        
        return resultSet.getInt( "day");
    }
	
	public String getAttendants( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        return resultSet.getString( "attendants");
    }
	
	public int getCreator( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        return resultSet.getInt( "creatorID");
    }
	
	public String getEventNote( int eventID) throws Exception
    {
        Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        return resultSet.getString( "eventNote");
    }
	
	public double getCoordinateX( int eventID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        return resultSet.getDouble( "coordinateX");
	}
	
	public double getCoordinateY( int eventID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        return resultSet.getDouble( "coordinateY");
	}
	
	public int getNumOfAttendants( int eventID) throws Exception
	{
		Statement st = connectDB();
        ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
        resultSet.first();
        
        if( resultSet.getString( "attendants") == null )
        {
        	return 0;
        }
        
        int numOfAttendants = resultSet.getString( "attendants").replaceAll("[^,]", "").length();
        
        return numOfAttendants;
	}
	
	//Setter Methods
	public void setEventName( int eventID, String eventName) throws Exception
	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     
	     resultSet.updateString( "name", eventName);
	     resultSet.updateRow();
	}
	
	public void setEventYear( int eventID, int year) throws Exception

	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     
	     resultSet.updateInt( "year", year);
	     resultSet.updateRow();
	}
	
	public void setEventMonth( int eventID, int month) throws Exception
	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     
	     resultSet.updateInt( "month", month);
	     resultSet.updateRow();
	}
	
	public void setEventDay( int eventID, int day) throws Exception
	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     
	     resultSet.updateInt( "day", day);
	     resultSet.updateRow();
	}
	
	public void setAttendant( int eventID, int userID) throws Exception
	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     if( resultSet.getString( "attendants") != null )
	     {
	    	 resultSet.updateString( "attendants", resultSet.getString( "attendants") + userID + ",");
	     }
	     else
	     {
	    	 resultSet.updateString( "attendants", userID + ","); 
	     }
	     
	   	 resultSet.updateRow();
	}
	
	public void setEventNote( int eventID, String eventNote) throws Exception
	{
		 Statement st = connectDB();
	     ResultSet resultSet =st.executeQuery("select * from events where ID =  "+eventID);
	     resultSet.first();
	     
	     resultSet.updateString( "eventNote", eventNote);
	     resultSet.updateRow();
	}	
	
}
