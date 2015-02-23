
public class Test {
	public static void main(String[] args) {
		Controller testController = new Controller();
		Runway testRunway = new Runway(9, null, 2400, 160, 0, 0, 0);
		
		testController.addRunway(testRunway);
		testController.addRunway(new Runway(18, 'L', 600, 80, 0, 0, 0));
		testController.setCurrentRunway(testRunway);
		testController.addObstacle(new Obstacle(50, 50, 50));
		//new Frame2D(map);
	}
}
