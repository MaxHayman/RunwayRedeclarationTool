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
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class XMLHandler {

	public static void saveXML(Airport airport, boolean ask) {
		File f = null;

		if(airport.fileName.equals("") || ask)
		{
			File workingDirectory = new File(System.getProperty("user.dir") + "/airports");
	
			if (!workingDirectory.exists()) {
				workingDirectory.mkdir();
			}
			
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(workingDirectory);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Airport XML files", "xml");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showSaveDialog(null);

		    if(returnVal != JFileChooser.APPROVE_OPTION) {
		    	return;
		    }
		    
		    String name = chooser.getSelectedFile().getAbsolutePath();
		    if(!name.endsWith(".xml"))
		    	name += ".xml";
		    
		    f = new File(name);
		    airport.fileName = name;
		}
		else
		{
			f = new File(airport.fileName);
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(f);
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
	}

	private static BufferedReader br = null;
	static int lineCount = 0;
	
	public static Airport loadXML(File file) {
		File f = file;
		
		boolean foundFile = false;
		
		if(file != null)
			foundFile = true;
		
		
		while (!foundFile){
			File workingDirectory = new File(System.getProperty("user.dir") + "/airports");
	
			if (!workingDirectory.exists()) {
				workingDirectory.mkdir();
			}
			
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(workingDirectory);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Airport XML files", "xml");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);

		    if(returnVal != JFileChooser.APPROVE_OPTION) {
		    	return null;
		    }
		    
		    f = new File(chooser.getSelectedFile().getAbsolutePath());
		    
		    if(f.exists() && !f.isDirectory())
		    	foundFile = true;
		    else
		    	JOptionPane.showMessageDialog(null, "That is an invalid file.");
		    
		}

		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Failed to open file: " + e1);
			return null;
		}
		
		Airport airport = new Airport();
		airport.fileName = f.getAbsolutePath();
				
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
							
						} else {
							throw new IOException("first runway was not found");
						}
						readLine();
						if(readLine().equals("<Runway>")) {
							pair.add(1, readRunway(obs));
						} else {
							throw new IOException("second runway was not found");
						}
						airport.runways.add(pair);
							
					} break;
				}
			}
		} catch (IOException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error parsing XML. " + e + " error on line " + lineCount);
			//e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to close file: " + e);
		}
		return airport;
	}
	
	public static String getValue(String line, String parameter) throws IOException {
		Pattern p = Pattern.compile("^[\\s]*<" + parameter + ">(.*)<" + parameter + "\\/>[\\s]*$");
		Matcher m = p.matcher(line);
		
		if(m.find()) {
			return m.group(1);
		} else {
			throw new IOException(parameter + " was not found");
		}
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
		} else {
			throw new IOException("obstacle list was not found");
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
		
		Runway r = new Runway(orientation, designation, TORA, TODA, ASDA, LDA);
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
		} else {
			throw new IOException("obstacle list was not found");
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
