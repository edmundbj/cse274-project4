import java.util.ArrayList;
import java.util.Set;

/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the Dijkstra class for Project 4.
 * 
 */
public class Dijkstra {
	
	// ========================================================== Methods

	@SuppressWarnings("static-access")
	public static Path shortestPath(Graph g, String v1, String v2) {
		HeapPriorityQueue<State> pq = new HeapPriorityQueue<State>();
		pq.add(new State(v1, 0, v1));
		ArrayList<String> visited = new ArrayList<String>();
		
		while (!pq.isEmpty()) {
			State nextEntry = pq.remove();
			if (visited.contains(nextEntry.vertex)) {
				continue;
			}
			
			visited.add(nextEntry.vertex);
			if (nextEntry.vertex.equals(v2)) {
				return new Path(nextEntry);
			} else {
				String currVertex = nextEntry.vertex;
				int currCost = nextEntry.cost;
				String currPath = nextEntry.path;
				
				Set<Vertex> neighbors = g.getNeighbors(g.getVertex(currVertex));
				
				for (Vertex v : neighbors) { 
					if (!visited.contains(v.getSymbol())) {
						int nextCost = 0;
						if (g.useDistCost) nextCost = currCost + g.getEdge(currVertex, v.getSymbol()).getDistCost();
						else nextCost = currCost + g.getEdge(currVertex, v.getSymbol()).getTimeCost();
						String nextPath = currPath + v.getSymbol();
						pq.add(new State(v.getSymbol(), nextCost, nextPath));
					}
				}
				
			}
		}
		
		// there is no possible path between the two Vertices
		return null;
		
	}
	
}
