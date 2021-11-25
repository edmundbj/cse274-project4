
public class State implements Comparable<State> {

	public String vertex, path;
	public int cost;
	
	public State(String vertex, int cost, String path) {
		this.vertex = vertex;
		this.cost = cost;
		this.path = path;
	}
	
	@Override
	public int compareTo(State other) {
		return cost - other.cost;
	}

}
