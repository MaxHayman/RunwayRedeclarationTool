import java.awt.Color;
import java.util.ArrayList;


public class MapObject {
	public ArrayList<MapObject> objects = new ArrayList<MapObject>();
	String name = "";
	int x = 0;
	int y = 0;
	int z = 0;
	int height = 0;
	int width = 0;
	int length = 0;
	Color color = Color.black;
	
	void drawTop(int[][] map) {
		for(int i = x; i < (x + width); i++) {
			for(int j = y; j < (y + length); j++) {
				map[i][j] = color.getRGB();
			}
		}
		
		for(MapObject o : objects) {
			o.drawTop(map);
		}
	}
	
	void drawSide(int[][] map) {
		for(int i = x; i < (x + width); i++) {
			for(int j = z; j < (z + height); j++) {
				map[i][(int)(map.length/2)-1-j] = color.getRGB();
			}
		}
		
		for(MapObject o : objects) {
			//o.drawSide(map);
		}
	}
}
