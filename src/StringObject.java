

public class StringObject extends MapObject{

	String s;
	public StringObject(String s) {
		this.s = s;
	}
	
	public void init() {
		for (int i = 0; i < s.length(); i++){
		    char c = s.charAt(i);        
		    MapObject co = new CharacterObject(c);
		    co.x = this.x + 8*i;
		    co.y = this.y;
		    co.color = this.color;
			this.objects.add(co);
		}
	}
	 
	void drawTop(int[][] map) {
		for(MapObject o : objects) {
			o.drawTop(map);
		}
	}
}
