
public class Main {
	public static void main(String args[]) {
		Airport heathrow = new Airport("Heathrow");
		
		Runway r09R = new Runway("09", "R", 3660, 3660, 3660, 3353);
		r09R.setDisplacedThreshold(307);

		Runway r27L = new Runway("27", "L", 3660, 3660, 3660, 3660);

		Runway r09L = new Runway("09", "L", 3902, 3902, 3902, 3595);
		r09L.setDisplacedThreshold(306);
		
		Runway r27R = new Runway("27", "R", 3884, 3962, 3884, 3884);
		
		RunwayPair north = new RunwayPair();
		north.add(0, r09R);
		north.add(1, r27L);
		
		RunwayPair south = new RunwayPair();
		south.add(0, r09L);
		south.add(1, r27R);
		
		heathrow.runways.add(north);
		heathrow.runways.add(south);
		
    	MainFrame mainFrame = new MainFrame(heathrow);
    	mainFrame.setVisible(true);
    	new Frame2D(mainFrame, Display2DTop.View.TOP_VIEW);
    	new Frame2D(mainFrame, Display2DTop.View.SIDE_VIEW);
    }
}
