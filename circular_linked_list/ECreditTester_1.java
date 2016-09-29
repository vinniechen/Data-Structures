package hw2;

import static org.junit.Assert.*;

import org.junit.*;

public class ECreditTester_1 {

	private CLinkedList<Integer> orig;
	private CLinkedList<Integer> concat;
	private CLinkedList<Integer> nullList;
	
	/** Setting Up Lists */
	@Before
	public void setUp() {
		orig = new CLinkedList<Integer>();
		concat = new CLinkedList<Integer>();
		// 0=1=2=3
		orig.add(0, new Integer(0));
		orig.add(1, new Integer(1));
		orig.add(2, new Integer(2));
		orig.add(3, new Integer(3));
	
		//6=7=8=9=10=11=12
		concat.add(0, new Integer(6));
		concat.add(1, new Integer(7));
		concat.add(2, new Integer(8));
		concat.add(3, new Integer(9));
		concat.add(4, new Integer(10));
		concat.add(5, new Integer(11));
		concat.add(6, new Integer(12));
		
	}
	/** Test reverseAndConcat() */
	@Test
	public void testReverseAndConcat() {
		orig.reverseAndConcat(concat); // concatenate and reverse 0=1=2=3
		assertEquals("Index 0", new Integer(0), concat.get(0));
		assertEquals("Index 1", new Integer(1), concat.get(1));
		assertEquals("Index 2", new Integer(2), concat.get(2));
		assertEquals("Index 3", new Integer(3), concat.get(3));
		assertEquals("Index 4", new Integer(3), concat.get(4));
		assertEquals("Index 5", new Integer(2), concat.get(5));
		assertEquals("Index 6", new Integer(1), concat.get(6));
		assertEquals("Index 7", new Integer(0), concat.get(7));
		//0=1=2=3=3=2=1=0
		try {
			nullList.reverseAndConcat(concat);
			orig.reverseAndConcat(nullList);
			
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			
		}
		
	}

}
