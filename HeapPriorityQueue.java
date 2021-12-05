import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 
 * @author Blake Edmunds
 * @author Sam Curran
 *
 * The HeapPriorityQueue class for Lab 11. This class is of a generic data type
 * and implements PQInterface.
 */
public class HeapPriorityQueue<T extends Comparable<? super T>> implements PQInterface<T> {

	// =============================================================== Instance Properties
	
	private T[] elements;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	// =============================================================== Constructors
	
	public HeapPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int initialCapacity) {
		elements = (T[]) new Comparable[initialCapacity + 1];
	}
	
	public HeapPriorityQueue(T[] entries) {
		this(entries.length);
		size = entries.length;
		
		for (int i = 0; i < entries.length; i++) {
			elements[i + 1] = entries[i];
		}
		
		for (int r = size / 2; r > 0; r--) {
			reheapDown(r);
		}
	}
	
	// =============================================================== Methods
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		int length = elements.length;
		elements = (T[]) new Comparable[length];
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public void add(T newEntry) {
		if (isFull()) verifyCapacity();
		elements[++size] = newEntry;
		reheapUp(size);
	}
	@Override
	public T peek() {
		return elements[1];
	}
	@Override
	public T remove() {
		if (isEmpty()) throw new NoSuchElementException();
		
		T ret = elements[1];
		elements[1] = elements[size];
		elements[size--] = null;
		reheapDown(1);
		
		return ret;
	}

	@Override
	public boolean isFull() {
		return size == elements.length - 1;
	}
	
	@Override
	public String toString() {
		if (size == 0) return "[]";
		
		String ret = "";
		
		for (int i = 0; i < elements.length; i++) {
			ret += elements[i];
			if (i + 1 < elements.length) ret += ", ";
		}
		
		return "[" + ret + "]";
		
	}
	
	// =============================== Helper Methods
	
	private void reheapDown(int index) {
		if (size == 1) return;
		int parentIndex = index;
		int leftIndex = 2 * parentIndex;
		int rightIndex = 2 * parentIndex + 1;
		
		while (leftIndex <= size) {
			int maxChildIndex = findMaxChildIndex(leftIndex, rightIndex);
			swap(parentIndex, maxChildIndex);
			parentIndex = maxChildIndex;
			leftIndex = 2 * parentIndex;
			rightIndex = 2 * parentIndex + 1;
		}
	}
	
	private void reheapUp(int index) {
		if (index < 2) return;
		
		int parentIndex = index / 2;
		if (elements[index].compareTo(elements[parentIndex]) > 0) return;
		swap(index, parentIndex);
		reheapUp(parentIndex);
		
	}
	
	private int findMaxChildIndex(int leftIndex, int rightIndex) {
		if (elements[rightIndex] == null) return leftIndex;
		
		if (elements[leftIndex].compareTo(elements[rightIndex]) > 0) return leftIndex;
		else return rightIndex;
	}
	
	private void swap(int index1, int index2) {
		T temp = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = temp;
	}
	
	private void verifyCapacity() {
		if (isFull())
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
	
}



