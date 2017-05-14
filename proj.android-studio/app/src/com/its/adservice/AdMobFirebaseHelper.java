package com.its.adservice;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 13/05/17.
 */
public class AdMobFirebaseHelper extends AdMobHelper implements RewardedVideoAdListener {

    private static final String APP_ID="ca-app-pub-3940256099942544~3347511713";
    private static final String AD_UNIT_ID_VIDEO="ca-app-pub-3940256099942544/5224354917";

    private RewardedVideoAd mAd;
    private boolean isRewardShown;

    public AdMobFirebaseHelper(Activity activity) {
        super(activity);

        MobileAds.initialize(activity.getApplicationContext(), APP_ID);

        initView();

        mAd = MobileAds.getRewardedVideoAdInstance(activity);
        mAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mAd.loadAd(AD_UNIT_ID_VIDEO, new AdRequest.Builder().build());
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(activity.getApplicationContext(), "Finished Loading Reward Video", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(activity.getApplicationContext(), "Closed Reward Video", Toast.LENGTH_SHORT).show();

        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public boolean showVideoAd(boolean isReward){

        isRewardShown=false;
        if(isReward) {

            activity.runOnUiThread(new Runnable() {
                public void run() {

                    if (mAd.isLoaded()) {
                        mAd.show();
                        isRewardShown=true;

                    } else {
                        loadRewardedVideoAd();
                    }
                }

            });
        }

        return isRewardShown;
    }

    @Override
    public void resume() {
        mAd.resume(activity);
        super.resume();
    }

    @Override
    public void pause(){
        mAd.pause(activity);
        super.pause();
    }

    @Override
    public void destroy(){
        mAd.destroy(activity);
        super.destroy();
    }

    @Override
    public boolean isVideoAvailable(boolean isReward) {
       return isReward?mAd.isLoaded():false;
    }
}
