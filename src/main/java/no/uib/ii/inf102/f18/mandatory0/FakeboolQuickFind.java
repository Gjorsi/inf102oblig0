package no.uib.ii.inf102.f18.mandatory0;

public class FakeboolQuickFind {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt();
        int m = io.getInt();
        
        int[] id = new int[n];
        
        //fill array, nodes point to themselves
        for (int i=0; i<n; i++) {
            id[i] = i;
        }
        
        for (int i=0; i<m; i++) {
        	union(id, io.getInt(), io.getInt());
        }
        
        for (int i=0; i<n; i++) {
            io.print(id[i]);
            io.print(' ');
        }
        
        io.close();
    }
    
    public static void union(int[] id, int p, int q) {
        //get the oldest account associated with p or q, and set that as id
        int x = Math.min(id[p], id[q]);
        
        int y = Math.max(id[p], id[q]);
        
        for (int i=0; i<id.length; i++) {
            if (id[i] == y)
                id[i] = x;
        }
    }
}