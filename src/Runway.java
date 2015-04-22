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
	
	int displacedThreshold = 0;
	Map<Obstacle, Integer> obstacles = new HashMap<Obstacle, Integer>();
	public int calcType;
	public RunwayPair pair;

	public String orientation;
	public String designation;
	
	private String calculationsString = "";
	
	public Runway(String orientation, String designation, int TORA, int TODA, int ASDA, int LDA) {
		this.orientation = orientation;
		this.designation = designation;

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

		for(Obstacle o : obstacles.keySet()) {
			int dist = obstacles.get(o);
			boolean passed = true;
			for(Obstacle j : obstacles.keySet()) {
				int otherDist = obstacles.get(j);
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

	public void calculate(int calcType, int blastProtectionAllowance) {
		int THRESHOLD;
		String limitingFactor = "";
		calculationsString = "";

		Obstacle o = getObstacle();
		this.calcType = calcType;
		if(o == null) {
			nTORA = TORA;
			nTODA = TODA;
			nASDA = ASDA;
			nLDA = LDA;
			calculationsString += "No obstacles: TORA, TODA, ASDA and LDA unchanged";
			return;
		}

		if(calcType == 0) {
			// Take-off Towards
			calculationsString += "TAKE-OFF TOWARDS\n";
			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240) {
				THRESHOLD = 240;
				limitingFactor = "RESA";
			} else {
				limitingFactor = "slope calculation";
			}
			THRESHOLD += 60;
			calculationsString += limitingFactor + " = " + THRESHOLD + "\n";
			nASDA = nTODA = nTORA = obstacles.get(o) + displacedThreshold - THRESHOLD;
			calculationsString += "TORA\t= obstacle distance from threshold + displaced threshold - " + limitingFactor + "\n";
			calculationsString += "\t= " + obstacles.get(o) + " + " + displacedThreshold + " - " + THRESHOLD + "\n";
			calculationsString += "\n";

			calculationsString += "TODA\t= obstacle distance from threshold + displaced threshold - " + limitingFactor + "\n";
			calculationsString += "\t= " + obstacles.get(o) + " + " + displacedThreshold + " - " + THRESHOLD + "\n";
			calculationsString += "\n";

			calculationsString += "ASDA\t= obstacle distance from threshold + displaced threshold - " + limitingFactor + "\n";
			calculationsString += "\t= " + obstacles.get(o) + " + " + displacedThreshold + " - " + THRESHOLD + "\n";
			calculationsString += "\n";

			// Landing towards
			calculationsString += "LANDING TOWARDS\n";

			nLDA = obstacles.get(o) - 240 /*RESA*/ - 60 /*Strip end*/;
			calculationsString += "LDA\t= distance from threshold - RESA - strip end\n";
			calculationsString += "\t= " + obstacles.get(o) + " - 240  - 60"; 
		} else if (calcType == 1) {
			// Take-off Away
			calculationsString += "TAKE-OFF AWAY\n";

			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240) {
				THRESHOLD = 240;
				limitingFactor = "RESA";
			} else {
				limitingFactor = "slope calculations";
			}
			THRESHOLD += 60;

			nTORA = TORA - obstacles.get(o) - blastProtectionAllowance - displacedThreshold;
			nASDA = ASDA - obstacles.get(o) - blastProtectionAllowance - displacedThreshold;
			nTODA = TODA - obstacles.get(o) - blastProtectionAllowance - displacedThreshold;

			calculationsString += "TORA\t= obstacle distance from threshold - blast allowance - displaced threshold";
			calculationsString += "\t= " + obstacles.get(o) + " - " + blastProtectionAllowance + " - " + displacedThreshold;
			calculationsString += "\n";

			calculationsString += "TODA\t= obstacle distance from threshold - blast allowance - displaced threshold";
			calculationsString += "\t= " + obstacles.get(o) + " - " + blastProtectionAllowance + " - " + displacedThreshold;
			calculationsString += "\n";

			calculationsString += "ASDA\t= obstacle distance from threshold - blast allowance - displaced threshold";
			calculationsString += "\t= " + obstacles.get(o) + " - " + blastProtectionAllowance + " - " + displacedThreshold;
			calculationsString += "\n";

			// Landing over
			calculationsString += "LANDING OVER\n";

			THRESHOLD = (o.height * 50);
			if(THRESHOLD < 240) {
				THRESHOLD = 240;
				limitingFactor = "RESA";
			} else {
				limitingFactor = "slope calculation";
			}
			THRESHOLD += 60;

			nLDA = LDA - obstacles.get(o) - THRESHOLD;
			calculationsString += "LDA\t= original LDA - obstacle distance from threshold - " + limitingFactor;
			calculationsString += "\t= " + LDA + " - " + obstacles.get(o) + " - " + THRESHOLD;
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
	
	public String getCalculationsString() {
		return calculationsString;
	}
	
	public void clear() {
		obstacles.clear();
	}
	
	public String toString() {
		return orientation+designation;
	}
}
