package algs.exercise.c1.s1;

import edu.princeton.cs.algs4.*;

public class MathCompute {
	public static int lg(int n)
	{
		int count =0;
		for(int i=1;i<=n;i*=2)
			count++;
		return count-1;
	}
	public static double ln_Factorial(int N)
	{
		if(N==0||N==1) return 0;
		else return Math.log(N)+ln_Factorial(N-1);
	}
	public static int [] histogram(int[] a,int M)
	{
		int [] b=new int[M];
		for(int i=0;i<a.length;i++)
		{
			b[a[i]]++;
		}
		return b;
	}
	public static int r_multiply(int a,int b)
	{
		if(b==0) return 0;
		if(b%2==0) return r_multiply(a+a,b/2);
		return r_multiply(a+a,b/2)+a;
	}
	public static int r_power(int a,int b)
	{
		if(b==0) return 1;
		if(b%2==0) return r_power(a*a,b/2);
		return r_power(a*a,b/2)*a;
	}
	private static int dep = 0; 
	public void init() {
		dep = 0;
	}
	public static int gcd(int a,int b)
	{
		for(int i=0;i<dep;i++)
			StdOut.print(" ");
		StdOut.println(a+" "+b);
		dep++;
	//	if(a<b) {
	//		int temp = a;
//			a =b;
	//		b=temp;
		//}
		if(b==0) return a;
		else 
			return gcd(b,a%b);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int n = StdIn.readInt();
		//StdOut.print(lg(n));
		
		/*int M = 10;
		int [] a = new int [StdIn.readInt()];
		for(int i=0;i<a.length;i++)
		{
			a[i]=StdIn.readInt();
		}
		int [] b = new int [M];
		b=histogram(a,M);
		for(int i:b) 
		{
			StdOut.print(i);
		}*/
		
		//StdOut.println(r_multiply(2,25));
		//StdOut.println(r_power(3,11));
		StdOut.println(gcd(24,16));
		StdOut.println(gcd(17,16));
		StdOut.print(gcd(1111111,1234567));
		StdOut.println(gcd(16,24));
		StdOut.println(gcd(24,24));
	}

}
