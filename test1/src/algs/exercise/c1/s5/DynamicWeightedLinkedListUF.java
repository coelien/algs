package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.In;

public class DynamicWeightedLinkedListUF {

    private Node first;
    private int N;
    private int components;

    private class Node {

        int i;
        int size = 1;
        Node next;
        Node parent;

        public Node(int i) {
            this.i = i;
        }

    }

    public DynamicWeightedLinkedListUF() {}

    public void union(int m, int n){

        if(m > N - 1) throw new IllegalArgumentException(m+" has exceeded the limit. ");
        if(n > N - 1) throw new IllegalArgumentException(n+" has exceeded the limit. ");

        Node m_root = findNode(m);
        Node n_root = findNode(n);

        if(m_root.i == n_root.i) return;

        /*
        Node current_m = first;
        Node current_n = first;

        while(current_m != null){
            if(current_m.i == m_root) break;
            current_m = current_m.next;
        }

        while(current_n != null){
            if(current_n.i == n_root) break;
            current_n = current_n.next;
        }
         */

        if (m_root.size < n_root.size) {
            m_root.parent = n_root;
            n_root.size += m_root.size;
            components--;
        } else {
            n_root.parent = m_root;
            m_root.size += n_root.size;
            components--;
        }

    }

    private Node findNode(int m){
        //m can't exceed N - 1
        if(m > N - 1) throw new IllegalArgumentException(m+" has exceeded the limit. ");
        if(first == null) throw new NullPointerException("first is null");

        Node current = first;
        while (current != null){
            if(current.i == m) break;
            current = current.next;
        }

        if(current == null) throw new NullPointerException("current is null");

        while(current.parent != null){
            current = current.parent;
        }

        //System.out.println("found the root: "+current.i);

        return current;
    }

    public int find(int m) {
        //m can't exceed N - 1
        if(m > N - 1) throw new IllegalArgumentException(m+" has exceeded the limit. ");
        if(first == null) throw new NullPointerException("first is null");

        Node current = first;
        while (current != null){
            if(current.i == m) break;
            current = current.next;
        }

        if(current == null) throw new NullPointerException("current is null");

        while(current.parent != null){
            current = current.parent;
        }

        return current.i;
    }

    public int newSite(){
        Node oldFirst = first;
        first = new Node(N);
        first.next = oldFirst;

        components++;
        return N++;
    }

    public boolean isConnected(int m, int n) {

        if(m > N - 1) throw new IllegalArgumentException(m+" has exceeded the limit. ");
        if(n > N - 1) throw new IllegalArgumentException(n+" has exceeded the limit. ");

        return find(m) == find(n);
    }

    public int count(){
        return components;
    }

    public static void main(String [] args){
        In in = new In(args[0]);

        DynamicWeightedLinkedListUF q = new DynamicWeightedLinkedListUF();

        int sites = in.readInt();
        for(int i = 0; i < sites; i++){
            q.newSite();
        }

        System.out.println("begin processing connections");

        while(!in.isEmpty()) {
            int m = in.readInt();
            int n = in.readInt();
            if(q.isConnected(m, n)) continue;
            q.union(m, n);
        }
        System.out.println("the number of components is "+ q.count());
    }
}

