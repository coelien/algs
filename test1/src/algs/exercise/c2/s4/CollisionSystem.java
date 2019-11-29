//package algs.exercise.c2.s4;

import java.awt.Color;
import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class CollisionSystem {
	private static final double HZ = 0.5;
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private int N; //number of particles
	private long totalCollisions = 0;
	VisualAccumulator visual;
	private final class Event implements Comparable<Event>
	{
		private final double time; // time of event
		private final Particle a, b; // particles involved in event
		private final int countA, countB; // collision counts for a and b
		
		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if(a == null)
				countA = -1;
			else countA = a.count();
			if(b == null)
				countB = -1;
			else countB = b.count();	
		}
		
		public int compareTo(Event that)
		{ 
			return Double.compare(this.time, that.time);
		}
		public boolean isValid()
		{
			if(a != null)
				if(a.count() != this.countA)
					return false;
			if(b != null)
				if(b.count() != this.countB)
					return false;
			return true;
		}
	}
	
	public CollisionSystem(Particle[] particles) {
		N = particles.length;
		this.particles= particles.clone();
		visual = new VisualAccumulator(10000, 1);
	}
	
	private void predict(Particle a,double limit)
	{
		if (a == null) return;
		double dt = 0.0;
		for (int i = 0; i < N; i++)
		{
			dt = a.timeToHit(particles[i]);
			if(t+dt <= limit)
				pq.insert(new Event(t + dt, a, particles[i]));
		}
		dt = a.timeToHitVerticalWall();
		if(t+dt <= limit)
			pq.insert(new Event(t + dt , a, null));
		dt = a.timeToHitHorizontalWall();
		if(t+dt <= limit)
			pq.insert(new Event(t + dt, null, a));
	}
	
	private void redraw(int choose,double limit) {
		if(choose == 1) {
		StdDraw.clear();
		StdDraw.setScale(-.05, 1.05);
		StdDraw.textLeft(-0.025, -0.025, "Total Collisions: "+totalCollisions);
		StdDraw.textLeft(0.35, -0.025, "Total Time: "+t);
		//StdDraw.textLeft(0.45, -0.025, "MinQueue Size: "+pq.size());
		StdDraw.textLeft(0.6, -0.025, "Mean Collisions: "+totalCollisions/t);
		
		for(int i=0;i < N;i++)
			particles[i].draw();
		StdDraw.show();
		StdDraw.pause(20);
		if(t < limit) pq.insert(new Event(t + 1.0/HZ, null, null));
		}
		else if(choose == 0) {
			StdDraw.setXscale(-400,10000);
			StdDraw.setYscale(-1,25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(0,0,10000,0);
			StdDraw.line(0,0,0,25);
			StdDraw.textLeft(4000, 24.5, "Diffusion Verification");
			StdDraw.textLeft(4000, -0.5, "Total Time");
			StdDraw.text(-200, 10, "Mean Collisions",90);
			
			if(t!=0) {
				double mean = totalCollisions/t;
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.point(t, mean);
				StdDraw.show();
				StdDraw.pause(20);
			}
			if(t < limit) pq.insert(new Event(t + 1.0/HZ, null, null));
		}
		else if(choose == 3) {
			visual.addDataValue(particles[0].getX(),1.0/2);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(4000, -0.025, "Total Time");
			StdDraw.text(-250, 0.5, "Particle location",90);
			StdDraw.show();
			StdDraw.pause(20);
			if(t < limit) pq.insert(new Event(t + 1.0/2, null, null));
		}
		else if(choose == 4) {
			int trials = 10000;
			double max = 0.02;
			StdDraw.setXscale(-trials/25,trials);
			StdDraw.setYscale(-max/25,max);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(0, 0, trials, 0);
			StdDraw.line(0, 0, 0, max);
			StdDraw.setPenRadius(.005);
			double total=0.0,val = 0.0;
			for(int i = 0 ; i < N ;i++) {
				val = particles[i].getV();
				total+=val;
				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				if(i == 150) StdDraw.point(t, val);
			}
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(t,total/N);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(4000, -0.02/50, "Total Time");
			//StdDraw.textLeft(7000, -0.025, "Average speed: "+total/N);
			StdDraw.text(-250, 0.01, "Particle velocity",90);
			StdDraw.show();
			StdDraw.pause(20);
			if(t < limit) pq.insert(new Event(t + 1.0, null, null));
		}
	}
	
	public void simulate(int choose,double limit)
	{
		//StdOut.print("*");
		pq = new MinPQ<Event>();
		for(int i = 0; i < N; i++) predict(particles[i],limit);
		pq.insert(new Event(0, null, null));
		while(!pq.isEmpty())
		{
			Event event = pq.delMin();
			if(!event.isValid()) continue;
			Particle a = event.a;
			Particle b = event.b;
			for(int i = 0; i < N; i++)
			particles[i].move(event.time - t);
			t = event.time;
			if (a != null && b != null) a.bounceOff(b);
			else if (a != null && b == null) a.bounceOffVerticalWall();
			else if (a == null && b != null) b.bounceOffHorizontalWall();
			else if (a == null && b == null) redraw(choose,limit);
			if(a != null || b != null) totalCollisions++;
			predict(a,limit);
			predict(b,limit);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(600, 600);
		StdDraw.enableDoubleBuffering();
		
		In in = new In(args[0]);
		int choose = Integer.parseInt(args[1]);
		int n = in.readInt();
		Particle [] particles = new Particle[n];
		
		for(int i = 0;i < n;i++)
		{
			double rx = in.readDouble();
			double ry = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double radius = in.readDouble();
			double mass = in.readDouble();
			int r = in.readInt();
			int g = in.readInt();
			int b = in.readInt();
			Color color = new Color(r,g,b);
			Particle p = new Particle(rx,ry,vx,vy,radius,mass,color);
			particles[i] = p;
		}
		CollisionSystem system = new CollisionSystem(particles);
		system.simulate(choose,10000);
	}

}
