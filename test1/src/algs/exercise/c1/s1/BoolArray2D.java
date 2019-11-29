package algs.exercise.c1.s1;
import edu.princeton.cs.algs4.*;
public class BoolArray2D{
	private boolean[][] b;
	private int m;
	private int n;
	public BoolArray2D(String s)
	{
		In in= new In(s);
		this.m=in.readInt();
		this.n=in.readInt();
		b=new boolean [m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				b[i][j]=in.readBoolean();
	}
	public void toVision()
	{
		StdOut.print("       ");
		for(int j=0;j<n;j++)
		{
			StdOut.printf("%-7d",j+1);
		}
		StdOut.println();
		for(int i=0;i<m;i++)
		{
			StdOut.printf("%-7d",i+1);
			for(int j=0;j<n;j++)
			{
				if(b[i][j]==true)
					trueDraw();
				else
					falseDraw();
			}
			StdOut.println();
		}
	}
	public void transpose()
	{
		boolean [][]c = new boolean[n][m];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				c[i][j]=b[j][i];
		this.b=c;
	}
	private void trueDraw()
	{
		StdOut.printf("%-7s","*");
	}
	private void falseDraw()
	{
		StdOut.printf("%-7s"," ");
	}
	public static void main(String args[])
	{
		BoolArray2D b =new BoolArray2D("boolarray.txt");
		b.toVision();
		b.transpose();
		b.toVision();
	}
}