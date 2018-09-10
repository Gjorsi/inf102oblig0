package no.uib.ii.inf102.f18.mandatory0;

import java.util.Scanner;

public class TrollBook {

	public TrollBook() {
		
	}

	public static void main(String[] args) {
		
		SortableLinkedList<map<Integer, String>> list = new SortableLinkedList<>();
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int v;
		String s;
		
		for (int i=0; i<n ; i++) {
			s = sc.next();
			v = sc.nextInt();
			list.add(new map<Integer, String>(v, s));
		}
		
		list.sort();
		
		for (int i=0; i<n ; i++) {
			System.out.print(list.get(i).toString() + " ");
		}
		
		sc.close();
		
	}

}

class map<K extends Comparable<K>, V> implements Comparable<map<K, V>> {

	K key;
	V val;
	
	public map(K key, V val) {
		this.key = key;
		this.val = val;
	}
	
	@Override
	public int compareTo(map<K, V> other) {
		return this.key.compareTo(other.key);
	}
	
	public String toString() {
		return this.val.toString();
	}
	
}
