package com.matt.latter.app;

import com.matt.latter.util.storage.LattePreference;

/**
 * Created by mamen on 2017/9/29.
 * 管理用户信息
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户信息,登录后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    public static  boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }
    }
}
