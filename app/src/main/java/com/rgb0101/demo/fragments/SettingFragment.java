package com.rgb0101.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.rgb0101.demo.Constants;
import com.rgb0101.demo.MainActivity;
import com.rgb0101.demo.R;
import com.rgb0101.demo.SettingAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by noirCynical on 2015. 10. 7..
 */
public class SettingFragment extends Fragment {
    private View wholeView= null;

    private ListView mList= null;
    private ArrayList<String> mContent= null;
    private SettingAdapter mAdapter= null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        wholeView= inflater.inflate(R.layout.fragment_setting, null);
        return wholeView;
    }

    @Override
    public void onResume(){
        super.onResume();

        makeView();
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    private void makeView(){
        mContent= new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.listarray)));
        mAdapter= new SettingAdapter(getActivity(), R.layout.items, mContent);

        mList= (ListView)wholeView.findViewById(R.id.listSettingFragment);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(itemClick);
    }

    AdapterView.OnItemClickListener itemClick= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            switch(position){
                case 0: break;
                case 1: break;
                case 2: ((MainActivity)getActivity()).moveFragment(Constants.WEBVIEW); break;
                case 3: ((MainActivity)getActivity()).phoneCall(); break;
            }
        }
    };
}
