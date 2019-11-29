package algs.exercise.c2.s1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//algs 2.2
public class Insertion {
	public static void sort(Comparable[] a) {
		for(int i=1;i<a.length;i++)
		{
			for(int j=i;j>0&&less(a[j],a[j-1]);j--)
					exch(a,j,j-1);
		}
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w)<0;
	}
	
	public static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static void show(Comparable[] a) {
		for(int i=0;i<a.length;i++)
		{
			StdOut.print(a[i]+" ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i],a[i-1]))
				return false;
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);*/
		int [][] s = new int[4][3];
		StdOut.println(s.length);
	}

}
