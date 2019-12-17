//package algs.exercise.c1.s5;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyi {
	
	public static int count(int N) {
		int counter = 0;
		
		QuickUnion u = new QuickUnion(N);
		
		while(u.count() > 1) {
			counter++;
			int m = StdRandom.uniform(N);
			int n = StdRandom.uniform(N);
			if(u.isConnected(m, n)) continue;
			u.union(m, n);
		}
		
		return counter;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdOut.println("The number of connections generated is " + count(Integer.parseInt(args[0]))+". ");
	}

}
