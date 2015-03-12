import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;


public class Runway {

	private List<Obstacle> obstacleList;
	private Controller controller;
	private int orientation;
	private Character designation;
	private float length, width, clearway, stopway, TORA, TODA, ASDA, LDA, RESA = 240;
	private float takeoffDisplacementStart, takeoffDisplacementEnd, landingDisplacementStart, landingDisplacementEnd;
	private static final int SLOPE_RATIO = 50;
	private float blastProtection = 300;

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
		this.takeoffDisplacementStart = displacedThreshold;
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
	public void setDisplacedThreshold(float displacedThreshold){this.takeoffDisplacementEnd = displacedThreshold; recalculate();}
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

		//base TORA = length
		//base TODA = length + clearway
		//base ASDA = length + stopway
		//base LDA = length - displacedThreshold

		//take-off iteration
		//this needs to be done separately to account for the blast protection after the obstacle rather than the slope		
		List<UsableLength> lengthList = new ArrayList<UsableLength>();
		//add a length containing the entire runway minus displacement to the length list:
		lengthList.add(new UsableLength((float)0 + this.takeoffDisplacementStart, this.length-this.takeoffDisplacementEnd, true));
		
		for (Obstacle o: obstacleList){
			//obstacleStart and obstacleEnd represent the start and end of the unusable area for a plane
			float obstacleStart = o.getxLocation() - (o.getzSize()*SLOPE_RATIO);
			float obstacleEnd = o.getxLocation() + o.getxSize() + blastProtection;
			
			//make lists of things to be added and removed to prevent doing this during iteration
			List<UsableLength> addList = new ArrayList<UsableLength>();
			List<UsableLength> removeList = new ArrayList<UsableLength>();

			//Iterate through lengths
			for(Iterator<UsableLength> it = lengthList.iterator(); it.hasNext(); ) {
				UsableLength currentLength = it.next();
				boolean removed = false;
				
				//if the length contains the start of the obstructed area:
				if(obstacleStart >= currentLength.getStart() && obstacleStart <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(currentLength.getStart(), obstacleStart, false)); //replace it with a new one
				}

				//if the length contains the end of the obstructed area:
				if(obstacleEnd >= currentLength.getStart() && obstacleEnd <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(obstacleEnd, currentLength.getEnd(), currentLength.isEnd()));
				}
			}
			
			//remove and add the items in removeList and addList:
			lengthList.removeAll(removeList);
			lengthList.addAll(addList);
		}

		//remove all invalid lengths:
		for (UsableLength l: lengthList) {
			if(l.getStart() > l.getEnd()) {
				lengthList.remove(l);
			}
		}

		UsableLength maxTakeoffLength = Collections.max(lengthList);
		TORA = maxTakeoffLength.getLength();
		//the TODA is the normal TODA minus the start of the length being used
		TODA = this.length + this.clearway - maxTakeoffLength.getStart();
		if(maxTakeoffLength.isEnd()) {
			ASDA = maxTakeoffLength.getLength() + this.stopway;
		} else {
			ASDA = maxTakeoffLength.getLength();
		}
		
		//now we calculate the landing stuff:
		//here we don't need to worry about blast protection but we do need to consider the slope after an obstacle
		lengthList = new ArrayList<UsableLength>();
		lengthList.add(new UsableLength(this.landingDisplacementStart, this.length-this.landingDisplacementEnd, true));
		for(Obstacle o : obstacleList) {
			//calculate how much area this obstacle obstructs:
			float obstacleStart = o.getxLocation();
			float obstacleEnd = o.getxLocation() + o.getxSize() + (o.getzSize() * SLOPE_RATIO);
			
			//make lists of things to be added and removed to prevent doing this during iteration
			List<UsableLength> addList = new ArrayList<UsableLength>();
			List<UsableLength> removeList = new ArrayList<UsableLength>();
			
			for(Iterator<UsableLength> it = lengthList.iterator(); it.hasNext(); ) {
				UsableLength currentLength = it.next();
				boolean removed = false;
				//if the length contains the start of the obstructed area:
				if(obstacleStart >= currentLength.getStart() && obstacleStart <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(currentLength.getStart(), obstacleStart, false)); //replace it with a new one
				}
				
				//if the length contains the end of the obstructed area:
				if(obstacleEnd >= currentLength.getStart() && obstacleEnd <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(obstacleEnd, currentLength.getEnd(), currentLength.isEnd()));
				}
			}
			
			//remove and add the items in removeList and addList:
			lengthList.removeAll(removeList);
			lengthList.addAll(addList);
		}
		
		LDA = Collections.max(lengthList).getLength();
	}

	private class UsableLength implements Comparable<UsableLength>{
		private float start, end;
		private boolean endOfRunway;

		public UsableLength(float start, float end, boolean endOfRunway) {
			this.start = start;
			this.end = end;
			this.endOfRunway = endOfRunway;
		}

		public float getStart() { return start; }
		public float getEnd() { return end; }
		public float getLength() { return (end - start); }
		public boolean isEnd() { return endOfRunway; }

		public int compareTo(UsableLength that) {
			//I don't subtract the two lengths because that would require a cast from float to int
			if(this.getLength() == that.getLength()) {
				return 0;
			} else if (this.getLength() > that.getLength()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	//might not need this, not sure what to do if the longest TODA is not the same section as the longest TORA
	private class TODAComparator implements Comparator<UsableLength> {

		public int compare(UsableLength l1, UsableLength l2) {
			float l1TODA = l1.getLength(), l2TODA = l2.getLength();

			//if either length is the end length, add the clearway before comparing them
			if(l1.isEnd()) { l1TODA += clearway; }
			else if(l2.isEnd()) { l2TODA += clearway; }

			//I don't subtract the two lengths because that would require a cast from float to int
			if(l1TODA == l2TODA) {
				return 0;
			} else if (l1TODA > l2TODA) {
				return 1;
			} else {
				return -1;
			}
		}

	}


}
