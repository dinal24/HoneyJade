/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eranda
 */
public class TipDataReader {
    
    private Connection conn;
    private String hostname;
    private String dbName;
    private String username;
    private String password;
    private static final Logger logger = Logger.getLogger("Database");

    public TipDataReader() throws SQLException {
	this.hostname = "localhost";
	this.dbName = "gpsdata";
	this.username = "root";
	this.password = "";

	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (Exception e) {
	    throw new SQLException("Error loading MySQL driver");
	}
	connectToDatabase();
    }
    
       private void connectToDatabase() throws SQLException {
	StringBuilder connectionUrl = new StringBuilder("jdbc:mysql://");
	connectionUrl.append(hostname);
	connectionUrl.append("/").append(dbName);
	logger.log(Level.INFO, connectionUrl.toString());
	conn = DriverManager.getConnection(connectionUrl.toString(),
		username, password);
    }
       
    public int[] getStationsList(int routeID) throws SQLException{
        Statement select = conn.createStatement();
	ResultSet result = select.executeQuery("SELECT stationID FROM route_staions WHERE routeID = '" + routeID + "' ORDER BY stationsOrder  ASC;");
        //have to convert the reslutset to integer array
	}
    }

    public 
}
