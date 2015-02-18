import java.awt.Color;


public class MapObjectFactory {

	public static MapObject buildRunway(int x, int y, int width, int height) {
		
		/* Tarmac */
		MapObject o = new MapObject();
		o.color = Color.black;
		o.x = x;
		o.y = y;
		o.width = width;
		o.height = height;
		o.name = "Runway Tarmac";
		/* Center Line */
		{
			for(int i = (int) (width*0.05); i < (width*0.8); i += 100 ) {
				MapObject s = new MapObject();
				s.color = Color.white;
				s.x = (int) (x + i);
				s.y = (int) (y + Math.floor(height/2));
				s.width = 80;
				s.height = 1;
				s.name = "Runway Line";
				o.objects.add(s);
			}
		}
		
		return o;
	}
}
