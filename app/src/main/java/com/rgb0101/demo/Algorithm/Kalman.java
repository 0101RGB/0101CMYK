package com.rgb0101.demo.Algorithm;

import java.util.ArrayList;

public class Kalman {
	// matrix for computing
	private static double[][] trans= null;
	private static double[][] control= null;
	private static double[][] measure= null;
	private static double[][] uncertainty= null;
	private static double[][] noise= null;

    private static double[] measurement= null;
    private static double[] controlmat= null;

    private static double[] x= null;
    private static double[] p= null;
    private static double[] s= null;
    private static double[] k= null;
    private static double[] y= null;

	private static double[] lastX= null;
	private static double[][] lastP= null;
	
	private static ArrayList<Point> rPoints= null;
	private static ArrayList<Point> kPoints= null;
	private static ArrayList<Point> pPoints= null;
	
	private static Point mCur= null, mLast= null;
	
	private static void init(){
		trans= new double[][]{{1, 0, 0.2, 0}, {0, 1, 0, 0.2}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		control= new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		measure= new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		uncertainty= new double[][]{{0.001, 0, 0, 0}, {0, 0.1, 0, 0}, {0, 0, 0.1, 0}, {0, 0, 0, 0.1}};
		noise= new double[][]{{0.1, 0, 0, 0}, {0, 0.1, 0, 0}, {0, 0, 0.1, 0}, {0, 0, 0, 0.1}};

		rPoints= new ArrayList<Point>();
		kPoints= new ArrayList<Point>();
		pPoints= new ArrayList<Point>();
		
		mCur= new Point();
	}

	public static void calibrate(double rawX, double rawY){
        mCur= new Point(rawX, rawY);
        rPoints.add(mCur);

        if(mLast == null) mLast= new Point(0, 0);
        measurement= new double[]{rawX, rawY, rawX-mLast.getX(), rawY-mLast.getY()};
        controlmat= new double[]{0, 0, 0, 0};

        x= new double[]{0,0,0,0};
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++)
                x[i] += trans[i][j]*x[j]+control[i][j]*controlmat[j];
        }
    }

	public static void clear(){
		rPoints.clear();
		kPoints.clear();
		pPoints.clear();
	}
	
	public double[][] getTrans(){ return trans; }
	public double[][] getControl(){ return control; }
	public double[][] getMeasure(){ return measure; }
	public double[][] getUncertainty(){ return uncertainty; }
	public double[][] getNoise(){ return noise; }
	public Point getCurrentPoint(){ return mCur; }
	public void setTrans(double[][] trans){ this.trans= trans; }
	public void setControl(double[][] control){ this.control= control; }
	public void setMeaseure(double[][] measure){ this.measure= measure; }
	public void setUncertainty(double[][] uncertainty){ this.uncertainty= uncertainty; }
	public void setNoise(double[][] noise){ this.noise= noise; }
}