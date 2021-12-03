/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 * 
 * This is the Vertex class for Project 4.
 * 
 */
public class Vertex implements Comparable<Vertex> {
	
	// ========================================================== Properties
	
	private String symbol, address;
	
	// ========================================================== Constructor
	
	public Vertex(String symbol, String address) {
		this.symbol = symbol;
		this.address = address;
	}
	
	// ========================================================== Methods
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getAddress() {
		return address;
	}

	@Override
	public int compareTo(Vertex o) {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vertex)) return false;
		Vertex v = (Vertex) o;
		return symbol.equals(v.getSymbol()) &&
				address.equals(v.getAddress());	
	}
	
}
