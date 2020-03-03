package algs.exercise.c1.s5;

import algs.exercise.c1.s3.RandomBag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomGrid {
    static class Connection{
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }

        public int getQ() {
            return q;
        }

        public int getP() {
            return p;
        }
    }

    public static Iterable<Connection> generate(int N) {
        int sites_num = (N + 1)*(N + 1);
        RandomBag<Connection> bag = new RandomBag<Connection>();

        for(int i = 0;i < sites_num;i++){
            int row_num = i / (N + 1);
            int col_num = i % (N + 1);

            if (row_num != N && col_num != N) {
               bag.add(createRandomOrientation(i,i+1));
               bag.add(createRandomOrientation(i,i+N+1));

            } else if (row_num == N && col_num == N) {
                //do nothing
            } else if (row_num == N) {
                bag.add(createRandomOrientation(i,i+1));
            } else {//col_num == N
                bag.add(createRandomOrientation(i,i+N+1));
            }
        }

        return bag;
    }

    private static Connection createRandomOrientation(int p, int q){
        double r = StdRandom.uniform();
        if(r > 0.5){
            int temp = p;
            p = q;
            q = temp;
        }
        return new Connection(p,q);
    }
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int count = 0;
        for(Connection c : generate(N)){
            count++;
            StdOut.println("Connection " + count + " : " + c.p +", "+c.q);
        }

    }
}
