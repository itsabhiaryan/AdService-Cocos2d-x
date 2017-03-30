package com.ng.adservice;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-03-2017.
 */

public class AdMobHelper implements Ad {

    private Activity activity;
    private AdView topView,bottomView;
    private InterstitialAd interstitialAd;

    private static final String TopAdUnitId="ca-app-pub-xxxxxxxxxxxxxx";
    private static final String BottomAdUnitId="ca-app-pub-xxxxxxxxx";
    private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx";

    private final int SHOW_TOP_ADS = 0;
    private final int SHOW_BOTTOM_ADS = 1;
    private final int SHOW_TOP_BOTTOM_ADS = 2;
    private final int SHOW_NONE_ADS = 3;

    protected Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case SHOW_TOP_ADS:
                {
                    topView.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.GONE);
                    break;
                }
                case SHOW_BOTTOM_ADS:
                {
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.VISIBLE);
                    break;
                }
                case SHOW_TOP_BOTTOM_ADS:
                {
                    topView.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.VISIBLE);
                    break;
                }
                case SHOW_NONE_ADS:
                {
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                    break;
                }
            }
        }
    };


    public AdMobHelper(Activity activity){
        this.activity=activity;

        topView = new AdView(activity);
        topView.setAdSize(AdSize.BANNER);
        topView.setAdUnitId(TopAdUnitId);

        bottomView = new AdView(activity);
        bottomView.setAdSize(AdSize.BANNER);
        bottomView.setAdUnitId(BottomAdUnitId);

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        topView.loadAd(adRequestBuilder.build());

        AdRequest.Builder adRequestBuilder1 = new AdRequest.Builder();
        adRequestBuilder1.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        bottomView.loadAd(adRequestBuilder1.build());

        crateInterestial();

    }

    public void crateInterestial(){

        interstitialAd = new InterstitialAd(this.activity);
        interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(activity.getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdClosed() {
                Toast.makeText(activity.getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showAd(boolean isTop, boolean isBottom) {

        if(isBottom && isTop)
            handler.sendEmptyMessage(SHOW_TOP_BOTTOM_ADS);
        else if(!isBottom && !isTop)
            handler.sendEmptyMessage(SHOW_NONE_ADS);
        else if(isTop && !isBottom)
            handler.sendEmptyMessage(SHOW_TOP_ADS);
        else if(!isTop && isBottom)
            handler.sendEmptyMessage(SHOW_BOTTOM_ADS);
    }

    @Override
    public void embedView(FrameLayout fLayout) {

      /* LinearLayout layout = new LinearLayout(activity);
        int gravity= Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        layout.setGravity(gravity);
        activity.addContentView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(topView);
        layout.bringToFront();

        LinearLayout bottomlayout = new LinearLayout(activity);
        int bottom= Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        bottomlayout.setGravity(bottom);
        activity.addContentView(bottomlayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bottomlayout.addView(bottomView);
        bottomlayout.bringToFront();*/

        RelativeLayout relativeLayout=new RelativeLayout(activity);

        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        topParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        relativeLayout.addView(topView, topParams);

        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        relativeLayout.addView(bottomView, bottomParams);

        activity.addContentView(relativeLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayout.bringToFront();
    }

    @Override
    public void showOrLoadInterstitial() {

    }

    @Override
    public boolean showVideoAd(boolean isReward) {
        return false;
    }

    @Override
    public void destroy() {
        if (topView != null)topView.destroy();
        if (bottomView != null) bottomView.destroy();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {
        if (topView != null) topView.pause();
        if (bottomView != null) bottomView.pause();
    }

    @Override
    public void resume() {
        if (topView != null) topView.resume();
        if (bottomView != null) bottomView.resume();
    }
}
