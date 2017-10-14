package com.matt.latter.net.download;

import android.os.AsyncTask;

import com.matt.latter.net.RestCreator;
import com.matt.latter.net.callback.IError;
import com.matt.latter.net.callback.IFailure;
import com.matt.latter.net.callback.IRequest;
import com.matt.latter.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/29.
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;


    public DownloadHandler(String url,
                           IRequest request,
                           String download_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handlerDownload(){
            if (REQUEST!=null){
                REQUEST.onRequestStart();
            }
            RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //是否返回200
                    if (response.isSuccessful()){
                           final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                           final ResponseBody body = response.body();
                           //线程池方式执行
                           task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,body,NAME);
                           //判断文件是否下载全了
                           if(task.isCancelled()){
                               if (REQUEST!=null){
                                   REQUEST.onRequestEnd();
                               }
                           }
                       }else{
                           if (ERROR!=null){
                               ERROR.onError(response.code(),response.message());
                           }
                       }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (FAILURE!=null){
                        FAILURE.onFailure();
                    }
                }
            });
    }
}
