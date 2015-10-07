package com.rgb0101.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rgb0101.demo.fragments.HompageFragment;
import com.rgb0101.demo.fragments.MainFragment;
import com.rgb0101.demo.fragments.SettingFragment;

public class MainActivity extends FragmentActivity {
    private double backpressed= 0;
    private Toast toast= null;

    private FragmentTransaction mFrgTransaction= null;
    private MainFragment mMainFragment= null;
    private SettingFragment mSettingFragment= null;
    private HompageFragment mWebFragment= null;

    private int mType= Constants.MAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume(){
        super.onResume();

        makeView();
        moveFragment(Constants.MAIN);
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    private void makeView() {
        mMainFragment= new MainFragment();
        mSettingFragment= new SettingFragment();
        mWebFragment= new HompageFragment();
    }

    public void moveFragment(int type){
        mType= type;
        mFrgTransaction= getSupportFragmentManager().beginTransaction();
        switch(type){
            case Constants.MAIN: mFrgTransaction.replace(R.id.container, mMainFragment); break;
            case Constants.SETTING: mFrgTransaction.replace(R.id.container, mSettingFragment); break;
            case Constants.WEBVIEW: mFrgTransaction.replace(R.id.container, mWebFragment); break;
        }
        mFrgTransaction.commit();
    }

    public void toggleAlgorithm(boolean check){
        Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show();
    }
    public void phoneCall(){
        Intent intent= new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constants.Phone));
        try{ startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex){ Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show(); }
    }

    @Override
    public void onBackPressed(){
        if(mType != Constants.MAIN){
            moveFragment(Constants.MAIN);
            return ;
        }
        if(System.currentTimeMillis() > backpressed + 2000){
            backpressed= System.currentTimeMillis();
            toast= Toast.makeText(getApplicationContext(), getResources().getString(R.string.backpressed), Toast.LENGTH_SHORT);
            toast.show();
            return ;
        }
        if(System.currentTimeMillis() <= backpressed+2000){
            toast.cancel();
            finish();
        }
    }
}
