//package algs.exercise.c2.s4;

import edu.princeton.cs.algs4.StdDraw;

//import edu.princeton.cs.algs4.StdDraw;

public class VisualAccumulator {
	private double total;
	private double N;
	private int count;
	public VisualAccumulator(int trials, double max) {
		StdDraw.setXscale(-trials/25,trials);
		StdDraw.setYscale(-max/25,max);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0, 0, trials, 0);
		StdDraw.line(0, 0, 0, max);
		StdDraw.setPenRadius(.005);
	}
	
	public void addDataValue(double val,double increment) {
		count++;
		N+=increment;
		total+=val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N,total/count);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
