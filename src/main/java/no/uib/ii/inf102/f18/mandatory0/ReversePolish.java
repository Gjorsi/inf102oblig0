package no.uib.ii.inf102.f18.mandatory0;

/**
 * Note that when giving input by console, this implementation needs EOF to return answer. <br>
 * 
 * This algorithm builds a binary tree with operators as standard nodes, and operands as leaf nodes.
 * The Node class also implements a simple stack structure by keeping track of previous node in stack. 
 * (the top of the stack is tracked by variable topOfStack in the while loop)
 * 
 * @author Carl August Gjørsvik
 */
public class ReversePolish {

    public static void main(String[] args) {
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
                n2 = topOfStack.prevInStack;
                topOfStack = n2.prevInStack;
                
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
    
    static class Node{
        String content;
        Node left;
        Node right;
        Node prevInStack;

        /**
         * Create leaf node
         * 
         * @param prev Link to previous node in stack
         * @param s Content of this node. For a leaf node, it should be a number
         */
        public Node(Node prev, String s) {
            this.content = s;
            this.left = null;
            this.right = null;
            this.prevInStack = prev;
        }
        
        /**
         * Create operator node
         * 
         * @param prev Link to previous node in stack
         * @param s Content of this node. Should be one of the following: { +, -, *, /}
         * @param left The left child of this operator node
         * @param right The right child of this operator node
         */
        public Node(Node prev, String s, Node left, Node right) {
            this.content = s;
            this.left = left;
            this.right = right;
            this.prevInStack = prev;
        }
        
        /**
         * Recursive method to create the desired output in the form of a StringBuilder. <br>
         * This is achieved with a DFS-like approach, left-to-right.
         * 
         * @param sb The StringBuilder used to concatenate the values of the tree in the correct order
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
