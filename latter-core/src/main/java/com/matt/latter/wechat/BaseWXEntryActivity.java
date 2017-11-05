package com.matt.latter.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.matt.latter.net.RestClient;
import com.matt.latter.net.callback.ISuccess;
import com.matt.latter.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by mamen on 2017/10/29.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity{
    //用户登录成功后回掉
    protected abstract void onSignInSuccess(String userinfo);
    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final  String code =   ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatterWeChat.APP_ID)
                .append("&secret=")
                .append(LatterWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        LatteLogger.d("authUrl",authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl){
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final  String openid = authObj.getString("openid");
                        final  StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                        .append(accessToken)
                                .append("&openid=")
                                .append(openid)
                                .append("&lang=")
                                .append("zh_CN");
                        LatteLogger.d("userInfoUrl",userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }
    private void getUserInfo(String userInfoUrl){

    }
}
