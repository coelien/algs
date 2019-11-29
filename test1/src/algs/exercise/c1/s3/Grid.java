package algs.exercise.c1.s3;

/* *****************************************************************************
 *  Name:         Ibrahim Albluwi    
 *  NetID:        isma
 *  Precept:      P99
 *
 *  Description:    A generic data type representing a 2D grid of elements.
 *
 *  Exercise Steps: (a)   Complete the GridIterator class and test your
 *                        code using the given driver program.
 *
 *                  (b)   Implement a ColMajorIterator Iterator and then 
 *                        add code to main to test this iterator.
 *
 *                  (c)   Convert class Grid to an Iterable. Convert the while 
 *                        loop in the driver program for printing in
 *                        row-major order to a for-each loop.
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;


// A generic 2D grid of elements
public class Grid<Item> implements Iterable<Item>{

    private int n;          // the grid is of size n-by-n
    private Item[][] grid;  // a 2D array for holding items of any type


    // Creates an n-by-n grid
    public Grid(int n) {
        this.n = n;
        grid = (Item[][]) new Object[n][n];
    }


    // Sets the item at (row, col) in the grid to the given item
    public void set(int row, int col, Item item) {
        if (row < 0 || col < 0 || row >= n || col >= n)
            throw new IllegalArgumentException();
        grid[row][col] = item;
    }


    // Returns the item at (row, col) in the grid
    public Item get(int row, int col) {
       if (row < 0 || col < 0 || row >= n || col >= n)
            throw new IllegalArgumentException();
        return grid[row][col];
    }

    // Returns an instance of GridIterator
    public Iterator<Item> iterator() {
        // ADD CODE HERE
    	return new GridIterator();
    }
    public Iterator<Item> getColMajorIterator() {
        // ADD CODE HERE
    	return new ColMajorIterator();
    }
    // Allows iterating over the grid in row-major order 
    private class GridIterator implements Iterator<Item> {
        // ADD CODE HERE
    	private int id;

        // creates a new iterator
        public GridIterator() {
            // ADD CODE HERE
        	id = 0;
        }

        // is there a next item in the iterator?
        public boolean hasNext() {
            // ADD CODE HERE
        	int row = id/n;
        	int col = id%n;
        	if(row==n||col==n) return false;
        	return get(row,col)!=null;
        }

        // this method is optional in the Iterator interface
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // returns the next item in the iterator (and advances the iterator)
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
        	int row = id/n;
        	int col = id%n;        
        	// ADD CODE HERE
        	id = id+1;
        	return get(row,col);
        }
    }
 // Allows iterating over the grid in row-major order 
    private class ColMajorIterator implements Iterator<Item> {
        // ADD CODE HERE
    	private int id;

        // creates a new iterator
        public ColMajorIterator() {
            // ADD CODE HERE
        	id = 0;	
        }

        // is there a next item in the iterator?
        public boolean hasNext() {
            // ADD CODE HERE
        	int row = id%n;
        	int col = id/n;
        	if(row==n||col==n) return false;
        	return get(row,col)!=null;
        }

        // this method is optional in the Iterator interface
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // returns the next item in the iterator (and advances the iterator)
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
        	int row = id%n;
        	int col = id/n;        
        	// ADD CODE HERE
        	id = id+1;
        	return get(row,col);
        }
    }


    public static void main(String[] args) {
        int RANGE = 1000;

        // Create a size-by-size grid of Integers
        int size = Integer.parseInt(args[0]);
        Grid<Integer> myGrid = new Grid<Integer>(size);


        // Fill the grid with random numbers
        StdOut.println("2D Grid: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int rand = StdRandom.uniform(RANGE);
                StdOut.print(rand + "\t");
                myGrid.set(i, j, rand);
            }
            StdOut.println();
        }

        /* Print the Grid contents in row-major order
        StdOut.print("In Row-Major: ");
        Iterator<Integer> iterator = myGrid.getGridIterator();
        while (iterator.hasNext()) {
            StdOut.print(" " + iterator.next());
        }
        StdOut.println();
        StdOut.print("In Col-Major: ");
        iterator = myGrid.getColMajorIterator();
        while (iterator.hasNext()) {
            StdOut.print(" " + iterator.next());
        }
        StdOut.println();
        */
        StdOut.print("In Row-Major: ");
        for(int i:myGrid)
        	 StdOut.print(" " + i);
        
        // Check if all elements in the grid are distinct.
        // This is going to work only if the Iterator objects
        // can iterate independently.
        boolean distinct = true;
        for (int i : myGrid) {
            int count = 0;
            for (int j : myGrid)
                if (i == j)
                    count++;
            if (count > 1) {
                distinct = false;
                break;
            }
        }

        if (!distinct)
            StdOut.print("Not ");
        StdOut.println("Distinct");


        // (B)  ADD CODE TO PRINT IN COLUMN-MAJOR ORDER
        //      USE A WHILE LOOP

    }
}


