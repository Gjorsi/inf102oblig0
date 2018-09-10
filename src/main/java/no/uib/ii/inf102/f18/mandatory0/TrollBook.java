package no.uib.ii.inf102.f18.mandatory0;

public class TrollBook {

	public TrollBook() {
		
	}

	public static void main(String[] args) {
		
		SortableLinkedList<map<Integer, String>> list = new SortableLinkedList<>();
		
		list.add(new map<Integer, String>(3,"three"));
		list.add(new map<Integer, String>(2,"two"));
		list.add(new map<Integer, String>(1,"one"));
		
		list.sort();
		
		for (int i=0; i<3 ; i++) {
			System.out.println(list.get(i).toString());
		}
		
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
