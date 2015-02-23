
public class Main {
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		World world = new World(controller);
		controller.setWorld(world);
		
		new Frame2D(world, controller);
	}

}
