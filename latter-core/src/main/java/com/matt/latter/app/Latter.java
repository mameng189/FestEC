package com.matt.latter.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/16.
 */

public final class Latter {
    public static  Configurator init(Context context){
        getConfiguration().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<Object,Object> getConfiguration(){
        return Configurator.getInstance().getLatterConfigs();
    }
    public  static  Context getApplication(){
        return (Context) getConfiguration().get(ConfigType.APPLICATION_CONTEXT.name());
    }
    public static <T> T getConfigValues(Object key) {
        return Configurator.getInstance().getConfiguration(key);
    }
    public static Handler getHandler() {
        return Configurator.getInstance().getConfiguration(ConfigType.HANDLER.name());
    }
}
