package com.rgb0101.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MapView extends SurfaceView {

    private Context mContext= null;
    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon, bmpRaw, bmpCenter;
    private boolean isShowingIcon= true, isShowingRaw= true;
    private MapViewThread myThread;
    private double delay = 0.1;
    private int xPosIcon = getWidth()/4*3, xPosRaw= 0;
    private int yPosIcon = getHeight(), yPosRaw= 0;
    private int deltaXIcon = 5, deltaXRaw= 5;
    private int deltaYIcon = 5, deltaYRaw= 5;
    private int iconWidth;
    private int iconHeight;

    private static int mMode= Constants.STEP0;

    public MapView(Context context) {
        super(context);
        mContext= context;

        init();
    }
    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext= context;

        init();
    }
    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext= context;

        init();
    }

    public void setShowingIcon(boolean check){ isShowingIcon= check; }
    public void setShowingRaw(boolean check){ isShowingRaw= check; }
    public boolean getShowingIcon(){ return isShowingIcon; }
    public boolean getShowingRaw(){ return isShowingRaw; }

    private void init(){
        myThread = new MapViewThread(this);

        surfaceHolder = getHolder();
        bmpIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_small);
        bmpRaw= BitmapFactory.decodeResource(getResources(), R.drawable.icon_raw);
        bmpCenter= BitmapFactory.decodeResource(getResources(), R.drawable.center);

        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        xPosIcon= getWidth()/4*3;
//        xPosRaw= getWidth()-iconWidth;
    }

    protected void drawSomething(Canvas canvas) {
        if(mMode == Constants.STEP0) xPosIcon= canvas.getWidth()/4;
        canvas.drawColor(Color.parseColor("#88fdfdfd"));
        canvas.drawBitmap(bmpCenter, canvas.getWidth() / 2, canvas.getHeight() / 2, null);

        switch(mMode){
            case Constants.STEP0: deltaXIcon=0; deltaYIcon=12; break;
            case Constants.STEP1: deltaXIcon=7; deltaYIcon=0; break;
            case Constants.STEP2: deltaXIcon=0; deltaYIcon=-7; break;
            case Constants.STEP3: deltaXIcon=-10; deltaYIcon=0; break;
            case Constants.STEP4: deltaXIcon=0; deltaYIcon=-9; break;
            case Constants.STEP5: deltaXIcon=10; deltaYIcon=0; break;
            case Constants.STEPtemp: delay+=0.1; deltaXIcon = 0; deltaYIcon= 0; break;
            case Constants.STEP6: deltaXIcon=-6; deltaYIcon=0; break;
            case Constants.STEP7: deltaXIcon=0; deltaYIcon=0; break;
        }


//        if(mMode != Constants.STEPtemp){
            xPosIcon += deltaXIcon;
            yPosIcon += deltaYIcon;
            if(isShowingIcon) canvas.drawBitmap(bmpIcon, xPosIcon, yPosIcon, null);

            xPosRaw= xPosIcon + (int)(Math.random()*15.0 * (Math.random()*4.0-3));
            yPosRaw= yPosIcon + (int)(Math.random()*15.0 * (Math.random()*4.0-3));
            if(isShowingRaw) canvas.drawBitmap(bmpRaw, xPosRaw, yPosRaw, null);
//        }

        switch(mMode){
            case Constants.STEP0: if(yPosIcon >= canvas.getHeight()/4*3) mMode++; break;
            case Constants.STEP1: if(xPosIcon >= canvas.getWidth()/4*3) mMode++; break;
            case Constants.STEP2: if(yPosIcon <= canvas.getHeight()/2) mMode++; break;
            case Constants.STEP3: if(xPosIcon <= canvas.getWidth()/4) mMode++; break;
            case Constants.STEP4: if(yPosIcon <= canvas.getHeight()/4) mMode++; break;
            case Constants.STEP5: if(xPosIcon >= canvas.getWidth()/4*3) mMode++; break;
            case Constants.STEPtemp: if(delay >= 1.0) mMode++; break;
            case Constants.STEP6: if(xPosIcon <= canvas.getWidth()/4) mMode++; break;
        }
    }

}