package hw6;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * Test dHeap class's methods and constructors
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

public class dHeapTester {
	dHeap<Integer> addMax;
	dHeap<Integer> addMin;
	dHeap<Integer> dAddMax;
	dHeap<Integer> removeMax;
	dHeap<Integer> removeMaxMerge;
	dHeap<Integer> merge;
	dHeap<Integer> removeMin;
	dHeap<Integer> dRemoveMin;

	@Before
	public void setUp() throws Exception {
		addMax = new dHeap<Integer>(3, true);
		addMin = new dHeap<Integer>();
		dAddMax = new dHeap<Integer>(6, true, 3);

		// sets up removeMax
		removeMax = new dHeap<Integer>(3, true);
		removeMax.add(new Integer(10));
		removeMax.add(new Integer(5));
		removeMax.add(new Integer(6));
		removeMax.add(new Integer(3));
		removeMax.add(new Integer(2));
		removeMax.add(new Integer(1));
		removeMax.add(new Integer(4));

		// sets up removeMin
		removeMin = new dHeap<Integer>();
		removeMin.add(new Integer(1));
		removeMin.add(new Integer(2));
		removeMin.add(new Integer(3));
		removeMin.add(new Integer(5));
		removeMin.add(new Integer(4));
		removeMin.add(new Integer(6));
		removeMin.add(new Integer(10));



	}

	/** Tests bubbleUpMax, size() */
	@Test
	public void testAddMax() {

		// Test bubbleUpMax()
		addMax.add(new Integer(1));
		addMax.add(new Integer(3));
		addMax.add(new Integer(2));
		addMax.add(new Integer(7));
		addMax.add(new Integer(5));
		addMax.add(new Integer(6));
		assertEquals("Parent to index 6", 2, addMax.getParentInd(6));
		assertEquals("Parent to index 2", 0, addMax.getParentInd(2));
		assertEquals("Size should be 6", 6, addMax.size());

		// Should be: 7 5 6 1 3 2
		assertEquals("ind 0", new Integer(7), addMax.getElement(0));
		assertEquals("ind 1", new Integer(5), addMax.getElement(1));
		assertEquals("ind 2", new Integer(6), addMax.getElement(2));
		assertEquals("ind 3", new Integer(1), addMax.getElement(3));
		assertEquals("ind 4", new Integer(3), addMax.getElement(4));
		assertEquals("ind 5", new Integer(2), addMax.getElement(5));
	}

	/** Tests bubbleUpMin */
	@Test
	public void testAddMin() {
		addMin.add(new Integer(6));
		addMin.add(new Integer(5));
		addMin.add(new Integer(7));
		addMin.add(new Integer(2));
		addMin.add(new Integer(3));
		addMin.add(new Integer(1));

		// Should be: 1 3 2 6 5 7
		assertEquals("ind 0", new Integer(1), addMin.getElement(0));
		assertEquals("ind 1", new Integer(3), addMin.getElement(1));
		assertEquals("ind 2", new Integer(2), addMin.getElement(2));
		assertEquals("ind 3", new Integer(6), addMin.getElement(3));
		assertEquals("ind 4", new Integer(5), addMin.getElement(4));
		assertEquals("ind 5", new Integer(7), addMin.getElement(5));
	}

	/** Tests add and remove with non-binary heap */
	@Test
	public void testdAddMax() {
		dAddMax.add(new Integer(5));
		dAddMax.add(new Integer(2));
		dAddMax.add(new Integer(1));
		dAddMax.add(new Integer(5));
		dAddMax.add(new Integer(6));
		dAddMax.add(new Integer(3));
		dAddMax.add(new Integer(9));
		dAddMax.add(new Integer(15));

		// Should be: 15 6 9 5 2 3 5 1
		assertEquals("ind 0", new Integer(15), dAddMax.getElement(0));
		assertEquals("ind 1", new Integer(6), dAddMax.getElement(1));
		assertEquals("ind 2", new Integer(9), dAddMax.getElement(2));
		assertEquals("ind 3", new Integer(5), dAddMax.getElement(3));
		assertEquals("ind 4", new Integer(2), dAddMax.getElement(4));
		assertEquals("ind 5", new Integer(3), dAddMax.getElement(5));
		assertEquals("ind 6", new Integer(5), dAddMax.getElement(6));
		assertEquals("ind 7", new Integer(1), dAddMax.getElement(7));	

		dAddMax.add(new Integer(6));
		dAddMax.add(new Integer(1));
		dAddMax.remove();


		// Should be: 9 6 6 5 2 3 5 1 1
		assertEquals("ind 0", new Integer(9), dAddMax.getElement(0));
		assertEquals("ind 1", new Integer(6), dAddMax.getElement(1));
		assertEquals("ind 2", new Integer(6), dAddMax.getElement(2));
		assertEquals("ind 3", new Integer(5), dAddMax.getElement(3));
		assertEquals("ind 4", new Integer(2), dAddMax.getElement(4));
		assertEquals("ind 5", new Integer(3), dAddMax.getElement(5));
		assertEquals("ind 6", new Integer(5), dAddMax.getElement(6));
		assertEquals("ind 6", new Integer(1), dAddMax.getElement(7));
		assertEquals("ind 6", new Integer(1), dAddMax.getElement(8));

		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		dAddMax.remove();
		assertEquals("Empty", 0, dAddMax.size());

	}

	/** Tests trickleDownMax() */
	@Test
	public void testRemoveMax() {
		// Before: 10 5 6 3 2 1 4
		removeMax.remove(); // removes 10
		// After: 6 5 4 3 2 1
		assertEquals("ind 0", new Integer(6), removeMax.getElement(0));
		assertEquals("ind 1", new Integer(5), removeMax.getElement(1));
		assertEquals("ind 2", new Integer(4), removeMax.getElement(2));
		assertEquals("ind 3", new Integer(3), removeMax.getElement(3));
		assertEquals("ind 4", new Integer(2), removeMax.getElement(4));
		assertEquals("ind 5", new Integer(1), removeMax.getElement(5));

		removeMax.remove();
		// After: 5 3 4 1 2
		assertEquals("ind 0", new Integer(5), removeMax.getElement(0));
		assertEquals("ind 1", new Integer(3), removeMax.getElement(1));
		assertEquals("ind 2", new Integer(4), removeMax.getElement(2));
		assertEquals("ind 3", new Integer(1), removeMax.getElement(3));
		assertEquals("ind 4", new Integer(2), removeMax.getElement(4));
	}

	/** Tests trickleDownMin */
	@Test
	public void testRemoveMin() {
		removeMin.remove();
		/*for (int i= 0; i < removeMin.size(); i++) {
			System.out.print(removeMin.getElement(i)+" ");
		} */
		assertEquals("ind 0", new Integer(2), removeMin.getElement(0));
		assertEquals("ind 1", new Integer(4), removeMin.getElement(1));
		assertEquals("ind 2", new Integer(3), removeMin.getElement(2));
		assertEquals("ind 3", new Integer(5), removeMin.getElement(3));
		assertEquals("ind 4", new Integer(10), removeMin.getElement(4));
		assertEquals("ind 5", new Integer(6), removeMin.getElement(5));

		removeMin.remove();
		removeMin.remove();

		assertEquals("ind 0", new Integer(4), removeMin.getElement(0));
		assertEquals("ind 1", new Integer(5), removeMin.getElement(1));
		assertEquals("ind 2", new Integer(6), removeMin.getElement(2));
		assertEquals("ind 3", new Integer(10), removeMin.getElement(3));
	}

	/** Tests merge */
	@Test
	public void testMerge() {
		removeMaxMerge = new dHeap<Integer>(3, true);
		removeMaxMerge.add(new Integer(9));
		removeMaxMerge.add(new Integer(8));
		removeMaxMerge.add(new Integer(7));
		removeMaxMerge.add(new Integer(3));
		removeMaxMerge.add(new Integer(5));
		removeMaxMerge.add(new Integer(4));
		removeMaxMerge.add(new Integer(1));

		merge = removeMax.merge(removeMaxMerge, true);

		// Should be 10 9 6 8 7 4 4 3 5 2 5 1 3 1
		assertEquals("ind 0", new Integer(10), merge.getElement(0));
		assertEquals("ind 1", new Integer(9), merge.getElement(1));
		assertEquals("ind 2", new Integer(6), merge.getElement(2));
		assertEquals("ind 3", new Integer(8), merge.getElement(3));

		assertEquals("ind 4", new Integer(7), merge.getElement(4));
		assertEquals("ind 5", new Integer(4), merge.getElement(5));
		assertEquals("ind 6", new Integer(4), merge.getElement(6));
		assertEquals("ind 6", new Integer(3), merge.getElement(7));
		assertEquals("ind 6", new Integer(5), merge.getElement(8));
		assertEquals("ind 6", new Integer(2), merge.getElement(9));
		assertEquals("ind 6", new Integer(5), merge.getElement(10));
		assertEquals("ind 6", new Integer(1), merge.getElement(11));
		assertEquals("ind 6", new Integer(3), merge.getElement(12));
		assertEquals("ind 6", new Integer(1), merge.getElement(13));
	}

	/** Tests Exceptions */
	@Test
	public void testExceptions() {
		try {
			dHeap<Integer> test = new dHeap<Integer>(5, true, -1);
			addMax.add(null);
			addMax.remove();
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {	
		}
		catch (NoSuchElementException ex) {
		}
		catch (IllegalArgumentException exc) {

		}
	}
}
