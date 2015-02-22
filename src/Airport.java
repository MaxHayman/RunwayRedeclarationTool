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
	//GETTERS AND SETTERS:
	//============================================
	public String getName() {
		return name;
	}

}
