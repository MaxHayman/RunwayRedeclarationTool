import java.util.List;


public class Controller {

	private Airport airport;
	private Runway currentRunway;
	private World world;
	private Frame2D frame;

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Controller() {
		airport = new Airport("airport name");
	}


	//============================================
	//PUBLIC METHODS:
	//============================================

	public void setWorld(World w) {
		this.world = w;
	}
	
	public void setFrame(Frame2D f) {
		this.frame = f;
	}

	public void addRunway(Runway r) {
		airport.addRunway(r);
		if(frame!=null) {
			frame.updateRunways();
		}
	}

	public void addObstacle(Obstacle o) {
		if(currentRunway != null) {
			currentRunway.addObstacle(o);
			updateView();
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
		frame.updateDisplay();
	}

	//============================================
	//GETTERS AND SETTERS:
	//============================================

	public void setCurrentRunway(Runway r) {
		this.currentRunway = r;
		this.updateView();
	}
	
	//I don't really want to make the obstacles visible to the view but I can't think of a better way to do it:
	public List<Obstacle> getObstacleList() {
		return currentRunway.getObstacleList();
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
