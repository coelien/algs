package algs.exercise.c3.s3;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	private class Node{
		private Point2D point;
		private Node left,right;
		private int depth;
		private int size;
		public Node(Point2D point,Node left,Node right,int depth,int size) {
			this.point = point;
			this.left = left;
			this.right = right;
			this.depth = depth;
			this.size = size;
		}
	}
	private Node root;
	
	public KdTree()                          // construct an empty set of points 
	{
		
	}
	public boolean isEmpty()                   // is the set empty?
	{
		return root == null;
	}
	public int size()                          // number of points in the set
	{
		return size(root);
	}
	private int size(Node h) {
		if(h == null) return 0;
		return h.size;
	}
	public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
	{
		if(!contains(p))
			root = insert(root,p,0);
	}
	private Node insert(Node h,Point2D p,int depth) {
		depth++;
		if(h == null)
			return new Node(p,null,null,depth,1);
		if(h.depth%2 == 1) {
			if(p.x()<h.point.x())
				h.left = insert(h.left,p,depth);
			else
				h.right = insert(h.right,p,depth);
		}
		else {
			if(p.y()<h.point.y())
				h.left = insert(h.left,p,depth);
			else
				h.right = insert(h.right,p,depth);
		}
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	public boolean contains(Point2D p)         // does the set contain point p? 
	{
		return contains(root,p);
	}
	private boolean contains(Node h, Point2D p)
	{
		if(h == null) return false;
		if(h.depth%2 == 1) {
			if(p.x() < h.point.x())
				return contains(h.left,p);
			else if(p.x() > h.point.x())
				return contains(h.right,p);
			else
				return true;
		}
		else {
			if(p.y() < h.point.y())
				return contains(h.left,p);
			else if(p.y() > h.point.y())
				return contains(h.right,p);
			else 
				return true;
		}
	}
	public void draw()                         // draw all points to standard draw
	{
		RectHV rec = new RectHV(0, 0, 1, 1);
		draw(root,rec);
	}
	private void draw(Node h,RectHV rec) {
		if(h == null) return;
		if(h.depth%2 == 1) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.02);
			h.point.draw();
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			RectHV backup = rec;
			StdDraw.line(h.point.x(), rec.ymin(), h.point.x(), rec.ymax());
			rec = new RectHV(rec.xmin(), rec.ymin(), h.point.x(), rec.ymax());
			draw(h.left,rec);
			rec = new RectHV(h.point.x(), backup.ymin(),backup.xmax(), backup.ymax());
			backup = null;
			draw(h.right,rec);
		}
		else {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.02);
			h.point.draw();
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BLUE);
			RectHV backup = rec;
			StdDraw.line(rec.xmin(),h.point.y(), rec.xmax(), h.point.y());
			rec = new RectHV(rec.xmin(), rec.ymin(), rec.xmax(), h.point.y());
			draw(h.left,rec);
			rec = new RectHV(backup.xmin(), h.point.y(),backup.xmax(), backup.ymax());
			backup = null;
			draw(h.right,rec);
		}
	}
	//public Iterable<Point2D> range(RectHV rect)// all points that are inside the rectangle (or on the boundary) 
	//public Point2D nearest(Point2D p)          // a nearest neighbor in the set to point p; null if the set is empty 

	public static void main(String[] args) {
	// unit testing of the methods (optional) 
		KdTree tr = new KdTree();
		tr.insert(new Point2D(0.7, 0.2));
		tr.insert(new Point2D(0.5, 0.4));
		tr.insert(new Point2D(0.2, 0.3));
		tr.insert(new Point2D(0.4, 0.7));
		tr.insert(new Point2D(0.9, 0.6));
		StdOut.println("the size of tree: "+tr.size());
		tr.draw();
	}	

}
