import java.util.PriorityQueue;
import java.util.Queue;

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
	
//	public Path(String path) {
//		verticies = new HashSet<Vertex>();
//		cost = 0;
//		char prev = path.charAt(0);
//		
//		for (char c : path.substring(1).toCharArray()) {
//			Edge e = Graph.getEdge("" + prev, "" + c);
//			verticies.add(e.getSource());
//			
//			if (Graph.useDistCost) cost += e.getDistCost();
//			else cost += e.getTimeCost();
//			
//		}
//		
//	}

	// ========================================================== Methods
	
	@Override
	public int compareTo(Path p) {
		return cost - p.cost;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		//System.out.println(verticies.size());
		
		for (Vertex v : verticies) {
			ret += (Graph.returnAddress ? v.getAddress() : v.getSymbol()) + " ---> ";
		}
		
		return ret.substring(0, ret.length() - 6) + "    :    cost = " + cost;
	}

}
