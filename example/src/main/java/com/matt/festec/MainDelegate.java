package com.matt.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.matt.latter.delegates.LatterDelegate;
import com.matt.latter.net.RestClient;
import com.matt.latter.net.RestCreator;
import com.matt.latter.net.callback.IError;
import com.matt.latter.net.callback.IFailure;
import com.matt.latter.net.callback.ISuccess;
import com.matt.latter.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MainDelegate extends LatterDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //testRestClient();//测试Retrofit网络请求
        //onCallRxGet();//测试Retrofit+RxJava网络请求
        //onCallRxrestClient();//测试Retrofit+RxJava网络请求2
    }

    private void testRestClient() {
        RestClient.builder()
                //.url("news.baidu.com/")
                .url("http://127.0.0.1/index")//index为拦截器捕获的地址
                //.params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
    //TODO:测试方法一
    private  void onCallRxGet(){
        final String url = "http://news.baidu.com/";
        final WeakHashMap<String ,Object> params = new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url,params);
                //默认开启IO线程处理网络请求
                observable.subscribeOn(Schedulers.io())
                //结果在主线程进行处理
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //TODO:测试方法一
    private void onCallRxrestClient(){
        final String url = "http://news.baidu.com/";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                //结果在主线程进行处理
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
