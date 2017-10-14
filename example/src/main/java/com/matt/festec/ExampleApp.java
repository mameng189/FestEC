package com.matt.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.matt.latter.app.Latter;
import com.matt.latter.ec.database.DatabaseManager;
import com.matt.latter.ec.icon.FontEcModule;
import com.matt.latter.net.interceptors.DebugInterceptor;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
            super.onCreate();
            Latter.init(this)
                    .withIcon(new FontAwesomeModule())
                    .withIcon(new FontEcModule())
                    .withInterceptor(new DebugInterceptor("index",R.raw.test))//创建拦截器模拟请求响应
                   // .withApiHost("http://61.135.169.121/")//百度服务器ip host
                    .withApiHost("http://127.0.0.1/")//百度服务器ip host
                    .configure();
        //初始化GreenDao
        DatabaseManager.getInstance().init(this);
        //初始化Bmob后端服务
        //Bmob.initialize(this,"6e2443385a7806e32bae0a00dd247695");
        //初始化db查看工具
        initStetho();
    }
    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
