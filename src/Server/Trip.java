/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import Database.TripDataReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vimukthi Weerasiri
 */
public class Trip {
	private ArrayList<Waypoint> waypoints;
	private Map<Integer, Station> stations;
	//making singleton
	public static ArrayList<Trip> trip = new ArrayList<>();

        //singleton maker
	public static Trip getInstance(int routeID) {
		for (int i = 0; i < trip.size(); i++) {
			if (trip.get(i).routeID == routeID) {
				return trip.get(i);
			}
		}
		Trip newTrip = new Trip(routeID);
		trip.add(newTrip);
		return newTrip;
	}
        
        //constructor
        private Trip(int routeId) {
		routeID = routeId;
		setTripData();
		estimatedArrivalTimeForStations();
	}
        
        //fill data to trip object
        private void setTripData() {
         //initialize station data
            try {
                ResultSet rs=dataReader.getStationsData(routeID);
                while (rs.next()) {
                    stations.put(rs.getInt(6), new Station(rs.getInt(1),new Coordinate(rs.getFloat(2),rs.getFloat(3)),new Coordinate(rs.getFloat(4),rs.getFloat(5))));
                }
                this.passedStationIds=new boolean[stations.size()];
            } catch (SQLException ex) {
                Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        //initialize waypoints
        try {
            ResultSet rs_1=dataReader.getWayPointsData(routeID);
            while(rs_1.next()){
                waypoints.add(new Waypoint(rs_1.getInt(1),rs_1.getInt(2),new Coordinate(rs_1.getFloat(3),rs_1.getFloat(4)),rs_1.getDate(5),rs_1.getInt(6),rs_1.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //initialize IdpreviousNext
        //initialize IdpreviousNext
    }
        
	//////////////////////
        //execute using latest location that came
        public void execute(LocationBox locationBox) {
            Waypoint waypoint = getNearestWaypoint(locationBox.getLatitude(),locationBox.getLongitude());
	}
        
	public Waypoint getNearestWaypoint(float latitude, float longitude) {
		float lon, lat, distance, min = Float.MAX_VALUE;
		Waypoint temp = null, nearest = null;
		Coordinate location = null;

		//compare with all waypoint positions
		Iterator<Waypoint> iter = waypoints.iterator();
		while(iter.hasNext()) {
			temp = iter.next();
			location = temp.getLocation();
			lon = location.getLongitude();
			lat = location.getLatitude();

			//calcuate (square of) distance
			distance = (lon - longitude) * (lon - longitude)
					+ (lat - latitude) * (lat - latitude);
			//shortest distance?
			if (distance < min) {
				min = distance;
				nearest = temp;
			}
		}
		//returns null if no waypoints were found
		return nearest;
	}

	public float[] getCordinatesOfTheStations(int stationID, int latitude, int longitude) {
		return null;
	}

	public void countDelayToStations() {
		
	}

	public void didStationPassed() {
	}

	public void estimateTimeForAllStations() {
	}

	public void getTimeOfNeareestLocation() {
	}

	public void updateRasberryHandler() {
		RasberryHandler.getInstance().updateFromTrip(routeID, routeStationIdList, passedStationIds, estimatedArrivalTime);
	}

	
	private void estimatedArrivalTimeForStations() {
	}

	

    private int routeID;//
    private int[] routeStationIdList;           //removed
    private String[] estimatedArrivalTimeForStations;
    private String[] estimatedArrivalTime;
    private boolean[] passedStationIds;         //is this required?

    private float startedLatitude;
    private float startedLongitude;
    private int passedCheckingFactor;
    private int nearestLocationId;

    private float[][] wayPoints;            //removed
    private int[][] IdPreviousNext;         //removed
    private static TripDataReader dataReader=null;
    
    static{
        try {
            dataReader =new TripDataReader();
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    //////////////////////
    public int getIdOfNearestLocation(int latitude, int longitude) {
        //get ID from the array
        return 0;
    }

    
    

   
}
