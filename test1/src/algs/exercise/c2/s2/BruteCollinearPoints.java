//package algs.exercise.c2.s2;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private LineSegment[] segs;
	private int num;
	private Point[] initpoints;
	public BruteCollinearPoints(Point[] points) {
		if(points == null)
			throw new IllegalArgumentException();
		for(int i=0;i<points.length;i++)
			if(points[i]==null)
				throw new IllegalArgumentException();
		for(int i=0;i<points.length-1;i++)
			for(int j=i+1;j<points.length;j++)
				if(points[i].compareTo(points[j])==0)
					throw new IllegalArgumentException();
		initpoints = new Point[points.length];
		for(int i=0;i<points.length;i++)
			initpoints[i] = points[i];
		segs = new LineSegment[initpoints.length];
		//if(points.length<4) return; 
		Arrays.sort(initpoints);
		for(int i=0;i<initpoints.length-3;i++)
			for(int j=i+1;j<initpoints.length-2;j++)
				for(int k=j+1;k<initpoints.length-1;k++)
					for(int l=k+1;l<initpoints.length;l++)
					{
						Comparator<Point> c = initpoints[i].slopeOrder();
						if(c.compare(initpoints[j], initpoints[k])==0&&c.compare(initpoints[j], initpoints[l])==0)
							segs[num++] = new LineSegment(initpoints[i],initpoints[l]);
					}
	}
	
	public int numberOfSegments() {
		return num;
	}
	
	public LineSegment[] segments() {
		LineSegment[] seg = new LineSegment[num];
		for(int i=0;i<num;i++)
			seg[i] =segs[i];
		return seg;
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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	    	StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
