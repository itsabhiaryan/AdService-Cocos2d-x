//
//  AdService.hpp
//  AdService-Cocos2d-x
//
//  Created by Abhishek Aryan on 03/04/17.
//
//

#ifndef AdService_hpp
#define AdService_hpp

#if(CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "jni.h"
#include "platform/android/jni/JniHelper.h"
#endif

namespace Ad {
    
    void showAd(bool isTop,bool isBottom);
   
    bool showInterstitial();
    
    bool isInterstitialReady();
    
    bool isVideoAvailable(bool isReward);
    
    bool showVideoAd(bool isReward);
}


#endif /* AdService_hpp */
