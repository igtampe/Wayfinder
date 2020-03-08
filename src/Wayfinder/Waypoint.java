package Wayfinder;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Waypoint {

	/**
	 * Name of the waypoint
	 */
	private String Name;
	
	private Point2D.Double Position;
	private Boolean drawn;
	private ArrayList<connectionType> ConTypes = new ArrayList<connectionType>();
	
	/**
	 * All connections this waypoint has to other waypoints.
	 */
	private ArrayList<connection> Connections = new ArrayList<connection>();
	
	public Waypoint(String Name, int X, int Y) {
		this.Name=Name;
		this.Position=new Point2D.Double(X,Y);
		drawn=false;
	}
	
	public void setDrawn(Boolean set) {drawn=set;}
	public Boolean isDrawn() {return drawn;}
	
	
	/**
	 * Finds the shortest route between this waypoint and the specified waypoint
	 * @param otherWaypoint The destination
	 * @param maxConnections Maximum number of connections
	 * @return A string which helps u get to where u need to go
	 * @throws Exception Maybe
	 */
	public route RouteTo(Waypoint otherWaypoint, int maxConnections) {
		
		ArrayList<Waypoint> Doot = new ArrayList<Waypoint>();
		Doot.add(this);
		return RouteTo(otherWaypoint, maxConnections, Doot , new ArrayList<connection>());
	}

	/**
	 * Internal routing recursive function
	 * @param OtherWaypoint Destination of this routing
	 * @param PreviousWaypoints Previous waypoints used to get to where we are
	 * @param PreviousConnections Previous connections used to get to where we are
	 * @return
	 */
	private route RouteTo(Waypoint OtherWaypoint, int maxConnections, ArrayList<Waypoint> PreviousWaypoints, ArrayList<connection> PreviousConnections) {

		route LeadingPossibleSolution = new route();
		if (PreviousWaypoints.size()>maxConnections) {return LeadingPossibleSolution;} //Return if we have too many connections
		
		//If this is the waypoint we're routing to, we're done.
		if(this.equals(OtherWaypoint)) {
			route PossibleRoute = new route(PreviousWaypoints,PreviousConnections);
			VeloxWayfinder.AlternativeRoutes.add(PossibleRoute);
			//System.out.println("(" + PossibleRoute.count() + ", "+ Math.floor(PossibleRoute.totalDistnace()) +"m) " + PossibleRoute.toString());
			return PossibleRoute;
		}

		//Add the current waypoints to the list of waypoints.
		PreviousWaypoints.add(this);
		
		//For each waypoint in our connected waypoints...
		for (connection CurConnection : Connections) {
			
			//if the current waypoint isn't in our previous waypoints (to avoid backtracking)
			if(!PreviousWaypoints.contains(CurConnection.getWaypoint())) {
				PreviousConnections.add(CurConnection); //Add the current connection
				
				//Find a partial route
				route PartialRoute = CurConnection.getWaypoint().RouteTo(OtherWaypoint, maxConnections, PreviousWaypoints, PreviousConnections);

				//if the route is valid, and it's Shorter, make it the leading possible route.
				if(PartialRoute.isValid() && PartialRoute.totalDistnace()<LeadingPossibleSolution.totalDistnace()) {LeadingPossibleSolution=PartialRoute;}
				
				PreviousConnections.remove(CurConnection); //Remove the current connection
			}
		}

		PreviousWaypoints.remove(this);
		return LeadingPossibleSolution;

	}

	public String getName() {return this.Name;}
	public Point2D.Double getPos() {return this.Position;}
	public Double DistanceTo(Waypoint OtherWaypoint) {return this.Position.distance(OtherWaypoint.getPos());}

	/**
	 * Adds a connection from this waypoint to another waypoint IN THIS DIRECTION ONLY
	 * @param way Waypoint to link to
	 * @param con Connection Type
	 */
	public void Add(Waypoint way, connectionType con) {
		connection NewCon = new connection(way,con);
		NewCon.setDistance(this.DistanceTo(way));
		Connections.add(NewCon);
		
		if(!ConTypes.contains(con)) {ConTypes.add(con);}
		
	}
	
	/**
	 * Links this waypoint to another waypoint in BOTH DIRECTIONS
	 * @param way Waypoint to link to
	 * @param con Connection type
	 */
	public void Link(Waypoint way, connectionType con) {
		this.Add(way, con);
		way.Add(this, con);
		
	}

	@Override
	public boolean equals(Object obj) {

		Waypoint OtherWaypoint;
		//Cast obj to Waypoint. If it isn't one, this will fail and return false.
		try {OtherWaypoint = (Waypoint) obj;} catch (Exception e) {return false;}

		//The only thing we can really compare quickly is the name so yeah eso es eso.
		return this.getName()==OtherWaypoint.getName();
	}
	
	
	
	/**
	 * Essentially gets the waypoint
	 */
	public String toString() {
		return Name;
	}
	
	/**
	 * Gets all connection types
	 */
	public String getConnectionTypes() {
		String ReturnString="";
		for (connectionType CurCon : ConTypes) {
			ReturnString+=CurCon.getName() + ", ";
		}
		return ReturnString;
	}
	
	public ArrayList<connection> getConnections() {return Connections;}
	
	/***
	 * gets all connections as string.
	 */
	public String getConnectionsAsString() {
		String ReturnString="";
		for (connection CurCon : Connections) {
			ReturnString+=CurCon.toString() + ", ";
		}
		return ReturnString;
	}
	
	
	/***
	 * Displays all information of the waypoint in the console, including all of its connections.
	 */
	public void DisplayInfo() {
		System.out.println("--[" + Name + "]--\n\nCONNECTIONS TO:");
		for (connection CurCon : Connections) {
			System.out.println(CurCon.toString());
		}
	}
	

}
