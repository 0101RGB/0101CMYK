package com.rgb0101.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rgb0101.demo.R;

/**
 * Created by noirCynical on 2015. 10. 7..
 */
public class HompageFragment extends Fragment {
    private View wholeView= null;
    private WebView mWebView= null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        wholeView= inflater.inflate(R.layout.fragment_web, null);
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
        mWebView= (WebView)wholeView.findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = mWebView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        mWebView.loadUrl("http://0101rgb.github.io");
    }
}
