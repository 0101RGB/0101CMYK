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
    private int xPosIcon = getWidth()/4, xPosRaw= 0;
    private int yPosIcon = 5, yPosRaw= 0;
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

        xPosIcon= getWidth()/4;
//        xPosRaw= getWidth()-iconWidth;
    }

    protected void drawSomething(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#88fdfdfd"));
        canvas.drawBitmap(bmpCenter, canvas.getWidth() / 2, canvas.getHeight() / 2, null);

        switch(mMode){
            case Constants.STEP0: deltaXIcon=0; deltaYIcon=5; break;
            case Constants.STEP1: deltaXIcon=5; deltaYIcon=0; break;
            case Constants.STEP2: deltaXIcon=0; deltaYIcon=-5; break;
            case Constants.STEP3: deltaXIcon=-5; deltaYIcon=0; break;
            case Constants.STEP4: deltaXIcon=0; deltaYIcon=-5; break;
            case Constants.STEP5: deltaXIcon=0; deltaYIcon=0; break;
        }

        xPosIcon += deltaXIcon;
        yPosIcon += deltaYIcon;
        if(isShowingIcon) canvas.drawBitmap(bmpIcon, xPosIcon, yPosIcon, null);

        xPosRaw= xPosIcon + (int)(Math.random()*20.0 * (Math.random()*4.0-3));
        yPosRaw= yPosIcon + (int)(Math.random()*20.0 * (Math.random()*4.0-3));
        if(isShowingRaw) canvas.drawBitmap(bmpRaw, xPosRaw, yPosRaw, null);

        switch(mMode){
            case Constants.STEP0: if(yPosIcon >= canvas.getHeight()/4*3) mMode++; break;
            case Constants.STEP1: if(xPosIcon >= canvas.getWidth()/4*3) mMode++; break;
            case Constants.STEP2: if(yPosIcon <= canvas.getHeight()/4) mMode++; break;
            case Constants.STEP3: if(xPosIcon <= canvas.getWidth()/4) mMode++; break;
            case Constants.STEP4: if(yPosIcon <= -20) mMode++; break;
        }
    }

}