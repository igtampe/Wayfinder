package Wayfinder;
import javax.swing.JComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * The actual thing that renders the window.
 * @author igtampe
 *
 */
public class TheCenterOfAttention extends JComponent{

	/**
	 * A drawable route
	 * @author igtampe
	 *
	 */
	private class drawableRoute extends route{

		private route Route;
		private Color color;
		private boolean Draw;

		public drawableRoute() {
			super();
		}


		/**
		 * Create a drawable route
		 * @param theRoute The route u wish to draw
		 * @param routeColor its color
		 * @param drawn wether or not to draw it
		 */
		public drawableRoute(route theRoute, Color routeColor, boolean drawn) {
			super(theRoute.getPrevWaypoints(),theRoute.getPrevConnections());
			Route = theRoute;
			color = routeColor;
			Draw = drawn;
		}

		public drawableRoute(route theRoute, RouteType type, boolean drawn) {
			super(theRoute.getPrevWaypoints(),theRoute.getPrevConnections());
			Route = theRoute;
			switch (type) {
			case Deop:
				color=Color.orange;
				break;
			case Fastest:
				color=Color.red;
				break;
			case Longest:
				color=Color.blue;
				break;
			case Shortest:
				color= Color.red;
				break;
			case Alternate:
				color=Color.green;
				break;
			default:
				color=Color.gray;
				break;
			}

			Draw = drawn;

		}

		public boolean isDrawn() {return Draw;}


		public void SetDraw(boolean D) {

			//If its true and we want to set it to false, we have to make sure all of its waypoints are turned off.
			if(Draw && !D) {
				Waypoint prevWaypoint=Route.getPrevWaypoints().get(0);
				prevWaypoint.setDrawn(false); 
				for (connection connect : Route.getPrevConnections()) {
					prevWaypoint = connect.getWaypoint();
					prevWaypoint.setDrawn(false);
				}				
			}
			Draw=D;
		}

		/**
		 * Draw the current route
		 * @param g
		 */
		public void draw(Graphics2D g) {
			//If its set to draw
			if (Draw) {
				Waypoint prevWaypoint=Route.getPrevWaypoints().get(0); //get our innitial waypoint
				prevWaypoint.setDrawn(true); //Make sure its label is drawn
				for (connection connect : Route.getPrevConnections()) {

					//Get the points of the previous waypoint and where its going
					Point2D.Double Point1=ConvertPoint(prevWaypoint.getPos());
					Point2D.Double Point2=ConvertPoint(connect.getWaypoint().getPos());

					//Set some stuff for the drawing
					g.setStroke(RouteStroke);
					g.setColor(color);

					//Draw it
					g.drawLine((int) Point1.x+5, (int) Point1.y+5, (int) Point2.x+5, (int) Point2.y+5);

					//Get the next waypoint and make sure its label is drawn.
					prevWaypoint = connect.getWaypoint();
					prevWaypoint.setDrawn(true);
				}
			}
		}
	}

	private class drawablePartialRoute extends drawableRoute{

		private Waypoint FROM;
		private connection VIA;

		public drawablePartialRoute(Waypoint from, connection via) {
			FROM=from;
			VIA=via;
			super.Draw=true;
		}

		@Override
		public void SetDraw(boolean D) {

			if(super.Draw && !D) {
				FROM.setDrawn(false);
				VIA.getWaypoint().setDrawn(false);
			}
			super.Draw=D;
		}

		/**
		 * Draw the current route
		 * @param g
		 */
		@Override
		public void draw(Graphics2D g) {
			//If its set to draw
			if (super.Draw) {
				FROM.setDrawn(true);
				VIA.getWaypoint().setDrawn(true);

				//Get the points of the previous waypoint and where its going
				Point2D.Double Point1=ConvertPoint(FROM.getPos());
				Point2D.Double Point2=ConvertPoint(VIA.getWaypoint().getPos());

				g.setStroke(RouteStroke);
				g.setColor(Color.LIGHT_GRAY);

				g.drawLine((int) Point1.x+5, (int) Point1.y+5, (int) Point2.x+5, (int) Point2.y+5);


			}


		}
	}

	public static Boolean ISCLICKING=false;
	public static Boolean ISDRAWING=false;
	public static Point2D.Double DrawingBegin;
	public static Point2D.Double DrawingEnd;
	public static Point2D.Double CursorPos;
	public static Waypoint Origin;
	public static Waypoint Destination;
	private static Waypoint[] allPoints;
	private static ArrayList<drawableRoute> Allroutes;
	private static ArrayList<drawablePartialRoute> AllPartialRoutes;
	
	private static int MaxConnections=13;

	//Special routes
	private static route ShortestRoute;
	private static route LongestRoute;
	private static route DefaultRoute;

	//Special Waypoint
	private static Waypoint hoverwaypoint;

	//Seleciton stuff
	private static int SelectedRoute=-1;

	//graphics stuff
	private static BasicStroke RouteStroke = new BasicStroke(3);
	private static BasicStroke DrawStroke = new BasicStroke(2);
	private static BasicStroke FooterStroke = new BasicStroke(5);
	private static Font LabelFont = new Font("Arial", 2, 10);
	private static Font HeaderFont = new Font("Arial", 4, 20);
	private static Font RouteFont = new Font("Arial", 2, 15);
	private static Font DetailRouteFont = new Font("Arial", 1, 10);
	private static Font FooterFont = new Font("Arial", 2, 10);

	private static String HeaderText="";

	/**
	 * ENUM of route types
	 * @author igtampe
	 *
	 */
	public static enum RouteType{Fastest,Shortest,Longest,Deop,Alternate,Other}

	public TheCenterOfAttention(Waypoint[] Help) {
		allPoints=Help;
		Allroutes=new ArrayList<drawableRoute>();
		AllPartialRoutes=new ArrayList<drawablePartialRoute>();
		CursorPos=new Point2D.Double(0,0);
	}


	public void paintComponent(Graphics g) {

		g.setFont(LabelFont);

		Dimension Dim;
		Dim=VeloxWayfinder.GetSizeOfWindow();		

		//Draw all routes
		for (drawableRoute beep : Allroutes) {beep.draw((Graphics2D) g);}
		for (drawablePartialRoute beep : AllPartialRoutes) {beep.draw((Graphics2D) g);}
		g.setColor(Color.black);

		//Draw all waypoints
		for (Waypoint waypoint : allPoints) {drawWaypoint(g, waypoint,Dim);}

		//Draw a small tiny cursor
		g.setColor(Color.red);
		((Graphics2D) g).draw(new Ellipse2D.Double(CursorPos.x,CursorPos.y,1,1));
		g.setColor(Color.black);

		//Get a temporary Graphics2D
		Graphics2D Temp = (Graphics2D) g;

		//If we're drawing, draw a line from where we're drawing to where we're going
		if(ISDRAWING) {
			Temp.setStroke(DrawStroke);
			Temp.setColor(Color.DARK_GRAY);
			Temp.drawLine((int)DrawingBegin.x, (int)DrawingBegin.y, (int)DrawingEnd.x,(int)DrawingEnd.y);
		}

		//Draw the footer
		Temp.setStroke(FooterStroke);
		Temp.setColor(Color.darkGray);
		Temp.fill(new Rectangle(0,Dim.height-200,Dim.width,200));
		Temp.setColor(Color.black);
		Temp.drawLine(0, Dim.height-200, Dim.width, Dim.height-200);

		//Draw the header of the footer
		Temp.setFont(HeaderFont);
		Temp.setColor(Color.WHITE);
		
		//If we're hovering over a point, display its info
		if (hoverwaypoint!=null) {HeaderText= hoverwaypoint.getName() + " (" + hoverwaypoint.getPos().x + ", " +  hoverwaypoint.getPos().y + ")";}

		//If we're not routing to anywhere at the moment, use the default header
		else if (Origin == null || Destination == null) {HeaderText="Velox Wayfinder";}
		
		//If we *are* routing somewhere, display the origin and destination
		else {HeaderText=Origin.getName() + " to " + Destination.getName();}

		//Draw the coso
		Temp.drawString(HeaderText, 10, Dim.height-178);

		
		Temp.setFont(RouteFont);

		//Draw the route informations if the routes are defined
		if (ShortestRoute != null && LongestRoute != null && DefaultRoute != null && SelectedRoute==-1) {
			Temp.drawString("Shortest Route: " + (ShortestRoute.count()-1) + " Stop(s) with a distance of around " + Math.floor(ShortestRoute.totalDistnace()) + " meters (" + Math.floor(ShortestRoute.totalTime()) + " Seconds)", 10, Dim.height-140);
			Temp.drawString("Default Route: " + (DefaultRoute.count()-1) + " Stop(s) with a distance of around " + Math.floor(DefaultRoute.totalDistnace()) + " meters (" + Math.floor(DefaultRoute.totalTime()) + " Seconds)", 10, Dim.height-120);
			Temp.drawString("Longest Route: " + (LongestRoute.count()-1) + " Stop(s) with a distance of around " + Math.floor(LongestRoute.totalDistnace()) + " meters (" + Math.floor(LongestRoute.totalTime()) + " Seconds)", 10, Dim.height-100);			
		}

		//If there's a selected route, lets show it
		if (SelectedRoute!=-1 && !Allroutes.isEmpty()) {

			String BeepBoop;
			if (SelectedRoute==Allroutes.size()-3) {BeepBoop="Longest";}
			else if (SelectedRoute==Allroutes.size()-2) {BeepBoop="Default";}
			else if (SelectedRoute==Allroutes.size()-1) {BeepBoop="Shortest";}
			else {BeepBoop="" + (SelectedRoute+1);}

			Temp.drawString("Selected Route (" + BeepBoop + "): " + (Allroutes.get(SelectedRoute).count()-1) + " Stop(s) with a distance of around " + Math.floor(Allroutes.get(SelectedRoute).totalDistnace()) + " meters", 10, Dim.height-140);
			Temp.setFont(DetailRouteFont);
			Temp.drawString(Origin.getName() + Allroutes.get(SelectedRoute).toString(),10, Dim.height-125);
		}

		//If there's a point hovered on, show it
		if (hoverwaypoint!=null) {
			Temp.drawString("Connections available: " + hoverwaypoint.getConnectionTypes(), 10, Dim.height-140);
			Temp.setFont(DetailRouteFont);
			Temp.drawString(hoverwaypoint.getConnectionsAsString(),10, Dim.height-125);
		}

		//Draw the last tidbit at the end
		Temp.setFont(FooterFont);
		//Temp.setColor(Color.lightGray);
		Temp.drawString("Discarding routes logner than " + MaxConnections + " stops.", 10, Dim.height-70);
		Temp.drawString(AllPartialRoutes.size() + " Connection(s) displayed. Press PageUp to temporarily display all possible connections", 10, Dim.height-60);
		Temp.drawString(VeloxWayfinder.AlternativeRoutes.size() + " Route(s) loaded. To cycle through them, use left and right arrow keys. Press escape to reset, and press PageDown to toggle showing all routes.", 10, Dim.height-50);

	}

	/**
	 * Add a route to the array of stored routes.
	 * @param Doot
	 * @param ColorfulDoot
	 * @param SilentDoot
	 */
	public void AddRoute(route Doot, Color ColorfulDoot,boolean SilentDoot) {Allroutes.add(new drawableRoute(Doot, ColorfulDoot,SilentDoot));}
	public void AddRoute(route Doot, RouteType InformativelDoot,boolean SilentDoot) {Allroutes.add(new drawableRoute(Doot, InformativelDoot,SilentDoot));}

	/**
	 * Reset all drawn routes
	 */
	public void reset() {
		Allroutes.clear();
		for (Waypoint D : allPoints) {
			D.setDrawn(false);
		}
		SelectedRoute= -1;
		ShortestRoute=null;
		LongestRoute=null;
		DefaultRoute=null;
		Origin=null;
		Destination=null;

	}

	/***
	 * Convert a point in the map to the point on the window
	 * @param AAA The point
	 * @return A point that's visible on the window
	 */
	public Point2D.Double ConvertPoint(Point2D.Double AAA){
		Dimension Dim=VeloxWayfinder.GetSizeOfWindow();
		return new Point2D.Double((AAA.x+1000)*(Dim.width/2900.0),((AAA.y+1600)*((Dim.height-200)/3500.0)));
	}


	public Ellipse2D.Double GetCircle(Waypoint Way, Dimension Dim){
		Point2D.Double Center = ConvertPoint(Way.getPos());
		return new Ellipse2D.Double(Center.x,Center.y,5,5);
	}

	public Ellipse2D.Double GetArea(Waypoint Way, Dimension Dim){
		Point2D.Double Center = ConvertPoint(Way.getPos());
		return new Ellipse2D.Double(Center.x-5,Center.y-5,5+10,5+10);
	}

	/***
	 * Draw a certain waypoint
	 * @param g Graphics
	 * @param Way Waypoint to draw
	 * @param Dim Dimension of the window
	 */
	public void drawWaypoint(Graphics g, Waypoint Way, Dimension Dim) {
		//Translate the point.
		Point2D.Double Center = ConvertPoint(Way.getPos());

		Graphics2D haha = (Graphics2D) g;
		haha.fill(GetCircle(Way,Dim));
		if(Way.isDrawn()) {haha.drawString(Way.getName(), (int) Center.x+10, (int) Center.y+10);}
	}

	//INPUT COSOS
	
	
	public void onMousePressed(MouseEvent e) {
		ISCLICKING=true;
		hoverwaypoint=null;
		Destination=null;
		AllPartialRoutes.clear();

		double mouseX = e.getPoint().getX()-8;
		double mouseY = e.getPoint().getY()-27;

		Origin=null;

		//find out if we're clicking on a point
		if(Allroutes.isEmpty()) {
			for (int i = 0; i < allPoints.length; i++) {
				CursorPos.setLocation(mouseX,mouseY);
				Ellipse2D.Double C= GetArea(allPoints[i],VeloxWayfinder.GetSizeOfWindow());
				if(C.contains(mouseX,mouseY)) Origin=allPoints[i];
			}
		}
		else {this.reset();}


		//If we have an origin, We're drawing.
		if (!(Origin == null)) {
			ISDRAWING=true;
			DrawingBegin=new Point2D.Double(mouseX,mouseY);
			DrawingEnd=new Point2D.Double(mouseX,mouseY);
		}



	}

	public void onMouseDragged(MouseEvent e){
		double mouseX = e.getPoint().getX()-8;
		double mouseY = e.getPoint().getY()-27;

		//If we're drawing, find if we intersect with a point.
		if(ISDRAWING) {
			DrawingEnd.setLocation(mouseX,mouseY);
			Destination=null;

			if(Allroutes.isEmpty()) {
				for (int i = 0; i < allPoints.length; i++) {
					CursorPos.setLocation(mouseX,mouseY);
					Ellipse2D.Double C= GetArea(allPoints[i],VeloxWayfinder.GetSizeOfWindow());
					allPoints[i].setDrawn(C.contains(mouseX,mouseY));
					if (allPoints[i].isDrawn()) {Destination=allPoints[i];}
				}
			}
			Origin.setDrawn(true);}
	}

	public void onMouseRelease(MouseEvent e){
		ISCLICKING=false; //We're no longer clicking

		//if we were drawing...
		if (ISDRAWING) {
			ISDRAWING=false;

			//And we do have a destination...
			if(Destination!=null) {
				VeloxWayfinder.AlternativeRoutes.clear(); //Clear alternative routes
				
				//Find our shortest route
				ShortestRoute = Origin.RouteTo(Destination, MaxConnections);

				//Collect all the relevant alternative routes.
				DefaultRoute = VeloxWayfinder.AlternativeRoutes.get(0);
				LongestRoute = VeloxWayfinder.AlternativeRoutes.get(0);
				for (route a : VeloxWayfinder.AlternativeRoutes) {
					if(a.equals(LongestRoute)) {} else {
						if(ShortestRoute.count()==a.count()) {AddRoute(a,RouteType.Alternate,true);}
						else if (ShortestRoute.count() > a.count()) {AddRoute(a,RouteType.Fastest,true);}
						else {AddRoute(a,RouteType.Other,false);}
						if(a.totalDistnace()>LongestRoute.totalDistnace()) {LongestRoute=a;}}
				}

				//Add the routes to the screen.
				AddRoute(LongestRoute, RouteType.Longest,true);
				AddRoute(DefaultRoute,RouteType.Deop,true);
				AddRoute(ShortestRoute,RouteType.Shortest,true);

			}
		}
	}


	public void onMouseMove(MouseEvent e){
		double mouseX = e.getPoint().getX()-8;
		double mouseY = e.getPoint().getY()-27;
		
		//IF we're not displaying any routes...
		if(Allroutes.isEmpty()) {
			hoverwaypoint=null;
			AllPartialRoutes.clear();

			//Find out if the mouse is over a point, and if so, add a partial route to show all its connections
			for (int i = 0; i < allPoints.length; i++) {
				CursorPos.setLocation(mouseX,mouseY);
				Ellipse2D.Double C= GetArea(allPoints[i],VeloxWayfinder.GetSizeOfWindow());
				allPoints[i].setDrawn(C.contains(mouseX,mouseY));
				if (C.contains(mouseX,mouseY)) {
					hoverwaypoint=allPoints[i];
					for (connection curcon : hoverwaypoint.getConnections()) {
						AllPartialRoutes.add(new drawablePartialRoute(hoverwaypoint, curcon));
					}
				}
			}
		}

	}


	public void setPrimaryRouteVisibility(Boolean D) {
		if(!Allroutes.isEmpty()) {
			Allroutes.get(Allroutes.size()-3).SetDraw(D);
			Allroutes.get(Allroutes.size()-2).SetDraw(D);
			Allroutes.get(Allroutes.size()-1).SetDraw(D);
		}
	}

	public void togglePrimaryRouteVisibility() {
		for (drawableRoute drawableRoute : Allroutes) {
			drawableRoute.SetDraw(!drawableRoute.isDrawn());
		}		
	}

	public void displayAllRoutes(Boolean D) {
		for (drawableRoute drawableRoute : Allroutes) {
			drawableRoute.SetDraw(D);
		}
	}


	public void onKeyPress(KeyEvent e) {
		//Show all routes
		if (e.getKeyCode()==KeyEvent.VK_PAGE_UP) {
			AllPartialRoutes.clear();
			for (Waypoint curway : allPoints) {
				for (connection curcon : curway.getConnections()) {
					AllPartialRoutes.add(new drawablePartialRoute(curway, curcon));
				}
			}
		}

		//Reset
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {this.reset();}

		//Modify the maximum number of connections we're looking for
		if(e.getKeyCode()==KeyEvent.VK_DOWN && MaxConnections!=10) {MaxConnections--;}
		if(e.getKeyCode()==KeyEvent.VK_UP && MaxConnections!=80) {MaxConnections++;}
		
		//Toggle the visibility of all routes being displayed
		if (e.getKeyCode()==KeyEvent.VK_PAGE_DOWN) {togglePrimaryRouteVisibility();}

		//Go through each route
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {

			displayAllRoutes(false);

			SelectedRoute--; 
			if(SelectedRoute<-1) {SelectedRoute=Allroutes.size()-1;}

			if (SelectedRoute!=-1 && !Allroutes.isEmpty()) {Allroutes.get(SelectedRoute).SetDraw(true);} else {setPrimaryRouteVisibility(true);}

		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {

			displayAllRoutes(false);

			SelectedRoute++; 
			if(SelectedRoute>Allroutes.size()-1) {SelectedRoute=-1;}

			if (SelectedRoute!=-1 && !Allroutes.isEmpty()) {Allroutes.get(SelectedRoute).SetDraw(true);} else {setPrimaryRouteVisibility(true);}

		}
	}

}
