package Wayfinder;
import java.util.ArrayList;



/** A possible way to get to where you're going.
	 * @author igtampe
	 *
	 */
	public class route {
		private int C;
		private double Dist;
		private double Time;
		private ArrayList<Waypoint> PrevWaypoints;
		private ArrayList<connection> PrevConnections;
	
		private Boolean found;
		
		/**
		 * Creates a dummy route
		 */
		public route() {
			found=false;
			C=99999;
			Dist=999999999;
		}
		
		public boolean isValid() {return found;} 
		
		
		/**
		 * Creates a route
		 * @param Waypoints Waypoints used to get to this route
		 * @param Connections Connections used to get from the origin to the destination.
		 */
		public route(ArrayList<Waypoint> Waypoints, ArrayList<connection> Connections) {
			found=true;
			PrevWaypoints=new ArrayList<Waypoint>(Waypoints);
			PrevConnections=new ArrayList<connection>(Connections);
					
			C=PrevWaypoints.size()-1;
			Dist=0;
			Time=0;
			
			for (connection CurCon : PrevConnections) {
				Dist += CurCon.getDistance();
				Time+= CurCon.getDistance()/CurCon.getType().getSpeed();
			}
		}
		
		public ArrayList<Waypoint> getPrevWaypoints() {return PrevWaypoints;}
		public ArrayList<connection> getPrevConnections() {return PrevConnections;}
		public double totalDistnace() {return Dist;}
		public double totalTime() {return Time;}
		public int count() {return C;}
		
		/**
		 * @return Origin to Destination with all connections (IE: Origin ConnectionString Destination)
		 */
		public String toString() {
			String Route="";
			for (connection CurCon : PrevConnections) {
				Route+= CurCon.getType().getConnectionString() + " " + CurCon.getWaypoint().getName() + " ";
			}
			return Route;
		}
	}