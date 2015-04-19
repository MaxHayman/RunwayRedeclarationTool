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
	private String calculationString;

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
		return "Runway " + this.getName() + " of size " + length + "*" + width + " with clearway " + clearway + " and stopway " + stopway;
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
	public String getCalculationString() {return this.calculationString;}

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
		
		for (Obstacle currentObstacle : obstacleList){
			//obstacleStart and obstacleEnd represent the start and end of the unusable area for a plane
			float obstacleStart = currentObstacle.getxLocation() - (currentObstacle.getzSize()*SLOPE_RATIO);
			float obstacleEnd = currentObstacle.getxLocation() + currentObstacle.getxSize() + blastProtection;
			
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
					//replace it with a new one
					//the end obstacle is changed to be the current obstacle
					addList.add(new UsableLength(currentLength.getStart(), obstacleStart, false, currentLength.getStartObstacle(), currentObstacle));
				}

				//if the length contains the end of the obstructed area:
				if(obstacleEnd >= currentLength.getStart() && obstacleEnd <= currentLength.getEnd()) {
					removeList.add(currentLength);
					//the start obstacle is now the current obstacle
					addList.add(new UsableLength(obstacleEnd, currentLength.getEnd(), currentLength.isEnd(), currentObstacle, currentLength.getEndObstacle()));
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
		
		//calculation string part 1:
		calculationString = "";
		if(maxTakeoffLength.getStartObstacle() != null) {
			calculationString += ("Takeoff area start: " + maxTakeoffLength.getStart() + "\n");
			calculationString += ("Preceding obstacle: " + maxTakeoffLength.getStartObstacle().getName() + "\n");
			calculationString += ("obstacle x-location + obstacle x-size + blast protection =\n");
			calculationString += (maxTakeoffLength.getStartObstacle().getxLocation() + " + " + maxTakeoffLength.getStartObstacle().getxSize() + " + " + blastProtection + " = " + maxTakeoffLength.getStart() + "\n");
			calculationString += "\n";
		} else if (this.takeoffDisplacementStart > 0) {
			calculationString += ("Takeoff area start " + maxTakeoffLength.getStart() + "due to displacement\n");
			calculationString += "\n";
		}
		
		if(maxTakeoffLength.getEndObstacle() != null) {
			calculationString += ("Takeoff area end: " + maxTakeoffLength.getEnd() + "\n");
			calculationString += ("Following obstacle: " + maxTakeoffLength.getEndObstacle().getName() + "\n");
			calculationString += ("obstacle x-location - (obstacle z-size * " + SLOPE_RATIO + ") =\n");
			calculationString += (maxTakeoffLength.getEndObstacle().getxLocation() + " - (" + maxTakeoffLength.getEndObstacle().getzSize() + " * " + SLOPE_RATIO + ") = " + maxTakeoffLength.getEnd() + "\n");
			calculationString += "\n";
		} else if (this.takeoffDisplacementEnd > 0) {
			calculationString += ("Takeoff area end " + maxTakeoffLength.getEnd() + "due to displacement\n");
			calculationString += "\n";
		}
		
		calculationString += ("usable takeoff area length = area end - area start = " + maxTakeoffLength.getEnd() + " - " + maxTakeoffLength.getStart() + "\n");
		
		//declarations calculations:
		calculationString += ("TORA = usable takeoff area length = " + TORA + "\n");
		calculationString += ("TODA = length of runway + clearway - usable takeoff area start = " + this.length + " + " + this.clearway + " - " + maxTakeoffLength.getStart() + "\n");
		if(maxTakeoffLength.isEnd()) {
			calculationString += ("ASDA = usable takeoff area length + stopway = " + maxTakeoffLength.getLength() + " + " + this.stopway + "\n");
		} else {
			calculationString += ("ASDA = usable takeoff area length = " + maxTakeoffLength.getLength() + "\n");
		}
		calculationString += "\n";
		
		//now we calculate the landing stuff:
		//here we don't need to worry about blast protection but we do need to consider the slope after an obstacle
		lengthList = new ArrayList<UsableLength>();
		lengthList.add(new UsableLength(this.landingDisplacementStart, this.length-this.landingDisplacementEnd, true));
		for(Obstacle currentObstacle : obstacleList) {
			//calculate how much area this obstacle obstructs:
			float obstacleStart = currentObstacle.getxLocation();
			float obstacleEnd = currentObstacle.getxLocation() + currentObstacle.getxSize() + (currentObstacle.getzSize() * SLOPE_RATIO);
			
			//make lists of things to be added and removed to prevent doing this during iteration
			List<UsableLength> addList = new ArrayList<UsableLength>();
			List<UsableLength> removeList = new ArrayList<UsableLength>();
			
			for(Iterator<UsableLength> it = lengthList.iterator(); it.hasNext(); ) {
				UsableLength currentLength = it.next();
				boolean removed = false;
				//if the length contains the start of the obstructed area:
				if(obstacleStart >= currentLength.getStart() && obstacleStart <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(currentLength.getStart(), obstacleStart, false, currentLength.getStartObstacle(), currentObstacle));
				}
				
				//if the length contains the end of the obstructed area:
				if(obstacleEnd >= currentLength.getStart() && obstacleEnd <= currentLength.getEnd()) {
					removeList.add(currentLength);
					addList.add(new UsableLength(obstacleEnd, currentLength.getEnd(), currentLength.isEnd(), currentObstacle, currentLength.getEndObstacle()));
				}
			}
			
			//remove and add the items in removeList and addList:
			lengthList.removeAll(removeList);
			lengthList.addAll(addList);
		}
		
		maxTakeoffLength = Collections.max(lengthList);
		LDA = maxTakeoffLength.getLength();
		
		//calculation string stuff part 2: landing
		if(maxTakeoffLength.getStartObstacle() != null) {
			calculationString += ("Landing area start: " + maxTakeoffLength.getStart() + "\n");
			calculationString += ("Preceding obstacle: " + maxTakeoffLength.getStartObstacle().getName() + "\n");
			calculationString += ("obstacle x-location + obstacle x-size + (obstacle z-size * " + SLOPE_RATIO + ") =\n");
			calculationString += (maxTakeoffLength.getStartObstacle().getxLocation() + " + "
								+ maxTakeoffLength.getStartObstacle().getxSize() + " + ("
								+ maxTakeoffLength.getStartObstacle().getzSize() + " * " + SLOPE_RATIO +") = "
								+ maxTakeoffLength.getStart() + "\n");
			calculationString += "\n";
		} else if (this.landingDisplacementStart > 0) {
			calculationString += ("landing area start " + maxTakeoffLength.getStart() + "due to displacement\n");
			calculationString += "\n";
		}
		
		if(maxTakeoffLength.getEndObstacle() != null) {
			calculationString += ("Landing area end: " + maxTakeoffLength.getEnd() + "\n");
			calculationString += ("Following obstacle: " + maxTakeoffLength.getEndObstacle().getName() + "\n");
			calculationString += ("obstacle x-location = " + maxTakeoffLength.getEndObstacle().getxLocation() + "\n");
			calculationString += "\n";
		} else if (this.landingDisplacementEnd > 0) {
			calculationString += ("Takeoff area end " + maxTakeoffLength.getEnd() + "due to displacement\n");
			calculationString += "\n";
		}
		
		calculationString += ("usable landing area length = area end - area start = " + maxTakeoffLength.getEnd() + " - " + maxTakeoffLength.getStart() + "\n");
		calculationString += ("LDA = usable landing area length = " + maxTakeoffLength.getLength() + "\n");
	}

	private class UsableLength implements Comparable<UsableLength>{
		private float start, end;
		private boolean endOfRunway;
		private Obstacle startObstacle, endObstacle;

		public UsableLength(float start, float end, boolean endOfRunway) {
			this.start = start;
			this.end = end;
			this.endOfRunway = endOfRunway;
		}
		
		public UsableLength(float start, float end, boolean endOfRunway, Obstacle startObstacle, Obstacle endObstacle) {
			this.start = start;
			this.end = end;
			this.endOfRunway = endOfRunway;
			this.startObstacle = startObstacle;
			this.endObstacle = endObstacle;
		}

		public float getStart() { return start; }
		public float getEnd() { return end; }
		public float getLength() { return (end - start); }
		public boolean isEnd() { return endOfRunway; }
		public Obstacle getStartObstacle() { return startObstacle; }
		public Obstacle getEndObstacle() { return endObstacle; }

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
