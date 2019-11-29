//package algs.exercise.c2.s4;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	// find a solution to the initial board (using the A* algorithm)
	private SearchNode currentNode;
	private SearchNode twinCurrentNode;
	
	
    public Solver(Board initial) {
    	if(initial == null) throw new IllegalArgumentException("Can't be null!");
    	
    	MinPQ<SearchNode> pq1 = new MinPQ<SearchNode>(new ComparatorPQ());
    	MinPQ<SearchNode> pq2 = new MinPQ<SearchNode>(new ComparatorPQ());
    	currentNode = new SearchNode(initial,null);
    	twinCurrentNode = new SearchNode(initial.twin(),null);
    	pq1.insert(currentNode); 	
    	pq2.insert(twinCurrentNode);
    	
    	while(!(currentNode.board.isGoal()||twinCurrentNode.board.isGoal()))
    	{
    		for(Board b :currentNode.board.neighbors())
    		{
    			if(b.equals(currentNode.preBoard))
    				continue;
    			pq1.insert(new SearchNode(b,currentNode));
    		}
    		/*StdOut.println("Step "+moves1);
    		for(SearchNode s: pq1)
    		{
    			StdOut.print("priority: "+(s.hamming+s.moves));
    			StdOut.println(" moves: "+s.moves);
    			StdOut.println(s.board);
    		}*/
    		currentNode = pq1.delMin();

    		for(Board b :twinCurrentNode.board.neighbors())
    		{
    			if(b.equals(twinCurrentNode.preBoard))
    				continue;
    			pq2.insert(new SearchNode(b,twinCurrentNode));
    		}
    		twinCurrentNode = pq2.delMin();
    	}
    	
    }
    private class ComparatorPQ implements Comparator<SearchNode>{

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			if((o1.priority) < (o2.priority)) return -1;
			if((o1.priority) > (o2.priority)) return 1;
			return 0;
		}
    	
    }
    private class SearchNode{
    	Board board;
    	int moves;
    	int priority;
    	Board preBoard;
    	SearchNode preNode;
    	
    	public SearchNode(Board board, SearchNode preSearchNode) {
    		this.board = board;
    		if(preSearchNode == null)
    			this.moves = 0;
    		else moves = preSearchNode.moves+1;
    		if(preSearchNode == null)
    			this.preBoard = null;
    		else preBoard = preSearchNode.board; 
    		this.preNode = preSearchNode;
    		this.priority = moves + board.manhattan();
    	}
    }
    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return currentNode.board.isGoal();
    }

    // min number of moves to solve initial board
    public int moves() {
    	if(!isSolvable()) throw new IllegalArgumentException("can't solve the problem!");
    	return currentNode.moves;
    }
    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
    	if(!isSolvable()) throw new IllegalArgumentException("can't solve the problem!");
    	Stack<Board> s =new Stack<Board>();
    	SearchNode current= currentNode;
    	while(current != null)
    	{
    		s.push(current.board);
    		current = current.preNode;
    	}
    	return s;   
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	 // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
	}

}
