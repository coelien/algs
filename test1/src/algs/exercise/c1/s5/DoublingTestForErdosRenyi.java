package algs.exercise.c1.s5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class DoublingTestForErdosRenyi {
    //count one trial time of ErdosRenyi
    private static double timecounter(int N){
        Stopwatch watch = new Stopwatch();
        int n = ErdosRenyi.count(N);
        return watch.elapsedTime();
    }
    //count one int number of connections generated of ErdosRenyi
    private static int intCounter(int N) {
        return ErdosRenyi.count(N);
    }

    //calculate avg time of T trials
    private static double avgTimeTrials(int T, int N) {
        double time_total = 0;
        for (int i = 0; i < T; i++) {
            double time = timecounter(N);
             time_total += time;
        }
        return  time_total/T;
    }

    //calculate avg int number of T trials
    private static int avgNumTrials(int T, int N) {
        int n_total = 0;
        for (int i = 0; i < T; i++) {
            double n = intCounter(N);
            n_total += n;
        }
        return n_total/T;
    }

    public static void main(String[] args){

        int T = Integer.parseInt(args[0]);

        double pre = avgTimeTrials(T,125);

        for (int i = 250; true; i+=i) {

            double cu = avgTimeTrials(T,i);
            int n = avgNumTrials(T,i);
            StdOut.printf("%8d %8d %5.1f\n", i, n, cu/pre);
            //update the pre
            pre = cu;
        }
    }
}
