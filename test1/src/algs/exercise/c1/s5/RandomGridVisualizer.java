package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.StdDraw;

public class RandomGridVisualizer {

    public static void draw(int p, int q, int N) {
        //represent the site's coordinates
        double p_x = p % (N + 1) + 1;
        double q_x = q % (N + 1) + 1;
        double p_y = N - p / (N + 1) + 1;
        double q_y = N - q / (N + 1) + 1;
        //draw the line between two sites
        StdDraw.line(p_x,p_y,q_x,q_y);
    }


    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        SizeWeightedQuickUnion uf = new SizeWeightedQuickUnion((N+1)*(N+1));

        StdDraw.setScale(0,N+2);

        for(RandomGrid.Connection c : RandomGrid.generate(N)){
            int p = c.getP();
            int q = c.getQ();
            if(uf.isConnected(p,q)) continue;
            uf.union(p,q);
            draw(p,q,N);
            StdDraw.pause(1000);
        }

    }
}
