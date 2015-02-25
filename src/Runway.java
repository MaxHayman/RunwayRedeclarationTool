import java.util.ArrayList;
import java.util.List;


public class Runway {

	private List<Obstacle> obstacleList;
	private Controller controller;
	private int orientation;
	private Character designation;
	private float length, width, clearway, stopway, displacedThreshold, TORA, TODA, ASDA, LDA, RESA = 240;

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Runway(int orientation, Character designation, float length, float width, float clearway, float stopway, float displacedThreshold, Controller controller) {
		this.orientation = orientation;
		this.designation = designation;
		this.length = length;
		this.width = width;
		this.clearway = clearway;
		this.stopway = stopway;
		this.displacedThreshold = displacedThreshold;
		this.controller = controller;
		obstacleList = new ArrayList<Obstacle>();
		recalculate();
	}

	//============================================
	//METHODS:
	//============================================
	
	public void addObstacle(Obstacle o) {
		this.obstacleList.add(o);
		recalculate();
		controller.updateLabels();
		}
	
	public void removeObstacle(Obstacle o){
		this.obstacleList.remove(o);
		recalculate();
		controller.updateLabels();
	}
	
	//override this for display in the ComboBox:
	public String toString() {
		return this.getName();
	}


	//============================================
	//GETTERS AND SETTERS:
	//============================================
	public void setOrientation(int orientation){this.orientation = orientation;}
	public void setDesignation(Character designation){this.designation = designation;}
	public void setWidth(float width){this.width = width; recalculate();}
	public void setLength(float length){this.length = length; recalculate();}
	public void setClearway(float clearway){this.clearway = clearway; recalculate();}
	public void setStopway(float stopway){this.stopway = stopway; recalculate();}
	public void setDisplacedThreshold(float displacedThreshold){this.displacedThreshold = displacedThreshold; recalculate();}
	public float getTORA(){return this.TORA;}
	public float getTODA(){return this.TODA;}
	public float getASDA(){return this.ASDA;}
	public float getLDA(){return this.LDA;}
	
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

	//===========
	//Methods
	//===========
	public void recalculate(){
		this.TORA = length;
		this.TODA = TORA + clearway;
		this.ASDA = TORA + stopway;
		this.LDA = TORA - displacedThreshold;
		for (Obstacle o: obstacleList){
			//TORA - Displaced Threshold - Distance of object from threshold - height of object times 50 - safety distance(60m)
			//TORA - Displaced Threshold - RESA - safety distance(60m)
			float RLDA = Math.max(o.getxLocation() - displacedThreshold - (o.getzSize() * 50) - 60,
					o.getxLocation() - displacedThreshold - RESA - 60);
			if (RLDA < LDA){
				this.LDA = RLDA;
			}
		}
	}

}
