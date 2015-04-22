import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/*

public class ControllerTest {
	
	Controller testController;
	TestRunway r;
	TestObstacle o;
	
	@Before
	public void setup() {
		testController = new Controller();
	//	r = new TestRunway();
	//	o = new TestObstacle();
	}

	@Test
	public void testSingleRunway() {
		testController.addRunway(r);
		assertEquals(testController.getRunwayList().get(0), r);
	}
	
	@Test
	public void testCurrentRunway() {
		testController.addRunway(r);
		testController.setCurrentRunway(r);
		assertEquals(testController.getRunwayLength(), 3000, 0);
		assertEquals(testController.getRunwayWidth(), 50, 0);
	}
	
	@Test
	public void testSingleObstacle() {
		testController.addRunway(r);
		testController.setCurrentRunway(r);
		//testController.addObstacle(o);
		assertEquals(testController.getObstacleList().get(0), o);
	}
/*
	private class TestRunway extends Runway {
		public TestRunway() {
			//super(9, new Character('L'), (float)3000, (float)50, (float)0, (float)0, (float)0, testController);
		}
	}
	
	private class TestObstacle extends Obstacle {
		public TestObstacle() {
			super(10, 10, 10);
		}
	}
}*/
