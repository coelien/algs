package algs.exercise.c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int num;
	
	private class Node{
		Item item;
		Node before;
		Node next;
	}
	public Deque() {
		
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return num;
	}
	
	public void addFirst(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node oldnew = first;
		first = new Node();
	    first.item = item;
	    first.next = null;
	    first.before = null;
	    if(oldnew == null) 	last = first;
	    else {
	    	first.next = oldnew;
	    	oldnew.before = first;
	    }
	    num++;
	}
	
	public void addLast(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node oldlast = last;
		last = new Node();
	    last.item = item;
	    last.next = null;
	    last.before = null;
	    if(isEmpty()) 	first = last;
	    else{
	    	oldlast.next =last;
	    	last.before = oldlast;
	    }
	    num++;
	}
	
	public Item removeFirst() {
		if(isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		else {
			first.before = null;
		}
		num--;
		return item;
	}
	
	public Item removeLast() {
		if(isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		last = last.before;
		if(last==null) first = null;
		else {
			last.next = null;
		}
		num--;
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current;
		
		public ListIterator() {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> deq = new Deque<Integer>();
		deq.addFirst(5);
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.addLast(7);
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.addFirst(6);
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.addLast(9);
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		StdOut.println(deq.size()+" "+deq.isEmpty());
		deq.removeFirst();
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.removeLast();
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.removeFirst();
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
		deq.removeLast();
		for(int s :deq) StdOut.print(s+" ");
		StdOut.println();
	}

}
