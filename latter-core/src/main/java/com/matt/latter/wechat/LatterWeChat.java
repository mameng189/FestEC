package com.matt.latter.wechat;

import android.app.Activity;

import com.matt.latter.app.ConfigType;
import com.matt.latter.app.Configurator;
import com.matt.latter.app.Latter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by mamen on 2017/10/28.
 */

public class LatterWeChat {
    public static final String APP_ID = Latter.getConfigValues(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latter.getConfigValues(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;

    private static final class Holder {
        private static final LatterWeChat INSTANCE = new LatterWeChat();
    }

    public static LatterWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private LatterWeChat() {
        final Activity activity = Latter.getConfigValues(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
