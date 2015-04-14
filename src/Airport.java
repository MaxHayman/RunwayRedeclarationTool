import java.util.ArrayList;
import java.util.List;


public class Airport {
	List<RunwayPair> runways = new ArrayList<RunwayPair>();
	String name;
	
	public Airport(String name) {
		this.name = name;
	}
}
