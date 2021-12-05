import java.util.ArrayList;
import java.util.PriorityQueue;

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
	
	private ArrayList<Vertex> vertices;
	private int cost;
	
	// ========================================================== Constructors
	
	public Path(ArrayList<Vertex> vertices, int cost) {
		this.vertices = vertices;
		this.cost = cost;
	}
	
	public Path(State s) {
		vertices = new ArrayList<Vertex>();
		//this.cost = Dijkstra.totalCost;
		this.cost = s.cost;
		
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
			ret += (Graph.returnAddress ? v.getAddress() + "  -->  \n": v.getSymbol() + "  -->  ");
		}
		
		return ret.substring(0, ret.length() - 6) + "\n\nThe shortest path costs " + cost + (Graph.useDistCost ? " miles" : " minutes");
	}

}
