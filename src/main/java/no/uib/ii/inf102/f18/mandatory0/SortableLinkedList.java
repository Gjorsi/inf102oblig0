package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

/**
 * Implementation of {@link ISortableList} for INF102.mandatory0 <br>
 * 
 * A singly-linked list. <br>
 * Access to index 0 and size-1 is fast, but other indexes will be accessed iteratively from 0.
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

        if (index == 0) return first.val;
        if (index == size-1) return last.val;

        return findNodeBefore(index).next.val;
    }

    public boolean isEmpty() {
        return (size==0);
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

        if (a.length < size) {
            throw new IndexOutOfBoundsException("Size of array is insufficient to insert elements of the linkedlist" + 
                    " \nLinkedlist size: " + size + 
                    " \nArray size: " + a.length);
        }

        node t = first;
        for (int i=0; i<a.length; i++) {
            if(i<size) {
                a[i] = t.val;
                t = t.next;
            } else {
                a[i] = null;
            }

        }
        return a;
    }


    /**
     * Returns the node linking to the given index. 
     * This is necessary to be able to insert / remove nodes at specific indexes.
     * 
     * @param index index of the node of which we want the previous node
     * @return node linking to the node at index
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
     * find the middle of given list starting with node t. <br>
     * Split into two lists, equally long +/- 1. <br>
     * Repeat for the two lists created by splitting (recursively). <br>
     * Merge on the way up the tree.
     * 
     * @param t        the leading node of the list to sort
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
     * two "iterators" go through the given list, one at 1-step speed, the other at 2-step speed. <br>
     * When the faster reaches end of list, the slower is at the mid-point
     * 
     * @param tSlow        the head of the given list, this parameter is used as the slow iterator
     * @return reference to the middle element of the list
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
     * 
     * @param left head of the left list
     * @param right head of the right list
     * @return head of the sorted list made up of the two inputs
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

        /**
         * Node in the linkedlist
         * 
         * @param value    The value contained in this position of the linkedlist
         * @param next    Reference to the next node, should be null if this node is end of linkedlist
         */
        public node(E value, node next) {
            val = value;
            this.next = next;
        }
    }
}
