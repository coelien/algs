package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.StdDraw;

public class ErdosRenyiVisualizer {

    private static double MAX_X = 15000;
    private static double MAX_Y = 75000;

    public static void initAxis(){
        StdDraw.setXscale(-MAX_X/30,MAX_X);
        StdDraw.setYscale(-MAX_Y/30,MAX_Y);

        StdDraw.line(-MAX_X/30, 0,MAX_X,0);
        StdDraw.line(0,-MAX_Y/30,0, MAX_Y);

        StdDraw.text(MAX_X/2,-MAX_Y/60,"number of sites");
        StdDraw.text(-MAX_X/60,MAX_Y/2,"number of pairs generated to get one component",90);
    }

    private static double compute(int N){
        return Math.log(N)*0.5*N;
    }

    public static void draw(int N){
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(N,compute(N));
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N,ErdosRenyi.count(N));
    }

    public static void main(String[] args) {
        initAxis();
        int N = 0;
        for (int i = 0; i < 150; i++) {
            N += 100;
            draw(N);
        }
    }
}
