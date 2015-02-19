import java.awt.Color;
import java.util.ArrayList;

import javax.naming.spi.ObjectFactory;


public class World {

	public int[][] map = null;
	public int size = 6000;
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	
	public World() {
		map = new int[size][];
		for(int i = 0; i < map.length; i++) {
			map[i] = new int[size];
		}
		
		objects.add(MapObjectFactory.buildRunway(20, 20, 2400, 160));
		objects.add(MapObjectFactory.buildRunway(20, 200, 2400, 160));
		objects.add(MapObjectFactory.buildRunway(20, 400, 800, 80));
		
		objects.add(MapObjectFactory.buildBadArea(300, 40, 40, 100));
		objects.add(MapObjectFactory.buildBadArea(800, 280, 400, 50));
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
