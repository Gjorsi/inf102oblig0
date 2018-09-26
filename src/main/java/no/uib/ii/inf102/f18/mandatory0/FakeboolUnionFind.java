package no.uib.ii.inf102.f18.mandatory0;

/**
 * @author Carl August Gjørsvik
 */
public class FakeboolUnionFind {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt();
        int m = io.getInt();
        final String DAEMON = "DAEMON";
        int[] id = new int[n];
        
        //fill array, nodes point to themselves
        for (int i=0; i<n; i++) {
            id[i] = i;
        }
        
        for (int i=0; i<m; i++) {
            if (io.getWord().equals(DAEMON)) {
                union(id, io.getInt(), io.getInt());
            } else {
                io.println(find(id, io.getInt()));
            }
        }
        io.close();
    }
    
    /**
     * Find the roots to unite, make the lesser (oldest) of those root of the combined set
     * @param id    array representing the parent-tree of accounts
     * @param p
     * @param q
     */
    public static void union(int[] id, int p, int q) {
        int x = find(id, p);
        int y = find(id, q);

        if (x < y) {
            id[y] = x;
        } else {
            id[x] = y;
        }
    }
    
    /**
     * Find the root of @param p by "climbing" the parent tree until id[p] refers to itself, 
     * including some path compression on the way
     * @param id
     * @param p
     * @return p    the root
     */
    public static int find(int[] id, int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
}