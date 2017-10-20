package com.matt.latter.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.matt.latter.delegates.bottom.BottomItemDelegate;
import com.matt.latter.ec.R;

/**
 * Created by Administrator on 2017/10/18.
 */

public  class IndexDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
