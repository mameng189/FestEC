package com.matt.latter.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.matt.latter.app.Latter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latter.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = Latter.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
