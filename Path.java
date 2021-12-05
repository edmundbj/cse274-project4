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
	
	private HeapPriorityQueue<Vertex> vertices;
	private int cost;
	
	// ========================================================== Constructors
	
	public Path(HeapPriorityQueue<Vertex> vertices, int cost) {
		this.vertices = vertices;
		this.cost = cost;
	}
	
	public Path(State s) {
		vertices = new HeapPriorityQueue<Vertex>();
		cost = s.cost;
		
		for (char c : s.path.toCharArray()) {
			vertices.add(Graph.getVertex("" + c));
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
		
		for (Vertex v : vertices) {
			ret += (Graph.returnAddress ? v.getAddress() : v.getSymbol()) + "  -->  ";
		}
		
		return ret.substring(0, ret.length() - 6) + "\n\nThe shortest path costs " + cost + (Graph.useDistCost ? " miles" : " minutes");
	}

}
