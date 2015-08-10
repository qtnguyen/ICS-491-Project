/*
 * DBConnection3.java
 * 
 * DBConnection version 3 - Final version
 * 
 * This program will load JDBC driver and connect to the login and 
 * the PS4 game databases that are stored locally for us in Login4.java
 * 
 * @group:		LulzSec
 * 
 * @author:		Quynh-Tram Nguyen, Spencer Bishop, Andy Omori
 * 
 * @course:		ICS 491
 * 
 * @date:		August 9, 2015
 *  
 */

//imports for database connections
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection3 
{
    //Variables 
    private Connection dbConnectLogin;
    private Connection dbConnectPS4;
	
    //Create a connection to the login database
    public Connection connectLoginDatabase() throws SQLException
    {
    	try 
    	{
    	    //Load JDBC driver to setup connections for databases
    	    Class.forName("com.mysql.jdbc.Driver");  
    	    System.out.println("Connection Success");
    
    	    //Connect to the login database that is stored locally
    	    dbConnectLogin = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
    	    System.out.println("Database Connected");
    	}
    	catch(Exception e) {
    		//If connection failed, an error message will appears on the 
    		//console to notify connection failure
    		System.err.println("Database Connection Failure" + e);
    	}
        return dbConnectLogin;
    }
    /*
    *  PS4 database currently not used.
    *  Connection will be needed when search becomes functional. 
    *  Leaving in for testing purposes
    */
    //Create a connection to the PS4 database
    public Connection connectPS4Database() throws SQLException
    {
    	try 
    	{
    	    //Load JDBC driver to setup connections for databases
    	    Class.forName("com.mysql.jdbc.Driver");  
    	    System.out.println("Connection Success");
    
    	    //Connect to the PS4 database that is stored locally
    	    dbConnectPS4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ps4","root","");
    	    System.out.println("Database Connected");
    	}
    	catch(Exception e) {
    	//If connection failed, an error message will appears
    	//on the console to indicate connection failure
    	System.err.println("Database Connection Failure" + e);
    	}
        return dbConnectPS4;
    }
}
