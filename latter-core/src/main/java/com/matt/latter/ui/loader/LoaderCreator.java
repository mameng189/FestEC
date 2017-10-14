package com.matt.latter.ui.loader;

import android.content.Context;
import android.graphics.Color;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/7/23.
 */
//通过类的反射机制获取Loader实例
public final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_WAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_WAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_WAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_WAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //传入的是类名
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators.");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
