package no.uib.ii.inf102.f18.mandatory0;

import java.util.Arrays;
import java.util.Random;

public class TestLinkedList {

	public static void main(String[] args) {
		
		int N = 10;
		
//		test1(N);
//		test2(N);
//		test3();
		test4(N);
	}
	
	private static void test4(int N) {
		SortableLinkedList<Integer> list = new SortableLinkedList<Integer>();
		
		list.add(4);
		
		list.remove(0);
		
		System.out.println("list size should be 0: " + list.size());
		
		list.add(6);
		list.add(3);
		
		for (int i=0; i<list.size() ; i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
		
		list.remove(1);
		System.out.println("should be 6: " + list.get(0));
		System.out.println("size: " + list.size());
		
		for (int i=0; i<N ; i++) {
			list.add(i);
		}
		
		System.out.println("get(0): " + list.get(0));
		System.out.println("get(1): " + list.get(1));
		
		for (int i=0; i<list.size() ; i++) {
			System.out.print(list.get(i));
		}
		
		System.out.println();
		
		list.sort();
		
		System.out.println(list.get(N));
		
		
	}
	
	private static void test3() {
		SortableLinkedList<String> list = new SortableLinkedList<String>();
		
		list.add("abcd");
		list.add("aab");
		list.add("aaaaaaaaaaaaaaaaa");
		list.add("quack");
		list.add("xwasjk");
		list.add("abcd");
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		list.sort();
		
		System.out.println();
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	/*
	 * test the correctness of sorting with random values
	 */
	private static void test2(int N) {
		Random r = new Random();
		
		SortableLinkedList<Integer> list = new SortableLinkedList<Integer>();
		
		for (int i=0; i<N; i++) {
			list.add(r.nextInt(N));
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		list.sort();
		
		System.out.println();
		for (Integer i: list) {
			System.out.print(i + " ");
		}
//		for (int i=0; i<list.size(); i++) {
//			System.out.print(list.get(i) + " ");
//		}
		
		
	}
	
	private static void test1(int N) {
		
		SortableLinkedList<Integer> list = new SortableLinkedList<Integer>();
		
		for (int i=0; i<N; i++) {
			list.add(i);
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		list.remove(3);
		
		System.out.println();
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		list.add(9, 3);
		
		System.out.println();
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		Integer[] arr = new Integer[list.size()];
		list.toArray(arr);
		
		System.out.println();
		System.out.println(Arrays.toString(arr));
		
		for (Integer i: list) {
			System.out.print(i + " ");
		}
		
		list.sort();
		
		System.out.println("last: " + list.get(9));
		
		System.out.println();
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}

}
