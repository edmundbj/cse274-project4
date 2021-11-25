
public class Edge {

	// ========================================================== Properties
	
	private Vertex source, destination;
	private int timeCost, distCost;
	
	// ========================================================== Constructors
	
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
	
}
