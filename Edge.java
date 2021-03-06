/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the Edge class for Project 4.
 * 
 */
public class Edge implements Comparable<Edge> {

	// ========================================================== Properties
	
	private Vertex source, destination;
	private int timeCost, distCost;
	
	// ========================================================== Constructor
	
	public Edge(Vertex source, Vertex destination, int timeCost, int distCost) {
		this.source = source;
		this.destination = destination;
		this.timeCost = timeCost;
		this.distCost = distCost;
	}
	// ========================================================== Methods

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public int getTimeCost() {
		return timeCost;
	}

	public int getDistCost() {
		return distCost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "" + source.getSymbol() + "  -->  " + destination.getSymbol();
				
	}
	
}
