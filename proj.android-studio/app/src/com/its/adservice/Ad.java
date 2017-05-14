package com.its.adservice;

import android.widget.FrameLayout;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30/3/2017.
 */

public interface Ad {


    void showAd(boolean isTop,boolean isBottom);
    void embedView(FrameLayout layout);

    boolean isInterstitialReady();
    boolean showOrLoadInterstitial();

    boolean isVideoAvailable(boolean isReward);
    boolean showVideoAd(boolean isReward);

    void destroy();
    void start();
    void stop();
    void pause();

    void resume();


}
