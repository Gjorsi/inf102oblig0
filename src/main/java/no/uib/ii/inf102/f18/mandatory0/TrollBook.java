package no.uib.ii.inf102.f18.mandatory0;

public class TrollBook {

	public static void main(String[] args) {
		
		SortableLinkedList<map<Integer, String>> list = new SortableLinkedList<>();
		
		Kattio io = new Kattio(System.in, System.out);
		
		int n = io.getInt();
		int v;
		String s;
		
		for (int i=0; i<n ; i++) {
			s = io.getWord();
			v = io.getInt();
			list.add(new map<Integer, String>(v, s));
		}
		
		list.sort();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<n ; i++) {
			sb.append(list.remove(0).toString()).append(" ");
		}
		
		io.write(sb.toString());
		io.close();
	}
}

// a map object to insert into linkedlist data structure -> to be able to sort words based on their page number
class map<K extends Comparable<K>, V> implements Comparable<map<K, V>> {
	K key;
	V val;

	public map(K key, V val) {
		this.key = key;
		this.val = val;
	}
	
	public int compareTo(map<K, V> other) {
		return this.key.compareTo(other.key);
	}
	
	public String toString() {
		return this.val.toString();
	}	
}
