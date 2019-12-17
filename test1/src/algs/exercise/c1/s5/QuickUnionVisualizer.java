//package algs.exercise.c1.s5;

import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
//at present Visualizer is fake(implemented someday),actually the analysis on console
public class QuickUnionVisualizer {
	private int[] array;
	private int size;
	private int count;
	//total accesses of union() in client
	public int sum = 0;
	private static int MAX_ACCESSES = 0;
	//this could be changed, but in order to maintain consistency
	//I won't change the data source at present
	private static int MAX_CONNECTIONS = 900;
	public int time = 0;
	
	public QuickUnionVisualizer(int n) {
		array = new int [n];
		for(int i =0;i < n;i++) {
			array[i] = i;
		}
		size = n;
		count = size;
		
		//initialize the MAX_ACCESSES by using size n
        MAX_ACCESSES =150 ;
		//initialize the y-axis and x-axis
		StdDraw.setXscale(-MAX_CONNECTIONS/30, MAX_CONNECTIONS*1.05);
		StdDraw.setYscale(-MAX_ACCESSES/30, MAX_ACCESSES);
		StdDraw.line(-MAX_CONNECTIONS/30, 0, MAX_CONNECTIONS*1.05, 0);
		StdDraw.line(0, -MAX_ACCESSES/30, 0, MAX_ACCESSES);
        //adding information to the picture
		StdDraw.text(-MAX_CONNECTIONS/60,MAX_ACCESSES/2,"array accesses", 90);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,-MAX_ACCESSES/60,"number of connections",0);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,MAX_ACCESSES*1.0/60*59,"Amortized Cost for Quick Union",0);
		StdDraw.text(-MAX_CONNECTIONS/60,-MAX_ACCESSES/60,"0",0);
	}
	
	public void union(int m, int n) {
		int root_m = find(m);
		int root_n = find(n);
		//numbers of array accesses of find() method
		int number = findAcessess(m) + findAcessess(n);
		
		if (root_m == root_n) {
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("the number of array accesses is: " + number);
		    System.out.println();
			*/
			sum += number;
			draw(time,number);
			return;
		}
		
		//combining two trees needs one access
		number++;
		array[root_n] = root_m;
		count--;
		
		/*
		System.out.print("the array content is: ");
		for(int i = 0;i < size;i++) {
			System.out.print(array[i]+" ");
		}
		
		System.out.print("the number of array accesses is: " + number);	
		System.out.println();
		*/
		sum += number;
		draw(time,number);
	}
	// draw the amortized cost plot
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
		In in = new In(args[0]);
		QuickUnionVisualizer q = new QuickUnionVisualizer(in.readInt());
		while(!in.isEmpty()) {
			int m = in.readInt();
			int n = in.readInt();
			if(q.isConnected(m, n)) continue;
			q.union(m, n);
		}
		System.out.println("the number of components is: "+ q.count() + " the total array accesses are: " + q.sum);
	}

}
