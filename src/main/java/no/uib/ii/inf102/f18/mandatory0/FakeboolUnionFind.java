package no.uib.ii.inf102.f18.mandatory0;

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
    
    public static void union(int[] id, int p, int q) {
        //find the roots to unite
        int x = find(id, p);
        int y = find(id, q);
        
        //make the oldest (lower value) root of the other
        if (x < y) {
            id[y] = x;
        } else {
            id[x] = y;
        }
    }
    
    public static int find(int[] id, int p) {
        //find root of set, with an implementation of path compression
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
}