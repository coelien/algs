package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class DoublingTestComparison {

    public static void main(String[] args){

        int T = Integer.parseInt(args[0]);


        for (int i = 250; true; i+=i) {

            double qf=0,qu=0;
            int c_qf = 0;
            int c_qu = 0;
            for(int j = 0; j < T; j++){
                ErdosRenyi e = new ErdosRenyi(i);
                Stopwatch watch1 = new Stopwatch();
                c_qf+=e.count(i);
                qf += watch1.elapsedTime();
                Stopwatch watch2 = new Stopwatch();
                c_qu+=e.count_quick_union(i);
                qu += watch2.elapsedTime();
            }

            StdOut.printf("%8d %5.1f %5.1f\n", i, qf/qu,c_qf*1.0/c_qu);
        }
    }}
