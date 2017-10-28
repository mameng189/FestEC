package com.matt.latter.app;


import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/7/16.
 */

public class Configurator {
    private static final HashMap<Object, Object> LATTER_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器实现模拟请求
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //ConfigType.CONFIG_READY.name() 枚举类以字符串的形式输出出来
    private Configurator() {
        //配置文件初始化还没有完成
        LATTER_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        LATTER_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    //单例模式
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatterConfigs() {
        return LATTER_CONFIGS;
    }

    //使用静态内部类可以保证线程安全（懒汉模式）
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        LATTER_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTER_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //初始化图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initalizer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initalizer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descripeter) {
        ICONS.add(descripeter);
        return this;
    }

    //配置拦截器
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTER_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTER_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    //是否调用过configure方法完成配置
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTER_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configruration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTER_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTER_CONFIGS.get(key);
    }
}
