package algs.exercise.c1.s3;
//package algs.exercise.c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> que = new RandomizedQueue<String>();
		while(!StdIn.isEmpty())
		{
			que.enqueue(StdIn.readString());
		}
		for(int i=0;i<k;i++) 
		{
			StdOut.println(que.dequeue());
		}
	}

}
