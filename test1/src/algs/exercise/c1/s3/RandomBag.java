package algs.exercise.c1.s3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item>{
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public boolean isEmpty() {
       return first == null;
    }

    public int size() {
        return N;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private Node current = first;

        public ListIterator() {

            Item[] array;
            array = (Item[]) new Object[N];

            for (int i = 0;i < N;i++){
                array[i] = next();
            }

            StdRandom.shuffle(array);

            current = first;
            for (int i = 0;i < N;i++){
                current.item = array[i];
                current = current.next;
            }
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {}

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
