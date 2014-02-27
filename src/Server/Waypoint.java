package Server;

/**
 * @author Janaka
 */
public class Waypoint {
	private float estimateReachTime;
	private float actualReachTime;
	private Coordinate location;
	private int id;
	private int routeID;
	private int prevStationID;
	private int nextStationID;

	/**
	 * @return the estimateReachTime
	 */
	public float getEstimateReachTime() {
		return estimateReachTime;
	}

	/**
	 * @return the actualReachTime
	 */
	public float getActualReachTime() {
		return actualReachTime;
	}

	/**
	 * @param actualReachTime the actualReachTime to set
	 */
	public void setActualReachTime(float actualReachTime) {
		this.actualReachTime = actualReachTime;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the routeID
	 */
	public int getRouteID() {
		return routeID;
	}

	/**
	 * @return the prevStationID
	 */
	public int getPrevStationID() {
		return prevStationID;
	}

	/**
	 * @return the nextStationID
	 */
	public int getNextStationID() {
		return nextStationID;
	}

	/**
	 * @return the location
	 */
	public Coordinate getLocation() {
		return location;
	}
}
