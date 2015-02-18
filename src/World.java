import java.awt.Color;
import java.util.ArrayList;

import javax.naming.spi.ObjectFactory;


public class World {

	public int[][] map = null;
	public int size = 600;
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	
	public World() {
		map = new int[size][];
		for(int i = 0; i < map.length; i++) {
			map[i] = new int[size];
		}
		
		objects.add(ObjectBuilder.buildRunway(20, 20, 300, 80));
		draw();
	}
	
	public int[][] draw() {
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				/*if(i % 2 == 0 && j % 2 ==0)
					map[i][j] = Color.GREEN.darker().darker().darker().getRGB();
				else*/
					map[i][j] = Color.GREEN.getRGB();
			}
		}
		
		for(MapObject o : objects) {
			o.draw(map);
		}
		
		return map;
	}
}
