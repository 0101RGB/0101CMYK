package com.rgb0101.demo.Algorithm;

public class test{
	public static void main(String[] args){
		Point predict= new Point(3, 2);
		Point a= new Point(0, 0);
		Point b= new Point(2, 0);
		Point c= new Point(2, 2);
		Point p= Trilateration.getResultPointFromPoint(a, b, c, Trilateration.getLengthBtwPoint(predict, a), Trilateration.getLengthBtwPoint(predict, b), Trilateration.getLengthBtwPoint(predict, c));
		
		System.out.println("x: "+p.getX() + " ::: y: "+p.getY());
	}
}