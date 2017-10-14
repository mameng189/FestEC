package com.matt.latter.net.callback;

import android.os.Handler;

import com.matt.latter.ui.loader.LaterLoader;
import com.matt.latter.ui.loader.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/22.
 */

public class RequestCallbacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();
    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle loaderstyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderstyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
            //响应成功
            if (response.isSuccessful()){
                //call被执行
                if (call.isExecuted()){
                    if (SUCCESS!=null){
                        SUCCESS.onSuccess(response.body());
                    }
                }
            }else{
                if (ERROR!=null){
                    ERROR.onError(response.code(),response.message());
                }
            }

            stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
            if (FAILURE!=null){
                FAILURE.onFailure();
            }
            if (REQUEST!=null){
                REQUEST.onRequestEnd();
            }
            stopLoading();
    }
    private void  stopLoading(){
        if (LOADER_STYLE!=null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LaterLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
