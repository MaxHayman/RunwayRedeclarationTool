import java.awt.Color;
import java.util.ArrayList;


public class MapObject {
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	String name = "";
	int x = 0;
	int y = 0;
	int height = 0;
	int width = 0;
	Color color = Color.black;
	
	void draw(int[][] map) {
		System.out.println("Drawing: " + name);
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(i >= x && i <= (x + width) && j >= y && j <= (y + height))
					map[i][j] = color.getRGB();
			}
		}
		
		for(MapObject o : objects) {
			o.draw(map);
		}
	}
}
