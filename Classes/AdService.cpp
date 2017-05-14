//
//  AdService.cpp
//  AdService-Cocos2d-x
//
//  Created by Abhishek Aryan on 03/04/17.
//
//

#include "AdService.hpp"

USING_NS_CC;

//
//
//if(CC_TARGET_PLATFORM==CC_PLATFORM_ANDROID)
//#include <jni.h>
//extern
//"C"
//{
//    void Java_com_ng_adservice_AppActivity_callFromJNI(JNIEnv* env, jobject thiz,jstring textStr){
//        
//        const char* str;
//        str = env->GetStringUTFChars(textStr, false);
//        std::string tempStr(str);
//        CCLOG("%s","IN METHOD");
//    }
//    
//    
//    
//}
//#endif

    
//    JNIEXPORT void JNICALL Java_org_test_Wrapper_initBridge(JNIEnv *env, jobject jobj){
//        javaObj = env->NewGlobalRef(jobj);
//        
//        return;
//    }

   // static void createObject(){

//        JNIEXPORT void JNICALL Java_accessBuffThroughObjectTwo(JNIEnv *env, jobject obj,  jobject objectTwo) {
//            jclass clazz;
//            jclass bufferClazz;
//            jobject bufferJObject;
//            jfieldID fid;
//
//            clazz = (*env)->GetObjectClass(env, objectTwo);
//            assert(clazz != NULL);
//            fid = (*env)->GetFieldID(env, clazz, "obj", "Ljava/lang/Object;");
//            assert(fid != NULL);
//            bufferJObject = (*env)->GetObjectField(env, javascsicommand, fid);
//            assert(bufferJObject != NULL);
//            bufferClazz = (*env)->GetObjectClass(env, bufferJObject);
//            assert(bufferClazz != NULL);
//            fid = (*env)->GetFieldID(env, bufferClazz, "buff", "[B");
//            assert(fid != NULL);
//        }

//    }

//}

void Ad::showAd(bool isTop,bool isBottom){
    
    #if (CC_TARGET_PLATFORM==CC_PLATFORM_ANDROID)
    
        JniMethodInfo methodInfo;
    
        const char* class_name="com/its/adservice/CocosService";
        const char* method_name="showAd";
        const char* parameter= "(ZZ)V";
    
        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
            return;
        }
    
        methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID,isTop,isBottom);
        methodInfo.env->DeleteLocalRef(methodInfo.classID);
    
    #endif
    
}

bool Ad::showInterstitial(){
    
    #if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

        JniMethodInfo methodInfo;
    
        const char* class_name="com/its/adservice/CocosService";
        const char* method_name="showInterstitial";
        const char* parameter= "()Z";
    
        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
            
            return false;
        }
    
        jboolean var=false;
    
        var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID);
        methodInfo.env->DeleteLocalRef(methodInfo.classID);
    
        return var;
    
    #endif
    
    
    return false;
}

bool Ad::isInterstitialReady(){

    #if (CC_TARGET_PLATFORM==CC_PLATFORM_ANDROID)
    
        JniMethodInfo methodInfo;

        const char* class_name="com/its/adservice/CocosService";
        const char* method_name="isInterstitialReady";
        const char* parameter= "()Z";

        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
            return false;
        }

        jboolean var=false;

        var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID);
        methodInfo.env->DeleteLocalRef(methodInfo.classID);

        return var;
    
    #endif

    return false;
}


bool Ad::isVideoAvailable(bool isReward){
    
#if (CC_TARGET_PLATFORM==CC_PLATFORM_ANDROID)
    
    JniMethodInfo methodInfo;
    
    const char* class_name="com/its/adservice/CocosService";
    const char* method_name="isVideoAvailable";
    const char* parameter= "(Z)Z";
    
    if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
        return false;
    }
    
    jboolean var=false;
    
    var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID,isReward);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
    
    return var;
    
#endif
    
    return false;

    
}

bool Ad::showVideoAd(bool isReward){
    
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    
    JniMethodInfo methodInfo;
    
    const char* class_name="com/its/adservice/CocosService";
    const char* method_name="showVideo";
    const char* parameter= "(Z)Z";
    
    if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name ,parameter )) {
        
        return false;
    }
    
    jboolean var=false;
    
    var = methodInfo.env->CallStaticBooleanMethod(methodInfo.classID, methodInfo.methodID,isReward);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
    
    return var;
    
#endif
    
    
    return false;

}



