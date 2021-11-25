import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Dijkstra {

	// ========================================================== Properties
	
	public static int totalCost = 0;
	
	// ========================================================== Constructors
	
	// ========================================================== Methods
	
	/**
	 * 1. Don't need to create a Dijkstra object in main method since this method is static, just call it.
	 * 
	 * 2. Remember to replace PriorityQueue with our implementation of HeapPriorityQueue.
	 */
	public static Path shortestPath(String v1, String v2) {
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(v1, 0, v1));
		ArrayList<String> visited = new ArrayList<String>();
		
		while (!pq.isEmpty()) {
			State nextEntry = pq.remove();
			if (visited.contains(nextEntry.vertex)) {
				continue;
			} else {
				// not sure
			}
			
			visited.add(nextEntry.vertex);
			if (nextEntry.vertex.equals(v2)) {
				return new Path(nextEntry.path); // changes from State to Path
			} else {
				String currVertex = nextEntry.vertex;
				int currCost = nextEntry.cost;
				String currPath = nextEntry.path;
				Set<Vertex> neighbors = Graph.getNeighbors(Graph.getVertex(currPath));
				
				for (Vertex v : neighbors) { 
					if (!visited.contains(v.getSymbol())) { // every unvisited neighbor to currVertex, V
						int nextCost = 0; // edge cost of currVertex --> V
						if (Graph.useDistCost) nextCost = currCost + Graph.getEdge(currVertex, v.getSymbol()).getDistCost();
						else nextCost = currCost + Graph.getEdge(currVertex, v.getSymbol()).getTimeCost();
						String nextPath = currPath + v.getSymbol(); // V appended
						pq.add(new State(v.getSymbol(), nextCost, nextPath));
					}
				}
				
			}
		}
		
		throw new NoSuchElementException();
		
	}
	
}
