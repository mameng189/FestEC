package com.matt.latter.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.matt.latter.app.AccountManager;
import com.matt.latter.app.IUserChecker;
import com.matt.latter.delegates.LatterDelegate;
import com.matt.latter.ec.R;
import com.matt.latter.ui.launcher.ILauncherListener;
import com.matt.latter.ui.launcher.LauncherHolderCreator;
import com.matt.latter.ui.launcher.OnLauncherFinishTag;
import com.matt.latter.ui.launcher.ScrollLauncherTag;
import com.matt.latter.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/7.
 */

public class LauncherScrollDelegate extends LatterDelegate implements OnItemClickListener{
    //图片资源数据用integer类型
    private ConvenientBanner<Integer> mBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mLauncherListener = null;
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                //滑动下面的小球球
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//水平居中
                .setOnItemClickListener(this)
                .setCanLoop(false);//设置可以循环
    }
    @Override
    public Object setLayout() {
        mBanner = new ConvenientBanner<Integer>(getContext());
        return mBanner;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //交给宿主activity来处理(fragment 和 activity之间通信)
        if (activity instanceof ILauncherListener){
            mLauncherListener = (ILauncherListener) activity;
        }
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一张图片
        if (position == INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
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
}
