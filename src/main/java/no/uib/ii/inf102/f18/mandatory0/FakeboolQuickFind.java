package no.uib.ii.inf102.f18.mandatory0;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;

public class FakeboolQuickFind { 
    
    int[] parent;
    
    public static void main(String[] args) throws IOException {
        new FakeboolQuickFind();
    }
    
    public FakeboolQuickFind() throws IOException {
        Kattio2 io = new Kattio2(System.in, System.out);
        
        int n1, n2;
            
        int n = io.getInt();
        int m = io.getInt();
        
        parent = new int[n];
        
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
            
        for (int i=0; i<m; i++) {
            n1 = io.getInt();
            n2 = io.getInt();
            
            merge(n1, n2);
        }
        
        for (int j=0; j<n; j++) {
            sb.append(find(j)).append(" ");
        }
        io.write(sb.toString() + "\n");
        
        io.close();
    }
    
    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    
    void merge(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) {
            parent[y] = x;
        } else if (x > y) {
            parent[x] = y;
        }
    }
}



class Kattio2 extends PrintWriter {
    public Kattio2(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio2(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}