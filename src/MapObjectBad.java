
public class MapObjectBad extends MapObject {

	
	
	void draw(int[][] map) {
		for(int i = x; i < (x + width); i++) {
			for(int j = y; j < (y + height); j++) {
				if(i == 0 || j == 0 || i == (x + width - 1) || j == (y + height - 1) || i % 5 == 0 || j % 5 == 0)
					map[i][j] = color.getRGB();
			}
		}
	}
}
