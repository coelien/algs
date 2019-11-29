package algs.exercise.c1.s1;
import java.awt.Font;

import edu.princeton.cs.algs4.*;
public class TestStdDraw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.enableDoubleBuffering();
		//StdDraw.setScale(-2,2);
		for(double t=0;true;t+=0.2) {
		double x = Math.sin(t)*0.2;
		double y = Math.cos(t)*0.2;
		StdDraw.clear();
		StdDraw.picture(0.5,0.5,"baokan.png",1,1);
		StdDraw.setPenRadius(0.15);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(x+0.5,y+0.5);
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);
		StdDraw.arc(0.5, 0.5, 0.3, 270, 180);
		Font font = new Font("Arial",Font.ROMAN_BASELINE,30);
		StdDraw.setFont(font);
		StdDraw.text(x+0.5, y+0.5, "two");
		StdDraw.show();	
		StdDraw.pause(10);
		}
		
	}

}
