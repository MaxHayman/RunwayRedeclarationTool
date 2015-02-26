
public class Test {
	public static void main(String[] args) {
		Controller testController = new Controller();
		Runway testRunway = new Runway(9, null, 2400, 160, 0, 0, 0, testController);
		testController.addRunway(testRunway);
		//testController.addRunway(new Runway(18, 'L', 600, 80, 0, 0, 0, testController));
		testController.setCurrentRunway(testRunway);
		testController.addObstacle(new Obstacle(10, 10, 12, 100, 75, 0, "ob"));
		//new Frame2D(map);
	}
}
