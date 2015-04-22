import java.awt.Color;
import java.util.ArrayList;

public class World {
	
	public enum View {
		SIDE_VIEW,
		TOP_VIEW,
	}
	
	private static final int OFFSET_X = 0, OFFSET_Y = 0;
	public int[][] mapSide = null;
	public int[][] mapTop = null;
	public int sizex = 3000;
	public int sizey = 300;
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	public Controller controller;
	
	public World(Controller controller) {
		this.controller = controller;
		
		mapTop = new int[sizex][];
		for(int i = 0; i < mapTop.length; i++) {
			mapTop[i] = new int[sizey];
		}
		/*
		mapSide = new int[size][];
		for(int i = 0; i < mapSide.length; i++) {
			mapSide[i] = new int[size];
		}
		*/
		update();
	}
	
	//update makes the World refetch all its information from the Controller
	//this should be called when the model might have changed
	public void update() {
		//first check if there is a runway being viewed, if there isn't you don't need to do anything
		System.out.println("updating world");
		if(controller.viewingRunway()) {
			//clear the previous stuff:
			objects.clear();
			
			//add the runway:
			objects.add(MapObjectFactory.buildRunway(OFFSET_X, OFFSET_Y, (int)controller.getRunwayLength(), (int)controller.getRunwayWidth(), 0));
		
			//add any obstacles on the runway:
			//I want to find a way to do this that doesn't give the view the Obstacle instances
			for(Obstacle o : controller.getObstacleList()) {
				objects.add(MapObjectFactory.buildBadArea((int)controller.getCurrentRunway().getObstacleDistance(o)+OFFSET_X, (int)100+OFFSET_Y, 0, (int)20, (int)20, (int)o.height, o.name));
				
				// Example landing slope code, will generate a landing slope starting at the top of the Obstacle touching the ground 300m further.
				MapObjectLandingSlope m = new MapObjectLandingSlope();
				m.x = (int)(controller.getCurrentRunway().getObstacleDistance(o)+OFFSET_X + 20);
				m.y = 0;
				m.z = 0;
				m.height = (int)o.height;
				m.length = 0;
				m.width = (int)o.height*50;
				m.color = Color.red;
				objects.add(m);
			}
			objects.add(MapObjectFactory.buildString(50, (int)(50 + OFFSET_Y + controller.getRunwayWidth()), 0, Color.black, "TORA: " + controller.getCurrentRunway().nTORA));
			objects.add(MapObjectFactory.buildString(50, (int)(59 + OFFSET_Y + controller.getRunwayWidth()), 0, Color.black, "TODA: " + controller.getCurrentRunway().nTODA));
			objects.add(MapObjectFactory.buildString(50, (int)(68 + OFFSET_Y + controller.getRunwayWidth()), 0, Color.black, "ASDA: " + controller.getCurrentRunway().nASDA));
			objects.add(MapObjectFactory.buildString(50, (int)(77 + OFFSET_Y + controller.getRunwayWidth()), 0, Color.black, "LDA: " + controller.getCurrentRunway().nLDA));
		}
		
		//redraw after updating:
		draw();	
	}
	
	public void draw() {
		/*
		for(int i = 0; i < mapTop.length; i++) {
			for(int j = 0; j < mapTop.length; j++) {

				mapTop[i][j] = Color.GREEN.getRGB();

				if(j < mapTop.length / 2)
					mapSide[i][j] = Color.CYAN.getRGB();
				else
					mapSide[i][j] = Color.GREEN.getRGB();
			}
		}
		
*/
		for(MapObject o : objects) {
				o.drawTop(mapTop);
				o.drawSide(mapSide);
		}
	}
	
}
