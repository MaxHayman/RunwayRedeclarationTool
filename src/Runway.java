import java.util.ArrayList;
import java.util.List;


public class Runway {

	private List<Obstacle> obstacleList;
	private int orientation;
	private char designation;
	private float length;

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Runway(int orientation, char designation, float length) {
		this.orientation = orientation;
		this.designation = designation;
		this.length = length;
		obstacleList = new ArrayList<Obstacle>();
	}

	//============================================
	//METHODS:
	//============================================
	
	public void addObstacle(Obstacle o) {
		this.obstacleList.add(o);
	}


	//============================================
	//GETTERS AND SETTERS:
	//============================================
	public int getOrientation() {
		return orientation;
	}

	public String getName() {
		return Integer.toString(orientation) + designation;
	}

	public float getLength() {
		return length;
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
