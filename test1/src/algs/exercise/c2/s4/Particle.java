//package algs.exercise.c2.s4;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class Particle {
	private static final double INFINITY = Double.POSITIVE_INFINITY;
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private final Color color; //color
	private int count; // number of collisions
	                 
	
	public Particle(double rx, double ry, double vx, double vy, double radius,double mass,Color color) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
		this.color = color;
		this.count = 0;
	}
	
	public double getV() {
		return  Math.sqrt(vx*vx + vy*vy);
	}
	
	public double getX() {
		return rx;
	}
	
	public void move(double dt) {
		rx += dt * vx;
		ry += dt * vy;
	}
	
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
	}
	
	public double timeToHit(Particle that)
	{
		if (this == that) return INFINITY;
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx*dvx + dy*dvy;
		if( dvdr > 0) return INFINITY;
		double dvdv = dvx*dvx + dvy*dvy;
		if(dvdv == 0) return INFINITY;
		double drdr = dx*dx + dy*dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
		if (d < 0) return INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}
	public double timeToHitVerticalWall() {
		if(vx == 0)
			return INFINITY;
		else if(vx > 0)
			return (1-radius-rx) / vx;
		else 
			return (radius-rx) / vx;
	}
    public double timeToHitHorizontalWall() {
    	if(vy == 0)
			return INFINITY;
		else if(vy > 0)
			return (1-radius-ry) / vy;
		else 
			return (radius-ry) / vy;
    }
	
    public void bounceOff(Particle that)
    {
	    double dx = that.rx - this.rx, dy = that.ry - this.ry;
	    double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
	    double dvdr = dx*dvx + dy*dvy;
	    double dist = this.radius + that.radius;
	    double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
	    double Jx = J * dx / dist;
	    double Jy = J * dy / dist;
	    this.vx += Jx / this.mass;
	    this.vy += Jy / this.mass;
	    that.vx -= Jx / that.mass;
	    that.vy -= Jy / that.mass;
	    this.count++;
	    that.count++;
    }
	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}
	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;
	}
	public int count() {
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
