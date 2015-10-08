package com.rgb0101.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by noirCynical on 2015. 10. 7..
 */
public class SettingAdapter extends ArrayAdapter<String> {
    private Context mContext= null;
    private ArrayList<String> mContent= null;
    private CheckBox mCheck= null;

    public SettingAdapter(Context context, int resId, ArrayList<String> content){
        super(context, resId, content);
        mContext= context;
        mContent= content;
    }

    @Override
    public View getView(int position, View v, ViewGroup container){
        if(position == 0 || position == 1){
            v= LayoutInflater.from(mContext).inflate(R.layout.first_item, null);
            ((CheckBox)v.findViewById(R.id.checkListView)).setOnClickListener(new CheckClickListener(position));
            ((CheckBox)v.findViewById(R.id.checkListView)).setChecked(((MainActivity)mContext).getObjectShowing(position));
        } else if(v == null) v= LayoutInflater.from(mContext).inflate(R.layout.items, null);
        ((TextView)v.findViewById(R.id.textListView)).setText(mContent.get(position));
        ((ImageView)v.findViewById(R.id.imageListView)).setImageDrawable(((MainActivity)mContext).getResources().getDrawable(R.drawable.icon01+position));
        return v;
    }

    @Override
    public String getItem(int position){ return mContent.get(position); }
    @Override
    public int getCount(){ return mContent.size(); }

    private class CheckClickListener implements View.OnClickListener {
        private int mType= 0;

        public CheckClickListener(int type){ mType= type; }

        @Override
        public void onClick(View v){
            boolean check= ((CheckBox)v).isChecked();
            ((MainActivity)mContext).setObjectShowing(check, mType);
        }
        public int getType(){ return mType; }
    };
}
