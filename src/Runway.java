import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Runway {

	private int TORA;
	private int TODA;
	private int ASDA;
	private int LDA;
	
	int nTORA;
	int nLDA;
	int nASDA;
	int nTODA;
	
	String name;
	
	int displacedThreshold = 0;
	private Map<Obstacle, Integer> obsticles = new HashMap<Obstacle, Integer>();
	public int calcType;
	//public RunwayPair pair;
	
	public Runway(String name, int TORA, int TODA, int ASDA, int LDA) {
		this.name = name;
		this.TORA = nTORA = TORA;
		this.TODA = nTODA = TODA;
		this.ASDA = nASDA = ASDA;
		this.LDA = nLDA = LDA;
	}
	
	public void setTORA(int TORA) {
		this.TORA = nTORA = TORA;
		calcualte(300);
	}
	
	public void setTODA(int TODA) {
		this.TODA = nTODA = TODA;
		calcualte(300);
	}
	
	public void setASDA(int ASDA) {
		this.ASDA = nASDA = ASDA;
		calcualte(300);
	}
	
	public void setLDA(int LDA) {
		this.LDA = nLDA = LDA;
		calcualte(300);
	}
	
	public void setDisplacedThreshold(int displacedThreshold) {
		this.displacedThreshold = displacedThreshold;
	}
	
	public void addObstacle(Obstacle o, int distance) {
		obsticles.put(o, distance);
		calcualte(300);
	}
	
	public void removeObstacle(Obstacle o) {
		obsticles.remove(o);
		calcualte(300);
	}
	
	public Set<Obstacle> getObstacles() {
		return obsticles.keySet();
	}

	public Obstacle getObstacle() {
		double grad = 0.02;

		for(Obstacle o : obsticles.keySet()) {
			int dist = obsticles.get(o);
			boolean passed = true;
			for(Obstacle j : obsticles.keySet()) {
				int otherDist = obsticles.get(j);
				int dif = otherDist - dist;
				double height = o.height + dif*grad;
				if (height < j.height) {
					passed = false;
					break;
				}
			}

			if(passed) {
				return o;
			}
		}

		return null;

	}
	
	public int whatCalc() {
		Obstacle o = getObstacle();

		if(o == null) {
			return 0;
		}
		
		if(obsticles.get(o) < TODA/2)
			return 1;
		else
			return 0;
	}

	public void calcualte(int blastProtectionAllowance) {
		int THRESHOLD;
		
		Obstacle o = getObstacle();
		this.calcType = whatCalc();
		if(o == null) {
			nTORA = TORA;
			nTODA = TODA;
			nASDA = ASDA;
			nLDA = LDA;
			return;
		}
		System.out.println("go" + obsticles.get(o));
		System.out.println("go");
		if(calcType == 0) {
			System.out.println("go0");
			// Take-off Towards
			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240)
				THRESHOLD = 240;
			THRESHOLD += 60;
			nASDA = nTODA = nTORA = obsticles.get(o) + displacedThreshold - THRESHOLD;

			// Landing towards 
			
			nLDA = obsticles.get(o) - 240 /*RESA*/ - 60 /*Strip end*/;
		} else if (calcType == 1) {
			System.out.println("go1");
			// Take-off Away
			
			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240)
				THRESHOLD = 240;
			THRESHOLD += 60;

			nTORA = TORA - obsticles.get(o) - blastProtectionAllowance - displacedThreshold;
			nASDA = ASDA - obsticles.get(o) - blastProtectionAllowance - displacedThreshold;
			nTODA = TODA - obsticles.get(o) - blastProtectionAllowance - displacedThreshold;
			
			// Landing over
			
			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240)
				THRESHOLD = 240;
			THRESHOLD += 60;

			nLDA = LDA - obsticles.get(o) - THRESHOLD;
			
			System.out.println(LDA  + " " + obsticles.get(o) + " " + THRESHOLD);
		}
		
		if(nTORA < 0)
			nTORA = 0;
		
		if(nASDA < 0)
			nASDA = 0;
		
		if(nTODA < 0)
			nTODA = 0;
		
		if(nLDA < 0)
			nLDA = 0;
		
		System.out.println("TORA: " + nTORA);
		System.out.println("TODA: " + nTODA);
		System.out.println("ASDA: " + nASDA);
		System.out.println("LDA: " + nLDA);
	}
	
	public void clear() {
		obsticles.clear();
	}
	
	public String toString() {
		return name;
	}
}
