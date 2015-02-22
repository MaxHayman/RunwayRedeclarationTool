import java.awt.Color;
import java.util.ArrayList;

public class World {

	private static final int OFFSET_X = 20, OFFSET_Y = 20;
	public int[][] map = null;
	public int size = 6000;
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	private Controller controller;
	
	public World(Controller controller) {
		this.controller = controller;
		
		map = new int[size][];
		for(int i = 0; i < map.length; i++) {
			map[i] = new int[size];
		}
		
		draw();
		
		/*objects.add(MapObjectFactory.buildRunway(20, 20, 2400, 160));
		objects.add(MapObjectFactory.buildRunway(20, 200, 2400, 160));
		objects.add(MapObjectFactory.buildRunway(20, 400, 800, 80));
		
		objects.add(MapObjectFactory.buildBadArea(300, 40, 40, 100));
		objects.add(MapObjectFactory.buildBadArea(800, 280, 400, 50));*/
	}
	
	//update makes the World refetch all its information from the Controller
	//this should be called when the model might have changed
	public void update() {
		//first check if there is a runway being viewed, if there isn't you don't need to do anything
		if(controller.viewingRunway()) {
			
			//add the runway:
			objects.add(MapObjectFactory.buildRunway(OFFSET_X, OFFSET_Y, (int)controller.getRunwayLength(), (int)controller.getRunwayWidth()));
		
			//add any obstacles on the runway:
			//I want to find a way to do this that doesn't give the view the Obstacle instances
			for(Obstacle o : controller.getObstacleList()) {
				objects.add(MapObjectFactory.buildBadArea((int)o.getxLocation()+OFFSET_X, (int)o.getyLocation()+OFFSET_Y, (int)o.getxSize(), (int)o.getySize()));
			}
		}
		
		//redraw after updating:
		draw();		
	}
	
	public void draw() {
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j] = Color.GREEN.getRGB();
			}
		}
		
		for(MapObject o : objects) {
			o.draw(map);
		}
	}
}
