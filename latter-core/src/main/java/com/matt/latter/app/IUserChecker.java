package com.matt.latter.app;

/**
 * Created by mamen on 2017/9/29.
 * 用户登录状态回掉
 *
 */

public interface IUserChecker {
    void onSignIn();//有用户信息
    void onNotSignIn();//无用户信息
}
