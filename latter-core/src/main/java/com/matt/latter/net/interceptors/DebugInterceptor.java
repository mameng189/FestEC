package com.matt.latter.net.interceptors;

import android.support.annotation.RawRes;

import com.matt.latter.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/8/5.
 */

public class DebugInterceptor extends BaseInterceptor{
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String url, int rawid) {
        this.DEBUG_URL = url;
        this.DEBUG_RAW_ID = rawid;
    }
    //获取响应信息
    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }
    private Response debugResponse(Chain chain, @RawRes int rawid){
        final String json = FileUtil.getRawFile(rawid);
        return  getResponse(chain,json);
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取拦截的Url
        final String url = chain.request().url().toString();
        //拦截的URl包含拦截的关键字
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
