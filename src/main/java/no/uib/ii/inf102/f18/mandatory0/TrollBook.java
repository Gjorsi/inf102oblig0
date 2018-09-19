package no.uib.ii.inf102.f18.mandatory0;

/**
 * Create a sortable map of integer values pointing to words (string), using SortableLinkedList<E> and map<K,V>.
 * Sort the pages and print the text.
 * 
 * @author Carl August Gjørsvik
 */
public class TrollBook {
	
	public static void main(String[] args) {
		
		SortableLinkedList<map<Integer, String>> list = new SortableLinkedList<>();
		
		Kattio io = new Kattio(System.in, System.out);
		
		int nPages = io.getInt();
		int pageNr;
		String word;
		
		for (int i=0; i<nPages ; i++) {
			word = io.getWord();
			pageNr = io.getInt();
			list.add(new map<Integer, String>(pageNr, word));
		}
		
		list.sort();
		
		StringBuilder sb = new StringBuilder();
		
		for (map page: list) {
			sb.append(page.toString()).append(" ");
		}
		
		io.write(sb.toString());
		io.close();
	}
}

/**
 * A map object to insert into linkedlist data structure -> to be able to sort words based on their page number
 * @param <K>	The key to access V, must be comparable
 * @param <V>	The value belonging to a key
 */
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
