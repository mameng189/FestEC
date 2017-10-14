package com.matt.latter.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/8/5.
 * 拦截器
 */

public abstract class BaseInterceptor implements Interceptor {

    //将参数封装成map
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        //请求参数的个数
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    //获参数的值
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }
    //form表单提交获取参数
    protected LinkedHashMap<String, String> getBodyParamters(Chain chain) {
        final FormBody formbody = (FormBody) chain.request().body();
        int size = formbody.size();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(formbody.name(i), formbody.value(i));
        }
        return params;
    }
    protected String getBodyParameters(Chain chain, String key){
        return getBodyParamters(chain).get(key);
    }
}
