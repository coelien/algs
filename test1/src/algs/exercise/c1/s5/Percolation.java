package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
	private boolean pe[][];
	//private boolean isPercolate;
	private WeightedQuickUnionUF qf;
	private int n;
	private int sites;
	//creates n-by-n grid,with all sites initially blocked
	public Percolation(int n){
		if(n<=0) throw new IllegalArgumentException("n<=0");
		this.n = n;
		sites = 0;
	//	isPercolate = false;
		pe = new boolean[n][n];
		qf = new WeightedQuickUnionUF(n*n+2);
	//	for(int i=0;i<n;i++)
	//	{
	//		qf.union(0, i+1);
	//		qf.union(n*n-i, n*n+1);
	//	}
	}
	
	//open the blocked site
	public void open(int row,int col) {
		if (row <= 0 || row > n) throw new IllegalArgumentException("row out of bounds");
		if (col <= 0 || col > n) throw new IllegalArgumentException("col out of bounds");
		if(!isOpen(row,col))
		{
			pe[row-1][col-1] = true;
			sites++;
			if(col-1>=1)
				if(isOpen(row,col-1))
					qf.union(n*(row-1)+col,n*(row-1)+col-1);
			if(row+1<=n)
				if(isOpen(row+1,col))
					qf.union(n*(row-1)+col, n*row+col);
			if(col+1<=n)
				if(isOpen(row,col+1))
					qf.union(n*(row-1)+col, n*(row-1)+col+1);
			if(row-1>=1)
				if(isOpen(row-1,col))
					qf.union(n*(row-1)+col, n*(row-2)+col);
			
			if(row==1)
				qf.union(0, n*(row-1)+col);
			if(row==n) 
			{
				//if(qf.connected(0,  n*(row-1)+col))
				//	isPercolate = true;
				if(!percolates())
				 qf.union(n*n+1, n*(row-1)+col);
			}
			
			/*for(int i = 0;i<n;i++)
			{
				if(pe[n-1][i])
					if(qf.connected(0,  n*(n-1)+i+1))
						isPercolate = true;
			}*/
		}
			
	}
	
	public boolean isOpen(int row, int col) {
		if (row <= 0 || row > n) throw new IllegalArgumentException("row out of bounds");
		if (col <= 0 || col > n) throw new IllegalArgumentException("col out of bounds");
		return pe[row-1][col-1];
	}
	
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > n) throw new IllegalArgumentException("row out of bounds");
		if (col <= 0 || col > n) throw new IllegalArgumentException("col out of bounds");
		return qf.connected(0, n*(row-1)+col);
	}
	
	public int numberOfOpenSites() {
		return sites;
	}
	
	public boolean percolates() {
		//return isPercolate;
		return qf.connected(0, n*n+1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

