import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the Graph class for Project 4.
 * 
 */
public class Graph {
	
	// ========================================================== Properties
	
	public static boolean useDistCost = true;
	public static boolean returnAddress = false;
	private static Map<Vertex, Set<Edge>> graph;
	
	// ========================================================== Constructor
	
	public Graph(String fileName) {
		
		graph = new HashMap<Vertex, Set<Edge>>();
		readFile(fileName);
		
	}
	
	// ========================================================== Methods
	
	public void readFile(String fileName) {
		try {
			
			// Creates a scanner
			Scanner file = new Scanner(new File(fileName));
			String line = file.nextLine();
			
			Map<String, String> verticies = new HashMap<String, String>();
			
			// Skips lines until the Nodes are reached
			while (!line.equals("<Nodes>")) { line = file.nextLine(); }
			
			// Skips two lines of header text in the file
			file.nextLine();
			line = file.nextLine();
			
			// Creates Vertex objects (each of which contains a symbol and an address property)
			while (!line.equals("</Nodes>")) {
				String[] s = line.split("\t");
				verticies.put(s[0], s[1]);
				line = file.nextLine();
			}
			
			// Skips lines until Edges are reached
			while (!line.equals("<Edges>")) { line = file.nextLine(); }
			file.nextLine();
			
			// Creates Edge objects (each of which contains a source, destination, time cost, 
			// and distance cost property) and adds them to a set
			line = file.nextLine();
			String[] s = line.split("\t");
			while (!line.equals("</Edges>")) {
				Vertex v = new Vertex(s[0], verticies.get(s[0]));
				Set<Edge> edges = new HashSet<Edge>();

				do {
					Vertex destination = new Vertex(s[1], verticies.get(s[1]));
					edges.add(new Edge(v, destination, Integer.parseInt(s[2]), Integer.parseInt(s[3])));
					line = file.nextLine();
					s = line.split("\t");
				} while (s[0].equals(v.getSymbol()));
				
				// When the next line contains a different source Vertex, the current set of Edges are
				// added to the graph Map as values of the corresponding Vertex key (the source Vertex)
				graph.put(v, edges);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception f) {
			f.printStackTrace();
		}
	}
	
	// Returns the Vertex object that contains the corresponding symbol
	public static Vertex getVertex(String symbol) {
		for (Vertex v : graph.keySet()) {
			if (v.getSymbol().equals(symbol)) return v;
		}
		
		return null;
	}
	
	// Returns a set of Vertex objects representing the "neighbors" of a given Vertex object
	public static Set<Vertex> getNeighbors(Vertex v) {
		Set<Vertex> ret = new HashSet<Vertex>();
		
		for (Edge e : graph.get(v)) {
			ret.add(e.getDestination());
		}
		
		return ret;
	}
	
	// Returns the Edge object that contains the corresponding source and destination Vertex symbols
	public static Edge getEdge(String v1, String v2) {
		Set<Edge> edges = graph.get(getVertex(v1));
		
		for (Edge e : edges) {
			if (e.getDestination().equals(getVertex(v2))) return e;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		for (Vertex v : graph.keySet()) {
			for (Edge e : graph.get(v)) {
				ret += (returnAddress ? e.getSource().getAddress() : e.getSource().getSymbol()) + " -----> " +
						(returnAddress ? e.getDestination().getAddress() : e.getDestination().getSymbol()) + " -----> " +
						(useDistCost ? e.getDistCost() : e.getTimeCost()) + "\n";
			}
		}
		
		return ret;
	}
	
}
