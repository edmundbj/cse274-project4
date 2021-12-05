/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 *
 */
public interface PQInterface<T extends Comparable<? super T>> {

	boolean isEmpty();
	
	void clear();
	
	int size();
	
	void add(T newEntry);
	
	T peek(); // return null if list is empty
	
	T remove(); // throws NoSuchElementException if list is empty
	
	boolean isFull();
}
