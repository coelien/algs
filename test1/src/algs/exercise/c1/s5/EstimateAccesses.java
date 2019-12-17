package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.StdOut;

public class EstimateAccesses {
	public static double log2(double value) {
		return Math.log(value)/Math.log(2.0);
	}
	
	public double cal(int val) {
		return cal(val, 0);
	}
	
	private double cal(int val, int depth) {
		val = val / 2;
		if(val == 0) return 0;
		double sum = (log2(val) * 2 + 1) * 2 + 1;
		sum = sum * Math.pow(2, depth);
		depth++;
		sum += cal(val, depth);
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EstimateAccesses e = new EstimateAccesses();
		double simulation = e.cal(1000000) + 1000006 * ((2 * 2.08 + 1)*2);
		StdOut.println(simulation);
		StdOut.println(log2(Math.pow(10, 9)));
	}

}
