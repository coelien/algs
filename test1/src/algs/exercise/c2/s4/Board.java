//package algs.exercise.c2.s4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private int [][]tiles;
	private int n;
	private int zeroR = 0;
	private int zeroC = 0;
	// create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	n = tiles.length;
    	this.tiles = new int [n][n];
    	for(int i=0;i<n;i++)
    		for(int j=0;j<n;j++)
    		{
    			this.tiles[i][j] = tiles[i][j];
    			if(tiles[i][j] == 0)
    			{
    				zeroR = i;
    				zeroC = j;
    			}
    		}
    }
                                           
    // string representation of this board
    public String toString() {
    	String s = n + "\n";
    	//StdOut.println(n);
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0;j<n;j++)
    			s += (" "+tiles[i][j]);
        	s += "\n";
    		//StdOut.println();
    	}
    	return s;
    }

    // board dimension n
    public int dimension() {
    	return n;
    }

    // number of tiles out of place
    public int hamming() {
    	int count = 0;
    	for(int i=0;i<n;i++)
    		for(int j=0;j<n;j++)
    			if(tiles[i][j] != n * i + j + 1)
    			{
    				if(tiles[i][j] == 0)
    					continue;
    				count++;
    			}
    	return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int man = 0;
    	for(int i = 0;i < n;i++)
    		for(int j = 0;j < n;j++)
    		{
    			int p = (tiles[i][j] - 1) / n;
    			int q = (tiles[i][j] - 1) % n;
    			if(tiles[i][j] != n * i + j + 1)
    			{
    				if(tiles[i][j] == 0)
    					continue;
    				man += Math.abs(p-i) + Math.abs(q-j);
    			}
    		}
    	return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	if(hamming() == 0)
    		return true;
    	return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	if(y == this) return true;
    	if(y == null) return false;
    	if(y.getClass() != this.getClass()) return false;
    	Board that = (Board) y;
    	if(this.n != that.n) return false;
    	for(int i=0;i<n;i++)
    		for(int j=0;j<n;j++)
    			if(this.tiles[i][j] != that.tiles[i][j])
    				return false;
    	return true;
    }
    
    private int[][] exchange(int a, int b, int p, int q) {
    	int[][] m = new int[n][n];
    	for(int i=0;i<n;i++)
    		for(int j=0;j<n;j++)
    			m[i][j] = tiles[i][j];
    	int temp = m[a][b];
    	m[a][b] = m[p][q];
    	m[p][q] = temp;
    	return m;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
    	Stack<Board> bag =new Stack<Board>();
    	int i = zeroR;
    	int j = zeroC;
    	int [][] t ;
    			/*if(i == 0 || j == 0)
    			{
    				if(i == 0 && j == 0) {
    					t = exchange(i,j,i,j+1);
    					bag.push(new Board(t));
    					t = exchange(i,j,i+1,j);
    					bag.push(new Board(t));
    				}else if(i != 0) {
    					if(i == n-1)
    					{
    						t = exchange(i,j,i,j+1);
        					bag.push(new Board(t));
        					t = exchange(i,j,i-1,j);
        					bag.push(new Board(t));
    					}
    					else {
    						t = exchange(i,j,i,j+1);
    						bag.push(new Board(t));
    						t = exchange(i,j,i+1,j);
    						bag.push(new Board(t));
    						t = exchange(i,j,i-1,j);
    						bag.push(new Board(t));
    					}
    					
    				}else {
    					if(j == n-1)
    					{
    						t = exchange(i,j,i,j-1);
        					bag.push(new Board(t));
        					t = exchange(i,j,i+1,j);
        					bag.push(new Board(t));
    					}
    					else {
    						t = exchange(i,j,i,j+1);
        					bag.push(new Board(t));
        					t = exchange(i,j,i+1,j);
        					bag.push(new Board(t));
        					t = exchange(i,j,i,j-1);
        					bag.push(new Board(t));
    					}	
    				}	
    			}
    			else if(i == n-1 || j == n-1)
    			{
    				if(i == n-1 && j == n-1) {
    					t = exchange(i,j,i,j-1);
    					bag.push(new Board(t));
    					t = exchange(i,j,i-1,j);
    					bag.push(new Board(t));
    				}else if(i != n-1) {
    					t = exchange(i,j,i-1,j);
    					bag.push(new Board(t));
    					t = exchange(i,j,i+1,j);
    					bag.push(new Board(t));
    					t = exchange(i,j,i,j-1);
    					bag.push(new Board(t));
    				}else {
    					t = exchange(i,j,i,j+1);
    					bag.push(new Board(t));
    					t = exchange(i,j,i-1,j);
    					bag.push(new Board(t));
    					t = exchange(i,j,i,j-1);
    					bag.push(new Board(t));
    				}
    			}
    			else{
    				t = exchange(i,j,i,j+1);
					bag.push(new Board(t));
					t = exchange(i,j,i+1,j);
					bag.push(new Board(t));
					t = exchange(i,j,i,j-1);
					bag.push(new Board(t));
					t = exchange(i,j,i-1,j);
					bag.push(new Board(t));
    			}*/
    	if(i > 0)
    	{
    		t = exchange(i,j,i-1,j);
    		bag.push(new Board(t));
    	}
    	if(i < n-1)
    	{
    		t = exchange(i,j,i+1,j);
    		bag.push(new Board(t));
    	}
    	if(j > 0)
    	{
    		t = exchange(i,j,i,j-1);
    		bag.push(new Board(t));
    	}
    	if(j < n-1)
    	{
    		t = exchange(i,j,i,j+1);
    		bag.push(new Board(t));
    	}
		return bag;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	Board board; 
    	if(zeroC != 0 || zeroR != 0 )
    		{
    			if(zeroC != 1)
    				board = new Board(exchange(0,0,0,1));
    			else if(zeroR != 0)
    				board = new Board(exchange(0,0,0,1));
    			else {
    				board = new Board(exchange(0,0,1,0));
    			}
    		}
    	else {
    		board = new Board(exchange(0,1,1,0));
    	}
		//StdOut.println(board);
    	return board;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] a = {{8,1,3},{4,0,2},{7,6,5}};
		Board b = new Board(a);
		StdOut.println("hamming: "+b.hamming());
		StdOut.println("manhattan: "+b.manhattan());
		StdOut.print(b.toString());
	
		for(Board i :b.neighbors()) {
			StdOut.println(i);
		}
		Board c = b.twin();
		StdOut.println(c);
	}

}
