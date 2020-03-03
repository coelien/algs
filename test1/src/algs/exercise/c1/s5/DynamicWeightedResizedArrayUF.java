package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DynamicWeightedResizedArrayUF {

    private int[] array = new int[1];
    private int[] weight = new int[1];
    private int N;
    private int components;

    public DynamicWeightedResizedArrayUF() {}

    private void resize(int max){
        int[] temp_a = new int[max];
        int[] temp_b = new int[max];
        for (int i = 0; i < N; i++) {
            temp_a[i] = array[i];
            temp_b[i] = weight[i];
        }

        array = temp_a;
        weight = temp_b;
    }

    public void union(int m,int n){
        int root_m = find(m);
        int root_n = find(n);
        if (root_m == root_n) return;

        if(weight[root_m] > weight[root_n]) {
            array[root_n] = root_m;
            components--;
            //weight[root_n] = -1;//set root_n as none exist
            weight[root_m] += weight[root_n];
        }
        else {
            array[root_m] = root_n;
            components--;
            //weight[root_m] = -1;//set root_m as none exist
            weight[root_n] += weight[root_m];
        }
    }

    public int find(int m){
        if(m > N - 1) throw new IllegalArgumentException("Out of range.");
        while(m != array[m]) {
            m = array[m];
        }
        return m;
    }

    public int newSite(){
        if(array.length == N) resize(2*array.length);
        array[N] = N;
        weight[N] = 1;
        components++;

        return N++;
    }

    public boolean isConnected(int m, int n){
        return find(m) == find(n);
    }

    public int count(){
        return components;
    }

    public static void main(String [] args){
        In in = new In(args[0]);

        DynamicWeightedResizedArrayUF q = new DynamicWeightedResizedArrayUF();

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

