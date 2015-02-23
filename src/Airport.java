import java.util.ArrayList;
import java.util.List;


public class Airport {

	private  List<Runway> runwayList;
	private String name;

	//============================================
	//CONSTRUCTORS:
	//============================================
	public Airport(String name) {
		this.name = name;
		this.runwayList = new ArrayList<Runway>();
	}

	//============================================
	//METHODS:
	//============================================
	
	public void addRunway(Runway r) {
		runwayList.add(r);
	}

	//============================================
	//GETTERS AND SETTERS:
	//============================================
	public List<Runway> getRunwayList() {
		return runwayList;
	}
	
	public String getName() {
		return name;
	}

}
