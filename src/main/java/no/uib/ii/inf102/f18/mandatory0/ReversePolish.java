package no.uib.ii.inf102.f18.mandatory0;
import java.io.IOException;

public class ReversePolish {

    public static void main(String[] args) throws IOException {
        new ReversePolish();
    }
    
    /*
     * Note that when giving input by console, this implementation needs EOF to return answer
     * 
     * This algorithm builds a binary tree with operators as standard nodes, and operands as leaf nodes.
     * The Node class also implements a simple stack structure by keeping track of previous node in stack. 
     * (the top of the stack is tracked by variable topOfStack in the while loop)
     */
    public ReversePolish () throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        Node topOfStack = null, n1, n2;
        String s;
        
        while (io.hasMoreTokens()) {
        	
            s = io.getWord();
            
            //input is a number, add it to the stack
            if (s.charAt(0) < '*' || s.charAt(0) > '/') { 
                topOfStack = new Node(topOfStack, s);
                
            //input is an operator, "pop" two values, create a new operator node and return it to the "stack"
            } else {
                n1 = topOfStack;
                n2 = topOfStack.getPrev();
                topOfStack = n2.getPrev();
                
                if (topOfStack == null) {
                	topOfStack = new Node(null, s, n2, n1);
                } else {
                	topOfStack = new Node(topOfStack, s, n2, n1);
                }      
            }
        }
        StringBuilder sb = new StringBuilder();
        topOfStack.nodeToString(sb);
        io.write(sb.toString());
        io.close();
    }
    
    class SimpleStack<E> {
    	private Element top;
    	public SimpleStack() {
    		
    	}
    	
    	class Element {
    		private Element prev;
    		public Element() {
    	}
    }
    } 
    
    class Node{
        String content;
        Node left;
        Node right;
        Node prevInStack;
        
        /*
         * create leaf node
         */
        public Node(Node prev, String s) {
            this.content = s;
            this.left = null;
            this.right = null;
            this.prevInStack = prev;
        }
        
        /*
         * create operator node
         */
        public Node(Node prev, String s, Node l, Node r) {
            this.content = s;
            this.left = l;
            this.right = r;
            this.prevInStack = prev;
        }
        
        public Node getPrev() {
        	return this.prevInStack;
        }
        
        /*
         * recursive method to create the desired output in the form of a StringBuilder.
         * This is achieved with a DFS-like approach, left-to-right.
         */
        public void nodeToString(StringBuilder sb) {
            if (left == null) {
            	// leaf node, just append the value
                sb.append(content);
            } else {
                sb.append("(");
                left.nodeToString(sb);
                sb.append(content);
                right.nodeToString(sb);
                sb.append(")");
            }
        }
    }
}
