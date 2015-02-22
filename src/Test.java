
public class Test {
	public static void main(String[] args) {
		Controller testController = new Controller();
		Runway testRunway = new Runway(9, null, 2400, 160);		
		World testWorld = new World(testController);
		testController.setWorld(testWorld);
		
		testController.addRunway(testRunway);
		testController.setCurrentRunway(testRunway);
		testController.addObstacle(new Obstacle(50, 50, 50));
		
		new Frame2D(testWorld);
		//new Frame2D(map);
	}
}
