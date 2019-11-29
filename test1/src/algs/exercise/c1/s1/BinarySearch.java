package algs.exercise.c1.s1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	private static int dep=0;

	public static int rank(int key,int[] a)
	{
		return rank(key,a,0,a.length-1);
	}
	public static int rank(int key,int []a,int lo,int hi)
	{
		for(int i=0;i<dep;i++)
			StdOut.print(" ");
		StdOut.println(lo+" "+hi);
		dep++;
		if(lo>hi) return -1;
		//int mid = lo+(hi-lo)/2;
		int mid = (lo+hi)/2;
		if(key<a[mid]) return rank(key,a,lo,mid-1);
		else if(key>a[mid]) return rank(key,a,mid+1,hi);
		else return mid;
	}//http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=150
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = In.readInts("whitelist.txt");
		Arrays.sort(a);
		char c = StdIn.readChar();
		
		while(!StdIn.isEmpty())
		{
			int key = StdIn.readInt();
			int m=rank(key,a);
			if(c=='+')
			{
				if(m==-1)
					StdOut.print(key+" ");
			}
			else
			{
				if(m!=-1)
					StdOut.print(a[m]+" ");
			}
			dep =0;
		}
	}

}
