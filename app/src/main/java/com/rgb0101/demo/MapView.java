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
    }

}