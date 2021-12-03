import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the Path class for Project 4.
 * 
 */
public class Path implements Comparable<Path> {
	
	// ========================================================== Properties
	
	private Queue<Vertex> verticies;
	private int cost;
	
	// ========================================================== Constructors
	
	public Path(Queue<Vertex> verticies, int cost) {
		this.verticies = verticies;
		this.cost = cost;
	}
	
	public Path(State s) {
		verticies = new PriorityQueue<Vertex>();
		cost = s.cost;
		
		for (char c : s.path.toCharArray()) {
			verticies.add(Graph.getVertex("" + c));
		}
		
	}

	// ========================================================== Methods
	
	@Override
	public int compareTo(Path p) {
		return cost - p.cost;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		for (Vertex v : verticies) {
			ret += (Graph.returnAddress ? v.getAddress() : v.getSymbol()) + "  -->  ";
		}
		
		return ret.substring(0, ret.length() - 6) + "\n\nThe shortest path costs " + cost + (Graph.useDistCost ? " miles" : " minutes");
	}

}
