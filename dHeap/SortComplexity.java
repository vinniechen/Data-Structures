package hw6;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.*;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * Class to compare the runtime of the sort methods
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

public class SortComplexity {
	public static   int     comparisons;
	public  static  int     movements;
	public  static long    time;
	protected static   Boolean    sorted;
	protected static    int    count;
	public static int[] array;

	/**
	 * Main method. Records start and end time, averages over 1000
	 */
	public static void main(String[] args) {
		System.gc(); // garbage collector
		long totalTime = 0;
		int n = 1000; // Test for values 1000 to 10000, increment by 1000
		int runs = 1000;
		long timeStart = 0;
		long timeEnd = 0;
		array = new int[n];
		Random rand =  new Random();
		/* //Uncomment for random array 
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(1000);
		}
		 */

		// Use for almost sorted array
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		for (int i = 0; i < array.length/2; i+=43) {
			array[i] = array[array.length-i-3];
		}


		for (int i = 0; i < 10; i++) { // runs for numbers 1000-10000
			for (int j = 0; j < runs; j++) { // average for 1000 runs
				timeStart = System.nanoTime();
				// Replace with selectionsort(array) and bubblesort(array)
				heapSort(array, true); 
				timeEnd = (System.nanoTime());
				totalTime += timeEnd-timeStart;
			}
			System.out.println("Average Time "+n+": "+ (totalTime/((long)runs)));
			n+=1000;
		}

	}
	/*
	 * Sorts a list of elements, 
	 * @param list[] to be sorted
	 * @param whether sorting in max or min heap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void heapSort(int list[], boolean isMaxHeap) {

		dHeap heapSort = new dHeap(5, isMaxHeap);
		int length = list.length;
		for (int i = 0; i < length; i++) { // add from array to heap
			heapSort.add(new Integer(list[i]));
		}
		if (isMaxHeap) {
			for (int i = length-1; i >= 0; i--) { // remove to place in order
				list[i] = (int) heapSort.remove(); 
			}	
		}
		if (!isMaxHeap) {
			for (int i = 0; i < length; i++) { // place in order backwards
				list[i] = (int) heapSort.remove();
			}
		}
	}
	/** Selection sort */
	public static void selectionsort(int list[])
	{
		for (int j=list.length-1; j>0; j--)
		{
			int maxpos = 0;
			for (int k=1; k<=j; k++)
			{
				comparisons++;
				if (list[k]>list[maxpos])
				{
					maxpos = k;
				}
			}
			if (j != maxpos)    // Only move if we must
			{
				movements += 3;
				int temp = list[j];
				list[j] = list[maxpos];
				list[maxpos] = temp;
			}
		}
	}

	/** Bubble sort */
	public static void bubblesort(int list[])
	{
		int temp;
		do {
			movements++;
			temp = list[0];
			for (int j=1; j<list.length; j++)
			{
				comparisons++;
				if (list[j-1]>list[j])
				{
					movements += 3;
					temp = list[j];
					list[j] = list[j-1];
					list[j-1] = temp;
				}
			}
			comparisons++;
		} while (temp != list[0]);
	}

}