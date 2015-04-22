import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XMLHandler {

	public static void saveXML(Airport airport) {
		PrintWriter out = null;
		try {
			out = new PrintWriter("filename.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		out.println("<Airport>");

		for(RunwayPair pair : airport.runways) {
			out.println("\t<RunwayPair>");

			out.println("\t\t<Obstacles>");
			for(Obstacle o : pair.runways[0].obstacles.keySet()) {
				out.println("\t\t\t<Obstacle>");
				out.println("\t\t\t\t<Id>" + o.hashCode() + "<Id/>");
				out.println("\t\t\t\t<Name>" + o.name + "<Name/>");
				out.println("\t\t\t\t<Height>" + o.height + "<Height/>");
				out.println("\t\t\t<Obstacle/>");
			}
			out.println("\t\t<Obstacles/>");

			for(Runway runway : pair.runways) {
				out.println("\t\t<Runway>");
				out.println("\t\t\t<Orientation>" + runway.orientation + "<Orientation/>");
				out.println("\t\t\t<Designation>" + runway.designation + "<Designation/>");
				out.println("\t\t\t<TODA>" + runway.TODA + "<TODA/>");
				out.println("\t\t\t<TORA>" + runway.TORA + "<TORA/>");
				out.println("\t\t\t<ASDA>" + runway.ASDA + "<ASDA/>");
				out.println("\t\t\t<LDA>" + runway.LDA + "<LDA/>");
				out.println("\t\t\t<DisplacedThreshold>" + runway.displacedThreshold + "<DisplacedThreshold/>");

				out.println("\t\t\t<Obstacles>");
				for(Obstacle o : runway.obstacles.keySet()) {
					out.println("\t\t\t\t<Obstacle>");
					out.println("\t\t\t\t\t<Id>" + o.hashCode() + "<Id/>");
					out.println("\t\t\t\t\t<DistanceFromThreshold>" + runway.obstacles.get(o) + "<DistanceFromThreshold/>");
					out.println("\t\t\t\t<Obstacle/>");
				}
				out.println("\t\t\t<Obstacles/>");

				out.println("\t\t<Runway/>");
			}

			out.println("\t<RunwayPair/>");
		}

		out.println("<Airport/>");
		out.close();
		loadXML();
	}

	private static BufferedReader br = null;
	static int lineCount = 0;
	public static Airport loadXML() {
		Airport airport = new Airport();

		try {
			br = new BufferedReader(new FileReader("filename.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String line;
		RunwayPair pair = null;

		Map<Integer, Obstacle> obs = null;
		try {
			while((line = readLine()) != null) {

				switch(line) {
					case "<RunwayPair>": {
						pair = new RunwayPair();
						obs = readObstacles();
						if(readLine().equals("<Runway>")) {
							pair.add(0, readRunway(obs));
							
						}
						readLine();
						if(readLine().equals("<Runway>")) {
							pair.add(1, readRunway(obs));
						}
						airport.runways.add(pair);
							
					} break;
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error parsing xml. " + e + " error on line " + lineCount);
			e.printStackTrace();
		}

		return airport;
	}
	
	public static String getValue(String line, String parameter) {
		Pattern p = Pattern.compile("^[\\s]*<" + parameter + ">(.*)<" + parameter + "\\/>[\\s]*$");
		Matcher m = p.matcher(line);
		
		if(m.find()) {
			return m.group(1);
		}
		
		return null;

	}
	
	public static SimpleEntry<Integer, Obstacle> readObstacleData() throws NumberFormatException, IOException {
		Obstacle o = new Obstacle();
		int id = Integer.parseInt(getValue(readLine(), "Id"));
		String name = getValue(readLine(), "Name");
		int height = Integer.parseInt(getValue(readLine(), "Height"));
		o.name = name;
		o.height = height;
		return new SimpleEntry<Integer, Obstacle>(id, o);
	}
	
	public static Map<Integer, Obstacle> readObstacles() throws IOException {
		Map<Integer, Obstacle> obs = new HashMap<Integer, Obstacle>();
		
		String line;

		if(readLine().equals("<Obstacles>")) {
			line = readLine();
	
			if(!line.equals("<Obstacles/>")) {
				while(line.equals("<Obstacle>") && !line.equals("<Obstacles/>")) {
					SimpleEntry<Integer, Obstacle> o = readObstacleData();
					obs.put(o.getKey(), o.getValue());
					line = readLine();
				}
				readLine();
			}
		}

		return obs;
	}
	
	public static Runway readRunway(Map<Integer, Obstacle> obs) throws IOException {
		
		String orientation = getValue(readLine(), "Orientation");
		String designation = getValue(readLine(), "Designation");
		int TODA = Integer.parseInt(getValue(readLine(), "TODA"));
		int TORA = Integer.parseInt(getValue(readLine(), "TORA"));
		int ASDA = Integer.parseInt(getValue(readLine(), "ASDA"));
		int LDA = Integer.parseInt(getValue(readLine(), "LDA"));
		int displacedThreshold = Integer.parseInt(getValue(readLine(), "DisplacedThreshold"));
		
		Runway r = new Runway(orientation, designation, TODA, TORA, ASDA, LDA);
		r.setDisplacedThreshold(displacedThreshold);
		
		if(readLine().equals("<Obstacles>")) {
			String line = readLine();
			
			if(!line.equals("<Obstacles/>")) {
				while(line.equals("<Obstacle>") && !line.equals("<Obstacles/>")) {
					int id = Integer.parseInt(getValue(readLine(), "Id"));
					int distanceFromThreshold = Integer.parseInt(getValue(readLine(), "DistanceFromThreshold"));
					r.obstacles.put(obs.get(id), distanceFromThreshold);
					line = readLine();
				}
				readLine();
			}
		}
		
		return r;
	}
	
	private static String readLine() throws IOException {
		lineCount++;
		String line = br.readLine();
		if(line != null)
			line = line.replaceAll("\\s+","");
		
		return line;
	}
}
