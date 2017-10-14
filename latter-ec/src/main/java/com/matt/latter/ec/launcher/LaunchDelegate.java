package com.matt.latter.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.matt.latter.app.AccountManager;
import com.matt.latter.app.IUserChecker;
import com.matt.latter.delegates.LatterDelegate;
import com.matt.latter.ec.R;
import com.matt.latter.ec.R2;
import com.matt.latter.ui.launcher.ILauncherListener;
import com.matt.latter.ui.launcher.OnLauncherFinishTag;
import com.matt.latter.ui.launcher.ScrollLauncherTag;
import com.matt.latter.util.storage.LattePreference;
import com.matt.latter.util.timer.BaseTimerTask;
import com.matt.latter.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/8/7.
 */

public class LaunchDelegate extends LatterDelegate implements ITimerListener {

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mLauncherListener = null;
    //butterknife在modle使用一定要加入依赖和插件引用
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    //点击后提前结束timer
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        //参数:task，立即开始，每隔一秒处理一次
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    //是否显示滚动启动页
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查用户是否登录了app
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mLauncherListener!=null){
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }
                @Override
                public void onNotSignIn() {
                    if (mLauncherListener!=null){
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SINGNED);
                    }
                }
            });
        }
    }
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
