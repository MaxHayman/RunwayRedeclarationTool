
public class MapObjectLandingSlope extends MapObject {

	void drawTop(int[][] map) {
		// We don't display this on the top down view
	}
	
	void drawSide(int[][] map) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				//if((30*i/width) == (30*j/height))
					//map[width - i + x][(int)(map.length/2)-1-(j + z)] = color.getRGB();
			}
		}
	}

}
