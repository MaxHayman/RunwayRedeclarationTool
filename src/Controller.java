import java.util.ArrayList;
import java.util.List;


public class Controller {

	private Airport airport;
	private Runway currentRunway;
	private Obstacle currentObstacle;
	private World world;
	private ControlFrame frame;
	public static EventManager eventManager = new EventManager();

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Controller() {
		airport = new Airport("airport name");
		
		world = new World(this);
		frame = new ControlFrame(this, world);
	}


	//============================================
	//PUBLIC METHODS:
	//============================================

	public void addRunway(Runway r) {
		airport.addRunway(r);
		if(frame!=null) {
			frame.updateRunways();
		}
	}
	
	public void removeRunway(Runway r){
		airport.removeRunway(r);
		if(frame!=null) {
			frame.updateRunways();
		}
	}
	
	public void updateRunways(){
		if(frame!=null){
			frame.updateRunways();
		}
	}
	
	public void addObstacle(Obstacle o) {
		if(currentRunway != null) {
			currentRunway.addObstacle(o);
			updateView();
		}
		if(frame!=null){
			frame.updateObstacles();
		}
	}
	
	public void removeObstacle(Obstacle o){
		if(currentRunway != null){
			currentRunway.removeObstacle(o);
			updateView();
		}
		if(frame!=null){
			frame.updateObstacles();
		}
	}
	
	public void updateObstacles(){
		if(frame!=null){
			frame.updateObstacles();
		}
	}

	public boolean viewingRunway() {
		return (currentRunway != null);
	}

	//============================================
	//PRIVATE METHODS:
	//============================================
	
	private void updateView() {
		world.update();
		eventManager.notify(EventManager.EventName.UPDATE_DISPLAY);
	}

	//============================================
	//GETTERS AND SETTERS:
	//============================================

	public void setCurrentRunway(Runway r) {
		this.currentRunway = r;
		this.updateView();
	}
	
	public void setCurrentObstacle(Obstacle o) {
		this.currentObstacle = o;
		this.updateView();
	}
	
	//I don't really want to make the obstacles visible to the view but I can't think of a better way to do it:
	public List<Obstacle> getObstacleList() {
		if (currentRunway != null){
			return currentRunway.getObstacleList();
		} else {return null;}
	}
	
	public List<Runway> getRunwayList() {
		return airport.getRunwayList();
	}

	public float getRunwayLength() {
		return currentRunway.getLength();
	}

	public float getRunwayWidth() {
		return currentRunway.getWidth();
	}

}
