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
		/* Lines */
		{
			for(int i = (int) (width * 0.05); i < (width * 0.95); i += 100 ) {
				
				/* Fixed Distance Marks */
				if(i == (width * 0.05) || ((i / 100) % 6) == 0) {
					for(int j = (int) (height * 0.1); j < height * 0.45; j += 4 ) {

						{
							MapObject s = new MapObject();
							s.color = Color.white;
							s.x = (int) (x + i) + 10;
							s.y = (int) (y + j);
							s.width = (int) (width * 0.02);
							s.height = 1;
							s.name = "Big Runway Line";
							o.objects.add(s);
						}
						{
							MapObject s = new MapObject();
							s.color = Color.white;
							s.x = (int) (x + i) + 10;
							s.y = (int)  (y + height - j) - 1;
							s.width = (int) (width * 0.02);
							s.height = 1;
							s.name = "Small Runway Line";
							o.objects.add(s);
						}
					}
				}
				else if(((i / 100) % 3) == 0){
					for(int j = (int) (height * 0.3); j < height * 0.45; j += 4 ) {
						
						{
							MapObject s = new MapObject();
							s.color = Color.white;
							s.x = (int) (x + i) + 10;
							s.y = (int) (y + j);
							s.width = (int) (width * 0.02);
							s.height = 1;
							s.name = "Small Runway Line";
							o.objects.add(s);
						}
						{
							MapObject s = new MapObject();
							s.color = Color.white;
							s.x = (int) (x + i) + 10;
							s.y = (int)  (y + height - j) - 1;
							s.width = (int) (width * 0.02);
							s.height = 1;
							s.name = "Small Runway Line";
							o.objects.add(s);
						}
					}
				}
				
				/* Center Line */
				{
					MapObject s = new MapObject();
					s.color = Color.white;
					s.x = (int) (x + i);
					s.y = (int) (y + Math.floor(height/2));
					s.width = (int) (((x + i + 80) > (width/* * 0.95*/)) ? ((width/* * 0.95*/) - i) : 80);
					s.height = 1;
					s.name = "Center Runway Line";
					o.objects.add(s);
				}
			}
			
			{
				MapObject s = new CharacterObject('0');
				s.x = (int) (width  *0.055);
				s.y = (int) (y + Math.floor(height/2)) - 6;
				o.objects.add(s);
			}
			{
				MapObject s = new CharacterObject('9');
				s.x = (int) (width * 0.055);
				s.y = (int) (y + Math.floor(height/2)) + 2;
				o.objects.add(s);
			}
			
			{
				MapObject s = new CharacterObject('L');
				s.x = (int) (width * 0.045);
				s.y = (int) (y + Math.floor(height/2)) - 2;
				o.objects.add(s);
			}
			
			/* Threshold */
			{
				for(int j = 4; j < height * 0.45; j += 4 ) {
					{
						MapObject s = new MapObject();
						s.color = Color.white;
						s.x = (int) (x + 2);
						s.y = (int) (y + j);
						s.width = (int) (width * 0.02);
						s.height = 1;
						s.name = "Start Runway Line";
						o.objects.add(s);
					}
					{
						MapObject s = new MapObject();
						s.color = Color.white;
						s.x = (int) (x + 2);
						s.y = (int) (y + height - j - 1);
						s.width = (int) (width * 0.02);
						s.height = 1;
						s.name = "Start Runway Line";
						o.objects.add(s);
					}
				}
			}
		}
		
		return o;
	}

	public static MapObject buildBadArea(int x, int y, int width, int height) {
		/* Tarmac */
		MapObject o = new MapObjectBad();
		o.color = Color.red;
		o.x = x;
		o.y = y;
		o.width = width;
		o.height = height;
		o.name = "Bad";
		
		return o;
	}
}
