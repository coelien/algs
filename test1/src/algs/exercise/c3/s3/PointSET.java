//package algs.exercise.c3.s3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	private SET<Point2D> set;
	public PointSET()                          // construct an empty set of points 
	{
		set = new SET<Point2D>();
	}
	public boolean isEmpty()                   // is the set empty?
	{
		return set.isEmpty();
	}
	public int size()                          // number of points in the set 
	{
		return set.size();
	}
	public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
	{
		if(p == null) throw new IllegalArgumentException("argument is empty");
		if(!contains(p))
			set.add(p);
	}
	public boolean contains(Point2D p)         // does the set contain point p? 
	{
		if(p == null) throw new IllegalArgumentException("argument is empty");
		return set.contains(p);
	}
	public void draw()                         // draw all points to standard draw 
	{
		for(Point2D i : set) {
			i.draw();
		}
	}
	public Iterable<Point2D> range(RectHV rect)// all points that are inside the rectangle (or on the boundary)
	{
		if(rect == null) throw new IllegalArgumentException("argument is empty");
		Bag<Point2D> bag = new Bag<Point2D>();
		for(Point2D i : set) {
			if(rect.contains(i))
				bag.add(i);
		}
		return bag;
	}
	public Point2D nearest(Point2D p)          // a nearest neighbor in the set to point p; null if the set is empty
	{
		if(p == null) throw new IllegalArgumentException("argument is empty");
		if (isEmpty())
			return null;
		double min = 2.0;
		Point2D np = null;
		for(Point2D i : set) {
			if(p.distanceTo(i)<min)
			{
				min=p.distanceTo(i);
				np = i;
			}
		}
		return np;
	}

	public static void main(String[] args) {
	// unit testing of the methods (optional) 
	}	
}