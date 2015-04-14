import java.util.HashMap;
import java.util.Map;


public class Runway {

	int TORA;
	int TODA;
	int ASDA;
	int LDA;
	
	int nTORA;
	int nLDA;
	int nASDA;
	int nTODA;
	
	String name;
	
	int displacedThreshold = 0;
	Map<Obstacle, Integer> obsticles = new HashMap<Obstacle, Integer>();
	public int calcType;
	public RunwayPair pair;
	
	public Runway(String name, int TORA, int TODA, int ASDA, int LDA) {
		this.name = name;
		this.TORA = nTORA = TORA;
		this.TODA = nTODA = TODA;
		this.ASDA = nASDA = ASDA;
		this.LDA = nLDA = LDA;
	}
	
	public void setDisplacedThreshold(int displacedThreshold) {
		this.displacedThreshold = displacedThreshold;
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

	public void calcualte(int calcType, int blastProtectionAllowance) {
		int THRESHOLD;
		
		Obstacle o = getObstacle();
		this.calcType = calcType;
		if(o == null) {
			nTORA = TORA;
			nTODA = TODA;
			nASDA = ASDA;
			nLDA = LDA;
			return;
		}
		
		if(calcType == 0) {
			// Take-off Towards
			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240)
				THRESHOLD = 240;
			THRESHOLD += 60;
			nASDA = nTODA = nTORA = obsticles.get(o) + displacedThreshold - THRESHOLD;

			// Landing towards 
			
			nLDA = obsticles.get(o) - 240 /*RESA*/ - 60 /*Strip end*/;
		} else if (calcType == 1) {
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
		}
		
		if(nTORA < 0)
			nTORA = 0;
		
		if(nASDA < 0)
			nASDA = 0;
		
		if(nTODA < 0)
			nTODA = 0;
		
		if(nLDA < 0)
			nLDA = 0;
	}
	
	public void clear() {
		obsticles.clear();
	}
	
	public String toString() {
		return name;
	}
}
