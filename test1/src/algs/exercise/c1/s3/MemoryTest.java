package algs.exercise.c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MemoryTest {
	private static Runtime rTime;
	private static long usedMemory() {  
		rTime= Runtime.getRuntime();
        return rTime.totalMemory() - rTime.freeMemory();  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int n  = StdIn.readInt();
		 long b = usedMemory();
         double [] a  = new double [n];
         long c= usedMemory();
         StdOut.print(c);
	}

}
