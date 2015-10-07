package com.rgb0101.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rgb0101.demo.Constants;
import com.rgb0101.demo.MainActivity;
import com.rgb0101.demo.R;

/**
 * Created by noirCynical on 2015. 10. 7..
 */
public class MainFragment extends Fragment {
    private View wholeView= null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        wholeView= inflater.inflate(R.layout.fragment_main, null);
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
        ((Button)wholeView.findViewById(R.id.buttonMainFragmentRefresh)).setOnClickListener(click);
        ((Button)wholeView.findViewById(R.id.buttonMainFragmentSetting)).setOnClickListener(click);
    }

    View.OnClickListener click= new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(v.getId() == R.id.buttonMainFragmentRefresh){

            } else if(v.getId() == R.id.buttonMainFragmentSetting) ((MainActivity)getActivity()).moveFragment(Constants.SETTING);
        }
    };
}
