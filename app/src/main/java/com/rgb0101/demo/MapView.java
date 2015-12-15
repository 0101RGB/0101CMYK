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

    private void init(){
    }

}