//
//  AdService.hpp
//  AdService-Cocos2d-x
//
//  Created by Abhishek Aryan on 03/04/17.
//
//

#ifndef AdService_hpp
#define AdService_hpp

#include <stdio.h>
#include "platform/android/jni/JniHelper.h"
#include <string>
#include "cocos2d.h"
#include "jni.h"


namespace Ad {
    
   void showAd(bool isTop,bool isBottom){
        
      JniMethodInfo methodInfo;
       
      const char* class_name="com/ng/adservice/CocosService";
      const char* method_name="showAd";
      const char* parameter= "(ZZ)V";
    
      if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
           return;
        }
       
      methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID,isTop,isBottom);
      methodInfo.env->DeleteLocalRef(methodInfo.classID);
   }
    
   
    bool showInterstitial(){
        
        JniMethodInfo methodInfo;
        
        const char* class_name="com/ng/adservice/CocosService";
        const char* method_name="showInterstitial";
        const char* parameter= "()Z";
        
        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
            return false;
        }
        
        jboolean var=false;
        
        var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID);
        methodInfo.env->DeleteLocalRef(methodInfo.classID);
        
        return var;
        
    }
    
    bool isInterstitialReady(){
        
        JniMethodInfo methodInfo;
        
        const char* class_name="com/ng/adservice/CocosService";
        const char* method_name="isInterstitialReady";
        const char* parameter= "()Z";
        
        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
            return false;
        }
        
        jboolean var=false;
        
        var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID);
        methodInfo.env->DeleteLocalRef(methodInfo.classID);
        
        return var;
 
    }
    
}


#endif /* AdService_hpp */
