import java.util.ArrayList;
import java.util.List;


public class Runway {

	private List<Obstacle> obstacleList;
	private int orientation;
	private Character designation;
	private float length, width;

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Runway(int orientation, Character designation, float length, float width) {
		this.orientation = orientation;
		this.designation = designation;
		this.length = length;
		this.width = width;
		obstacleList = new ArrayList<Obstacle>();
	}

	//============================================
	//METHODS:
	//============================================
	
	public void addObstacle(Obstacle o) {
		this.obstacleList.add(o);
	}
	
	//override this for display in the ComboBox:
	public String toString() {
		return this.getName();
	}


	//============================================
	//GETTERS AND SETTERS:
	//============================================
	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}
	
	public int getOrientation() {
		return orientation;
	}

	public String getName() {
		if(designation == null) {
			return Integer.toString(orientation);
		}
		return Integer.toString(orientation) + designation;
	}

	public float getLength() {
		return length;
	}
	
	public float getWidth() {
		return width;
	}

	//TODO: implement calculations
	public int getTORA() {
		return 0;
	}

	public int getTODA() {
		return 0;
	}

	public int getASDA() {
		return 0;
	}

	public int getTDA() {
		return 0;
	}

}
