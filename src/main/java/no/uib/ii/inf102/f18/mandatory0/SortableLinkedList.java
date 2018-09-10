package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

public class SortableLinkedList<E extends Comparable<E>> implements ISortableList<E> {

	int size;
	node first;
	node last;
	
	public SortableLinkedList() {
		
	}

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
		if (size == 0) {
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
		
		if (index == 0) return first.val;
		
		return findNodeBefore(index).next.val;
	}

	public boolean isEmpty() {
		return (size<1);
	}

	public E remove(int index) {
		if (index >= size || index < 0) return null;
		
		node t;
		if (index == 0) {
			t = first;
			first = t.next;
			size--;
			return t.val;
		}
		
		t = findNodeBefore(index);
		node r = t.next;
		t.next = r.next;
		size--;
		return r.val;
		
	}

	public int size() {
		return size;
	}

	public void sort() {		
		first = mergeSort(first);
		// last node might have moved, need to reset reference to end of list
		node t = first;
		for (int i=0; i<size; i++) {
			if (t.next == null) last = t;
			else t = t.next;
		}
	}
	
	private node mergeSort(node t) {
		if (t == null || t.next == null) return t;
		
		/*
		 * find the middle of given list starting with node t.
		 * Split into two lists, equally long +/- 1
		 * Repeat for the two lists created by splitting (recursively)
		 * Merge on the way up the tree
		 */
		node mid = findMid(t);
		
		node afterMid = mid.next;
		
		mid.next = null;
		
		node left = mergeSort(t);
		node right = mergeSort(afterMid);
		
		return merge(left, right);
	}
	
	private node findMid(node tSlow) {
		if (tSlow == null) return tSlow;
		node tFast = tSlow.next;
		
		/*
		 * two "iterators" go through the given list, one at 1-step speed, the other at 2-step speed.
		 * When the faster reaches end of list, the slower is at the mid-point
		 */
		while (tFast != null) {
			tFast = tFast.next;
			if (tFast != null) {
				tSlow = tSlow.next;
				tFast = tFast.next;
			}
		}
		return tSlow;
	}
	
	private node merge(node left, node right) {
		node r;
		node f;
		
		if (left.val == null) return right;
		if (right.val == null) return left;
		
		//set first element of new linkedlist
		if (left.val.compareTo(right.val) <= 0) {
			r = f = left;
			left = left.next;
		} else {
			r = f = right;
			right = right.next;
		}
		
		// while both left and right have more elements, keep adding the lesser of those to the new list
		while (left != null && right !=  null) {
			if (left.val.compareTo(right.val) <= 0) {
				r.next = left;
				r = r.next;
				left = left.next;
			} else {
				r.next = right;
				r = r.next;
				right = right.next;
			}
		}
		
		// either left or right or both is empty, append the potentially remaining list to the new list
		if (left != null) {
			r.next = left;
		}
		if (right != null) {
			r.next = right;
		}
		
		// return head of new (sorted) list
		return f;
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
	
	private node findNodeBefore(int index) {
		node t = first;
		for (int i=1; i<index; i++) {
			t = t.next;
		}
		
		return t;
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