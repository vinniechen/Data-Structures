package hw6;

import java.util.*;
import java.lang.*;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * dHeap class implements dHeapInterface and creates a heap with d branches
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
	private ArrayList<T> array;	
	private boolean maxOrder; // true = max, false = min
	private int d;
	private int nelems;
	T temp;

	/** O-argument constructor. Creates and empty dHeap with 
	 *  initial capacity = 5, and is a 2-min-heap 
	 */ 
	public dHeap()
	{
		array = new ArrayList<T>(5); 
		maxOrder = false;
		d = 2;
	}

	/** 
	 * Constructor to build a min or max dheap
	 * @param isMaxHeap  if true, this is a 2-max-heap, else a 2-min-heap 
	 * with initial size = 'capacity'
	 */ 
	public dHeap(int capacity, boolean isMaxHeap)
	{
		array = new ArrayList<T>(capacity);
		maxOrder = isMaxHeap;
		d = 2;
	}

	/** 
	 * Constructor to build a with specified initial capacity and
	 * given number of children d. 
	 * @param capacity initial capacity of the heap.	
	 * @param isMaxHeap if true, this is a max-heap, else a min-heap 
	 * @param d number of children, 
	 * @exception if d is less than one, throw IllegalArgumentException();
	 */ 
	public dHeap(int capacity, boolean isMaxHeap, int d)
	{
		if (d < 1) { // checks that d is at least 1
			throw new IllegalArgumentException();
		}
		array = new ArrayList<T>(capacity);
		maxOrder = isMaxHeap;
		this.d = d;
	}

	/**
	 * Method for the size of the dHeap
	 * @return number of elements
	 */
	public int size() {
		return nelems;
	}

	/**
	 * Adds data to the next available spot in the heap. uses bubbleUp methods
	 * to ensure correct order
	 * @param data to be added
	 */
	public void add (T data) {  
		if (data == null) {
			throw new NullPointerException();
		}

		try {
			array.add(nelems, data); // if add is within capacity
		}
		catch(IndexOutOfBoundsException e) { // if add at end of ArrayList
			array.add(data);	
		}
		// arrange in order
		if (maxOrder) { // max order
			bubbleUpMax(nelems);
		}
		else { // min order
			bubbleUpMin(nelems);
		}
		nelems++;
	}

	/**
	 * Bubbles up heap when adding to a max heap. Checks if parent and child
	 * are in correct order, switches if not.
	 * @param index where bubbleUp starts
	 */
	private void bubbleUpMax(int index) {
		int currIndex = index;
		while (currIndex >= 0 && 
				array.get(currIndex).compareTo(array.get(parentInd(currIndex)))
				> 0) { // if child is greater than parent
			temp = array.get(currIndex);
			array.set(currIndex, array.get(parentInd(currIndex)));
			array.set(parentInd(currIndex), temp);
			currIndex = parentInd(currIndex); // changes idx to parent idx

		}
	}

	/**
	 * Bubbles up heap when adding to a min heap. Checks if parent and child
	 * are in correct order, switches if not.
	 * @param index where bubbleUp starts
	 */
	private void bubbleUpMin(int index) {
		int currIndex = index;
		while (currIndex >= 0 && // if child is less than parent 
				array.get(currIndex).compareTo(array.get(parentInd(currIndex))) 
				< 0) { 
			temp = array.get(currIndex);
			array.set(currIndex, array.get(parentInd(currIndex)));
			array.set(parentInd(currIndex), temp);
			currIndex = parentInd(currIndex); // changes idx to parent idx

		}
	}

	/** Getter returns element at given index
	 * @return data at index
	 */
	public T getElement(int index) {
		return array.get(index);
	}

	/** returns the index of the parent 
	 *  @return parent index
	 * */
	private int parentInd(int index) {
		return (index-1)/d;
	}

	/** returns the index of the parent
	 *  @return parent index
	 * */
	public int getParentInd(int index) {
		return (index-1)/d;
	}

	/** returns the index of the first child
	 *  @return cgukd index
	 * */
	public int getChildInd(int index) {
		return d*index+1;
	}

	/** returns the index of the last child
	 *  @return child index
	 * */
	public int getChildIndEnd(int index) {
		if (d*index+d >= size()) {
			return size()-1;
		}
		return d*index+d;
	}

	/** Checks if parent at parameter index has a child 
	 *  @return boolean parent has child
	 * */
	private boolean hasChild(int index) {
		if (getChildInd(index) >= nelems) { // child index is less than size
			return false;
		}
		return true;
	}

	/** Removes element at root of d heap, shifts children to root by selecting
	 * next max or min element from its children.
	 * @return element removed
	 * @throws NoSuchElementException if empty
	 */
	public T remove() { 
		if (size() == 0) { // if empty
			throw new NoSuchElementException();
		}
		T removeElement = array.get(0);
		array.set(0, array.get(nelems-1));
		array.set(nelems-1, null); // set last element to be null
		nelems--;
		if (maxOrder) { // max order
			trickleDownMax(0);
		}
		else { // min order
			trickleDownMin(0);
		}
		return removeElement;
	}

	/**
	 * Trickles down to check if parent is greater than its children
	 * If not, replaces parent with greatest child and continues trickle
	 * down with index of the greatest child
	 * @param index start is 0
	 */
	private void trickleDownMax(int index) {
		int currIndex = 0;
		boolean continueTrickle = true;
		T max = array.get(currIndex); // sets current max to parent
		int maxIndex = 0;
		// while still has children and max condition not satisfied
		while (continueTrickle && hasChild(currIndex)) { 
			max = array.get(currIndex);
			// checks all children for max
			for (int i = getChildInd(currIndex); i <= getChildIndEnd(currIndex);
					i++) {
				if (max.compareTo(array.get(i)) < 0) {
					max = array.get(i);
					maxIndex = i;
				}	
			}
			if (maxIndex == currIndex) { // if no new max, list is in order
				continueTrickle = false;
			}
			else {
				array.set(maxIndex, array.get(currIndex));
				array.set(currIndex, max);
				currIndex = maxIndex; // assigns new current index based on max
			}

		}	
	}

	/**
	 * Trickles down to check if parent is less than its children
	 * If not, replaces parent with lesat child and continues trickle
	 * down with index of the least child
	 * @param index start is 0
	 */
	private void trickleDownMin(int index) {
		int currIndex = 0;
		boolean continueTrickle = true;
		T min = array.get(currIndex);
		int minIndex = 0;
		while (continueTrickle && hasChild(currIndex)) {
			min = array.get(currIndex);
			// checks all children for min
			for (int i = getChildInd(currIndex); i <= getChildIndEnd(currIndex);
					i++) {
				if (min.compareTo(array.get(i)) > 0) {
					min = array.get(i);
					minIndex = i;
				}	
			}
			if (minIndex == currIndex) { // if no new min, list is in order
				continueTrickle = false;
			}
			else {
				array.set(minIndex, array.get(currIndex));
				array.set(currIndex, min);
				currIndex = minIndex; // assigns new current index based on min
			}

		}	
	}

	/**
	 * Merges two heaps together maintaining max/min order
	 * @param hp dheap to be merged
	 * @param isMaxHeap max or min dheap
	 * @return merged dheap
	 */
	public dHeap<T> merge(dHeap<T> hp, boolean isMaxHeap) {
		int runs = hp.size();
		for (int i = 0; i < runs; i++) {
			this.add(hp.remove()); // removes from hp, adds to this
		}
		return this;
	}

}
