package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

/**
 * Implementation of ISortableList<E> for INF102.mandatory0
 * 
 * @author Carl August Gjørsvik
 *
 * @param <E>
 */

public class SortableLinkedList<E extends Comparable<E>> implements ISortableList<E> {

	private int size;
	private node first;
	private node last;

	public Iterator<E> iterator() {
		if (size < 1) return null;
		return new MyListIterator();
	}
	
	class MyListIterator implements Iterator<E>  {
		node current = first;
		
		public boolean hasNext() {
			return (current != null);
		}

		public E next() {
			node t = current;
			current = current.next;
			return t.val;
		}
	}

	public void add(E element) {
		if (element == null) return;

		if (size<1) {
			first = new node(element, null);
			last = first;
		} else {
			last.next = new node(element, null);
			last = last.next;
		}
		
		size++;
	}

	public void add(int index, E element) {
		if (element == null || index < 0 || index > size) return;
		
		node t;
		if (index == 0) {
			t = new node(element, first);
			first = t;
		} else if (index == size) {
			last.next = new node(element, null);
			last = last.next;
		} else {
			node r = findNodeBefore(index);
			t = new node(element, r.next);
			r.next = t;
		}
		size++;
	}

	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	public E get(int index) {
		if (index >= this.size || index < 0) return null;
		
		if (index == size-1) return last.val;
		if (index == 0) return first.val;
		
		return findNodeBefore(index).next.val;
	}

	public boolean isEmpty() {
		return (size<1);
	}

	public E remove(int index) {
		if (index >= size || index < 0) return null;
		
		node t;
		if (size == 1) {
			t = first;
			first = last = null;
		} else if (index == 0) {
			t = first;
			first = first.next;
		} else if (index == size-1) {
			t = last;
			last = findNodeBefore(index);
			last.next = null;
		} else {
			node r = findNodeBefore(index);
			t = r.next;
			r.next = t.next;
		}
		
		size--;
		return t.val;
		
	}

	public int size() {
		return size;
	}

	public E[] toArray(E[] a) {
		//nothing to put into array, or array is not big enough to hold the linkedlist
		if (size<1 || a.length<size) return a;
		
		node t = first;
		for (int i=0; i<size; i++) {
			a[i] = t.val;
			t = t.next;
		}
		return a;
	}
	
	
	/**
	 * Returns the node linking to the given index. 
	 * This is necessary to be able to insert / remove nodes at specific indexes.
	 * @param index
	 * @return t
	 */
	private node findNodeBefore(int index) {
		
		node t = first;
		for (int i=1; i<index; i++) {
			t = t.next;
		}
		
		return t;
	}
	
	public void sort() {		
		first = mergeSort(first);
		
		//last node might have been moved during sort, need to find it again
		last = findNodeBefore(size-1).next;
	}
	
	/**
	 * find the middle of given list starting with node t.
	 * Split into two lists, equally long +/- 1
	 * Repeat for the two lists created by splitting (recursively)
	 * Merge on the way up the tree
	 * @param t		the leading node of the list to sort
	 * @return {@link #merge(node, node)} 
	 	* the leading node of the list, now sorted
	 */
	private node mergeSort(node t) {
		if (t == null || t.next == null) return t;
		
		node mid = findMid(t);
		
		node afterMid = mid.next;
		
		// sever connection between left and right parts
		mid.next = null;
		
		node left = mergeSort(t);
		node right = mergeSort(afterMid);
		
		return merge(left, right);
	}
	
	/**
	 * two "iterators" go through the given list, one at 1-step speed, the other at 2-step speed.
	 * When the faster reaches end of list, the slower is at the mid-point
	 * @param tSlow		the head of the given list, this parameter is used as the slow iterator
	 * @return tSlow	this should at return be a reference to the middle element of the list
	 */
	private node findMid(node tSlow) {
		if (tSlow == null) return tSlow;
		node tFast = tSlow.next;
		
		while (tFast != null) {
			tFast = tFast.next;
			if (tFast != null) {
				tSlow = tSlow.next;
				tFast = tFast.next;
			}
		}
		return tSlow;
	}
	
	/**
	 * Merge two sorted lists such that the result is a sorted list
	 * @param left
	 * @param right
	 * @return head
	 */
	private node merge(node left, node right) {
		node curr;
		
		node head;
		
		if (left.val == null) return right;
		if (right.val == null) return left;
		
		//set first element of new linkedlist
		if (left.val.compareTo(right.val) <= 0) {
			curr = head = left;
			left = left.next;
		} else {
			curr = head = right;
			right = right.next;
		}
		
		// while both left and right have more elements, keep adding the lesser of those to the new list
		while (left != null && right !=  null) {
			if (left.val.compareTo(right.val) <= 0) {
				curr.next = left;
				curr = curr.next;
				left = left.next;
			} else {
				curr.next = right;
				curr = curr.next;
				right = right.next;
			}
		}
		
		// either left or right or both is empty, append the potentially remaining list to the new list
		if (left != null) {
			curr.next = left;
		}
		if (right != null) {
			curr.next = right;
		}
		
		return head;
	}
	
	class node {
		E val;
		node next;
		public node(E value, node next) {
			val = value;
			this.next = next;
		}
	}
}
