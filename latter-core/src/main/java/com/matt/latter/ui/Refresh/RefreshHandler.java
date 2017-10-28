package com.matt.latter.ui.Refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.matt.latter.app.Latter;
import com.matt.latter.net.RestClient;
import com.matt.latter.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/10/23.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

   private final SwipeRefreshLayout REFRESH_LATOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LATOUT) {
        this.REFRESH_LATOUT = REFRESH_LATOUT;
        REFRESH_LATOUT.setOnRefreshListener(this);
    }
    private void refresh(){
        REFRESH_LATOUT.setRefreshing(true);
    }
    @Override
    public void onRefresh() {
    //监听下拉刷新操作
        REFRESH_LATOUT.setRefreshing(true);
        Latter.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求操作
                REFRESH_LATOUT.setRefreshing(false);
            }
        },2000);
    }
    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latter.getApplication(),"请求成功",Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
