package com.rgb0101.demo.Algorithm;

public class Trilateration {
	
	public static Point getResultPointFromPoint(Point a, Point b, Point c, double da, double db, double dc){ return getResult(a, b, c, da, db, dc); }
	public static Point getResultPointFromValue(double xa, double ya, double xb, double yb, double xc, double yc, double da, double db, double dc){ return getResult(new Point(xa, ya), new Point(xb, yb), new Point(xc, yc), da, db, dc); }
	public static double[] getResultArrayFromPoint(Point a, Point b, Point c, double da, double db, double dc){
		Point p= getResult(a, b, c, da, db, dc);
		return new double[]{p.getX(), p.getY()};
	}
	public static double[] getResultArrayFromValue(double xa, double ya, double xb, double yb, double xc, double yc, double da, double db, double dc){
		Point p= getResult(new Point(xa, ya), new Point(xb, yb), new Point(xc, yc), da, db, dc);
		return new double[]{p.getX(), p.getY()};
	}
	
	private static Point getResult(Point a, Point b, Point c, double da, double db, double dc){
		double x= ((b.getY() - c.getY())*(db*db - da*da + getSquareValue(a.getX(), a.getY(), b.getX(), b.getY()))
					- (a.getY() - b.getY())*(dc*dc - db*db + getSquareValue(b.getX(), b.getY(), c.getX(), c.getY())))
				/(2*((b.getY() - c.getY())*(a.getX() - b.getX()) - (a.getY() - b.getY())*(b.getX() - c.getX())));
		double y= ((b.getX() - c.getX())*(db*db - da*da + getSquareValue(a.getX(), a.getY(), b.getX(), b.getY()))
					- (a.getX() - b.getX())*(dc*dc - db*db + getSquareValue(b.getX(), b.getY(), c.getX(), c.getY())))
				/(2*((b.getX() - c.getX())*(a.getY() - b.getY()) - (a.getX() - b.getX())*(b.getY() - c.getY())));
		return new Point(x, y);
	}
	
	private static double getSquareValue(double xa, double ya, double xb, double yb){ return xa*xa-xb*xb+ya*ya-yb*yb; }
	public static double getLengthBtwPoint(Point a, Point b){ return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)); }
	public static double getLengthBtwCoordi(double xa, double ya, double xb, double yb){ return Math.sqrt(Math.pow(xa-xb, 2) + Math.pow(ya-yb, 2)); }
}