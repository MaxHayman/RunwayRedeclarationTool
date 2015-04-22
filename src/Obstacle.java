
public class Obstacle {
	public String name;
	public int height;

	public String toString() {
		return name + " (" + height + "m tall)";
	}
	
	public Obstacle() {
		
	}
	public Obstacle(String name, int height) {
		this.name = name;
		this.height = height;
	}
}
