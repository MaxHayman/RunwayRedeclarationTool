
public class RunwayPair {
	public Runway[] runways = new Runway[2];
	
	public void add(int pos, Runway runway) {
		runways[pos] = runway;
		runway.pair = this;
	}
	
	public void removeObstacle(Obstacle o) {
		runways[0].obsticles.remove(o);
		runways[1].obsticles.remove(o);
	}
}
