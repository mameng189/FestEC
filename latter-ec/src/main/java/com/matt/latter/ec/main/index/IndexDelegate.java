package com.matt.latter.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.matt.latter.delegates.bottom.BottomItemDelegate;
import com.matt.latter.ec.R;
import com.matt.latter.ec.R2;
import com.matt.latter.ui.Refresh.RefreshHandler;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/10/18.
 */

public  class IndexDelegate extends BottomItemDelegate{
    @BindView(R2.id.rv_index)
    RecyclerView mRecycleview=null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearcheView = null;
    private RefreshHandler mRefreshHandle = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandle = new RefreshHandler(mRefreshLayout);
    }
    private void initRefreshLayout(){
        //下拉转动变化的颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //设置转圈起始高度和终止高度
        mRefreshLayout.setProgressViewOffset(true,120,300);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        mRefreshHandle.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
