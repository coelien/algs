package algs.exercise.c1.s4;

public class ThreeSum {
	
	//handle the overflow of the plus of two big ints
	public static int addEx(int a, int b)
	{
		int r = a+b;
		if(((a^r)&(b^r))<0) throw new ArithmeticException("Integer Overflow");
		return r;
	}
	
	
	public static int count(int[] a) {
	        int n = a.length;
	        int count = 0;
	        for (int i = 0; i < n; i++) {
	            for (int j = i+1; j < n; j++) {
	                for (int k = j+1; k < n; k++) {
	                    if (addEx(addEx(a[i], a[j]), a[k]) == 0) {
	                        count++;
	                    }
	                }
	            }
	        }
	        return count;
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
