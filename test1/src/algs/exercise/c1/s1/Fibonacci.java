package algs.exercise.c1.s1;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
	private static long[]f;
	public Fibonacci(int N)
	{
		f =new long[N];
	}
	public static long F(int N)
	{
		if(N==0) return f[0]=0;
		if(N==1) return f[1]=1;
		if(f[N]!=0) return f[N];
		else return f[N]=F(N-1)+F(N-2);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fibonacci fi =new Fibonacci(100);
		for(int N=0;N<100;N++)
			StdOut.println(N+" "+F(N));
		
	}

}
