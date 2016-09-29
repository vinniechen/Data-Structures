package hw6;

import java.io.*;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

public interface dHeapInterface<T extends Comparable<?super T>> {

	/** Adds the specified element to the heap, resizes storage if full
	 * Throws NullPointerException if o is null
	 * @param o Type T
	 */
	public void add(T o);

	/** Removes and returns the element stored on the heap
	 * If the heap is empty, throws a NoSuchElementException
	 * @returns T element removed
	 */
	public T remove();

	/** Returns the number of elements stored in the heap
	 * @returns int num elements
	 */
	public int size();
}