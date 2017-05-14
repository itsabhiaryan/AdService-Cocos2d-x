package com.its.adservice;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.interstitial.RevMobFullscreen;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 13/05/17.
 */
public class RevMobHelper implements Ad {

    private Activity currentActivity;
    private RevMob revmob;
    private RevMobBanner topBanner,bottomBanner;

    private RevMobFullscreen fullscreen, video, rewardedVideo;
    private boolean fullscreenIsLoaded, videoIsLoaded, rewardedVideoIsLoaded;

    private static final String APP_ID="59172dd20c0396011a58b516";


    public RevMobHelper(Activity currentActivity){

        this.currentActivity=currentActivity;
        fullscreenIsLoaded = false;
        revmob = RevMob.startWithListener(currentActivity, new RevMobAdsListener() {
            @Override
            public void onRevMobSessionStarted() {
                loadBanner(); // Cache the banner once the session is started
                loadFullscreen();
                loadVideo();
                loadRewardedVideo();
                Log.i("RevMob","Session Started");
            }
            @Override
            public void onRevMobSessionNotStarted(String message) {
                //If the session Fails to start, no ads can be displayed.
                Log.i("RevMob","Session Failed to Start");
            }
        }, APP_ID);

    }

    public void loadBanner(){
        topBanner = revmob.preLoadBanner(currentActivity, new RevMobAdsListener(){
            @Override
            public void onRevMobAdReceived() {
                addToLayout(true,false);
                Log.i("RevMob","Banner Ready to be Displayed"); //At this point, the banner is ready to be displayed.
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                Log.i("RevMob","Banner Not Failed to Load");
            }
            @Override
            public void onRevMobAdDisplayed() {
                Log.i("RevMob","Banner Displayed");
            }
        });


        bottomBanner = revmob.preLoadBanner(currentActivity, new RevMobAdsListener(){
            @Override
            public void onRevMobAdReceived() {
                addToLayout(false,true);
                Log.i("RevMob","Banner Ready to be Displayed"); //At this point, the banner is ready to be displayed.
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                Log.i("RevMob","Banner Not Failed to Load");
            }
            @Override
            public void onRevMobAdDisplayed() {
                Log.i("RevMob","Banner Displayed");
            }
        });
    }

    public void addToLayout(final boolean isTop,final boolean isBottom){

        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                RelativeLayout relativeLayout=new RelativeLayout(currentActivity);

                if(isTop) {
                    RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    topParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                    relativeLayout.addView(topBanner, topParams);
                    topBanner.setBackgroundColor(Color.BLACK);
                    showAd(isTop,isBottom);
                }

                if(isBottom) {
                    RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                    relativeLayout.addView(bottomBanner, bottomParams);
                    bottomBanner.setBackgroundColor(Color.BLACK);
                    showAd(isTop,isBottom);
                }

                currentActivity.addContentView(relativeLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                relativeLayout.bringToFront();
            }
        });

    }

    public void showBanner1() {
        revmob.showBanner(currentActivity, null, new RevMobAdsListener() {
            @Override
            public void onRevMobAdReceived() {
                Log.i("RevMob", "Banner loaded.");
            }

            @Override
            public void onRevMobAdNotReceived(String message) {
                Log.i("RevMob", "Banner not received.");
            }

            @Override
            public void onRevMobAdDisplayed() {
                Log.i("RevMob", "Banner displayed.");
            }

            @Override
            public void onRevMobAdDismissed() {
                Log.i("RevMob", "Banner dismissed.");
            }

            @Override
            public void onRevMobAdClicked() {
                Log.i("RevMob", "Banner clicked.");
            }
        });
    }

    public void loadFullscreen() {
        //load it with RevMob listeners to control the events fired
        fullscreen = revmob.createFullscreen(currentActivity,  new RevMobAdsListener() {
            @Override
            public void onRevMobAdReceived() {
                Log.i("RevMob", "Fullscreen loaded.");
                fullscreenIsLoaded = true;
                //showFullscreen();
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                Log.i("RevMob", "Fullscreen not received.");
            }
            @Override
            public void onRevMobAdDismissed() {
                Log.i("RevMob", "Fullscreen dismissed.");
                loadFullscreen();
            }
            @Override
            public void onRevMobAdClicked() {
                Log.i("RevMob", "Fullscreen clicked.");
            }
            @Override
            public void onRevMobAdDisplayed() {
                Log.i("RevMob", "Fullscreen displayed.");
            }
        });
    }

    public void loadVideo(){

        video = revmob.createVideo(currentActivity,new RevMobAdsListener(){
            @Override
            public void onRevMobVideoLoaded() {
                videoIsLoaded = true; //Video ready to be displayed
                //showVideo();
            }
            @Override
            public void onRevMobVideoStarted() {
                videoIsLoaded = false;
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                videoIsLoaded = false; //Ad failed to load;
            }

            @Override
            public void onRevMobAdClicked() {
                Log.i("RevMob", "Video clicked.");
            }

            @Override
            public void onRevMobAdDismissed() {
                Log.i("RevMob", "Video dismissed.");
            }


            @Override
            public void onRevMobVideoNotCompletelyLoaded() {
                Log.i("RevMob", "Video has not been completely loaded.");
                //You tried to show it but it's not loaded yet
            }

            @Override
            public void onRevMobVideoFinished(){
                Log.i("RevMob", "Video finished playing");
            }
        });
    }

    public void loadRewardedVideo(){
        rewardedVideo = revmob.createRewardedVideo(currentActivity, new RevMobAdsListener(){
            @Override
            public void onRevMobRewardedVideoLoaded() {
                rewardedVideoIsLoaded = true;
                Log.i("RevMob","Rewarded Video ready to be displayed");
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                rewardedVideoIsLoaded = false;
                Log.i("RevMob","Rewarded Video failed to load");
            }

            @Override
            public void onRevMobAdDismissed() {
                Log.i("RevMob", "Video dismissed.");
            }

            @Override
            public void onRevMobRewardedVideoNotCompletelyLoaded() {
                Log.i("RevMob", "RevMob Rewarded Video not completely loaded.");
                //You tried to show it but it's not loaded yet
            }

            @Override
            public void onRevMobRewardedVideoStarted() {
                rewardedVideoIsLoaded = false;
            }
            @Override
            public void onRevMobRewardedVideoCompleted() {
                Log.i("RevMob", "Rewarded Video completed. You earned a coin!");
                //User dismissed post-roll static adHelper
                loadRewardedVideo();
            }
            @Override
            public void onRevMobAdClicked() {
                Log.i("RevMob", "Rewarded Video clicked. You earned a coin!");
                //User clicked on post-roll static adHelper
            }

            @Override
            public void onRevMobRewardedVideoFinished() {
                Log.i("RevMob", "RevMob Rewarded Video finished playing.");
                //The video itself reached its end
                loadRewardedVideo();
            }

            @Override
            public void onRevMobRewardedPreRollDisplayed() {
                Log.i("RevMob", "RevMob Rewarded Video Pre-Roll displayed");
            }
        });
    }


    @Override
    public void showAd(final boolean isTop,final boolean isBottom) {

        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(topBanner!=null)
                    if(isTop)topBanner.show();
                    else topBanner.hide();

                if(bottomBanner!=null)
                    if(isBottom) bottomBanner.show();
                    else bottomBanner.hide();
            }
        });
    }

    @Override
    public void embedView(FrameLayout layout) {

        /** Not embed View May be View is not ready yet so add by addToLayout()   */

        //RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        //topParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

    }

    @Override
    public boolean isInterstitialReady() {
        return fullscreenIsLoaded;
    }

    @Override
    public boolean showOrLoadInterstitial() {

        if(fullscreenIsLoaded) {
            fullscreen.show(); // call it wherever you want to show the fullscreen adHelper
        } else {
            loadFullscreen();
            Log.i("RevMob", "Ad not loaded yet.");
        }

        return false;
    }

    @Override
    public boolean isVideoAvailable(boolean isReward) {
        return isReward?rewardedVideoIsLoaded:videoIsLoaded;
    }

    @Override
    public boolean showVideoAd(boolean isReward) {

        if(isReward)return showRewardedVideo();
        else {
            showVideo();
            return true;
        }
    }

    public void showVideo(){
        if(videoIsLoaded) video.showVideo();
        else loadVideo();
    }

    public boolean showRewardedVideo(){
        if(rewardedVideoIsLoaded) rewardedVideo.showRewardedVideo();
        return rewardedVideoIsLoaded;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
