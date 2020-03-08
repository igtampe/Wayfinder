package Wayfinder;

public class connection {

	private connectionType Type;
	private Waypoint To;
	private Double Distance;
	
	/**
	 * Creates a connection
	 * @param To The waypoint it's routing to
	 * @param Type Type of Connection
	 */
	public connection(Waypoint To, connectionType Type) {
		this.To = To;
		this.Type=Type;
	}

	/**
	 * Set the distance between the origin and the destination
	 * @param dis
	 */
	public void setDistance(Double dis) {this.Distance=dis;}
	
	/**
	 * @return Distnace between the two waypoints this connection connects
	 */
	public double getDistance() {return Distance;}
	public connectionType getType() {return Type;}
	public Waypoint getWaypoint() {return To;}
	
	@Override
	public boolean equals(Object obj) {
		connection OtherCon;
		//Cast obj to Waypoint. If it isn't one, this will fail and return false.
		try {OtherCon = (connection) obj;} catch (Exception e) {return false;}

		//Compare their waypoint.
		return this.getWaypoint().equals(OtherCon.getWaypoint());
		
	}
	
	/**
	 * @return Destination (Type of Connection)
	 */
	public String toString() {return To.getName() + " (" + Type.getName() + ")";}
	

}
