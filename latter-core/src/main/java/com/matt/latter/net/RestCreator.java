package com.matt.latter.net;

import com.matt.latter.app.ConfigType;
import com.matt.latter.app.Latter;
import com.matt.latter.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/7/22.
 */

public class RestCreator {
    //使用惰性加载
    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    /**
     * 获取Service实例
     * @return
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * 获取RxService实例
     * @return
     */
    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }
    /**
     * 构建全局Retrofit客户端
     */
    //静态内部类+build(建造者)模式 创建单例
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latter.getConfiguration().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //接收拦截器
        private static final ArrayList<Interceptor> INTERCEPTORS =  Latter.getConfigValues(ConfigType.INTERCEPTOR);
       private static  OkHttpClient.Builder addInterceptor(){
           if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
               for (Interceptor interceptor: INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
               }
           }
           return BUILDER;
       }
        private static final OkHttpClient OK_HTTP_CLIENT =addInterceptor()
                //拦截器
                //.addInterceptor()//响应被调用
                //.addNetworkInterceptor()//请求和响应都会被调用
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


}
