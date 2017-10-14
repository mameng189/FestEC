package com.matt.latter.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Administrator on 2017/8/7.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
