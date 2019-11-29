package algs.exercise.c1.s4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

//use the axis system to show the performance of ThreeSum
public class DoubleTest {
	
	private static double xRange;
	private static double yRange;
	private static double xInterval;
	private static double yInterval;
	public static  double timeTrial(int N)
	{
		int Max = 1000000;
		int [] a = new int [N];
		for(int i = 0;i<N;i++)
			a[i] = StdRandom.uniform(-Max, Max);
		//for(int i = 0;i<N;i++)
			//a[i] = i;
		Stopwatch watch = new Stopwatch();
		int cnt = ThreeSum.count(a);
		return watch.elapsedTime();
	}
	
	public static void drawF(int xHigh, int yHigh, int xinv, int yinv)
	{
		//set x or y scale to appropriate range
		xRange = xHigh;
		yRange = yHigh;
		xInterval = xinv;
		yInterval = yinv;
		StdDraw.setXscale(-xRange/8,xRange);
		StdDraw.setYscale(-yRange/8,yRange);
		double preX = 0.0;
		double preY = 0.0;
		
		//draw x y axis
		StdDraw.setPenRadius(0.02);
		StdDraw.line(0,0,0,yRange);
		StdDraw.line(0,0,xRange,0);
		
		//set and draw x y interval
		for(int i=0;i<yRange/yInterval+1;i++)
		{
			//StdDraw.setPenRadius(0.02);
			StdDraw.line(-xRange/20,i*yInterval,0,i*yInterval);
			Double b = i*yInterval;
			StdDraw.textRight(-xRange/17, i*yInterval,b.toString());
		}
		
		for(int i=0;i<xRange/xInterval+1;i++)
		{
			//StdDraw.setPenRadius(0.02);
			StdDraw.line(i*xInterval,0,i*xInterval,-yRange/20);
		}
		
		for(int n = 25000;n<=xRange;n*=2)
		{
			double X=n;
			double Y=timeTrial(n);
			StdDraw.setPenRadius(0.03);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(preX, preY, X, Y);
			StdDraw.point(X, Y);
			StdOut.println(X+" "+Y);
			preX = X;
			preY = Y;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int n = StdIn.readInt();
		drawF(4200,27,1000,3);
	}

}
