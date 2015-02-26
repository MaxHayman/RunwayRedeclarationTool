import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class RunwayCalculationsTest {
	
	Controller testController = new Controller();
	Runway heathrow09R;
	Runway heathrow09L;
	Runway heathrow27L;
	Runway heathrow27R;
	//length = 3902
	//clearway and stopway = 0
	//displaced threshold = 306

	@Before
	public void setUp() {
		heathrow09R = new Runway(9, 'R', (float)3902.0, (float)50.0, (float)0.0, (float)0.0, (float)0.0, testController);
		heathrow09L = new Runway(9, 'L', (float)3902.0, (float)50.0, (float)0.0, (float)0.0, (float)0.0, testController);
		heathrow27L = new Runway(27, 'L', (float)3902.0, (float)50.0, (float)0.0, (float)0.0, (float)0.0, testController);
		heathrow27R = new Runway(27, 'R', (float)3902.0, (float)50.0, (float)0.0, (float)0.0, (float)0.0, testController);
		heathrow09L.addObstacle(new Obstacle(0, 0, 12, -50, 25, 0, ""));
		heathrow27L.addObstacle(new Obstacle(0, 0, 12, 3646, 25, 0, ""));
	}

	@Test
	public void testGetTORA() {
		System.out.println("calculated 09L TORA: " + heathrow09L.getTORA());
		System.out.println("calculated 27R TORA: " + heathrow27R.getTORA());
		assertEquals(heathrow09L.getTORA(), 3346, 0.001);
		assertEquals(heathrow27R.getTORA(), 2986, 0.001);
	}

	@Test
	public void testGetTODA() {
		System.out.println("calculated 09L TODA: " + heathrow09L.getTODA());
		System.out.println("calculated 27R TODA: " + heathrow27R.getTODA());
		assertEquals(heathrow09L.getTODA(), 3356, 0.001);
		assertEquals(heathrow27L.getTODA(), 2986, 0.001);
	}

	@Test
	public void testGetASDA() {
		System.out.println("calculated 09L ASDA: " + heathrow09L.getASDA());
		System.out.println("calculated 27R ASDA: " + heathrow27R.getASDA());
		assertEquals(heathrow09L.getASDA(), 3346, 0.001);
		assertEquals(heathrow27R.getASDA(), 2986, 0.001);
	}

	@Test
	public void testGetLDA() {
		System.out.println("calculated 09L LDA: " + heathrow09L.getLDA());
		System.out.println("calculated 27R LDA: " + heathrow27R.getLDA());
		assertEquals(heathrow09L.getLDA(), 2985, 0.001);
		assertEquals(heathrow09L.getLDA(), 3346, 0.001);
	}

}
