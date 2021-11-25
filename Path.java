import java.util.Set;
import java.util.TreeSet;

public class Path implements Comparable<Path> {
	
	// ========================================================== Properties
	
	private Set<Vertex> verticies;
	private int cost;
	
	// ========================================================== Constructors
	
	public Path(Set<Vertex> verticies, int cost) {
		this.verticies = verticies;
		this.cost = cost;
	}
	
	public Path(String path) {
		verticies = new TreeSet<Vertex>();
		cost = 0;
		char prev = path.charAt(0);
		
		for (char c : path.substring(1).toCharArray()) {
			Edge e = Graph.getEdge("" + prev, "" + c);
			verticies.add(e.getSource());
			
			if (Graph.useDistCost) cost += e.getDistCost();
			else cost += e.getTimeCost();
			
		}
		
	}

	// ========================================================== Methods
	
	@Override
	public int compareTo(Path p) {
		return cost - p.cost;
	}

}
