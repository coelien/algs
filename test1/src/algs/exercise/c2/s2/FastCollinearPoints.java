//	package algs.exercise.c2.s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private LineSegment[] segs;
	private int num;
	
	
	private class PointPair{
		private Point pFront;
		private Point pEnd;
		public PointPair(Point p1,Point p2) {
			pFront = p1;
			pEnd = p2;
		}
		public Point getFront() {
			return pFront;
		}
		public Point getBack() {
			return pEnd;
		}
		public boolean equals(PointPair p) {
			if(pFront.compareTo(p.pFront)==0&&pEnd.compareTo(p.pEnd)==0)
				return true;
			return false;
		}	
	}
	//clear the duplicate couples
	private void dedup(Stack<PointPair> stack) {
	    Bag<PointPair> bag= new Bag<PointPair>();
		for(PointPair i:stack)
		{
			PointPair p = stack.pop();
			assert i.equals(p);
			boolean flag = false;
			for(PointPair j:stack)
			{
				if(i.equals(j))
				{
					flag = true;
					break;
				}
			}
			if(!flag)
			{
				bag.add(i);
			}
		}
		segs = new LineSegment[bag.size()];
		for(PointPair i:bag)
		{
			segs[num++] = new LineSegment(i.getFront(),i.getBack());
		}
	}
	
	public FastCollinearPoints(Point[] points) {
		//push all found segments in it
		Stack<PointPair>stack = new Stack<PointPair> ();
		//corner cases
		if(points == null)
			throw new IllegalArgumentException();
		for(int i=0;i<points.length;i++)
			if(points[i]==null)
				throw new IllegalArgumentException();
		for(int i=0;i<points.length-1;i++)
			for(int j=i+1;j<points.length;j++)
				if(points[i].compareTo(points[j])==0)
					throw new IllegalArgumentException();
		
		//segs = new LineSegment[points.length*points.length];
		
		//in order to not not influence the original data
		//create point[] initpoints;
		Point[] initpoints = new Point[points.length];
		for(int i=0;i<points.length;i++)
			initpoints[i] = points[i];
		
		int min=0,max=0;
		for(int i=0;i<points.length;i++)
		{
			Arrays.sort(initpoints,points[i].slopeOrder());
			int count = 0;
			for(int j=1;j<points.length-2;j++)
			{
				count = 0;
				min=0;
				max=0;
				while(initpoints[0].slopeTo(initpoints[j+1])==initpoints[0].slopeTo(initpoints[j]))
				{
					count++;
					j++;
					if(initpoints[min].compareTo(initpoints[j-1])>0) {
						min = j-1;
					}
					if(initpoints[max].compareTo(initpoints[j-1])<0){
						max = j-1;
					}
					if(j==initpoints.length-1) break;
				}
				if(count>=2)
				{
					if(initpoints[min].compareTo(initpoints[j])>0) {
						min = j;
					}
					if(initpoints[max].compareTo(initpoints[j])<0){
						max = j;
					}
					
					PointPair pop = new PointPair(initpoints[min],initpoints[max]);
					stack.push(pop);
					
				}
			}
		}
		dedup(stack);
	}
	
	public int numberOfSegments() {
		return num;
	}
	
	public LineSegment[] segments() {
		return segs;
	}
	
	public static void main(String[] args) {

		    // read the n points from a file
		    In in = new In(args[0]);
		    int n = in.readInt();
		    Point[] points = new Point[n];
		    for (int i = 0; i < n; i++) {
		        int x = in.readInt();
		        int y = in.readInt();
		        points[i] = new Point(x, y);
		    }

		    // draw the points
		    StdDraw.enableDoubleBuffering();
		    StdDraw.setXscale(0, 32768);
		    StdDraw.setYscale(0, 32768);
		    for (Point p : points) {
		        p.draw();
		    }
		    StdDraw.show();

		    // print and draw the line segments
		    FastCollinearPoints collinear = new FastCollinearPoints(points);
		    //if(collinear.segments()==null) StdOut.println("s");
		    //StdOut.println("dd");
		    for (LineSegment segment : collinear.segments()) {
		        StdOut.println(segment);
		        segment.draw();
		    }
		    StdDraw.show();
		}
}
