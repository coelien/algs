package algs.exercise.c3.s3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST <Key extends Comparable<Key>, Value>{
	
	private Node root;
	private int depth;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	public RedBlackBST(){
		root = null;
	}
	
	private class Node{
		Key key;
		Value value;
		Node left,right;
		double x,y;
		int N;
		boolean color;
		
		private Node(Key key, Value value, int N, boolean color) {
			this.key 		= key;
			this.value 		= value;
			this.N 			= N;
			this.color 		= color;
		}
	}
	
	private void setCoordinates() {
		int num = 0;
		int depth = 0;
		setCoordinates(root,num,depth);
	}
	
	private void setCoordinates(Node h,int num, int depth) {
		if(h == null) return;
		depth++;
		h.y = depth;					//from top to bottom(1 to tree depth)	:0~tree depth + 1
		h.x = size(h.left) + 1 + num;	//from 1   to treeNode number			:0~treeNode number + 1
		setCoordinates(h.left, num, depth);
		setCoordinates(h.right, num + 1 + size(h.left),depth);
	}
	private boolean isRed(Node h) {
		if(h == null) return false;
		else return h.color == RED;
	}
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node moveRedLeft(Node h) {
		flipColors(h);
		if(isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}
	
	private void flipColors(Node h) {
		if(h.left.color == RED) h.left.color = BLACK;
		else					h.left.color = RED;
		if(h.right.color == RED)h.right.color = BLACK;
		else					h.right.color = RED;
		if(h.color == RED)		h.color = BLACK;
		else					h.color = RED;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node h) {
		if(h == null)   return 0;
		else 			return h.N;
	}
	
	public Value get(Key k) {
		return get(root ,k);

	}
	
	private Value get(Node h, Key k) {
		if(h == null) return null;
		int cmp = k.compareTo(h.key);
		if(cmp < 0) 		return get(h.left,k);
		else if(cmp > 0) 	return get(h.right,k);
		else				return h.value;
	}
	
	public void put(Key key, Value val) {
		root = put(root,key,val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val) {
		if(h == null) return new Node(key,val,1,RED);
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0) 		h.left = put(h.left,key,val);
		else if(cmp > 0) 	h.right = put(h.right,key,val);
		else 				h.value = val;
		
		if(isRed(h.right) && !isRed(h.left)) 	h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) 	flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void deleteMin() {
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if(!isEmpty()) root.color = BLACK;
	}
	
	public Node deleteMin(Node h) {
		if(h.left == null) return null; //find and delete the min by return the null
		if(!isRed(h.left) && !isRed(h.left.left)) //find 2 node
			h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);
	}
	
	private Node balance(Node h) {
		if(isRed(h.right)) 						h = rotateLeft(h);//why need this?
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) 	flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	//public void deleteMax() {
		
	//}
	/*
	public void delete(Key key) {
		root = delete(root,key);
	}
	
	private Node delete(Node h, Key key) {
		
	}*/
	private void treeDepth() {
		depth = treeDepth(root);
	}
	
	private int treeDepth(Node h) {
		if(h == null) return 0;
		int a = treeDepth(h.left);
		int b = treeDepth(h.right);
		return ((a > b)? a : b) + 1;
	}
	
	public void draw() {
		setCoordinates();	//before draw set coordinate of each Node
		treeDepth();		//set the present tree depth 
		StdDraw.setXscale(0, size() + 1);
		StdDraw.setYscale(0, depth + 1);
		//StdOut.println("depth: "+depth+" root val: "+root.value);
		draw(root);
		//StdDraw.show();
	}
	
	private void draw(Node h) {
		if(h == null) return;
		//StdOut.println("x: "+h.x + " y: "+ (depth - h.y + 1)+" h.y: "+ h.y);
		double x0 = h.x;
		double y0 = depth- h.y + 1;
		double x1,y1;
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.020);
		StdDraw.point(x0, y0);
		StdDraw.text(x0+size()/18.0, y0, h.value.toString());
		StdDraw.text(x0-size()/25.0, y0, h.key.toString());
		StdDraw.setPenRadius(0.005);
		if(h.left != null) {
			x1 = h.left.x;
			y1 = depth - h.left.y + 1;
			if(isRed(h.left)) StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x0, y0, x1, y1);
		}
		else {
			StdDraw.setPenColor(StdDraw.BLACK);
			x1 = x0 - 0.5;
			y1 = depth - h.y + 1 - 0.5;
			StdDraw.line(x0, y0, x1, y1);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		if(h.right != null) {
			x1 = h.right.x;
			y1 = depth - h.right.y + 1;
			StdDraw.line(x0, y0, x1, y1);
		}
		else {
			x1 = x0 + 0.5;
			y1 = depth - h.y + 1 - 0.5;
			StdDraw.line(x0, y0, x1, y1);
		}
		draw(h.left);
		draw(h.right);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackBST<Integer, String> rb = new RedBlackBST<Integer, String>();
		rb.put(1, "Sam");
		rb.put(2, "Tom");
		rb.put(3, "Jerry");
		rb.put(4, "Linda");
		rb.put(5, "Jeff");
		rb.put(6, "Bob");
		rb.put(7, "Marry");
		rb.put(8, "Lily");
		rb.put(9, "Jacob");
		rb.put(10, "Rob");
		
		
		
		StdOut.println("The number 2 student is " + rb.get(2));
		StdOut.println("The number 7 student is " + rb.get(7));
		StdOut.println("The number 9 student is " + rb.get(9));
		StdOut.println("The number of students are " + rb.size());
		
		rb.deleteMin();
		rb.deleteMin();
		rb.deleteMin();
		rb.draw();
	}

}
