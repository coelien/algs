//package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class DepthWeightedQuickUnionVisualizer {
	private int[] array;
	private int size;
	private int count;
	private int[] depth;
	public int sum = 0;
	public DepthWeightedQuickUnionVisualizer(int n) {
		array = new int [n];
		depth = new int [n];
		for(int i =0;i < n;i++) {
			array[i] = i;
		}
		size = n;
		count = size;
	}
	
	public void union(int m, int n) {
		int root_m = find(m);
		int root_n = find(n);
		//numbers of array accesses of find() method
		int number = findAcessess(m) + findAcessess(n);
		
		if (root_m == root_n) {
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("the array accesses are: " + number);
			System.out.println();
			*/
			sum += number;
			return;
		}
		
		if(depth[root_m] > depth[root_n]) {
			array[root_n] = root_m;
			count--;
		}	
		else if(depth[root_m] < depth[root_n]) {
			array[root_m] = root_n;
			count--;
		}
		else {
			array[root_n] = root_m;
			count--;
			//depth[root_n] = -1;//set root_n as none exist
			depth[root_m] ++;
		}
		
		number++;
		/*
		System.out.print("the array content is: ");
		for(int i = 0;i < size;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.print("the array accesses are: " + number);
		System.out.println();
		*/
		sum += number;
		
	}
	//helper functions to figure out the time of array accesses in find()
	private int findAcessess(int m) {
		int number = 0;
		if(m > size-1) throw new IllegalArgumentException("Out of range.");
		while(m != array[m]) {
			m = array[m];
			number+=2;
		}
		return ++number;
	}
    // helper functions to figure out the node's depth
	private int findDepth(int m) {
		int number = 0;
		if(m > size-1) throw new IllegalArgumentException("Out of range.");
		while(m != array[m]) {
			m = array[m];
			number++;
		}
		return number;
	}	
	
	public double getAvgDepth() {
		double total = 0;
		for(int i=0; i < size; i++) {
			total += findDepth(i);
		}
		return total/size;
	}
	public int find(int m) {
		if(m > size-1) throw new IllegalArgumentException("Out of range.");
		while(m != array[m]) {
			m = array[m];
		}
		return m;
	}
	
	public boolean isConnected(int m, int n) {
		return find(m) == find(n);
	}
	
	public int count() {
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i< 10;i++) {
			In in = new In(args[0]);
			DepthWeightedQuickUnionVisualizer q = new DepthWeightedQuickUnionVisualizer(in.readInt());
			while(!in.isEmpty()) {
				int m = in.readInt();
				int n = in.readInt();
				if(q.isConnected(m, n)) continue;
				q.union(m, n);
			}
		}
		double time = timer.elapsedTime();
		System.out.println("the average time of 10 trials is : " + time/10 + " seconds");
		//System.out.println("the number of components is "+ q.count()+" the number of total array accesses is "+q.sum + " the average nodes' depth is: "+ q.getAvgDepth()+" time: "+time+" seconds");
	}
}
