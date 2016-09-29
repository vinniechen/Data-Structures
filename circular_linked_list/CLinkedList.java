// *
// NAME: Vinnie Chen
// ID: A12148745
// LOGIN: cs12sau
// *

/**
 * Circular Doubly-Linked list that stores non-null objects of a type.
 * @version 1.0
 * @author Vinnie Chen
 * @since 4/11/16
 */

package hw2;

import java.util.*;

public class CLinkedList<E> extends AbstractList<E> {

	private int nelems;
	private Node head;
	private Node tail; // if needed
	private Node currNode;
	private static final int ZERO = 0;

	protected class Node {
		E data;
		Node next;
		Node prev;

		/** Constructor to create singleton Node */
		public Node(E element)
		{
			if (element == null) {
				throw new NullPointerException();
			}
			this.data = element;
			this.next = null;
			this.prev = null;
		}
		/**
		 * Constructor to create singleton link it between previous and next 
		 *   @param element Element to add, can be null
		 *   @param prevNode predecessor Node, can be null
		 *   @param nextNode successor Node, can be null 
		 */
		public Node(E element, Node prevNode, Node nextNode)
		{   
			this.data = element;
			this.next = nextNode; // sets doubly linked pointers
			this.prev = prevNode;
			this.next.setPrev(this);
			this.prev.setNext(this);
		}
		/** Remove this node from the list. Update previous and next nodes */
		public void remove()
		{	
			this.prev.setNext(this.getNext()); // points next and equal each other
			this.next.setPrev(this.getPrev());
		}
		/** Set the previous node in the list
		 *  @param p new previous node
		 */
		public void setPrev(Node p)
		{
			this.prev = p;
		}
		/** Set the next node in the list
		 * @param n new next node
		 */
		public void setNext(Node n)
		{
			this.next = n;
			//n.prev = this;
		}

		/** Set the element 
		 *  @param e new element 
		 */
		public void setElement(E e)
		{
			this.data = e;
		}

		/** Accessor to get the next Node in the list
		 *  @return next node
		 */
		public Node getNext()
		{
			return this.next; 
		}
		/** Accessor to get the prev Node in the list 
		 * @return previous node
		 */
		public Node getPrev()
		{
			return this.prev; 
		} 
		/** Accessor to get the Nodes Element 
		 * @return node's element 
		 */
		public E getElement()
		{
			return this.data; 
		} 
	}

	/** ListIterator implementation */ 
	protected class MyListIterator implements ListIterator<E> {

		private boolean forward;
		private boolean canRemove;
		private Node left,right; // Cursor sits between these two nodes
		private int idx = 0;     // Current position, what next() would return 

		public MyListIterator()
		{ 	
			forward = true;
			right = head; 
			left = tail;
			canRemove = false; 
		}

		/**
		 * Adds an element to the list between 'left' and 'right'.
		 * 
		 * @param: Element e: element to be added
		 * @throws: NullPointerException
		 */
		@Override
		public void add(E e) throws NullPointerException
		{
			if (e == null) {
				throw new NullPointerException();
			}

			if (nelems == 0) { // if list has no nodes
				Node node = new Node(e);
				node.setNext(node);
				node.setPrev(node);
				right = node;
				left = node;
				nelems++;
			}

			else { // if list has > 0 nodes
				if (idx == 0) { // if adding at index 0
					Node node = new Node(e, left, right);
					left = node;
					nelems++;
				}
				else { // regular case
					Node node = new Node(e,left, right);
					left = node;
					nelems++;
					idx = (idx + 1) % nelems;
				}	
			}
			canRemove = false;	
		}
		/**
		 * Checks if there is another element to be retrieved by calling 
		 * next().
		 * @return whether there is a next node
		 */
		@Override
		public boolean hasNext()
		{
			if (nelems == 0) { 
				return false;
			}
			return true; 
		}

		/**
		 * Checks if there is another element to be retrieved by calling 
		 * previous().
		 * @return whether there is a previous node
		 */
		@Override
		public boolean hasPrevious()
		{
			if (nelems == 0) { 
				return false;
			}
			return true; // 
		}

		/**
		 * Moves the iterator to next, returns element
		 * @return the element from the next node iterated over
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			if (!hasNext()) {  // has no elements
				throw new NoSuchElementException();
			}
			if (nelems != 1) { // maintains index @ 0 if nelems is 1
				idx = (idx + 1) % nelems;
			}

			forward = true;
			canRemove = true;
			E element = right.getElement();
			left = right;
			right = left.getNext();
			return element;  
		}
		/**
		 * Next index
		 * @return the next index
		 */
		@Override
		public int nextIndex()
		{
			return (idx+1) % nelems; 
		}
		/**
		 * Iterates to the previous, returns element
		 * @return the element from the previous node iterated over
		 */
		@Override
		public E previous() throws NoSuchElementException
		{
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (nelems != 1) {
				idx = --idx % nelems;
			}
			if (idx < 0) {
				idx = nelems-1;
			}
			forward = false;
			canRemove = true;
			E element = left.getElement();
			right = left;
			left = right.getPrev();
			return element; 
		}
		/**
		 * Previous index, loops around circle
		 * @return the previous index
		 */
		@Override
		public int previousIndex()
		{
			int returnIdx = (idx-1) % nelems;

			if (returnIdx < 0) { // if index becomes negative, loops
				returnIdx = nelems-1;
			}

			return returnIdx;  
		}
		/**
		 * Removes node next or previous depending on forward. Must
		 * be called after next or previous
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if (canRemove == false) { // checks if remove is legal
				throw new IllegalStateException();
			}
			if (!forward) { //false
				right.remove();
				right = left.getNext();
			}
			else if (forward) { // true
				left.remove();
				left = right.getPrev();
			}
			canRemove = false;
		}
		/**
		 * Replaces node next or previous depending on forward. Must
		 * be called after next or previous
		 * @param e element to be replaced in node
		 */
		@Override
		public void set(E e) 
				throws NullPointerException,IllegalStateException
		{
			if (canRemove == false) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			if (!forward) { // true
				right.setElement(e);
			}
			else if (forward) { // false
				left.setElement(e);
			}
		}

	}



	//  Implementation of the CLinkedList Class


	/** Only 0-argument constructor is defined */
	public CLinkedList()
	{
		this.nelems = ZERO;
		this.head = tail;
		this.tail = head;
		this.currNode = null;
	}

	/**
	 * Uses counter to determine size
	 * @return size of CLinkedList
	 */
	@Override
	public int size()
	{
		return nelems;  
	}

	/**
	 * Gets element at an index
	 * @param index selected to get
	 * @return element at the index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index >= this.size() || index < 0) { // checks if data is accessible
			throw new IndexOutOfBoundsException();
		}
		return getNth(index).getElement();  
	}

	@Override
	/** Add an element to the list 
	 * @param index  where in the list to add
	 * @param data data to add
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */ 
	public void add(int index, E data) 
			throws IndexOutOfBoundsException,NullPointerException
	{
		Node newNode = null;
		if (data == null) {
			throw new NullPointerException();
		}
		if (index > this.size() || index < 0) { // checks if data is accessible
			if (size() != 0 || index != 0) { 
				throw new IndexOutOfBoundsException();  
			}		  
		}

		newNode = new Node(data);
		if (index == 0) { 
			if (size() == 0) { // if trying to make first node 
				head = newNode;
				tail = newNode;
			}		 
			newNode.setNext(head);
			newNode.setPrev(tail);
			tail.setNext(newNode);
			head.setPrev(newNode);
			head = newNode;
			nelems++;
		}

		else if (index == (size())) { // if inserting before tail
			newNode = new Node(data, tail, head);
			tail = newNode;
			tail.prev.setNext(tail);
			head.setPrev(tail);
			nelems++;
		}
		else { // regular case
			newNode = new Node(data);
			newNode.setNext(getNth(index));
			newNode.setPrev(getNth(index-1));
			getNth(index-1).setNext(newNode); 
			getNth(index+1).setPrev(newNode); 
			nelems++;
		}		  

	}

	/** Set the element at an index in the list 
	 * @param index  where in the list to add
	 * @param data data to add
	 * @return element that was previously at this index.
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */ 
	public E set(int index, E data) 
			throws IndexOutOfBoundsException,NullPointerException
	{
		E oldData = null; // contains old data

		if (data == null) { // checks if data is null
			throw new NullPointerException();
		}
		if (index >= this.size() || index < 0) { // checks if data is accessible
			throw new IndexOutOfBoundsException();
		}

		oldData = getNth(index).getElement(); 
		getNth(index).setElement(data);


		return oldData; 
	}

	/** Remove the element at an index in the list 
	 * @param index  where in the list to add
	 * @return element the data found
	 * @throws IndexOutOfBoundsException
	 */ 
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index >= this.size() || index < 0) { // checks if data is accessible
			throw new IndexOutOfBoundsException();
		}

		E oldData = getNth(index).getElement();

		if (index == 0) { // if trying to remove head
			getNth(index).remove();
			this.head = getNth(1);
			tail.next = head;
			head.prev = tail;
			head.next = getNth(1);
			getNth(1).prev = head;
			nelems--;
			if (size() <= 1) {
				tail = head;
			}
		}
		else if (index == size()-1) { // removing tail
			getNth(index).remove();
			this.tail = getNth(size()-1);
			nelems--;

		}
		else { // regular case
			getNth(index).remove();
			nelems--;
		}	  
		return oldData; 
	}

	/** Clear the linked list */
	public void clear() 
	{
		nelems = 0;
		this.head = null;
		this.tail = this.head;
	}

	/** Determine if the list empty 
	 *  @return true if empty, false otherwise 
	 */
	public boolean isEmpty()
	{
		if (this.size() == 0 ) {
			return true;
		}
		return false;  
	}

	/** Reverse and concatenate a list 
	 * @param List to be concatenated 
	 */
	public void reverseAndConcat (CLinkedList <E> list )
			throws NullPointerException {
		if (this == null || list == null) {
			throw new NullPointerException();
		}
		list.clear();
		for (int i = 0; i < this.size(); i++) {
			list.add(i, this.get(i));
		}
		for (int j = this.size()-1; j >= 0; j--) {
			list.add(list.size(), this.get(j));
		}
	}

	/** 
	 * Uses two linked lists, two indices and swaps the nodes between the indices
	 * Throw IndexOutOfBoundsException if no data exist at given indices
	 * @param 2 lists, and 2 indexes that want to be switched between lists
	 */
	public void swapNodes(CLinkedList <E> list, int ind1, int ind2)
			throws IndexOutOfBoundsException,NullPointerException {
		if (list == null || this == null) {
			throw new NullPointerException();
		}
		if (ind1 >= size() || ind1 < 0 || ind2 >= size() || ind2 < 0 
				|| ind1 > ind2) {
			throw new IndexOutOfBoundsException();		  
			// ALSO TEST IF IND1 > IND2?????
		}

		ArrayList<E> array = new ArrayList<E>();
		int arrayIndex = 0;
		// if ind1 < ind2
		for (int i = ind1; i <= ind2; i++) {

			array.add(list.getNth(i).getElement());
			list.set(i, this.getNth(i).getElement());
			this.set(i, array.get(arrayIndex));
			arrayIndex++;
		}


		// 
	}

	public Iterator<E> QQQiterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> QQQlistIterator()
	{
		return new MyListIterator();
	}

	/** Returns node at the index 
	 *  @return node at the index
	 */
	private Node getNth(int index) 
	{ 
		currNode = this.head;
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		return currNode;  
	}

	public Iterator<E> iterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> listIterator()
	{
		return new MyListIterator();
	}

}

