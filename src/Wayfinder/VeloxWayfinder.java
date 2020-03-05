package Wayfinder;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import input.KeyManager;
import input.MouseManager;

public class VeloxWayfinder {

	
	
	//UMT
	public static Waypoint ISector= new Waypoint("Industrial Sector",385,-1105);
	public static Waypoint Synergia= new Waypoint("Synergia",-760, -233);


	//--VELOX Newpond
	public static Waypoint Kamatsu= new Waypoint("Kamatsu", 325, -349);
	public static Waypoint Sumus= new Waypoint("Sumus", 366, -129);
	public static Waypoint Kaigi= new Waypoint("Kaigi", 296, -25);
	public static Waypoint MSprings= new Waypoint("Midnight Springs", 370, -12);
	public static Waypoint Cocoa= new Waypoint("Cocoa", 619,32);
	public static Waypoint FTMotor= new Waypoint("FT Motors St.", 368, 64);
	public static Waypoint TIPS= new Waypoint("TIPS",509, 57);
	public static Waypoint ISSS= new Waypoint("ISSS",368, 154);
	public static Waypoint Verde= new Waypoint("Verde Mall",450, 146);
	public static Waypoint FourPoints= new Waypoint("Four Points",590, 202);
	public static Waypoint UMSNB= new Waypoint("UMS Natl. Bank Center",661, 196);
	public static Waypoint Manabus= new Waypoint("Manabus Station (Newpond-EX)",708, 429);
	public static Waypoint ManabNorth= new Waypoint("Manabus North",770, 360);
	public static Waypoint ManabSouth= new Waypoint("Manabus South",770, 527);
	public static Waypoint Volucris= new Waypoint("Volucris (Somnus Hotel)",832, 108);
	public static Waypoint VolE= new Waypoint("Volucris Terminal E",947, 51);
	public static Waypoint VolD= new Waypoint("Volucris Terminal D",983, 51);
	public static Waypoint VolC3= new Waypoint("Volucris Terminal C3",994, 82);
	public static Waypoint VolC2= new Waypoint("Volucris Terminal C2",994, 137);
	public static Waypoint VolC1= new Waypoint("Volucris Terminal C1",994, 203);
	public static Waypoint VolB= new Waypoint("Volucris Terminal B",983, 231);
	public static Waypoint VolA= new Waypoint("Volucris Terminal A",947, 231);
	public static Waypoint Newpond= new Waypoint("Newpond Central",261, 180);
	public static Waypoint Burb= new Waypoint("Burberry",370, 257);
	public static Waypoint Treelife = new Waypoint("Treelife",464, 229);
	
	//--VELOX Urbia
	public static Waypoint Urbia= new Waypoint("Urbia Central",417, 545);
	public static Waypoint SouthUrbia= new Waypoint("South Urbia",562, 758);
	public static Waypoint SWUrbia= new Waypoint("Southwest Urbia",440, 960);
	public static Waypoint Pianura= new Waypoint("Pianura",620, 1036);
	public static Waypoint PianuraS= new Waypoint("Pianura South",520, 1072);
	public static Waypoint LC1= new Waypoint("Lab Center Main Terminal",433, 1236);
	public static Waypoint LC2= new Waypoint("Lab Center Terminal 2",376, 1244);
	public static Waypoint LC3= new Waypoint("Lab Center Terminal 3",316, 1256);
	
	//--VELOX Suburbia
	public static Waypoint Suburbia= new Waypoint("Suburbia Central",85, 479);
	public static Waypoint NCouncil= new Waypoint("Neighborhood Council",-106, 361);
	public static Waypoint MoM= new Waypoint("Mall of Minecraft (Former)",-155, 519);
	public static Waypoint SouthSuburbia= new Waypoint("South Suburbia",-84, 689);
	
	//--VELOX Paradisus
	public static Waypoint PNW= new Waypoint("Paradisus Northwest",1, 848);
	public static Waypoint PNE= new Waypoint("Paradisus Northeast",262, 848);
	public static Waypoint PSW= new Waypoint("Paradisus Southwest",1, 1094);
	public static Waypoint PS= new Waypoint("Paradisus South",132, 1094);
	public static Waypoint PSE= new Waypoint("Paradisus Southeast",262, 1094);
	public static Waypoint Paradisus= new Waypoint("Paradisus Central",132, 969);
	public static Waypoint Mercatum= new Waypoint("Mercatum Mall",16, 1160);
	public static Waypoint Domum= new Waypoint("Domum",-257, 1062);
	
	//--VELOX Laertes
	public static Waypoint LFSW= new Waypoint("Laertes Far Southwest",-706, 865);
	public static Waypoint LFSE= new Waypoint("Laertes Far Southeast",-552, 865);
	public static Waypoint LSW= new Waypoint("Laertes Southwest",-706, 711);
	public static Waypoint LSE= new Waypoint("Laertes Southeast",-552, 711);
	public static Waypoint Laertes= new Waypoint("Laertes Central",-629, 594);
	public static Waypoint LNW= new Waypoint("Laertes Northwest",-706, 480);
	public static Waypoint LNE= new Waypoint("Laertes Northeast",-552, 480);
	public static Waypoint LFNW= new Waypoint("Laertes Far Northwest",-706, 326);
	public static Waypoint LFNE= new Waypoint("Laertes Far Northeast",-552, 326);

	//NewpondBus
	public static Waypoint RBeltway= new Waypoint("Riverdane Beltway",236,-1229);
	public static Waypoint RCentral= new Waypoint("Riverdane Central",127,-1395);
	public static Waypoint RWest= new Waypoint("Riverdane West",-58,-1243);
	public static Waypoint RFarms= new Waypoint("Riverdane Farms",-378,-1267);
	public static Waypoint Creeksburg= new Waypoint("Creeksburg",445,-1509);
	
	//Connection Types
	public static connectionType UMTRed = new connectionType("UMT Red Line", "=[R UMT]=>");
	public static connectionType UMTGreen = new connectionType("UMT Green Line", "=[G UMT]=>");
	public static connectionType UMTBlue = new connectionType("UMT Blue Line", "=[B UMT]=>");
	public static connectionType LPT = new connectionType("LPT", "~[LPT]~>");
	public static connectionType EPT = new connectionType("EPT", "-[EPT]->");
	public static connectionType RBUS = new connectionType("North Newpond Bus System", ">-RBUS->");
	public static connectionType LCR = new connectionType("Labcenter Rail", "))LCR->");
	
	//Setup
	public static void UMTLinks() {
		
		//GreenLine
		Newpond.Link(ISector, UMTGreen);
		ISector.Link(Synergia,UMTGreen);
		Synergia.Link(Laertes, UMTGreen);		
		Laertes.Link(Domum, UMTGreen);
		Domum.Link(Paradisus, UMTGreen);
		Paradisus.Link(Urbia, UMTGreen);
		Urbia.Link(Newpond, UMTGreen);
		
		//RedLine
		Newpond.Link(Laertes, UMTRed);
		Laertes.Link(Domum, UMTRed);
		Domum.Link(Suburbia,UMTRed);
		Suburbia.Link(Urbia, UMTRed);
		Urbia.Link(Manabus, UMTRed);
		Manabus.Link(Volucris,UMTRed);
		Volucris.Link(Newpond, UMTRed);
		
		//BlueLine
		Newpond.Link(Laertes, UMTBlue);
		Laertes.Link(Paradisus, UMTBlue);
		Paradisus.Link(Suburbia, UMTBlue);
		Suburbia.Link(Newpond, UMTBlue);
		
	}
	public static void RBusLinks() {
		ISector.Link(RBeltway, RBUS);
		RBeltway.Link(RCentral, RBUS);
		RCentral.Link(Creeksburg, RBUS);
		RCentral.Link(RWest, RBUS);
		RWest.Link(RFarms, RBUS);
	}
	public static void EPTLinks() {
		Kamatsu.Link(Newpond, EPT);
		Sumus.Link(Newpond,EPT);
		Urbia.Link(Pianura, EPT);
		VolRedLineLinks();
	}
	public static void VolRedLineLinks() {
		Newpond.Add(VolA, EPT);
		VolA.Add(VolB, LPT);
		VolB.Add(VolC1,LPT);
		VolC1.Add(VolC2, LPT);
		VolC2.Add(VolC3, LPT);
		VolC3.Add(VolD, LPT);
		VolD.Add(VolE, LPT);
		VolE.Add(Volucris, LPT);
		Volucris.Add(VolA, LPT);
		
	} 
	public static void LPTLinks() {
		
		//Newpond
		Newpond.Link(Kaigi, LPT);
		Newpond.Link(ISSS, LPT);
		Kaigi.Link(MSprings, LPT);
		ISSS.Link(FTMotor, LPT);
		ISSS.Link(Burb, LPT);
		ISSS.Link(Verde, LPT);
		FTMotor.Link(MSprings, LPT);
		Burb.Link(Treelife, LPT);
		MSprings.Link(TIPS, LPT);
		Verde.Link(TIPS, LPT);
		Verde.Link(Treelife, LPT);
		Treelife.Link(FourPoints, LPT);
		TIPS.Link(Cocoa, LPT);
		Cocoa.Link(FourPoints, LPT);
		FourPoints.Link(UMSNB, LPT);
		UMSNB.Link(Manabus, LPT);
		Manabus.Link(ManabNorth, LPT);
		Manabus.Link(ManabSouth, LPT);
		
		//Suburbia
		Suburbia.Link(NCouncil, LPT);
		Suburbia.Link(MoM, LPT);
		Suburbia.Link(SouthSuburbia, LPT);
		SouthSuburbia.Link(PNW, LPT);
		
		//Urbia
		Urbia.Link(SouthUrbia, LPT);
		Urbia.Link(SWUrbia, LPT);
		SWUrbia.Link(Pianura, LPT);
		SWUrbia.Link(Paradisus, LPT);
		
		//Pianura
		Pianura.Link(PianuraS, LPT);
		LCRailLinks();
		
		//Paradisus
		PNE.Link(PSE, LPT);
		PNE.Link(Paradisus, LPT);
		PSE.Link(Paradisus, LPT);
		PNE.Link(PNW, LPT);
		PSE.Link(PS, LPT);
		PS.Link(PSW, LPT);
		PSW.Link(PNW, LPT);
		PNW.Link(Paradisus, LPT);
		PSW.Link(Paradisus, LPT);
		PS.Link(Paradisus, LPT);
		PS.Link(Mercatum, LPT);
		PSW.Link(Mercatum, LPT);
		PSW.Link(Domum, LPT);
		Domum.Link(Mercatum, LPT);
		
		//Laertes
		Laertes.Link(LNW, LPT);
		Laertes.Link(LNE, LPT);
		Laertes.Link(LSW, LPT);
		Laertes.Link(LSE, LPT);
		LSE.Link(LNE, LPT);
		LNE.Link(LNW, LPT);
		LNW.Link(LSW, LPT);
		LSW.Link(LSE, LPT);
		LSE.Link(LFSE, LPT);
		LFSE.Link(LFSW, LPT);
		LFSW.Link(LSW, LPT);
		LNW.Link(LFNW, LPT);
		LFNW.Link(LFNE, LPT);
		LFNE.Link(LNE, LPT);
		
	}
	public static void LCRailLinks() {
		LC1.Link(PianuraS, LCR);
		LC1.Link(LC2, LCR);
		LC2.Link(LC3, LCR);
		LC3.Link(PSE, LCR);
		
	}
	
	//final bits
	public static Waypoint[] allPoints= {ISector,Synergia,Kamatsu,Sumus,Kaigi,MSprings,Cocoa,FTMotor,TIPS,ISSS,Verde,FourPoints,UMSNB,Manabus,ManabNorth,ManabSouth,Volucris,VolE,VolD,VolC3,VolC2,VolC1,VolB,VolA,Newpond,Burb,Treelife,Urbia,SouthUrbia,SWUrbia,Pianura,PianuraS,LC1,LC2,LC3,Suburbia,NCouncil,MoM,SouthSuburbia,PNW,PNE,PSW,PS,PSE,Paradisus,Mercatum,Domum,LFSW,LFSE,LSW,LSE,Laertes,LNW,LNE,LFNW,LFNE,RBeltway,RCentral,RWest,RFarms,Creeksburg};
	public static TheCenterOfAttention AllEyesOnMe = new TheCenterOfAttention(allPoints);
	public static ArrayList<route> AlternativeRoutes = new ArrayList<route>();
	
	//Window Stuff
	private static JFrame mainFrame = new JFrame();
	private final static int Framerate = 60; 
	private static KeyManager keyManager;
	protected static MouseManager mouseManager;
	
	
	public static void main(String[] args) {
		UMTLinks();
		RBusLinks();
		EPTLinks();
		LPTLinks();
		
		
		
		
		mainFrame.setSize(800,800);
		mainFrame.setTitle("Velox Wayfinder");
		
		
		keyManager= new KeyManager(AllEyesOnMe);
        mouseManager = new MouseManager(AllEyesOnMe);
        
        mainFrame.addKeyListener(keyManager);
        mainFrame.addMouseListener(mouseManager);
        mainFrame.addMouseMotionListener(mouseManager);
		
		TheCenterOfAttention AllEyesOnMe=new TheCenterOfAttention(allPoints);

		
		mainFrame.add(AllEyesOnMe);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		AlternativeRoutes.clear();
		//route Doot=VolA.RouteTo(LC2, 15);
		
		//for (route a : AlternativeRoutes) {AllEyesOnMe.AddRoute(a,Color.LIGHT_GRAY,true);}
		
		//AllEyesOnMe.AddRoute(Doot, Color.red,true);
		
		
		
		
		while(true) {
			mainFrame.repaint();
			try {Thread.sleep(1000/Framerate);} catch (InterruptedException e) {}
			keyManager.tick();
		}

	}
	
	public static Dimension GetSizeOfWindow() {return mainFrame.getSize();}
	public static int GetFramerate() {return Framerate;}

	
	
}
