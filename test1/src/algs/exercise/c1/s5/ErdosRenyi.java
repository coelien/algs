package algs.exercise.c1.s5;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyi {

	private static Queue<RandomGrid.Connection> queue = new Queue<RandomGrid.Connection>();
	private static int N;
	public  ErdosRenyi(int N){
		this.N = N;
		UF u = new UF(N);

		while(u.count() > 1) {
			int m = StdRandom.uniform(N);
			int n = StdRandom.uniform(N);
			queue.enqueue(new RandomGrid.Connection(m,n));

			if(u.isConnected(m, n)) continue;
			u.union(m, n);
		}
	}
	
	public static int count(int N) {
		int counter = 0;
		
		UF u = new UF(N);
		Queue<RandomGrid.Connection> cu = new Queue<RandomGrid.Connection>();
		for (RandomGrid.Connection c : queue){
			cu.enqueue(c);
		}
		
		while(u.count() > 1) {
			counter++;
			int m,n;
			if(!cu.isEmpty()) {
				RandomGrid.Connection c = cu.dequeue();
				m = c.getP();
				n = c.getQ();
			}
			else{
				m = StdRandom.uniform(N);
				n = StdRandom.uniform(N);

			}
			if(u.isConnected(m, n)) continue;
			u.union(m, n);
		}
		
		return counter;
	}
	public static int count_quick_union(int N){
		int counter = 0;

		QuickUnion u = new QuickUnion(N);
		Queue<RandomGrid.Connection> cu = new Queue<RandomGrid.Connection>();
		for (RandomGrid.Connection c : queue){
			cu.enqueue(c);
		}
		while(u.count() > 1) {
			counter++;
			int m,n;
			if(!cu.isEmpty()) {
				RandomGrid.Connection c = cu.dequeue();
				m = c.getP();
				n = c.getQ();
			}
			else{
				m = StdRandom.uniform(N);
				n = StdRandom.uniform(N);
				StdOut.println("**");

			}

			if(u.isConnected(m, n)) continue;
			u.union(m, n);
		}

		return counter;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//StdOut.println("The number of connections generated is " + count(Integer.parseInt(args[0]))+". ");
	}

}
