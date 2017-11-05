package com.matt.festec;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.matt.latter.activitys.ProxyActivity;
import com.matt.latter.app.Configurator;
import com.matt.latter.app.Latter;
import com.matt.latter.delegates.LatterDelegate;
import com.matt.latter.ec.launcher.LaunchDelegate;
import com.matt.latter.ec.main.EcBottomDelegate;
import com.matt.latter.ec.sign.ISignListener;
import com.matt.latter.ec.sign.SignInDelegate;
import com.matt.latter.ui.launcher.ILauncherListener;
import com.matt.latter.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Configurator.getInstance().withActivity(this);
    }

    @Override
    public LatterDelegate setRootDelegare() {
        //return new MainDelegate();//测试网络请求
        return new LaunchDelegate();//测试欢迎页读秒
        //return new LauncherScrollDelegate();//测试滚动banner
       // return  new SignUpDelegate();
       // return new SignInDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功了",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功了",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
            switch (tag){
                case SIGNED:
                    Toast.makeText(this,"启动结束，用户登录了",Toast.LENGTH_LONG).show();
                    //startWithPop(new MainDelegate());
                    startWithPop(new EcBottomDelegate());
                    break;
                case NOT_SINGNED:
                    Toast.makeText(this,"启动结束，用户未登录",Toast.LENGTH_LONG).show();
                    //清除上一个栈进入登录页
                    startWithPop(new SignInDelegate());
                    break;
                default:
                    break;
            }
    }
}
