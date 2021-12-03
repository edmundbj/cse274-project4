/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the State class for Project 4.
 * 
 */
public class State implements Comparable<State> {

	// ========================================================== Properties
	
	public String vertex, path;
	public int cost;
	
	// ========================================================== Constructor
	
	public State(String vertex, int cost, String path) {
		this.vertex = vertex;
		this.cost = cost;
		this.path = path;
	}
	
	// ========================================================== Methods
	
	@Override
	public int compareTo(State other) {
		return cost - other.cost;
	}

}
