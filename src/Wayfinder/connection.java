package Wayfinder;

public class connection {

	private connectionType Type;
	private Waypoint To;
	private Double Distance;
	
	public connection(Waypoint To, connectionType Type) {
		this.To = To;
		this.Type=Type;
	}

	public void setDistance(Double dis) {this.Distance=dis;}
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
	
	@Override
	public String toString() {
		return To.getName() + " (" + Type.getName() + ")"; 
	}
	

}
