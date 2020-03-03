package algs.exercise.c1.s5;//package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.In;

public class DepthWeightedQuickUnion {
	private int[] array;
	private int size;
	private int count;
	private int[] depth;
	public DepthWeightedQuickUnion(int n) {
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
		if (root_m == root_n) return;
		
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
		
		/*
		System.out.print("the array content is: ");
		for(int i = 0;i < size;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
		*/
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
		In in = new In(args[0]);
		DepthWeightedQuickUnion q = new DepthWeightedQuickUnion(in.readInt());
		while(!in.isEmpty()) {
			int m = in.readInt();
			int n = in.readInt();
			if(q.isConnected(m, n)) continue;
			q.union(m, n);
		}
		System.out.println("the number of components is "+ q.count());
		//System.out.println("the number of components is "+ u.count()+" the number of total array accesses is "+u.sum);
	}

}
