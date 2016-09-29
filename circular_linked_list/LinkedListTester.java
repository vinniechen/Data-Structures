package hw2;

// Name: Vinnie CHen
// PID: A12148745
// Login: cs12sau

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Title: class CLinkedListTester Description: JUnit test class for LinkedList
 * class
 */

/*
 * You should modify the information above to add your name
 * 
 * Finally, when your tester is complete, you will rename it
 * CLinkedListTester.java (renaming LinkedList to CLinkedList everywhere in the
 * file) so that you can use it to test your CLinkedList and MyListIterator
 * classes.
 */
public class LinkedListTester {
	private LinkedList<Integer> empty;
	private LinkedList<Integer> one;
	private LinkedList<Integer> several;
	private LinkedList<String> slist;
	static final int DIM = 5;

	private CLinkedList<Integer> cTester;
	private CLinkedList<Integer> cTesterFull;
	private CLinkedList<Integer> cSlist;
	private CLinkedList<Integer> cNewList;

	private ListIterator<Integer> iterTester;
	private ListIterator<Integer> iterTesterFull;

	/**
	 * Standard Test Fixture. An empty list, a list with one entry (0) and a
	 * list with several entries (0,1,2)
	 */
	@Before
	public void setUp() {
		empty = new LinkedList<Integer>();

		one = new LinkedList<Integer>();
		one.add(0, new Integer(0));

		several = new LinkedList<Integer>();
		// List: 1,2,3,...,Dim
		for (int i = DIM; i > 0; i--)
			several.add(0, new Integer(i));

		// List: "First","Last"
		slist = new LinkedList<String>();
		slist.add(0, "First");
		slist.add(1, "Last");

		// ****** FOR CLINKEDLISTTESTER ******
		cTester = new CLinkedList<Integer>();

		cTesterFull = new CLinkedList<Integer>();
		cTesterFull.add(0, new Integer(0));
		cTesterFull.add(1, new Integer(1));
		cTesterFull.add(2, new Integer(2));
		cTesterFull.add(3, new Integer(3));
		cTesterFull.add(4, new Integer(4));
		

		cSlist = new CLinkedList<Integer>();

		// ******* FOR MYLISTITERATOR **********
		iterTester = cTester.listIterator();
		iterTesterFull = cTesterFull.listIterator();
	}

	/** Test if heads of the lists are correct */
	@Test
	public void testGetHead() {
		assertEquals("Check 0", new Integer(0), one.get(0));
		assertEquals("Check 0", new Integer(1), several.get(0));
	}

	/** Test if size of lists are correct */
	@Test
	public void testListSize() {
		assertEquals("Check Empty Size", 0, empty.size());
		assertEquals("Check One Size", 1, one.size());
		assertEquals("Check Several Size", DIM, several.size());
	}

	/** Test setting a specific entry */
	@Test
	public void testSet() {
		slist.set(1, "Final");
		assertEquals("Setting specific value", "Final", slist.get(1));
	}

	/** Test isEmpty */
	@Test
	public void testEmpty() {
		assertTrue("empty is empty", empty.isEmpty());
		assertTrue("one is not empty", !one.isEmpty());
		assertTrue("several is not empty", !several.isEmpty());
	}

	/** Test out of bounds exception on get */
	@Test
	public void testGetException() {
		try {
			empty.get(0);
			// This is how you can test when an exception is supposed
			// to be thrown
			fail("Should have generated an exception");
		} catch (IndexOutOfBoundsException e) {
			// normal
		}
	}

	/** Test if element is removed correctly */
	@Test
	public void testRemove() {
		several.remove(2);
		assertEquals("Remove 3", new Integer(4), several.get(2));
		assertEquals("Remove 3", new Integer(5), several.get(3));
	}

	/** Test iterator on empty list and several list */
	@Test
	public void testIterator() {
		int counter = 0;
		ListIterator<Integer> iter;
		for (iter = empty.listIterator(); iter.hasNext();) {
			fail("Iterating empty list and found element");
		}
		counter = 0;
		for (iter = several.listIterator(); iter.hasNext(); iter.next())
			counter++;
		assertEquals("Iterator several count", counter, DIM);
	}

	// CLINKEDLIST TESTS
	/** Test CLinkedList constructor */
	@Test
	public void cTestConstructor() {
		cNewList = new CLinkedList<Integer>();
		assertEquals("Num Elements", 0, cNewList.size());
	}

	/** Test size method */
	@Test
	public void ctestSize() {
		cSlist.add(0, new Integer(2));
		cSlist.add(0, new Integer(4));
		cSlist.add(0, new Integer(6));
		assertEquals("Size", 3, cSlist.size());
	}

	/** Test get method */
	@Test
	public void ctestGet() {
		assertEquals("Get index 0", new Integer(0), cTesterFull.get(0));
		assertEquals("Get index 4", new Integer(4), cTesterFull.get(4));
	}

	/** Test add method */
	@Test
	public void ctestAdd() {
		cTester.add(0, new Integer(3)); // create first node
		// 3
		assertEquals("Add 3", new Integer(3), cTester.get(0));

		cTester.add(0, new Integer(6)); // add before head
		// 6, 3
		assertEquals("Add 6", new Integer(6), cTester.get(0));

		cTester.add(1, new Integer(4)); // add before tail
		// 6, 4, 3
		assertEquals("Add 4", new Integer(4), cTester.get(1));

		cTester.add(1, new Integer(5));
		// 6, 5, 4, 3
		assertEquals("Add 5", new Integer(5), cTester.get(1));

		cTester.add(4, new Integer(2)); // add after tail
		// 6, 5, 4, 3, 2
		assertEquals("Add 2", new Integer(2), cTester.get(4));
	}

	/** Test remove method */
	@Test
	public void ctestRemove() {
		// 0, 1, 2, 3, 4

		// remove index 2
		cTesterFull.remove(2);
		assertEquals("Remove 2", new Integer(3), cTesterFull.get(2));
		// 0, 1, 3, 4

		// remove head
		cTesterFull.remove(0);
		assertEquals("Remove head", new Integer(1), cTesterFull.get(0));
		// 1, 3, 4

		// remove tail
		cTesterFull.remove(2);
		assertEquals("Remove tail", new Integer(3), cTesterFull.get(1));
		// 1, 3

		cTesterFull.remove(0);
		assertEquals("One node", new Integer(3), cTesterFull.get(0));
		// 3

		cTesterFull.remove(0);
		assertEquals("Size 0", 0, cTesterFull.size());

	}

	/** Test set method */
	@Test
	public void ctestSet() {
		cTesterFull.set(0, new Integer(7));
		assertEquals("Set i0 as 7", new Integer(7), cTesterFull.get(0));
		cTesterFull.set(2, new Integer(7));
		assertEquals("Set i0 as 7", new Integer(7), cTesterFull.get(2));
		cTesterFull.set(4, new Integer(7));
		assertEquals("Set i0 as 7", new Integer(7), cTesterFull.get(4));
	}

	/** Test clear method */
	@Test
	public void ctestClear() {
		cTesterFull.clear();
		assertEquals("Clear", cTesterFull, cTester);
	}

	/** Test empty method */
	@Test
	public void ctestEmpty() {
		assertFalse(cTesterFull.isEmpty());
		assertTrue(cTester.isEmpty());
	}


	/** Test out of bounds exception on get */
	@Test
	public void ctestGetException() {
		try {
			cTester.add(12, new Integer(5));
			cTester.add(-2, new Integer(2));
			cTester.add(1, null);
			cTesterFull.add(12, new Integer(5));
			cTesterFull.add(-2, new Integer(2));
			cTesterFull.add(1, null);
			cTester.remove(-1);
			cTester.remove(13);
			cTesterFull.remove(-5);
			cTesterFull.remove(40);
			cTester.set(12, new Integer(5));
			cTester.set(-2, new Integer(2));
			cTester.set(1, null);
			cTesterFull.set(12, new Integer(5));
			cTesterFull.set(-2, new Integer(2));
			cTesterFull.set(1, null);
			cTester.get(-1);
			cTester.get(13);
			cTesterFull.get(-5);
			cTesterFull.get(40);

			fail("Should have generated an exception");
		} catch (IndexOutOfBoundsException e) {
			// normal
		} catch (NullPointerException ex) {

		}
	}

	// ******* FOR MYLISTITERATOR **********

	/** Test for add to empty cLinkedList */
	@Test
	public void iterTestAdd() {
		// Empty cLinkedList
		iterTester.add(new Integer(5)); // add first node
		assertEquals("Check prev index", 0, iterTester.previousIndex());
		assertEquals("Check next index", 0, iterTester.nextIndex());
		Integer nextVal = iterTester.next();
		Integer prevVal = iterTester.previous();
		assertEquals("Check value", new Integer(5), nextVal);
		assertEquals("Check value", new Integer(5), prevVal);
		iterTester.add(new Integer(3)); // add at head
		assertEquals("Check prev index", 1, iterTester.previousIndex());
		assertEquals("Check next index", 1, iterTester.nextIndex());
		nextVal = iterTester.next();
		assertEquals("Check value", new Integer(5), nextVal);
		nextVal = iterTester.next();
		assertEquals("Check value", new Integer(3), nextVal);
		iterTester.previous();
		iterTester.add(new Integer(4)); // add to middle
		prevVal = iterTester.previous();
		assertEquals("Check prev val", new Integer(4), prevVal);

		// Full cLinkedList
		iterTesterFull.add(new Integer(5)); // add at head
		assertEquals("Check prev index", 5, iterTesterFull.previousIndex());
		assertEquals("Check next index", 1, iterTesterFull.nextIndex());
		prevVal = iterTesterFull.previous();
		iterTesterFull.add(new Integer(10)); // add in middle
		assertEquals("Check prev value", new Integer(5), prevVal);
		assertEquals("Check prev index", 5, iterTesterFull.previousIndex());

		try {
			iterTesterFull.add(null);
			fail("Should have generated an exception");
		} catch (NullPointerException e) {

		}
	}

	/** Test hasNext() method */
	@Test
	public void iterHasNext() {
		assertFalse(iterTester.hasNext());
		assertTrue(iterTesterFull.hasNext());
	}

	/** Test hasPrevious() method */
	@Test
	public void iterHasPrevious() {
		assertFalse(iterTester.hasPrevious());
		assertTrue(iterTesterFull.hasPrevious());
	}

	/** Test nextIndex */
	@Test
	public void iterNextIndex() {
		iterTesterFull.previous();
		assertEquals("Check next index", 0, iterTesterFull.nextIndex());
		iterTesterFull.next();
		assertEquals("Check next index", 1, iterTesterFull.nextIndex());
		iterTesterFull.next();
		assertEquals("Check next index", 2, iterTesterFull.nextIndex());
		iterTesterFull.next();
		assertEquals("Check next index", 3, iterTesterFull.nextIndex());
		iterTesterFull.next();
		assertEquals("Check next index", 4, iterTesterFull.nextIndex());
		iterTesterFull.next();
		assertEquals("Check next index", 0, iterTesterFull.nextIndex());
	}

	/** Test previousIndex */
	@Test
	public void iterPreviousIndex() {
		iterTesterFull.next();
		assertEquals("Check previous index", 0, iterTesterFull.previousIndex());
		iterTesterFull.previous();
		assertEquals("Check previous index", 4, iterTesterFull.previousIndex());
		iterTesterFull.previous();
		assertEquals("Check previous index", 3, iterTesterFull.previousIndex());
		iterTesterFull.previous();
		assertEquals("Check previous index", 2, iterTesterFull.previousIndex());
		iterTesterFull.previous();
		assertEquals("Check previous index", 1, iterTesterFull.previousIndex());
		iterTesterFull.previous();
		assertEquals("Check previous index", 0, iterTesterFull.previousIndex());
	}

	/** Test next */
	@Test
	public void iterTestNext() {
		Integer nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(0), nextVal);
		nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(1), nextVal);
		nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(2), nextVal);
		nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(3), nextVal);
		nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(4), nextVal);
		nextVal = iterTesterFull.next();
		assertEquals("Next Values", new Integer(0), nextVal);

		try {
			iterTester.next();

			fail("Should have generated an exception");
		} catch (NoSuchElementException e) {

		}
	}

	/** Test previous */
	@Test
	public void iterTestPrevious() {
		Integer prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(4), prevVal);
		prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(3), prevVal);
		prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(2), prevVal);
		prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(1), prevVal);
		prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(0), prevVal);
		prevVal = iterTesterFull.previous();
		assertEquals("Next Values", new Integer(4), prevVal);

		try {
			iterTester.previous();

			fail("Should have generated an exception");
		} catch (NoSuchElementException e) {

		}
	}

	/** Test remove */
	@Test
	public void iterRemove() {
		iterTesterFull.next();
		iterTesterFull.remove(); // remove backwards
		Integer nextVal = iterTesterFull.next();
		assertEquals("New Right", new Integer(1), nextVal);
		iterTesterFull.previous();
		Integer prevVal = iterTesterFull.previous();
		assertEquals("New Left", new Integer(4), prevVal);

		iterTesterFull.remove(); // remove forwards
		prevVal = iterTesterFull.previous();
		assertEquals("New Left", new Integer(3), prevVal);
		iterTesterFull.next();
		nextVal = iterTesterFull.next();
		assertEquals("New Right", new Integer(1), nextVal);

		try {
			iterTester.remove();
			iterTesterFull.add(new Integer(1));
			iterTesterFull.remove();
			fail("Should have generated an exception");
		} catch (IllegalStateException e) {

		}

		iterTesterFull.next();
		iterTesterFull.remove();
		nextVal = iterTesterFull.previous();
		assertEquals("New Right", new Integer(1), nextVal);
	}

	/** Test set */
	@Test
	public void iterTestSet() {
		iterTesterFull.next(); // set backwards
		iterTesterFull.set(new Integer(10));
		Integer nextVal = iterTesterFull.previous();
		assertEquals("New Element Forward", new Integer(10), nextVal);

		iterTesterFull.previous(); // set forwards
		iterTesterFull.set(new Integer(16));
		Integer prevVal = iterTesterFull.next();
		assertEquals("New Element Backwards", new Integer(16), prevVal);

		try {
			iterTesterFull.add(new Integer(1));
			iterTesterFull.set(new Integer(2));
			iterTesterFull.set(null);

			fail("Should have generated an exception");
		} catch (IllegalStateException e) {

		} catch (NullPointerException ex) {

		}
	}
	
	

}
