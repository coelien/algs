package algs.exercise.c1.s4;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class StaticSETofInts {
	
	private int[] a;
	public StaticSETofInts(int[]keys)
	{
		a = new int[keys.length];
		for(int i = 0;i<keys.length;i++)
			a[i] = keys[i];
		Arrays.sort(a);
	}
	
	public boolean contains(int key)
	{
		return rank(key)!=-1;
	}
	
	public int rank(int key)
	{
		int lo = 0;
		int hi = a.length - 1;
		
		while(lo<=hi)
		{
			int mid = lo + (hi-lo)/2;
			if(a[mid]>key) hi = mid - 1;
			else if(a[mid]<key) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	public int getLower(int key)
	{
		if(!contains(key)) return -1;
		int lo = 0;
		int hi = a.length - 1;
		
		while(lo<=hi)
		{
			int mid = lo + (hi-lo)/2;
			if(a[mid]>=key) hi = mid - 1;
			else lo = mid + 1;
			
		}
		return lo;
	}
	
	public int getUpper(int key)
	{
		if(!contains(key)) return -1;
		int lo = 0;
		int hi = a.length - 1;
		
		while(lo<=hi)
		{
			int mid = lo + (hi-lo)/2;
			if(a[mid]<=key) lo = mid + 1;
			else hi = mid - 1;
		}
		return hi;
	}
	
	public int howMany(int key)
	{
		return getUpper(key)-getLower(key)+1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int a[] = {0,0,1,0,0,1,1};
		//StdOut.println("low index : "+set.getLower(0));
		//StdOut.println("low index : "+set.getLower(0)+" up index: "+set.getUpper(0)+" num: "+set.howMany(0));
		//StdOut.println("low index : "+set.getLower(1)+" up index: "+set.getUpper(1)+" num: "+set.howMany(1));
		
		//find the common in the Array a,b
		int[] a = {0,2,2,4,6,6,8};
		//int[] a = new int[10];
		//for(int i=0;i<10;i++)
		//	a[i] = i;
		int[] b= new int[20];
		for(int i=0;i<20;i+=2)
			b[i] = i;
		StaticSETofInts set =new StaticSETofInts(b);
		int pre = 0;
		for(int i=0;i<a.length;i++)
		{
			if(set.rank(a[i])!=-1)
				if(a[i]!=pre||i==0)
					StdOut.print(a[i]+" ");
			pre=a[i];
		}

	}

}
