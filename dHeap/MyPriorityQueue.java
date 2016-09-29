package hw6;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * Priority Queue class uses min binary dHeap to create queue
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

public class MyPriorityQueue <T extends Comparable <? super T>> {
	dHeap queue;

	/**
	 * Creates min dHeap
	 * @param capacity initial capacity of MyPriorityQueue
	 */
	public MyPriorityQueue(int capacity) {
		queue = new dHeap(capacity, false);

	}
	/**
	 * Adds to dHeap
	 * @param Data to be added
	 * @throws NullPointerException if data is null
	 */
	public void add(T data) {
		if (data == null) {
			throw new NullPointerException();
		}
		queue.add(data);
	}

	/**
	 * Removes smallest element from MyPriorityQueue
	 * @return Element removed
	 */
	public T poll() {
		if (queue.size() == 0) { // returns null if empty
			return null;
		}
		T element = (T) queue.remove();
		return element;
	}



}