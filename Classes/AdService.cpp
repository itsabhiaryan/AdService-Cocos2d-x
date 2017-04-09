//
//  AdService.cpp
//  AdService-Cocos2d-x
//
//  Created by Abhishek Aryan on 03/04/17.
//
//

//#include "AdService.hpp"
////#include "platform/android/jni/JniHelper.h"
////#include <jni.h>
//#include <string>
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



//namespace Ad {

    //static void showInterestitial(){
    
//        cocos2d::JniMethodInfo methodInfo;
//        
//        
//        std::string class_name="com/ng/adservice/AppActivity";
//        std::string method_name="alertJNI";
//        std::string parameter= "()V";
//    
//        if (! cocos2d::JniHelper::getStaticMethodInfo(methodInfo, class_name, method_name , parameter )) {
//            return;
//        }
//    
//        methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID);
//        methodInfo.env->DeleteLocalRef(methodInfo.classID);
    
   // }
    
   
    
    
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

