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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vimukthi Weerasiri
 */
public class Trip {


	
//
	private ArrayList<Waypoint> waypoints;
	private Map<Integer, Station> stations;
	//making singleton
	public static ArrayList<Trip> trip = new ArrayList<>();

	private Trip(int routeId) {
		routeID = routeId;
		setTripData();
		estimatedArrivalTimeForStations();
	}

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

	//////////////////////
	public Waypoint getNearestWaypoint(int latitude, int longitude) {
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

	public void execute(LocationBox locationBox) {
	}

    private int routeID;//
    private int[] routeStationIdList;//
    private String[] estimatedArrivalTimeForStations;
    private String[] estimatedArrivalTime;
    private boolean[] passedStationIds;

    private float startedLatitude;
    private float startedLongitude;
    private int passedCheckingFactor;
    private int nearestLocationId;

    private float[][] wayPoints; //
    private int[][] IdPreviousNext;
    private static TripDataReader dataReader=null;
    
    static{
        try {
            dataReader =new TripDataReader();
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //making singleton
   

    //////////////////////
    public int getIdOfNearestLocation(int latitude, int longitude) {
        //get ID from the array
        return 0;
    }

    
    private void setTripData() {
        //initialize routeStationIds
        try {
            this.routeStationIdList=dataReader.getStationsList(this.routeID);
            this.passedStationIds=new boolean[routeStationIdList.length];
            for(int i=0;i<passedStationIds.length;i++)  passedStationIds[i]=false;
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize waypoints
        try {
            this.wayPoints=dataReader.getWayPoints(routeID);
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //initialize IdpreviousNext
        //initialize IdpreviousNext
    }

   
}
