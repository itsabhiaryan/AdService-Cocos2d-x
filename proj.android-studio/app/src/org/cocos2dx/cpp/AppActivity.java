package org.cocos2dx.cpp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.cocos2dx.lib.Cocos2dxActivity;

public class AppActivity extends Cocos2dxActivity {

    private Ad ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ad=new AdMobHelper(this);
        ad.embedView(mFrameLayout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ad.resume();
    }

    @Override
    protected void onPause() {

        ad.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        ad.destroy();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ad.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ad.stop();
    }
}
