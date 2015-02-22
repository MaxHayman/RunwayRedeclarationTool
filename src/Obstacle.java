
public class Obstacle {
	
	final static float DEFAULT_X_LOC = 0, DEFAULT_Y_LOC = 0, DEFAULT_ANGLE = 0;
	final static String DEFAULT_NAME = "obstacle";
	float xSize, ySize, zSize, xLocation, yLocation, angle;
	String name;

	//============================================
	//CONSTRUCTORS:
	//============================================
	
	//main constructor with everything set
	public Obstacle(float xSize, float ySize, float zSize, float xLocation, float yLocation, float angle, String name) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.angle = angle;
		this.name = name;
	}
	
	//constructor with default location and angle:
	public Obstacle(float xSize, float ySize, float zSize, String name) {
		this(xSize, ySize, zSize, DEFAULT_X_LOC, DEFAULT_Y_LOC, DEFAULT_ANGLE, name);
	}
	
	//constructor with default location, angle and name:
	public Obstacle(float xSize, float ySize, float zSize) {
		this(xSize, ySize, zSize, DEFAULT_X_LOC, DEFAULT_Y_LOC, DEFAULT_ANGLE, DEFAULT_NAME);
	}
	
	//constructor with default name:
	public Obstacle(float xSize, float ySize, float zSize, float xLocation, float yLocation, float angle) {
		this(xSize, ySize, zSize, xLocation, yLocation, angle, DEFAULT_NAME);
	}
	
	//============================================
	//GETTERS AND SETTERS:
	//============================================
	
	//these are all auto-generated (ty based Eclipse)
	public float getxLocation() {
		return xLocation;
	}

	public void setxLocation(float xLocation) {
		this.xLocation = xLocation;
	}

	public float getyLocation() {
		return yLocation;
	}

	public void setyLocation(float yLocation) {
		this.yLocation = yLocation;
	}

	public float getxSize() {
		return xSize;
	}

	public float getySize() {
		return ySize;
	}

	public float getzSize() {
		return zSize;
	}

	public float getAngle() {
		return angle;
	}

	public String getName() {
		return name;
	}

}
