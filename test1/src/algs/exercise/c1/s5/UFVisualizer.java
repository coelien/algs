//package algs.exercise.c1.s5;

import java.awt.Font;

import edu.princeton.cs.algs4.In;
//I use the api which is provided by https://algs4.cs.princeton.edu/code/javadoc/
import edu.princeton.cs.algs4.StdDraw;

public class UFVisualizer {
	private int[] array;
	private int size;
	private int[] count;
	public int sum = 0;// this is to store the total number of array accesses
	//add two constants to store the bolder information
    private static int MAX_ACCESSES = 0;
	//this could be changed, but in order to maintain consistency
    //I won't change the data source at present
	private static int MAX_CONNECTIONS = 900;
	public int time = 0;
	public UFVisualizer(int n) {
		array = new int[n];
		for(int i =0;i < n;i++) {
			array[i] = i;
		}
		size = n;
		count = new int[n];
		for(int i =0;i < n;i++) {
			count[i] = 1;
		}
		//initialize the MAX_ACCESSES by using size n
        MAX_ACCESSES =(int) 2.5*n + 6;
		//initialize the y-axis and x-axis
		StdDraw.setXscale(-MAX_CONNECTIONS/30, MAX_CONNECTIONS*1.05);
		StdDraw.setYscale(-MAX_ACCESSES/30, MAX_ACCESSES);
		StdDraw.line(-MAX_CONNECTIONS/30, 0, MAX_CONNECTIONS*1.05, 0);
		StdDraw.line(0, -MAX_ACCESSES/30, 0, MAX_ACCESSES);
        //adding information to the picture
		StdDraw.text(-MAX_CONNECTIONS/60,MAX_ACCESSES/2,"array accesses", 90);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,-MAX_ACCESSES/60,"number of connections",0);
		StdDraw.text(MAX_CONNECTIONS*1.05/2,MAX_ACCESSES/60*59,"Amortized Cost for Quick Find",0);
		StdDraw.text(-MAX_CONNECTIONS/60,-MAX_ACCESSES/60,"0",0);
		
	}
	
	public void addEdge(int m, int n) {
		int number = 0;//this is to store the number of array accesses per addEdge operation
		int id_m = find(m);
		int id_n = find(n);
		if(id_m != id_n) {
            //always change the id entries of the smaller component to the identifier of the larger component
			if((count[id_m]) > (count[id_n])) {
				number = 8;
				for(int i = 0;i < size;i++) {
					if(array[i] == id_n) {
						array[i] = id_m;
						count[id_m]++;
						count[id_n] = 0;
						number += 3;
					}
					number++;
				}
			}
			else {
				number = 8;
				for(int i = 0;i < size;i++) {
					if(array[i] == id_m) {
						array[i] = id_n;
						count[id_n]++;
						count[id_m] = 0;
						number += 3;
					}
					number++;
				}
			}
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("number of array accesses: " + number);
			System.out.println();
			*/
			sum+=number;
			
		}
		else {
			/*
			System.out.print("the array content is: ");
			for(int i = 0;i < size;i++) {
				System.out.print(array[i]+" ");
			}
			System.out.print("number of array accesses: " + 2);
			System.out.println();
			*/
			sum+=2;
		}
		
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
	public int find(int m) {
		if(m > size-1) throw new IllegalArgumentException("Out of range");
		return array[m];
	}
	
	public boolean isConnected(int m, int n) {
		time++;//the client uses this method for every connection
		if(n > size-1 || m > size-1) throw new IllegalArgumentException("Out of range");
		sum+=2;
		if(array[n]==array[m])//draw only when the client find the two sites are connected 
			draw(time,2);
		return array[n]==array[m];
	}
	
	public int count() {
		int num = 0;
		for(int i=0; i< size;i++) {
			if(count[i] != 0) {
				num++;
			}
		}
		return num;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		UFVisualizer u = new UFVisualizer(in.readInt());
		while(!in.isEmpty()) {
			int m = in.readInt();
			int n = in.readInt();
			if(u.isConnected(m, n)) continue;
			u.addEdge(m, n);
		}
		System.out.println("times: "+u.time+" the number of components is "+ u.count()+" the number of total array accesses is "+u.sum);
	}
}
