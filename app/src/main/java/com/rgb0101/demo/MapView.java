package com.rgb0101.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MapView extends SurfaceView {

    private Context mContext= null;
    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon, bmpRaw;
    private boolean isShowingIcon= true, isShowingRaw= true;
    private MapViewThread myThread;
    int xPosIcon = 0, xPosRaw= 0;
    int yPosIcon = 0, yPosRaw= 0;
    int deltaXIcon = 5, deltaXRaw= 5;
    int deltaYIcon = 5, deltaYRaw= 5;
    int iconWidth;
    int iconHeight;

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

        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        surfaceHolder.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {}
                }
            }});

        xPosRaw= getWidth()-iconWidth;
    }

    protected void drawSomething(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#fdfdfd"));
//        canvas.drawBitmap(bmpIcon, getWidth()/2, getHeight()/2, null);

        // icon moving
        xPosIcon += deltaXIcon;
        if(deltaXIcon > 0){
            if(xPosIcon >= getWidth() - iconWidth){
                deltaXIcon *= -1;
            }
        }else{
            if(xPosIcon <= 0){
                deltaXIcon *= -1;
            }
        }

        yPosIcon += deltaYIcon;
        if(deltaYIcon > 0){
            if(yPosIcon >= getHeight() - iconHeight){
                deltaYIcon *= -1;
            }
        }else{
            if(yPosIcon <= 0){
                deltaYIcon *= -1;
            }
        }
        if(isShowingIcon) canvas.drawBitmap(bmpIcon, xPosIcon, yPosIcon, null);

        // raw moving
        xPosRaw -= deltaXRaw;
        if(deltaXRaw > 0){
            if(xPosRaw >= getWidth() - iconWidth){
                deltaXRaw *= -1;
            }
        }else{
            if(xPosRaw <= 0){
                deltaXRaw *= -1;
            }
        }

        yPosRaw += deltaYRaw;
        if(deltaYRaw > 0){
            if(yPosRaw >= getHeight() - iconHeight){
                deltaYRaw *= -1;
            }
        }else{
            if(yPosRaw <= 0){
                deltaYRaw *= -1;
            }
        }
        if(isShowingRaw) canvas.drawBitmap(bmpRaw, xPosRaw, yPosRaw, null);
    }

}