import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {
	
	// ========================================================== Properties
	
	public static boolean useDistCost = true;
	public static boolean returnAddress = true;
	private static Map<Vertex, Set<Edge>> graph;
	
	// ========================================================== Constructors
	
	// HashMap<Vertex, Set<Edge>>;
	// vertex is the key, set is the value
	
	
	public Graph(String fileName) {
		
		graph = new HashMap<Vertex, Set<Edge>>();
		
		// Variable to check loops
		int count = 0;
		
		try {
			
			// Reads in the file
			Scanner file = new Scanner(new File(fileName));
			String line = file.nextLine();
			
			Map<String, String> verticies = new HashMap<String, String>();
			
			while (!line.equals("<Nodes>")) { line = file.nextLine(); }
			
			// Skips two lines of header text in the file
			file.nextLine();
			line = file.nextLine();
			
			while (!line.equals("</Nodes>")) {
				String[] s = line.split("\t");
				verticies.put(s[0], s[1]);
				line = file.nextLine();
			}
			
			while (!line.equals("<Edges>")) { line = file.nextLine(); }
			file.nextLine();
			
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
				
				graph.put(v, edges);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception f) {
			f.printStackTrace();
		}
	}
	
	// ========================================================== Methods
	
	public void addVertex(Character symbol, String address) {
		// use getVertex() to check if the Vertex object already exists
		// If null, create a Vertex and add it to the HashMap
	}
	
	public void addEdge(Vertex v0, Vertex v1, Integer c) {
		// If v0 doesn't exist as a key in my HashMap, create a new Vertex and add it as a key with
		// Create a new edge: Edge e = new Edge(v1, c)
		// Get the value (Set) of key (v0) and add the 
	}
	
	public static Vertex getVertex(String symbol) {
		for (Vertex v : graph.keySet()) {
			if (v.getSymbol().equals(symbol)) return v;
		}
		
		return null;
	}
	
	public static Set<Vertex> getNeighbors(Vertex v) {
		Set<Vertex> ret = new TreeSet<Vertex>();
		
		for (Edge e : graph.get(v)) {
			ret.add(e.getDestination());
		}
		
		return ret;
	}
	
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
		
		if (returnAddress) {
			if (useDistCost) {
				for (Vertex v : graph.keySet()) {
					for (Edge e : graph.get(v)) {
						ret += e.getSource().getAddress() + " -----> " + e.getDestination().getAddress() + " === " + e.getDistCost() + "\n";
					}
				}
			} else {
				for (Vertex v : graph.keySet()) {
					for (Edge e : graph.get(v)) {
						ret += e.getSource().getAddress() + " -----> " + e.getDestination().getAddress() + " === " + e.getTimeCost() + "\n";
					}
				}
			}
		} else {
			if (useDistCost) {
				for (Vertex v : graph.keySet()) {
					for (Edge e : graph.get(v)) {
						ret += e.getSource().getSymbol() + " -----> " + e.getDestination().getSymbol() + " === " + e.getDistCost() + "\n";
					}
				}
			} else {
				for (Vertex v : graph.keySet()) {
					for (Edge e : graph.get(v)) {
						ret += e.getSource().getSymbol() + " -----> " + e.getDestination().getSymbol() + " === " + e.getTimeCost() + "\n";
					}
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {
		Graph g = new Graph("MapInformation.txt");
		
		System.out.println(g.toString());
	}
	
}
