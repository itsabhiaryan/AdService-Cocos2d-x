package com.its.adservice;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 10/04/17.
 */
public class CocosService {

    public static void showAd(boolean isTop,boolean isBottom){
        AppActivity.ad.showAd(isTop,isBottom);
    }

    public static boolean showInterstitial(){
        return AppActivity.ad.showOrLoadInterstitial();
    }

    public static boolean isInterstitialReady(){
        return AppActivity.ad.isInterstitialReady();
    }

    public static boolean isVideoAvailable(boolean isReward){
        return AppActivity.ad.isVideoAvailable(isReward);
    }

    public static boolean showVideo(boolean isReward){
        return AppActivity.ad.showVideoAd(isReward);
    }

}
