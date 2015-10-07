public class Point {
	private double x;
	private double y;
	
	public Point(){};
	public Point(double x, double y){
		this.x= x;
		this.y= y;
	}
	
	public double getX(){ return x; }
	public double getY(){ return y; }
	public void setX(double x){ this.x= x; }
	public void setY(double y){ this.y= y; }
	public double getDist(){ return Math.sqrt(x*x + y*y); }
	public double getDist(double x, double y){ return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)); }
}
