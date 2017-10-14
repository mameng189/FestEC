package com.matt.latter.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.matt.latter.R;
import com.matt.latter.delegates.LatterDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/7/16.
 */

public abstract class ProxyActivity extends SupportActivity{
    public abstract LatterDelegate setRootDelegare();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegare());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
