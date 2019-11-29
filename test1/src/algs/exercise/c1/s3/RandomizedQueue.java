package algs.exercise.c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class RandomizedQueue<Item> implements Iterable<Item>{
	private Item[] a;
	private int N;
	private int in;
	//private int random;
	//private int out;
	public RandomizedQueue() {
		a =(Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize(int max) {
		//StdOut.print("resize!");
		Item[] temp =(Item[]) new Object[max];
		int i,j;
		for(i=0,j=0;j<N&&i<a.length;i++)
		{
			if(a[i]==null)
				continue;
			temp[j] = a[i];
			j++;
		}
		in = N-1;
		//out = 0;
		a = temp;
	}
	
	private int random() {
		/*int n;
		StdOut.println("*"+in+"-"+out+"*");
		if(in<=out)
			n = in+a.length-out;
		else
			n = in-out;
		
		StdOut.println("*"+in+"-"+out+"*");*/
		int id = StdRandom.uniform(a.length);
		while(a[id]==null) {
			id = StdRandom.uniform(a.length);
			//StdOut.println("*");
		}
		return id;
	}
	
	public void enqueue(Item item) {
		if(item == null) throw new IllegalArgumentException();
		if(N==a.length) resize(2*a.length);
		while(a[in]!=null) {
			in = (++in)%a.length;
			//StdOut.println("*");
		}
		a[in] = item;
		
		//StdOut.println("/*");
		//for(int i=0;i<a.length;i++)
		//	StdOut.print(a[i]+" ");
		//StdOut.println("*/"+a[in]);
		N++;
	}
	
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		int id =random();
		Item item = a[id];
		a[id] = null;
		/*if(id == out) {
			out = (++out)%a.length;
			while(a[out]==null)
				{
					out = (++out)%a.length;
					StdOut.print("*");
				}
		}*/
		N--;
		//in = id;
		if(N>0 && N==a.length/4) resize(a.length/2);
		return item;
	}
	
	public Item sample() {
		if(isEmpty()) throw new NoSuchElementException();
		int id = random();
		Item item = a[id];
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item>{
		
		private Item[] current;
		private int num = N;
		//private boolean[] flag;
		public RandomIterator(){
			current = (Item[])new Object[a.length];
			int i,j;
			for( i=0;i<a.length;i++)
			{
				current[i] = a[i];
			}
			//StdOut.println("/*");
			//for(i=0;i<a.length;i++)
			//	StdOut.print(a[i]+" ");
			//StdOut.println("*/");
			//StdOut.println("/*");
			//for(i=0;i<j;i++)
			//	StdOut.print(current[i]+" ");
			//StdOut.println("*/");
		}
		public boolean hasNext() {
			return num!=0;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		private int rand() {
			int id = StdRandom.uniform(a.length);
			while(current[id]==null) {
				id = StdRandom.uniform(a.length);
				//StdOut.print(id+" "+"_"+num+" ");
			}
		    return id;
		}
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			int id = rand();
			Item item = current[id];
			current[id] = null;
			num--;
			return item;
		}
	}
	/*public void show() {
		for(int i=0;i<a.length;i++)
				StdOut.print(a[i]+" ");
		StdOut.println();
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<Integer> deq = new RandomizedQueue<Integer>();
		/*while(!StdIn.isEmpty())
		{
			String item = StdIn.readString();
			if(!item.equals("-"))
				deq.enqueue(item);
			else if(!deq.isEmpty()) 
				{
				  StdOut.println(deq.dequeue()+" ");
				 // deq.show();
				}
		}
		StdOut.println("("+deq.size()+" left on queue)");
		*/
		int count=0;
		for(int n = 1025;n<2048000;n=(n-1)*2+1) {
			count++;
			Stopwatch watch = new Stopwatch();
			for(int i=0;i<n;i++)
				deq.enqueue(StdRandom.uniform(n));
			for(int i=0;i<n;i++)
			{
				deq.dequeue();
				deq.dequeue();
				deq.enqueue(StdRandom.uniform(n));
				deq.enqueue(StdRandom.uniform(n));
			}
			StdOut.println(count+": "+watch.elapsedTime()+" seconds("+n+")");
		}
	}

}
