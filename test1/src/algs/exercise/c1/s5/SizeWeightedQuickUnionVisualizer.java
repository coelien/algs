package algs.exercise.c1.s5;

import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;

public class SizeWeightedQuickUnionVisualizer {
	private int[] array;
	private int size;
	private int count;
	private int[] weight;
	public int sum = 0;
	private static double MAX_ACCESSES = 0;
	//this could be changed, but in order to maintain consistency
	//I won't change the data source at present
	private static double MAX_CONNECTIONS = 900;
	public int time = 0;
	
	public SizeWeightedQuickUnionVisualizer(int n) {
		array = new int [n];
		weight = new int [n];

		for(int i =0;i < n;i++) {
			array[i] = i;
		}
		for(int i =0;i < n;i++) {
			weight[i] = 1;
		}
		size = n;
		count = size;
		
		//initialize the MAX_ACCESSES by using size n
        MAX_ACCESSES =20;
		//initialize the y-axis and x-axis
		StdDraw.setXscale(-MAX_CONNECTIONS/30, MAX_CONNECTIONS*1.05);
		StdDraw.setYscale(-MAX_ACCESSES/30, MAX_ACCESSES);
		StdDraw.line(-MAX_CONNECTIONS/30, 0, MAX_CONNECTIONS*1.05, 0);
		StdDraw.line(0, -MAX_ACCESSES/30, 0, MAX_ACCESSES);
        //adding information to the picture
		StdDraw.text(-MAX_CONNECTIONS/60,MAX_ACCESSES/2,"array accesses", 90);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,-MAX_ACCESSES/60,"number of connections",0);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,MAX_ACCESSES*1.0/60*59,"Amortized Cost for Weighted Quick Union",0);
		StdDraw.text(-MAX_CONNECTIONS/60,-MAX_ACCESSES/60,"0",0);
	}
	
	public void union(int m, int n) {
		int root_m = find(m);
		int root_n = find(n);
		int number = findAcessess(m) + findAcessess(n);

		if (root_m == root_n) {
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("the array accesses are: " + number);
			System.out.println();
			*/
			sum += number;
			draw(time,number);
			return;
		}
		
		if(weight[root_n] > weight[root_m]) {
			array[root_m] = root_n;
			count--;
			//weight[root_m] = -1;//set root_m as none exist
			weight[root_n] += weight[root_m];
		}	
		else {
			array[root_n] = root_m;
			count--;
			//weight[root_n] = -1;//set root_n as none exist
			weight[root_m] += weight[root_n];
		}
		
		number++;
		/*
		System.out.print("the array content is: ");
		for(int i = 0;i < size;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.print("the array accesses are: " + number);
		System.out.println();
		*/
		
		sum += number;
		draw(time,number);
		
	}
	public void draw(int time, int number) {
        //draw a gray point which represent each connection
		 StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.point(time, number);
        // draw a red point which gives cumulative average
		 StdDraw.setPenRadius(0.0025);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(time, sum*1.0/time);
        //draw a horizontal line of the final average result
		if(time == 900) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(0, sum*1.0/time, MAX_CONNECTIONS, sum*1.0/time);
			Font font = new Font("Arial", Font.ITALIC, 10);
			StdDraw.setFont(font);
			Integer avg =(int)(sum*1.0/time);
			String text = avg.toString();
			StdDraw.text(-MAX_CONNECTIONS/60, sum*1.0/time, text, 90);
		}
		
	}
	//helper functions to figure out the time of array accesses in find()
		private int findAcessess(int m) {
			int number = 0;
			if(m > size-1) throw new IllegalArgumentException("Out of range.");
			while(m != array[m]) {
				m = array[m];
				number+=2;
			}
			return ++number;
		}	
	    // helper functions to figure out the node's depth
		private int findDepth(int m) {
			int number = 0;
			if(m > size-1) throw new IllegalArgumentException("Out of range.");
			while(m != array[m]) {
				m = array[m];
				number++;
			}
			return number;
		}	
		
	public double getAvgDepth() {
		double total = 0;
		for(int i=0; i < size; i++) {
			total += findDepth(i);
		}
		return total/size;
	}
	public int find(int m) {
		if(m > size-1) throw new IllegalArgumentException("Out of range.");
		while(m != array[m]) {
			m = array[m];
		}
		return m;
	}
	
	public boolean isConnected(int m, int n) {
		time++;//the client uses this method for every connection
		if(n > size-1 || m > size-1) throw new IllegalArgumentException("Out of range");
		int root_m = find(m);
		int root_n = find(n);
		//numbers of array accesses of find() method
		int number = findAcessess(m) + findAcessess(n);
		if(root_m == root_n){
			sum += number;
			draw(time,number);
		}
		return root_m == root_n;
	}
	
	public int count() {
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch timer = new Stopwatch();
		//for(int i = 0; i< 10;i++) {
			In in = new In(args[0]);
			SizeWeightedQuickUnionVisualizer q = new SizeWeightedQuickUnionVisualizer(in.readInt());
			while(!in.isEmpty()) {
				int m = in.readInt();
				int n = in.readInt();
				if(q.isConnected(m, n)) continue;
				q.union(m, n);
			}
		//}
		double time = timer.elapsedTime();
		//System.out.println("the average time of 10 trials is : " + time/10 + " seconds");
		//System.out.println("the number of components is "+ q.count()+" the number of total array accesses is "+q.sum + " the average nodes' depth is: "+ q.getAvgDepth()+" time: "+time+" seconds");
	}

}
