//package algs.exercise.c3.s3;

import edu.princeton.cs.algs4.Bag;
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
		private RectHV rec;
		public Node(Point2D point,Node left,Node right,int depth,int size,RectHV rec) {
			this.point = point;
			this.left = left;
			this.right = right;
			this.depth = depth;
			this.size = size;
			this.rec = rec;
		}
	}
	private Node root;
	private Bag<Point2D> bag;
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
		if(p == null) throw new IllegalArgumentException("p is null");
		if(!contains(p)) {
			RectHV rec = new RectHV(0, 0, 1, 1);
			root = insert(root,p,0,rec);
		}
	}
	private Node insert(Node h,Point2D p,int depth,RectHV rec) {
		depth++;
		//StdOut.println("p:"+p.toString()+" depth:"+depth);
		if(h == null)
			return new Node(p,null,null,depth,1,rec);
		//boolean flag = true;
		if(h.depth%2 == 1) {
			if(p.x()<h.point.x())
			{
				rec = new RectHV(rec.xmin(), rec.ymin(), h.point.x(), rec.ymax());	
				h.left = insert(h.left,p,depth,rec);
			//	flag = true;
			}
			else{
				rec = new RectHV(h.point.x(), rec.ymin(), rec.xmax(), rec.ymax());
				h.right = insert(h.right,p,depth,rec);
			//	flag = true;
			}
		}
		else {
			if(p.y()<h.point.y()) {
				rec = new RectHV(rec.xmin(), rec.ymin(), rec.xmax(), h.point.y());
				h.left = insert(h.left,p,depth,rec);
			//	flag = true;
			}
			else {
				rec = new RectHV(rec.xmin(), h.point.y(), rec.xmax(), rec.ymax());
				h.right = insert(h.right,p,depth,rec);
			//	flag = true;
			}
		}
		//if(flag == true)
			h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	public boolean contains(Point2D p)         // does the set contain point p? 
	{
		if(p == null) throw new IllegalArgumentException("p is null");
		return contains(root,p);
	}
	private boolean contains(Node h, Point2D p)
	{
		if(h == null) return false;
		if(h.depth%2 == 1) {
			if(p.x() < h.point.x())
				return contains(h.left,p);
			else if(p.x() == h.point.x() && p.y()==h.point.y())
			{
				//h.size++;
				return true;
			}
			else
				return contains(h.right,p);
		}
		else {
			if(p.y() < h.point.y())
				return contains(h.left,p);
			else if(p.y() == h.point.y() && p.x() == h.point.x())
			{
				//h.size++;
				return true;
			}
			else 
				return contains(h.right,p);
		}
	}
	public void draw()                         // draw all points to standard draw
	{
		draw(root);
	}
	private void draw(Node h) {
		if(h == null) return;
		if(h.depth%2 == 1) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.02);
			h.point.draw();
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.line(h.point.x(), h.rec.ymin(), h.point.x(), h.rec.ymax());
			draw(h.left);
			draw(h.right);
		}
		else {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.02);
			h.point.draw();
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(h.rec.xmin(),h.point.y(), h.rec.xmax(), h.point.y());
			draw(h.left);
			draw(h.right);
		}
	}
	public Iterable<Point2D> range(RectHV rect)// all points that are inside the rectangle (or on the boundary)
	{
		// if the query rectangle does not intersect the rectangle corresponding to a node, 
		//there is no need to explore that node (or its subtrees). A subtree is searched 
		//only if it might contain a point contained in the query rectangle.
		if(rect == null) throw new IllegalArgumentException("rect is null!");
		bag = new Bag<Point2D> ();
		if(!isEmpty())
			range(root,rect);
		else return null;
		return bag;
	}
	
	private void range(Node h,RectHV rect){
		if(rect.contains(h.point))
			bag.add(h.point);
		if(h.depth%2 == 1) {
			if(h.left != null) {
				if(rect.intersects(h.left.rec))
					range(h.left,rect);
			}
			if(h.right != null) {
				if(rect.intersects(h.right.rec))
					range(h.right,rect);
			}
		}
		else if(h.depth%2 == 0) {
			if(h.left != null) {
				if(rect.intersects(h.left.rec))
					range(h.left,rect);
			}
			if(h.right != null) {
				if(rect.intersects(h.right.rec))
					range(h.right,rect);
			}
		}
	}
	
	public Point2D nearest(Point2D p)          // a nearest neighbor in the set to point p; null if the set is empty
	{
		//if the closest point discovered so far is closer than the distance between the query point 
		//and the rectangle corresponding to a node, there is no need to explore that node (or its subtrees).
		if(p == null) throw new IllegalArgumentException("point is null!");
		if(root == null) return null;
		return nearest(root,p,root.point);
	}
	
	private Point2D nearest(Node h, Point2D p,Point2D cp) {
		if(cp.distanceSquaredTo(p) > h.point.distanceSquaredTo(p))
			cp = h.point;
		double d = cp.distanceTo(p);
		if(h.depth%2 == 1) {
			if(p.x()<h.point.x()){
				if(h.left != null) {
					if(!(d < h.left.rec.distanceTo(p)))
						cp = nearest(h.left,p,cp);
			    }
				if(h.right != null) {
					if(!(d < h.right.rec.distanceTo(p)))
						cp = nearest(h.right,p,cp);
				}
			}
			else {
				if(h.right != null) {
					if(!(d < h.right.rec.distanceTo(p)))
						cp = nearest(h.right,p,cp);
				}
				if(h.left != null) {
					if(!(d < h.left.rec.distanceTo(p)))
						cp = nearest(h.left,p,cp);
			    }
			}
		}
		else if(h.depth%2 == 0) {
			if(p.y()<h.point.y()){
				if(h.left != null) {
					if(!(d < h.left.rec.distanceTo(p)))
						cp = nearest(h.left,p,cp);
			    }
				if(h.right != null) {
					if(!(d < h.right.rec.distanceTo(p)))
						cp = nearest(h.right,p,cp);
				}
			}
			else {
				if(h.right != null) {
					if(!(d < h.right.rec.distanceTo(p)))
						cp = nearest(h.right,p,cp);
				}
				if(h.left != null) {
					if(!(d < h.left.rec.distanceTo(p)))
						cp = nearest(h.left,p,cp);
			    }
			}
		}
		return cp;
	}

	public static void main(String[] args) {
	// unit testing of the methods (optional) 
		KdTree tr = new KdTree();
		tr.insert(new Point2D(0.7, 0.2));
		tr.insert(new Point2D(0.5, 0.4));
		tr.insert(new Point2D(0.7, 0.5));
		tr.insert(new Point2D(0.7, 0.5));
		
		StdOut.println("the size of tree: "+tr.size());
		RectHV rec = new RectHV(0.1, 0.1, 0.9, 0.8);
		for(Point2D i :tr.range(rec)) {
			StdOut.println("x: "+i.x()+" y: "+i.y());
		}
		Point2D np = tr.nearest(new Point2D(0.69, 0.69));
		StdOut.println("nearest point x: "+np.x()+" y: "+np.y());
		tr.draw();
	}	

}
