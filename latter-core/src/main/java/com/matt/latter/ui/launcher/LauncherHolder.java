package com.matt.latter.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2017/8/7.
 */

public class LauncherHolder implements Holder<Integer>{
    private AppCompatImageView mImageView = null;
    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }
    //每次滑动更新UI
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
