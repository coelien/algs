//package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.In;

public class UF {
	private int[] array;
	private int size;
	private int[] count;
	public int sum = 0;
	public UF(int n) {
		array = new int[n];
		for(int i =0;i < n;i++) {
			array[i] = i;
		}
		size = n;
		count = new int[n];
		for(int i =0;i < n;i++) {
			count[i] = 1;
		}
	}
	
	public void addEdge(int m, int n) {
		int number = 0;
		if(!isConnected(m, n)) {
			int cu,pre;
			if((cu = count[array[m]]) > (pre = count[array[n]])) {
				number = 6;
				for(int i = 0;i < size;i++) {
					if(array[i] == pre) {
						array[i] = cu;
						count[cu]++;
						count[pre] = 0;
						number += 3;
					}
					number++;
				}
			}
			else {
				number = 8;
				cu = array[n];
				pre = array[m];
				for(int i = 0;i < size;i++) {
					if(array[i] == pre) {
						array[i] = cu;
						count[cu]++;
						count[pre] = 0;
						number += 3;
					}
					number++;
				}
			}
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("number of array accesses: " + number);
			*/
			sum+=number;
			//System.out.println();
		}
		else {
			/*System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("number of array accesses: " + 2);
			*/
			sum+=2;
			//System.out.println();
		}
	}
	
	public int find(int m) {
		if(m > size-1) throw new IllegalArgumentException("Out of range");
		return array[m];
	}
	
	public boolean isConnected(int m, int n) {
		if(n > size-1 || m > size-1) throw new IllegalArgumentException("Out of range");
		return array[n]==array[m];
	}
	
	public int count() {
		int num = 0;
		for(int i=0; i< size;i++) {
			if(count[i] != 0) {
				num++;
			}
		}
		return num;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		UF u = new UF(in.readInt());
		while(!in.isEmpty()) {
			int m = in.readInt();
			int n = in.readInt();
			if(u.isConnected(m, n)) continue;
			u.addEdge(m, n);
		}
		System.out.println("the number of components is "+ u.count()+" the number of total array accesses is "+u.sum);
	}

}
