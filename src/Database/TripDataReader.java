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
public class TripDataReader {
    
    private Connection conn;
    private String hostname;
    private String dbName;
    private String username;
    private String password;
    private static final Logger logger = Logger.getLogger("Database");

    public TripDataReader() throws SQLException {
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
        int[] output;
        Statement select = conn.createStatement();
	ResultSet result = select.executeQuery("SELECT stationID FROM route_staions WHERE routeID = '" + routeID + "' ORDER BY stationsOrder  ASC;");
        //have to convert the reslutset to integer array
        ArrayList<Integer> temp = new ArrayList<>();
        while (result.next()) {
            temp.add(result.getInt(1));
        }
        output = new int[temp.size()];
        for(int i=0, len = temp.size(); i < len; i++)   output[i] = temp.get(i);
        return output;
    }
    
    public float[][] getWayPoints(int routeID)throws SQLException{
        float[][] output;
        Statement select = conn.createStatement();
	ResultSet result = select.executeQuery("SELECT latitude,longitude FROM waypoint WHERE routeID = '" + routeID + "' ;");
        ArrayList<Float> temp1 = new ArrayList<>();
        ArrayList<Float> temp2 = new ArrayList<>();
        
        while (result.next()) {
            temp1.add(result.getFloat(1));
            temp2.add(result.getFloat(2));
        }
        
        output = new float[2][temp1.size()];
        for(int i=0, len = temp1.size(); i < len; i++){
            output[0][i] = temp1.get(i);
            output[1][i] = temp1.get(i);
        }
        //output[0] has lattudes, output[1] has longitudes
        return output;
    }
        
    }

    

