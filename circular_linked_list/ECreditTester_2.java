package hw2;

import static org.junit.Assert.*;


import org.junit.*;

public class ECreditTester_2 {

	private CLinkedList<Integer> list1;
	private CLinkedList<Integer> list2;
	private CLinkedList<Integer> list3;
	
	/** Setting Up Lists */
	@Before
	public void setUp() {
		list1 = new CLinkedList<Integer>();
		list2 = new CLinkedList<Integer>();
		// 1=2=3=4=5
		list1.add(0, new Integer(1));
		list1.add(1, new Integer(2));
		list1.add(2, new Integer(3));
		list1.add(3, new Integer(4));
		list1.add(4, new Integer(5));
		//6=7=8=9=10=11=12
		list2.add(0, new Integer(6));
		list2.add(1, new Integer(7));
		list2.add(2, new Integer(8));
		list2.add(3, new Integer(9));
		list2.add(4, new Integer(10));
		list2.add(5, new Integer(11));
		list2.add(6, new Integer(12));
		
	}
	/** Test swapNodes() */
	@Test
	public void testSwapNodes() {
		list1.swapNodes(list2, 2, 4); // Swap nodes between 2-4
		assertEquals("Index 2", new Integer(8), list1.get(2));
		assertEquals("Index 3", new Integer(9), list1.get(3));
		assertEquals("Index 4", new Integer(10), list1.get(4));
		assertEquals("Index 2", new Integer(3), list2.get(2));
		assertEquals("Index 3", new Integer(4), list2.get(3));
		assertEquals("Index 4", new Integer(5), list2.get(4));
		
		// Swap nodes of the same index
		list1.swapNodes(list2, 1, 1);
		assertEquals("Index 1", new Integer(7), list1.get(1));
		assertEquals("Index 1", new Integer(2), list2.get(1));
		
		try {
			list1.swapNodes(list2, 4, 2);
			list1.swapNodes(list2, 10, 4);
			list1.swapNodes(list2, 4, 10);
			list1.swapNodes(list2, -1, 4);
			list1.swapNodes(list2, 2, -3);
			list1.swapNodes(list3, 2, 0);
			list3.swapNodes(list3, 2, 0);
			
			fail("Should have thrown an exception");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException ex) {
			
		}
		
	}
	

}
