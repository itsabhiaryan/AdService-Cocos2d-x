package org.cocos2dx.cpp;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30/3/2017.
 */

public interface Ad {


    void showAd(boolean isTop,boolean isBottom);
    void embedView(FrameLayout layout);

    void showOrLoadInterstitial();
    boolean showVideoAd(boolean isReward);

    void destroy();
    void start();
    void stop();
    void pause();

    void resume();


}
