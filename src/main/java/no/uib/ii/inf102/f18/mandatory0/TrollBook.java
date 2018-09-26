package no.uib.ii.inf102.f18.mandatory0;

/**
 * Create a sortable map of integer values pointing to words (string), using {@link SortableLinkedList} and {@link Pointer}.
 * Sort the pages and print the text.
 * 
 * @author Carl August Gjørsvik
 */
public class TrollBook {
    
    public static void main(String[] args) {
        
        SortableLinkedList<Pointer<Integer, String>> list = new SortableLinkedList<Pointer<Integer, String>>();
        
        Kattio io = new Kattio(System.in, System.out);
        
        int nPages = io.getInt();
        int pageNr;
        String word;
        
        for (int i=0; i<nPages ; i++) {
            word = io.getWord();
            pageNr = io.getInt();
            list.add(new Pointer<Integer, String>(pageNr, word));
        }
        
        list.sort();
        
        StringBuilder sb = new StringBuilder();
        
        for (Pointer page: list) {
            sb.append(page.toString()).append(" ");
        }
        
        io.write(sb.toString());
        io.close();
    }
}

/**
 * A pointer-object to insert into linkedlist data structure -> to be able to sort words based on their page number
 * @param <K>    The key to access V, must be comparable
 * @param <V>    The value belonging to a key
 */
class Pointer<K extends Comparable<K>, V> implements Comparable<Pointer<K, V>> {
    K key;
    V val;

    public Pointer(K key, V val) {
        this.key = key;
        this.val = val;
    }
    
    public int compareTo(Pointer<K, V> other) {
        return this.key.compareTo(other.key);
    }
    
    public String toString() {
        return this.val.toString();
    }    
}
