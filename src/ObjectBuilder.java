import java.awt.Color;


public class ObjectBuilder {

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
			MapObject s = new MapObject();
			s.color = Color.white;
			s.x = (int) (x + width*0.1);
			s.y = (int) (y + Math.floor(height/2) - 2);
			s.width = (int) (width*0.8);
			s.height = 4;
			s.name = "Runway Line";
			o.objects.add(s);
		}
		
		return o;
	}
}
